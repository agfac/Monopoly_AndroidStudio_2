package com.example.fabiola.monopoly;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    public static TcpClient tcpClient;

    public static Integer id;

    public static String playerProperties;

    public static String playerBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ConnectTask(this.getApplicationContext()).execute("");

        /*if (savedInstanceState!=null && savedInstanceState.getBoolean(KEY_FIRSTRUN,true)) {
            new ConnectTask(this.getApplicationContext()).execute("");
        }*/

        // Set up click listeners for all the buttons
        View playNowButton = findViewById(R.id.playNow_button);
        playNowButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                tcpClient.sendMessage("Could play?");
            }
        });

        View helpButton = findViewById(R.id.help_button);
        helpButton.setOnClickListener(this);

        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
        //---------------------------------------------

    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            /*case R.id.playNow_button:
                tcpClient.sendMessage("Could I set game properties?");
                break;*/
            case R.id.help_button:
                i = new Intent(this, HelpActivity.class);
                tcpClient.sendMessage("help");
                startActivity(i);
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        /*outState.putBoolean(KEY_FIRSTRUN, false);
        super.onSaveInstanceState(outState);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter(MY_LOCAL_BROADCAST));*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*String responseData = intent.getStringExtra(KEY_RESPONSE);
            Toast.makeText(MainActivity.this, responseData, Toast.LENGTH_LONG).show();*/
        }
    };

    public class ConnectTask extends AsyncTask<String, String, TcpClient> {

        private Context context;

        public ConnectTask(Context context) {
            this.context = context;
        }

        @Override
        protected TcpClient doInBackground(String... message) {

            //we create a TCPClient object and
            tcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {

                    //this method calls the onProgressUpdate
                    publishProgress(message);

                }
            });
            tcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            String message=values[0];

            if(message.matches("Properties:(.*)")){
                String[] parts = message.split(":");
                playerProperties = parts[1];

                Intent i = new Intent(getApplicationContext(), ManageListActivity.class);
                finish();
                startActivity(i);
                ManageListActivity.properties=playerProperties;

            };

            if(message.matches("(.*);Next Player")){

                String[] parts = message.split(";");
                String image = parts[0];

                if (PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage(image);

                if (!(PlayNowActivity.active || ManageListActivity.active || ShowPropertyActivity.active || WaitActivity.active)) {
                    Intent i = new Intent(getApplicationContext(), WaitActivity.class);
                    PlayingActivity.fa.finish();
                    startActivity(i);
                }
            }

            if(message.matches("Your balance is;(.*)")){
                String[] parts = message.split(";");
                playerBalance = parts[1];

                PlayNowActivity.playerBalance.setText(playerBalance+" M");

                if(ManageListActivity.active)
                ManageListActivity.playerBalance.setText(playerBalance+" M");

            };

            switch (message){
                case "Yes, you could set game properties":
                    Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case "Another player defining the game properties. Please wait.":
                    Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "Game properties set correctly!":
                    i = new Intent(getApplicationContext(), PlayerPropertiesActivity.class);
                    startActivity(i);
                    finish();
                    //Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "Yes, you could set player properties":
                    i = new Intent(getApplicationContext(), PlayerPropertiesActivity.class);
                    startActivity(i);
                    finish();
                    //Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "That piece symbol already been choose! Please choose another piece.":
                    Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "Your ID is 1":
                    id = 1;
                    i = new Intent(getApplicationContext(), WaitActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case "Your ID is 2":
                    id = 2;
                    i = new Intent(getApplicationContext(), WaitActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case "Your ID is 3":
                    id = 3;
                    i = new Intent(getApplicationContext(), WaitActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case "Your ID is 4":
                    id = 4;
                    i = new Intent(getApplicationContext(), WaitActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case "1;It is your turn":
                    if(id.equals(1)) {
                        if (PlayNowActivity.active || PlayingActivity.active || ManageListActivity.active || ShowPropertyActivity.active || BuyingActivity.active) {
                            break;
                        } else {
                            i = new Intent(getApplicationContext(), PlayNowActivity.class);
                            WaitActivity.fw.finish();
                            startActivity(i);
                            break;
                        }
                    }
                case "2;It is your turn":
                    if(id.equals(2)) {
                        if (PlayNowActivity.active || PlayingActivity.active || ManageListActivity.active || ShowPropertyActivity.active || BuyingActivity.active) {
                            break;
                        } else {
                            i = new Intent(getApplicationContext(), PlayNowActivity.class);
                            WaitActivity.fw.finish();
                            startActivity(i);
                            break;
                        }
                    }
                case "3;It is your turn":
                    if(id.equals(3)) {
                        if (PlayNowActivity.active || PlayingActivity.active || ManageListActivity.active || ShowPropertyActivity.active || BuyingActivity.active) {
                            break;
                        } else {
                            i = new Intent(getApplicationContext(), PlayNowActivity.class);
                            WaitActivity.fw.finish();
                            startActivity(i);
                            break;
                        }
                    }
                case "4;It is your turn":
                    if(id.equals(4)) {
                        if (PlayNowActivity.active || PlayingActivity.active || ManageListActivity.active || ShowPropertyActivity.active || BuyingActivity.active) {
                            break;
                        } else {
                            i = new Intent(getApplicationContext(), PlayNowActivity.class);
                            WaitActivity.fw.finish();
                            startActivity(i);
                            break;
                        }
                    }
                case "It is not your turn":
                        if (PlayNowActivity.active || WaitActivity.active) {
                            break;
                        } else {
                            i = new Intent(getApplicationContext(), WaitActivity.class);
                            PlayingActivity.fa.finish();
                            startActivity(i);
                            break;
                        }
                case "0":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("0");
                    break;
                case "1":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("1");
                    break;
                case "2":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("2");
                    break;
                case "3":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("3");
                    break;
                case "4":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("4");
                    break;
                case "5":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("5");
                    break;
                case "6":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("6");
                    break;
                case "7":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("7");
                    break;
                case "8":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("8");
                    break;
                case "9":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("9");
                    break;
                case "10":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("10");
                    break;
                case "11":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("11");
                    break;
                case "12":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("12");
                    break;
                case "13":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("13");
                    break;
                case "14":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("14");
                    break;
                case "15":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("15");
                    break;
                case "16":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("16");
                    break;
                case "17":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("17");
                    break;
                case "18":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("18");
                    break;
                case "19":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("19");
                    break;
                case "20":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("20");
                    break;
                case "21":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("21");
                    break;
                case "22":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("22");
                    break;
                case "23":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("23");
                    break;
                case "24":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("24");
                    break;
                case "25":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("25");
                    break;
                case "26":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("26");
                    break;
                case "27":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("27");
                    break;
                case "28":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("28");
                    break;
                case "29":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("29");
                    break;
                case "30":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("30");
                    break;
                case "31":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("31");
                    break;
                case "32":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("32");
                    break;
                case "33":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("33");
                    break;
                case "34":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("34");
                    break;
                case "35":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("35");
                    break;
                case "36":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("36");
                    break;
                case "37":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("37");
                    break;
                case "38":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("38");
                    break;
                case "39":
                    if(PlayingActivity.active || BuyingActivity.active)
                    PlayingActivity.setImage("39");
                    break;
                case "1;Do you want to buy this property?":
                    if(id.equals(1)) {
                        if (PlayNowActivity.active || ManageListActivity.active || ShowPropertyActivity.active || BuyingActivity.active || WaitActivity.active) {
                            break;
                        } else if (PlayingActivity.active) {
                            i = new Intent(getApplicationContext(), BuyingActivity.class);
                            PlayingActivity.fa.finish();
                            startActivity(i);
                            break;
                        }
                    }
                case "2;Do you want to buy this property?":
                    if(id.equals(2)) {
                        if (PlayNowActivity.active || ManageListActivity.active || ShowPropertyActivity.active || BuyingActivity.active || WaitActivity.active) {
                            break;
                        } else if (PlayingActivity.active) {
                            i = new Intent(getApplicationContext(), BuyingActivity.class);
                            PlayingActivity.fa.finish();
                            startActivity(i);
                            break;
                        }
                    }
                case "3;Do you want to buy this property?":
                    if(id.equals(3)) {
                        if (PlayNowActivity.active || ManageListActivity.active || ShowPropertyActivity.active || BuyingActivity.active || WaitActivity.active) {
                            break;
                        } else if (PlayingActivity.active) {
                            i = new Intent(getApplicationContext(), BuyingActivity.class);
                            PlayingActivity.fa.finish();
                            startActivity(i);
                            break;
                        }
                    }
                case "4;Do you want to buy this property?":
                    if(id.equals(4)) {
                        if (PlayNowActivity.active || ManageListActivity.active || ShowPropertyActivity.active || BuyingActivity.active || WaitActivity.active) {
                            break;
                        } else if (PlayingActivity.active) {
                            i = new Intent(getApplicationContext(), BuyingActivity.class);
                            PlayingActivity.fa.finish();
                            startActivity(i);
                            break;
                        }
                    }
                case "Bought":
                    if (PlayNowActivity.active || ManageListActivity.active || ShowPropertyActivity.active || WaitActivity.active){
                        break;
                    }
                    else {
                        i = new Intent(getApplicationContext(), WaitActivity.class);
                        PlayingActivity.fa.finish();
                        startActivity(i);
                        break;
                    }
                case "Not Bought":
                    if (PlayNowActivity.active || ManageListActivity.active || ShowPropertyActivity.active || WaitActivity.active){
                        break;
                    }
                    else {
                        i = new Intent(getApplicationContext(), WaitActivity.class);
                        PlayingActivity.fa.finish();
                        startActivity(i);
                        break;
                    }
                case "Next Player":
                    if (PlayNowActivity.active || ManageListActivity.active || ShowPropertyActivity.active || WaitActivity.active){
                        break;
                    }
                    else {
                        i = new Intent(getApplicationContext(), WaitActivity.class);
                        PlayingActivity.fa.finish();
                        startActivity(i);
                        break;
                    }
                case "You couldn't mortgage this property":
                    Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "You couldn't unmortgage this property":
                    Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "You couldn't build more houses":
                    Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "You couldn't build hotel":
                    Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "You couldn't sell more houses":
                    Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "You couldn't sell hotel":
                    Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
                    break;
                case "You have no properties to manage!":
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    break;
            }

        }

        protected void onPostExecute(String result) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
