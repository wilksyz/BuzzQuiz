package com.company.antoine.buzzquiz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.company.antoine.buzzquiz.R;

public class ScoreActivity extends AppCompatActivity {

     private TextView mScoreView1;
     private TextView mScoreView2;
     private TextView mScoreView3;
     private TextView mScoreView4;
     private TextView mScoreView5;
     private Button mNameButton;
     private Button mScoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mScoreView1 = findViewById(R.id.activity_score_text_1);
        mScoreView2 = findViewById(R.id.activity_score_text_2);
        mScoreView3 = findViewById(R.id.activity_score_text_3);
        mScoreView4 = findViewById(R.id.activity_score_text_4);
        mScoreView5 = findViewById(R.id.activity_score_text_5);
        mNameButton = findViewById(R.id.activity_score_btn_1);
        mScoreButton = findViewById(R.id.activity_score_btn_2);



    }
}
