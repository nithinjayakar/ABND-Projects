package com.example.android.pokequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class question7 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question7);
    }
    public void submit7(View view) {
        Intent i = getIntent();
        int score = i.getIntExtra("score6",0);
        CheckBox ninjaskCheckBox = (CheckBox) findViewById(R.id.ninjask_checkBox);
        CheckBox shedinjaCheckBox = (CheckBox) findViewById(R.id.shedinja_checkBox);
        CheckBox smeargleCheckBox = (CheckBox) findViewById(R.id.smeargle_checkBox);
        CheckBox stanlerCheckBox = (CheckBox) findViewById(R.id.stanler_checkBox);
        boolean ans1 = ninjaskCheckBox.isChecked();
        boolean ans2 = shedinjaCheckBox.isChecked();
        boolean ans3 = smeargleCheckBox.isChecked();
        boolean ans4 = stanlerCheckBox.isChecked();
        if(ans1 && ans2&&!ans3&&!ans4)
        {
            score++;
            Intent i7 = new Intent(this, finalscore.class);
            i7.putExtra("score7", score);
            startActivity(i7);
        }
        else {
            Intent i7 = new Intent(this, finalscore.class);
            i7.putExtra("score7", score);
            startActivity(i7);
        }
    }
}
