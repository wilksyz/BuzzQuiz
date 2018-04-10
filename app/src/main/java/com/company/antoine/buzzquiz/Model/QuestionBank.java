package com.company.antoine.buzzquiz.Model;



import java.util.Collections;
import java.util.List;

/**
 * Created by Antoine on 03/04/2018.
 */

public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List <Question> pQuestionList){
        mQuestionList = pQuestionList;
        Collections.shuffle(mQuestionList);
        mNextQuestionIndex = 0;
    }

    public Question getQuestionBank() {
        if (mNextQuestionIndex == mQuestionList.size()) {
            mNextQuestionIndex = 0;
        }
            return mQuestionList.get(mNextQuestionIndex++);
    }

}
