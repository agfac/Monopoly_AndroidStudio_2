package com.example.fabiola.monopoly;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class WaitActivity extends Activity {

    Timer timer;
    MyTimerTask myTimerTask;

    public static Activity fw;

    public static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait2);

        fw = this;

        if(PlayingActivity.active)
            PlayingActivity.fa.finish();

        if(PlayNowActivity.active)
            PlayNowActivity.fpn.finish();

        if(BuyingActivity.active)
            BuyingActivity.fb.finish();

    }

    public void onResume() {
        super.onResume();

        Toast.makeText(getBaseContext(), "Please wait your turn", Toast.LENGTH_LONG).show();

        if(PlayingActivity.active)
            PlayingActivity.fa.finish();

        if(PlayNowActivity.active)
            PlayNowActivity.fpn.finish();

        if(BuyingActivity.active)
            BuyingActivity.fb.finish();

        if(timer != null){
            timer.cancel();
        }

        timer = new Timer();
        myTimerTask = new MyTimerTask();


        timer.schedule(myTimerTask, 0, 500);
        }

    @Override
    public void finish() {
        super.finish();
        active = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;

        if(PlayingActivity.active)
            PlayingActivity.fa.finish();

        if(PlayNowActivity.active)
            PlayNowActivity.fpn.finish();

        if(BuyingActivity.active)
            BuyingActivity.fb.finish();

    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        active = false;
    }

class MyTimerTask extends TimerTask {

    @Override
    public void run() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(active)
                    //Toast.makeText(getContext(), "Please wait your turn", Toast.LENGTH_LONG).show();
                MainActivity.tcpClient.sendMessage(Integer.toString(MainActivity.id) + ";Is it my turn?");

            }
        });
    }
}

}
