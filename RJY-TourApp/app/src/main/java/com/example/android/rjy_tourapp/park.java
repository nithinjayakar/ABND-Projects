package com.example.android.rjy_tourapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class park extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ArrayList<list> list  = new ArrayList<list>();
        list.add(new list(getString(R.string.Kambala),getString(R.string.KohinoorAddress),getString(R.string.KambalaPhone)));
        list.add(new list(getString(R.string.Kohinoor),getString(R.string.KohinoorAddress),getString(R.string.KohinoorPhone)));
        list.add(new list(getString(R.string.Jaya),getString(R.string.JayaAddess),getString(R.string.JayaPhone)));
        listAdapter itemAdapter = new listAdapter(this,list);
        ListView restaurantView = (ListView) findViewById(R.id.activity_restaurants);
        restaurantView.setAdapter(itemAdapter);

    }
}
