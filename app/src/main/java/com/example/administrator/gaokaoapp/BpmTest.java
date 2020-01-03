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

public class BpmTest extends AppCompatActivity implements View.OnClickListener{

    private String[] questions = null;
    private int curCount = 0;
    //private int maxCount = 0;
    private final int maxCount = 72;
    private String chosenColor = "#7FFFD4";
    private String originalColor = "#6699ff";
    private ViewGroup.LayoutParams lpParent;
    private ViewGroup.LayoutParams lpChild;
    private int eachProgress = 0;
    private int grade = 0;
    private int[] correctAnswer = {4,5,1,2,6,3,6,6,1,3,4,5,4,5,1,6,2,1,3,4,6,3,5,2,2,6,1,2,1,3,5,6,4,3,4,5,8,2,3,8,7,4,5,1,7,6,1,2,3,4,3,7,8,6,5,4,1,2,5,6,7,6,8,2,1,5,1,6,3,2,4,2};
    private TextView curCountShow = null;
    private LinearLayout progressAll = null;
    private ImageView progress = null;
    private View mainImg = null;
    
    private int[] answer = new int[maxCount];
    private View[] availableButton = null;
    private View[] controlButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bpm_test);
        try{
            init();
            dynamicMesRefresh();
        }
        catch (Exception e){
            String errInfo = "";
            for(int i= 0; i<e.getStackTrace().length; i++){
                errInfo += e.getStackTrace()[i] + " $$ ";
            }
            Toast.makeText(BpmTest.this, errInfo, Toast.LENGTH_LONG).show();
        }

    }

    public void init(){
//        [test]
//        findViewById( R.id.fin_q ).setVisibility( View.VISIBLE );
        progressAll = findViewById(R.id.progress_all);
        progress = findViewById(R.id.progress);
        lpParent = progressAll.getLayoutParams();
        eachProgress = Math.round(lpParent.width / maxCount);
        lpChild = progress.getLayoutParams();

        mainImg = findViewById( R.id.mainImg );
        curCountShow = findViewById(R.id.current_count);
        View[] controlButtonT = {findViewById( R.id.next_q ), findViewById( R.id.last_q ), findViewById( R.id.fin_q ), findViewById( R.id.fanhui_bt )};
        controlButton = controlButtonT;
        for(int i=0; i<controlButton.length; i++){
            controlButton[i].setOnClickListener( this );
        }
        availableButton = new View[8];
        for(int i=0; i<availableButton.length; i++){
            availableButton[i] = findViewById( getResources().getIdentifier( "answer_"+(i+1), "id", getPackageName() ) );
            availableButton[i].setOnClickListener( choosing );
        }
        availableButton[6].setClickable( false );
        availableButton[7].setClickable( false );
        for(int i=0; i<maxCount; i++){
            answer[i] = 10;
        }

    }

    public void dynamicMesRefresh(){
        int drawableID = getResources().getIdentifier( "a" + (curCount+1), "drawable", getPackageName() );
        mainImg.setBackground( getResources().getDrawable( drawableID ) );
        for(int i=0; i<availableButton.length; i++){
            availableButton[i].setBackgroundColor( Color.parseColor( originalColor ));
            if(curCount<=35){
                availableButton[6].setBackgroundColor( Color.parseColor( "#aaaaaa" ) );
                availableButton[7].setBackgroundColor( Color.parseColor( "#aaaaaa" ) );
            }
            else{
                availableButton[6].setBackgroundColor( Color.parseColor( originalColor ) );
                availableButton[7].setBackgroundColor( Color.parseColor( originalColor ) );
                availableButton[6].setClickable( true );
                availableButton[7].setClickable( true );
            }
        }
        if(answer[curCount] !=10){
            availableButton[answer[curCount]].setBackgroundColor( Color.parseColor( chosenColor ) );
        }
        curCountShow.setText(curCount+1 + "/72");
        lpChild.width = eachProgress * curCount;
        progress.setLayoutParams( lpChild );
    }

    @Override
    public void onClick(View v){

        try{
            switch (v.getId()){

                case R.id.next_q: {
                    if(curCount<maxCount-1){
                        if(answer[curCount] != 10){
                            if(++curCount == maxCount-1){
                                findViewById(R.id.fin_q).setVisibility(View.VISIBLE);
                            };
                        }
                        else{
                            Toast.makeText(BpmTest.this, "请回答", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case R.id.fin_q: {
                    if(answer[curCount] != 10){
                        Intent i = new Intent(BpmTest.this, AllResult.class);
                        i.putExtra("answer-bpm", answer);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(BpmTest.this, "请回答", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case R.id.last_q: {
                    if(curCount>0){
                        findViewById(R.id.fin_q).setVisibility(View.GONE);
                        if(--curCount == 0){
                            Toast.makeText(BpmTest.this, "已是第一题", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case R.id.fanhui_bt:{
                    Intent i = new Intent( BpmTest.this, main.class );
                    i.putExtra( "back", "true" );
                    startActivity(i);
                }
            }

            dynamicMesRefresh();
        }
        catch (Exception e){
            String errInfo = "";
            for(int i= 0; i<e.getStackTrace().length; i++){
                errInfo += e.getStackTrace()[i] + " $$ ";
            }
            Toast.makeText(BpmTest.this, errInfo, Toast.LENGTH_LONG).show();
        }

    }

    private View.OnClickListener choosing = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(int i=0; i<availableButton.length; i++){
                if(v.equals( availableButton[i] )){
                    answer[curCount] = i;
                }
            }
            dynamicMesRefresh();
        }
    };
}
