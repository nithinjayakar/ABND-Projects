package com.example.android.pokequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class question2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
    }
    public void submit2(View view) {
        Intent i = getIntent();
        int score = i.getIntExtra("score1",0);
        RadioButton radioButtonAns2 = (RadioButton) findViewById(R.id.ans2_radioButton);
        boolean ans1 = radioButtonAns2.isChecked();
        if (ans1) {
            score++;
            Intent i2 = new Intent(this, question3.class);
            i2.putExtra("score2", score);
            startActivity(i2);
        }
        else {
            Intent i2 = new Intent(this, question3.class);
            i2.putExtra("score2", score);
            startActivity(i2);
        }
    }
}
