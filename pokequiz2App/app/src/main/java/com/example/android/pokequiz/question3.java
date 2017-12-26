package com.example.android.pokequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class question3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
    }
    public void submit3 (View view) {
        Intent i = getIntent();
        int score = i.getIntExtra("score2",0);
        RadioButton radioButtonAns3 = (RadioButton) findViewById(R.id.ans3_radioButton);
        boolean ans1 = radioButtonAns3.isChecked();
        if (ans1) {
            score++;
            Intent i3 = new Intent(this, question4.class);
            i3.putExtra("score3", score);
            startActivity(i3);
        }
        else
        {
            Intent i3 = new Intent(this, question4.class);
            i3.putExtra("score3", score);
            startActivity(i3);
        }
    }
}
