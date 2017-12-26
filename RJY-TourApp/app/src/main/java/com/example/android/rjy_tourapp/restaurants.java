package com.example.android.rjy_tourapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class restaurants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ArrayList<list>list  = new ArrayList<list>();
        list.add(new list(getString(R.string.sri),getString(R.string.sriAddress),getString(R.string.sriPhone)));
        list.add(new list(getString(R.string.Green),getString(R.string.GreenAddres),getString(R.string.GreenPhone)));
        list.add(new list(getString(R.string.J_K),getString(R.string.J_KAddress),getString(R.string.J_KPhone)));
        list.add(new list(getString(R.string.Sai),getString(R.string.SaiAddress),getString(R.string.SaiPhone)));
        listAdapter itemAdapter = new listAdapter(this,list);
        ListView restaurantView = (ListView) findViewById(R.id.activity_restaurants);
        restaurantView.setAdapter(itemAdapter);
    }
}