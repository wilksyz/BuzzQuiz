package com.company.antoine.buzzquiz.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.company.antoine.buzzquiz.Model.Question;
import com.company.antoine.buzzquiz.Model.QuestionBank;
import com.company.antoine.buzzquiz.R;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mQuestionTextView;
    private Button mAnswerButton_1;
    private Button mAnswerButton_2;
    private Button mAnswerButton_3;
    private Button mAnswerButton_4;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mScore;
    private int mNumberOfQuestion;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "BUNDLE_SCORE_STATE";
    public static final String BUNDLE_STATE_QUESTION = "BUNDLE_STATE_QUESTION";
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (savedInstanceState != null){
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestion = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }else{
            mScore = 0;
            mNumberOfQuestion = 4;
        }

        mEnableTouchEvents = true;

        mQuestionTextView = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswerButton_1 = (Button) findViewById(R.id.activity_game_btn_1);
        mAnswerButton_2 = (Button) findViewById(R.id.activity_game_btn_2);
        mAnswerButton_3 = (Button) findViewById(R.id.activity_game_btn_3);
        mAnswerButton_4 = (Button) findViewById(R.id.activity_game_btn_4);

        mQuestionBank = this.generateQuestions();
        mCurrentQuestion = mQuestionBank.getQuestionBank();
        this.displayQuestion(mCurrentQuestion);

        mAnswerButton_1.setTag(0);
        mAnswerButton_2.setTag(1);
        mAnswerButton_3.setTag(2);
        mAnswerButton_4.setTag(3);

        mAnswerButton_1.setOnClickListener(this);
        mAnswerButton_2.setOnClickListener(this);
        mAnswerButton_3.setOnClickListener(this);
        mAnswerButton_4.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestion);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        mNumberOfQuestion--;
        int pResponseIndex = (int) v.getTag();
        if (pResponseIndex == mCurrentQuestion.getAnswerIndex()){
            //Bonne réponse
            Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            //Mauvaise réponse
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
        }
        mEnableTouchEvents = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //temps d'attente avant la prochaine question
                mEnableTouchEvents = true;
                if(mNumberOfQuestion == 0){
                    //Fin du jeu
                    endGame();
                }else{
                    //Renvoi une autre question
                    mCurrentQuestion = mQuestionBank.getQuestionBank();
                    displayQuestion(mCurrentQuestion);
                }
            }
        },2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setTitle("Well done")
        .setMessage("Your score is "+ mScore)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //renvoi le score au mainActivity avant de fermer l'activité
                Intent pIntent = new Intent();
                pIntent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                setResult(RESULT_OK, pIntent);
                finish();
            }
        })
            .create()
            .show();
    }

    private void displayQuestion(final Question pQuestion){
        mQuestionTextView.setText(pQuestion.getQuestion());
        mAnswerButton_1.setText(pQuestion.getChoiceList().get(0));
        mAnswerButton_2.setText(pQuestion.getChoiceList().get(1));
        mAnswerButton_3.setText(pQuestion.getChoiceList().get(2));
        mAnswerButton_4.setText(pQuestion.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        Question question10 = new Question("Which actor does play Sonny Crockett in Miami Vice 2006?",
                Arrays.asList("Vin Diesel, Colin Farell, Angelina Jolie, Bruce Willis"),
                1);
        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10));
    }
}
