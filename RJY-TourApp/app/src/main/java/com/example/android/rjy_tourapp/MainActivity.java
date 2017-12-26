package com.example.android.rjy_tourapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button restaurant = (Button)findViewById(R.id.restaurants_ButtonView);
        restaurant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,restaurants.class);
                startActivity(i);
            }

        });
        Button park = (Button)findViewById(R.id.park_ButtonView);
        park.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,park.class);
                startActivity(i);
            }
        });
       Button temple = (Button)findViewById(R.id.temple_ButtonView);
        temple.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,temple.class);
                startActivity(i);
            }
        });
        Button famous = (Button)findViewById(R.id.rjyFamous_ButtonView);
        famous.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,famous.class);
                startActivity(i);
            }
        });
    }
}
