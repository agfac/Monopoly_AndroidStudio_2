package com.example.fabiola.monopoly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

import static android.R.layout.simple_list_item_1;

/**
 * Created by Fabiola on 29/05/2016.
 */
public class ManageListActivity extends Activity {

    public static boolean active = false;

    private static Vector<String> myStringArray = new Vector<String>();

    public static String properties;

    private static String property;

    public static String imageSelected;

    private static String name;

    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.manage_list);

       properties = MainActivity.playerProperties;

       name = PlayerPropertiesActivity.namePlayer;

       TextView playerName = (TextView) findViewById(R.id.playerName_textView);
       playerName.setText(name);

        TextView playerBalance = (TextView) findViewById(R.id.money_textView);
        playerBalance.setText(SettingsActivity.initialBalance);

       int count = properties.length() - properties.replace(";", "").length();

       properties = properties.replace(".","");

       if(count!=0){
           String property="";
           String[] pp = properties.split(";");
           for(int i=0; i<=count;i++) {
               property = pp[i];
               myStringArray.add(property);
           }
       }
       else
           myStringArray.add(properties);


        properties="";
       /*for(int i =0; i < properties.length();i++){
           String c=properties.substring(i,i);
           if(!c.equals(".")|| !c.equals(";")){
               property+=c;
           }
           else{
               myStringArray.add(property);
               property="";
           }

       }*/


       final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, simple_list_item_1, myStringArray);

       final ListView listView = (ListView) findViewById(R.id.properties_listView);
       listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


               imageSelected = adapter.getItem(position);


               Intent i = new Intent(getApplicationContext(), ShowPropertyActivity.class);
               //i.putExtra("property_name", theText);
               startActivity(i);

               /*
               //para saber qual é a propriedade nome
               EditText et = (EditText) findViewById(parent.getId());
               String theText = et.getText().toString();

               Intent i = new Intent(getApplicationContext(), ShowPropertyActivity.class);
               i.putExtra("property_name", theText);
               startActivity(i);


               System.out.println("value : " + theText);
               System.out.println("PropertyPosition : " + position);
               System.out.println("Propertyid : " + id);
               System.out.println("PropertyParent : " + parent);
               System.out.println("-------------------------------------------");

               */
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

    public void onResume(){
        super.onResume();

        active = true;



    }

}
