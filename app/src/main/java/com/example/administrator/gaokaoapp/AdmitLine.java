package com.example.administrator.gaokaoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;

public class AdmitLine extends AppCompatActivity implements View.OnClickListener{

    private static double[] averageA = {0,0,0,0};
    private static double[] averageL = {0,0,0,0};
    private String[] universities = null;
    private String[] universitiesScore = null;
    private HashMap lineMap = new HashMap<String,Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admit_line );
    }

    //LD=line Difference线差, AD=Average
    private static String percentCal(double mark, double[] line, double[] average, double[] lowest){
        for(int i = 0; i< Array.getLength(line); i++){
            averageA[i] = average[i] - line[i];
            averageL[i] = lowest[i] - line[i];
            System.out.println(averageA[i] + " -- " + averageL[i]);
        }
        //double averageAA = Arrays.stream(averageA).average().orElse(0);
        //double averageLA = Arrays.stream(averageL).average().orElse(0);
        double averageLA = 0;
        double averageAA = 0;
        double percent = (mark-averageLA)/(averageAA-averageLA)*0.5+0.5;
        // percent = 0.5;
        System.out.println(averageAA + " -- " + averageLA);
        System.out.println(percent);
        NumberFormat nf = NumberFormat.getPercentInstance();
        // nf.setMinimumFractionDigits(1);
        System.out.println(nf.format(percent));
        return nf.format(percent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_mask:{

            }
        }
    }
}
