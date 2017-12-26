package com.example.android.pokequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class question5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);
    }
    public void submit5 (View view) {
        Intent i = getIntent();
        int score = i.getIntExtra("score4",0);
        RadioButton radioButtonAns5 = (RadioButton) findViewById(R.id.ans5_radioButton);
        boolean ans1 = radioButtonAns5.isChecked();
        if (ans1) {
            score++;
            Intent i5 = new Intent(this, question6.class);
            i5.putExtra("score5", score);
            startActivity(i5);
        }
        else {
            Intent i5 = new Intent(this, question6.class);
            i5.putExtra("score5", score);
            startActivity(i5);
        }
    }
}
