package com.example.android.pokequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class question4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);
    }
    public void submit4 (View view){
        Intent i = getIntent();
        int score = i.getIntExtra("score3",0);
        EditText ans4 = (EditText) findViewById(R.id.ans4_editView);
        String ans = ans4.getText().toString().trim();
        if (ans.equalsIgnoreCase("Gorebyss")) {
            score++;
            Intent i4 = new Intent(this, question5.class);
            i4.putExtra("score4", score);
            startActivity(i4);
        }
        else {
            Intent i4 = new Intent(this, question5.class);
            i4.putExtra("score4", score);
            startActivity(i4);
        }
    }
}
