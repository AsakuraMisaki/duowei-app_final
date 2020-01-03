package com.example.administrator.gaokaoapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class temper_type_test extends AppCompatActivity implements View.OnClickListener{

    private String[] questions = null;
    private int curCount = 0;
    private int maxCount = 0;
    private int[] notedChoose = new int[60];
    private String originalColor = "#E6E6FA";
    private String chosenColor = "#7FFFD4";
    private ViewGroup.LayoutParams lpParent;
    private ViewGroup.LayoutParams lpChild;
    private int eachProgress = 0;
    private View[] notedChooseShow;

    private TextView questionDes, curCountShow = null;
    private LinearLayout answerChooser, progressAll = null;
    private ImageView progress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temper_type_test);
        questions = getResources().getStringArray(R.array.temper_q);
        maxCount = questions.length;
        View[] allClickableView = {findViewById(R.id.next_q), findViewById(R.id.last_q), findViewById(R.id.fin_q), findViewById(R.id.not_agree_en), findViewById(R.id.not_agree), findViewById(R.id.not_sure), findViewById(R.id.little_agree), findViewById(R.id.agree)};
        notedChooseShow = allClickableView;
        for(int i=0; i<allClickableView.length; i++){
            allClickableView[i].setOnClickListener(this);
        }
        for(int i=0; i<notedChoose.length; i++){
            //[test]
//            notedChoose[i] = (int)Math.round(  2 - Math.random()*4);
            notedChoose[i] = 10;
        }
        //[test]
        //findViewById( R.id.fin_q ).setVisibility( View.VISIBLE );
        progressAll = findViewById(R.id.progress_all);
        progress = findViewById(R.id.progress);
        lpParent = progressAll.getLayoutParams();
        eachProgress = Math.round(lpParent.width / maxCount);
        lpChild = progress.getLayoutParams();

        questionDes = findViewById(R.id.question_desc);
        curCountShow = findViewById(R.id.current_count);
        answerChooser = findViewById(R.id.answer_chooser);
        findViewById( R.id.fanhui_bt ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( temper_type_test.this, main.class );
                i.putExtra( "back", "true" );
                startActivity(i);
            }
        } );
    }

    public void dynamicMesRefresh(){
        questionDes.setText(questions[curCount]);
        curCountShow.setText(curCount+1 + "/60");
        lpChild.width = eachProgress * curCount;
        progress.setLayoutParams(lpChild);
        if(notedChoose[curCount]!=10){
            notedChooseShow[notedChoose[curCount]+5].setBackgroundColor(Color.parseColor(chosenColor));
        }
    }

    public void colorChange(int value, View v){
        notedChoose[curCount]=value;
        v.setBackgroundColor(Color.parseColor(chosenColor));
    }

    @Override
    public void onClick(View v){

        try{
            for(int i=3; i<notedChooseShow.length; i++){
                notedChooseShow[i].setBackgroundColor(Color.parseColor(originalColor));
            }
            switch (v.getId()){

                case R.id.next_q: {
                    if(curCount<maxCount-1){
                        ((TextView)findViewById(R.id.last_q)).setText("上一题");
                        if(notedChoose[curCount] != 10){
                            if(++curCount == maxCount-1){
                                findViewById(R.id.fin_q).setVisibility(View.VISIBLE);
                            };
                            dynamicMesRefresh();
                        }
                        else{
                            Toast.makeText(temper_type_test.this, "请回答", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case R.id.fin_q: {
                    if(notedChoose[curCount] != 10){
                        dynamicMesRefresh();
                        Intent i = new Intent(temper_type_test.this, AllResult.class);
                        i.putExtra("answer-temper", notedChoose);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(temper_type_test.this, "请回答", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case R.id.last_q: {
                    if(curCount>0){
                        findViewById(R.id.fin_q).setVisibility(View.GONE);
                        if(--curCount == 0){
                            ((Button)v).setText("已是第一题");
                        };
                        dynamicMesRefresh();
                    }
                    else{
                        dynamicMesRefresh();
                    }
                    break;
                }
                case R.id.agree:{
                    colorChange(2, v);
                    break;
                }
                case R.id.little_agree:{
                    colorChange(1, v);
                    break;
                }
                case R.id.not_sure:{
                    colorChange(0, v);
                    break;
                }
                case R.id.not_agree:{
                    colorChange(-1, v);
                    break;
                }
                case R.id.not_agree_en:{
                    colorChange(-2, v);
                    break;
                }
            }
        }
        catch (Exception e){
            String errInfo = "";
            for(int i= 0; i<e.getStackTrace().length; i++){
                errInfo += e.getStackTrace()[i] + " $$ ";
            }
            Toast.makeText(temper_type_test.this, errInfo, Toast.LENGTH_LONG).show();
        }

    }
}
