package com.example.android.pokequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.duration;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class finalscore extends AppCompatActivity {
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalscore);
    }
    public void submit(View view){
        Intent i = getIntent();
        score = i.getIntExtra("score7",0);
        display(score);
    }
    public void tryAgain(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void display (int number){
        Toast.makeText(this,"well done!! your score is" + score,Toast.LENGTH_SHORT).show();
        TextView  soreTextView  = (TextView) findViewById(R.id.score_textView);
        soreTextView.setText(String.valueOf(number));
    }
}
