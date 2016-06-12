package com.example.fabiola.monopoly;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class PlayingActivity extends Activity {

    public static boolean active = false;

    public static String currImage="";

    public static Activity fa;

    Timer timer;
    MyTimerTask myTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        //setCurrentImage();
        //setImageRotateListener();

        fa = this;

        WaitActivity.fw.finish();
        PlayNowActivity.fpn.finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;

        WaitActivity.fw.finish();
        PlayNowActivity.fpn.finish();
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

    public void onResume() {
        super.onResume();

        WaitActivity.fw.finish();
        PlayNowActivity.fpn.finish();

        active = true;

        if(timer != null){
            timer.cancel();
        }

        timer = new Timer();
        myTimerTask = new MyTimerTask();


        timer.schedule(myTimerTask, 0, 500);
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            //Toast.makeText(WaitActivity.this, "Please wait for other players register", Toast.LENGTH_LONG).show();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    setCurrentImage(currImage);

                    MainActivity.tcpClient.sendMessage(Integer.toString(MainActivity.id)+";Which picture may I show?");
                }});
        }
    }
    /*private void setImageRotateListener() {
        final Button rotatebutton = (Button) findViewById(R.id.btnRotateImage);
        rotatebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currImage++;
                if (currImage == 3) {
                    currImage = 0;
                }
                setCurrentImage();
            }
        });
    }*/

    private void setCurrentImage(String image) {

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch (image){
            case "1":
                imageView.setImageResource(R.drawable.mediterranean_avenue);
                break;
            case "2":
                imageView.setImageResource(R.drawable.community_chest);
                break;
            case "3":
                imageView.setImageResource(R.drawable.baltic_avenue);
                break;
            case "4":
                imageView.setImageResource(R.drawable.income_tax);
                break;
            case "5":
                imageView.setImageResource(R.drawable.reading_railroad);
                break;
            case "6":
                imageView.setImageResource(R.drawable.oriental_avenue);
                break;
            case "7":
                imageView.setImageResource(R.drawable.chance2);
                break;
            case "8":
                imageView.setImageResource(R.drawable.vermont_avenue);
                break;
            case "9":
                imageView.setImageResource(R.drawable.connecticut_avenue);
                break;
            case "10":
                imageView.setImageResource(R.drawable.just_visiting2);
                break;
            case "11":
                imageView.setImageResource(R.drawable.st_charles_place);
                break;
            case "12":
                imageView.setImageResource(R.drawable.electric_company);
                break;
            case "13":
                imageView.setImageResource(R.drawable.states_avenue);
                break;
            case "14":
                imageView.setImageResource(R.drawable.virginia_avenue);
                break;
            case "15":
                imageView.setImageResource(R.drawable.pennsylvania_railroad);
                break;
            case "16":
                imageView.setImageResource(R.drawable.st_james_place);
                break;
            case "17":
                imageView.setImageResource(R.drawable.community_chest);
                break;
            case "18":
                imageView.setImageResource(R.drawable.tennesse_avenue);
                break;
            case "19":
                imageView.setImageResource(R.drawable.new_york_avenue);
                break;
            case "20":
                imageView.setImageResource(R.drawable.free_parking);
                break;
            case "21":
                imageView.setImageResource(R.drawable.kentucky_avenue);
                break;
            case "22":
                imageView.setImageResource(R.drawable.chance2);
                break;
            case "23":
                imageView.setImageResource(R.drawable.indiana_avenue);
                break;
            case "24":
                imageView.setImageResource(R.drawable.illinois_avenue);
                break;
            case "25":
                imageView.setImageResource(R.drawable.bo_railroad);
                break;
            case "26":
                imageView.setImageResource(R.drawable.atlantic_avenue);
                break;
            case "27":
                imageView.setImageResource(R.drawable.ventnor_avenue);
                break;
            case "28":
                imageView.setImageResource(R.drawable.water_works);
                break;
            case "29":
                imageView.setImageResource(R.drawable.marvin_gardens);
                break;
            case "30":
                imageView.setImageResource(R.drawable.go_to_jail2);
                break;
            case "31":
                imageView.setImageResource(R.drawable.pacific_avenue);
                break;
            case "32":
                imageView.setImageResource(R.drawable.north_carolina_avenue);
                break;
            case "33":
                imageView.setImageResource(R.drawable.community_chest);
                break;
            case "34":
                imageView.setImageResource(R.drawable.pennsylvania_avenue);
                break;
            case "35":
                imageView.setImageResource(R.drawable.short_line_railroad);
                break;
            case "36":
                imageView.setImageResource(R.drawable.chance2);
                break;
            case "37":
                imageView.setImageResource(R.drawable.park_place);
                break;
            case "38":
                imageView.setImageResource(R.drawable.luxury_tax2);
                break;
            case "39":
                imageView.setImageResource(R.drawable.board_walk);
                break;

        }

    }

    public static void setImage(String image){
        currImage=image;
    }
}
