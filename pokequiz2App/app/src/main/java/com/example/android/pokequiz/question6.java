package com.example.android.pokequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class question6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6);
    }
    public void submit6 (View view) {
        Intent i = getIntent();
        int score = i.getIntExtra("score5", 0);
        score++;
        Intent i6 = new Intent(this, question7.class);
        i6.putExtra("score6", score);
        startActivity(i6);
    }
    public void nonans (View view){
        Intent i = getIntent();
        int score = i.getIntExtra("score5", 0);
        Intent i6 = new Intent(this, question7.class);
        i6.putExtra("score6", score);
        startActivity(i6);
    }
}
