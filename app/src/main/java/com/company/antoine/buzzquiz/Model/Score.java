package com.company.antoine.buzzquiz.Model;




import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Score {

    private int mScore;
    private String mName;
    private List mListScore = new LinkedList();
    private FileOutputStream outputStream;
    String filename = "myfile";
    String fileContents = "Hello world!";


    public Score(String pName, int pScore) {
        mScore = pScore;
        mName = pName;
        mListScore.add(Arrays.asList(mName, mScore));
    }

    public void saveScoreList(){













    }
}
