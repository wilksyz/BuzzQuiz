package com.company.antoine.buzzquiz.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.company.antoine.buzzquiz.Model.Score;
import com.company.antoine.buzzquiz.Model.User;
import com.company.antoine.buzzquiz.R;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mScoreButton;
    private User mUser;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private SharedPreferences mPreference;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
    private Score mScore;

    protected void onActivityResult(int pRequestCode, int pResultCode, Intent pData){
        if (GAME_ACTIVITY_REQUEST_CODE == pRequestCode && RESULT_OK == pResultCode){
            //Fetch the score from the intent
            int pScore = pData.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreference.edit().putInt(PREF_KEY_SCORE, pScore).apply();
            playerControl();
            mScore = new Score(mUser.getFirstName(), pScore);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mScoreButton = (Button) findViewById(R.id.activity_main_Score_btn);
        mUser = new User();
        mPreference = getPreferences(MODE_PRIVATE);
        mPlayButton.setEnabled(false);
        playerControl();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() >= 4);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }
        );

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = mNameInput.getText().toString();
                mUser.setFirstName(firstName);
                mPreference.edit().putString(PREF_KEY_FIRSTNAME,mUser.getFirstName()).apply();
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent,GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        mScoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ScoreActivityIntent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(ScoreActivityIntent);
            }
        });
    }

    private void playerControl(){
        String pFirstName = getPreferences(MODE_PRIVATE).getString(PREF_KEY_FIRSTNAME, null);
        int pScorePlayer = getPreferences(MODE_PRIVATE).getInt(PREF_KEY_SCORE, 0);
        if (pFirstName != null){
            mGreetingText.setText("Welcome " +pFirstName+ " your best score is " +pScorePlayer);
            mNameInput.setText(pFirstName);
            mNameInput.setSelection(pFirstName.length());
            mPlayButton.setEnabled(true);
        }
    }
}
