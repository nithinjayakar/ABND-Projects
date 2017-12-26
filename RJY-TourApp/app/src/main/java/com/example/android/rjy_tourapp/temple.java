package com.example.android.rjy_tourapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class temple extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ArrayList<list> list  = new ArrayList<list>();
        list.add(new list(getString(R.string.ISKCON),getString(R.string.ISKCONAddress),getString(R.string.ISKCONPhone)));
        list.add(new list(getString(R.string.Swamy),getString(R.string.SwamyAddress),getString(R.string.SwamyPhone)));
        list.add(new list(getString(R.string.Syamalamba),getString(R.string.SyamalambaAddress),getString(R.string.SyamalambaPhone)));
        list.add(new list(getString(R.string.Markandeya),getString(R.string.MarkandeyaAddress),getString(R.string.MarkandeyaPhoe)));
        listAdapter itemAdapter = new listAdapter(this,list);
        ListView restaurantView = (ListView) findViewById(R.id.activity_restaurants);
        restaurantView.setAdapter(itemAdapter);
    }
}
