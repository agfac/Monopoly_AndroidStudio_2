package com.example.fabiola.monopoly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayNowActivity extends Activity implements OnClickListener {

    public static boolean active = false;
    private static String name;
    private static EditText textField;

    public static TextView playerBalance;

    public static Activity fpn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_now);

        MainActivity.tcpClient.sendMessage(Integer.toString(MainActivity.id)+";Which is my balance?");

        fpn = this;

        if(WaitActivity.active)
            WaitActivity.fw.finish();

        name = PlayerPropertiesActivity.namePlayer;

        TextView playerName = (TextView) findViewById(R.id.playerName_textView);
        playerName.setText(name);

        ImageView playerPiece = (ImageView) findViewById(R.id.playNowPiece_imageView);

        switch(PlayerPropertiesActivity.pieceSelected) {
            case "0":
                playerPiece.setImageResource(R.drawable.dog);
                break;
            case "1":
                playerPiece.setImageResource(R.drawable.car);
                break;
            case "2":
                playerPiece.setImageResource(R.drawable.ship);
                break;
            case "3":
                playerPiece.setImageResource(R.drawable.boot);
                break;
            case "4":
                playerPiece.setImageResource(R.drawable.hat);
                break;
            case "5":
                playerPiece.setImageResource(R.drawable.iron);
                break;
            case "6":
                playerPiece.setImageResource(R.drawable.thimble);
                break;
            case "7":
                playerPiece.setImageResource(R.drawable.wheelbarrow);
                break;
        }

        playerBalance = (TextView) findViewById(R.id.money_textView);
        //playerBalance.setText(SettingsActivity.initialBalance);

        // Set up click listeners for all the buttons
        View rollDice = findViewById(R.id.rollDice_button);
        rollDice.setOnClickListener(this);

        View manage = findViewById(R.id.manage_button);
        manage.setOnClickListener(this);

        View trade = findViewById(R.id.trade_button);
        trade.setOnClickListener(this);

        View menu = findViewById(R.id.menu_button);
        menu.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;

        if(WaitActivity.active)
            WaitActivity.fw.finish();
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

    @Override
    public void onResume() {
        super.onResume();
        active = true;

        Toast.makeText(getBaseContext(), "It is your turn", Toast.LENGTH_LONG).show();

        if(WaitActivity.active)
            WaitActivity.fw.finish();
    }

    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()){
            case R.id.rollDice_button:
                MainActivity.tcpClient.sendMessage("Playing begins");
                i = new Intent(this, PlayingActivity.class);
                finish();
                startActivity(i);
                break;
            case R.id.manage_button:
                i = new Intent(this, ManageListActivity.class);
                MainActivity.tcpClient.sendMessage("Manage Activity");
                /*finish();
                startActivity(i);*/
                break;
            case R.id.trade_button:
                // do something
                break;
            case R.id.menu_button:
                finish();
                break;
        }
    }
}
