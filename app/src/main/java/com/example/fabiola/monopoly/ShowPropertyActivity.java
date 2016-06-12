package com.example.fabiola.monopoly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Fabiola on 29/05/2016.
 */
public class ShowPropertyActivity extends Activity {

    private String propertyName;

    public static boolean active = false;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_card);

        View mortgagebutton = findViewById(R.id.button8);
        mortgagebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MainActivity.tcpClient.sendMessage(ManageListActivity.imageSelected+";Mortgage");

            }

        });

        View unmortgagebutton = findViewById(R.id.button7);
        unmortgagebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MainActivity.tcpClient.sendMessage(ManageListActivity.imageSelected+";Unmortgage");

            }

        });

        View buildhouses = findViewById(R.id.button9);
        buildhouses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MainActivity.tcpClient.sendMessage(ManageListActivity.imageSelected+";BuildHouse");

            }

        });

        View buildhotel = findViewById(R.id.button10);
        buildhotel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MainActivity.tcpClient.sendMessage(ManageListActivity.imageSelected+"BuildHotel");

            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
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

        setCurrentImage(ManageListActivity.imageSelected);

    }

    private void setCurrentImage(String image) {

        final ImageView imageView = (ImageView) findViewById(R.id.imageView4);

            switch (image){
                case "Mediterranean Avenue":
                    imageView.setImageResource(R.drawable.mediterranean_avenue);
                    break;
                case "Baltic Avenue":
                    imageView.setImageResource(R.drawable.baltic_avenue);
                    break;
                case "Reading RailRoad":
                    imageView.setImageResource(R.drawable.reading_railroad);
                    break;
                case "Oriental Avenue":
                    imageView.setImageResource(R.drawable.oriental_avenue);
                    break;
                case "Vermont Avenue":
                    imageView.setImageResource(R.drawable.vermont_avenue);
                    break;
                case "Connecticut Avenue":
                    imageView.setImageResource(R.drawable.connecticut_avenue);
                    break;
                case "St Charles Place":
                    imageView.setImageResource(R.drawable.st_charles_place);
                    break;
                case "Electric Company":
                    imageView.setImageResource(R.drawable.electric_company);
                    break;
                case "States Avenue":
                    imageView.setImageResource(R.drawable.states_avenue);
                    break;
                case "Virginia Avenue":
                    imageView.setImageResource(R.drawable.virginia_avenue);
                    break;
                case "Pennsylvania RailRoad":
                    imageView.setImageResource(R.drawable.pennsylvania_railroad);
                    break;
                case "St James Place":
                    imageView.setImageResource(R.drawable.st_james_place);
                    break;
                case "Tennesse Avenue":
                    imageView.setImageResource(R.drawable.tennesse_avenue);
                    break;
                case "New York Avenue":
                    imageView.setImageResource(R.drawable.new_york_avenue);
                    break;
                case "Kentucky Avenue":
                    imageView.setImageResource(R.drawable.kentucky_avenue);
                    break;
                case "Indiana Avenue":
                    imageView.setImageResource(R.drawable.indiana_avenue);
                    break;
                case "Illinois Avenue":
                    imageView.setImageResource(R.drawable.illinois_avenue);
                    break;
                case "B&O RailRoad":
                    imageView.setImageResource(R.drawable.bo_railroad);
                    break;
                case "Atlantic Avenue":
                    imageView.setImageResource(R.drawable.atlantic_avenue);
                    break;
                case "Ventnor Avenue":
                    imageView.setImageResource(R.drawable.ventnor_avenue);
                    break;
                case "Water Works":
                    imageView.setImageResource(R.drawable.water_works);
                    break;
                case "Marvin Gardens":
                    imageView.setImageResource(R.drawable.marvin_gardens);
                    break;
                case "Pacific Avenue":
                    imageView.setImageResource(R.drawable.pacific_avenue);
                    break;
                case "North Carolina Avenue":
                    imageView.setImageResource(R.drawable.north_carolina_avenue);
                    break;
                case "Pennsylvania Avenue":
                    imageView.setImageResource(R.drawable.pennsylvania_avenue);
                    break;
                case "Short Line RailRoad":
                    imageView.setImageResource(R.drawable.short_line_railroad);
                    break;
                case "Park Place":
                    imageView.setImageResource(R.drawable.park_place);
                    break;
                case "Board Walk":
                    imageView.setImageResource(R.drawable.board_walk);
                    break;

            }

        }
    }
