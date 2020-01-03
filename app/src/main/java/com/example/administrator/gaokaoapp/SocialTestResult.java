package com.example.administrator.gaokaoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SocialTestResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_test_result);
        char[] a = new char[20];
        jifen(a);
    }

    private void jifen(char[] a) {
        int score = 0;
        int[][] b = new int[][] {{2,1,3},{2,3,1},{3,2,1,},{2,1,3},{1,3,2},
                {1,2,3},{1,2,3},{3,1,2},{3,1,3},{1,2,3},
                {1,2,3},{1,3,2},{3,2,1},{3,1,2},{1,2,3},
                {3,1,2},{3,1,2},{3,1,2},{2,3,1},{3,2,1}};
        for(int i =0;i<20;i++) {
            int temp = a[i]=='a'?0:(a[i]=='b'?1:2);
            score = score + b[i][temp];
        }
        if(score<60&&score>49) {
            System.out.println("1");
        }
        else if(score>37) {
            System.out.println("2");
        }
        else if(score>25) {
            System.out.println("3");
        }
        else {
            System.out.println("4");
        }

    }
}
