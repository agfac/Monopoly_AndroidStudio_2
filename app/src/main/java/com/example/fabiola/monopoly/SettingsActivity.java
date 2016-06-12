package com.example.fabiola.monopoly;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.TextureView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    private String array_spinner[];
    private String array_spinner2[];
    private String numPlayers;
    public static String initialBalance;
    private Spinner spinner1;
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MainActivity.tcpClient.sendMessage("Set game properties begin");

        array_spinner = new String[3];
        array_spinner[0] = "2 players";
        array_spinner[1] = "3 players";
        array_spinner[2] = "4 players";

        spinner1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        spinner1.setAdapter(adapter);

        array_spinner2 = new String[3];
        array_spinner2[0] = "2000";
        array_spinner2[1] = "3000";
        array_spinner2[2] = "4000";

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner2);
        spinner2.setAdapter(adapter2);

        // Set up click listeners for all the buttons
        View okButton = findViewById(R.id.button);

        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                numPlayers = spinner1.getSelectedItem().toString();

                String[] parts = numPlayers.split(" ");
                String num = parts[0];

                numPlayers = num;

                initialBalance = spinner2.getSelectedItem().toString();

                String message = numPlayers + ";" + initialBalance;

                MainActivity.tcpClient.sendMessage(message);
            }
        });
    }
}
