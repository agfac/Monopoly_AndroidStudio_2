package com.example.fabiola.monopoly;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Fabiola on 28/05/2016.
 */
public class GridViewPiecesActivity extends Activity implements DialogInterface.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_pieces);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
               Toast.makeText(GridViewPiecesActivity.this, "" + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("edittextvalue",String.valueOf(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

}
