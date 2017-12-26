package com.example.android.rjy_tourapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class famous extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ArrayList<list> list  = new ArrayList<list>();
        list.add(new list(getString(R.string.RiverBay),getString(R.string.RiverBayAddress),getString(R.string.RiverBayPhone),R.drawable.riverbay));
        list.add(new list(getString(R.string.PushkarGhat),getString(R.string.PushkarGhatAddress),getString(R.string.PushkarGhatPhone),R.drawable.pushkar));
        list.add(new list(getString(R.string.CottonMuseum),getString(R.string.CottonMuseumAddress),getString(R.string.CottonMuseumPhone),R.drawable.cottonmuseum));

        listAdapter itemAdapter = new listAdapter(this,list);
        ListView restaurantView = (ListView) findViewById(R.id.activity_restaurants);
        restaurantView.setAdapter(itemAdapter);
    }
}
