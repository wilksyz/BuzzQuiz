package com.company.antoine.buzzquiz.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 03/04/2018.
 */

public class Question {
    private String mQuestion;
    private List<String> mChoiceList ;
    private int mAnswerIndex;

    public Question(String pQuestion,List<String> pChoiceList, int pAnswerIndex) {
        mChoiceList = new ArrayList<String>();
        mChoiceList = pChoiceList;
        mQuestion = pQuestion;
        mAnswerIndex = pAnswerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }
}
