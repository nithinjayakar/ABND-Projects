package com.example.android.pokequiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.key;
import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class question1 extends AppCompatActivity {
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
    }
    public void submit1(View view) {
        CheckBox dragonCheckBox = (CheckBox) findViewById(R.id.dragon_checkBox);
        CheckBox groundCheckBox = (CheckBox) findViewById(R.id.ground_checkBox);
        CheckBox grassCheckBox = (CheckBox) findViewById(R.id.grass_checkBox);
        CheckBox flyingCheckBox = (CheckBox)findViewById(R.id.flying_checkBox);
        boolean ans1 = dragonCheckBox.isChecked();
        boolean ans2 = groundCheckBox.isChecked();
        boolean ans3 = flyingCheckBox.isChecked();
        boolean ans4 = grassCheckBox.isChecked();
        if (ans1 && ans2&&!ans3&&!ans4) {
            score++;
            Intent i1 = new Intent(this, question2.class);
            i1.putExtra("score1", score);
            startActivity(i1);
        }
        else {
            Intent i1 = new Intent(this, question2.class);
            i1.putExtra("score1", score);
            startActivity(i1);
        }
    }
}