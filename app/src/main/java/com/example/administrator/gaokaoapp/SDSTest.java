package com.example.administrator.gaokaoapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SDSTest extends AppCompatActivity {

    private TextView textView1;
    private Button BTN_a;
    private Button BTN_b;
    private Button btn_shang;
    private  Button btn_next;
    private ImageButton btn_back;
    private TextView curCountShow = null;
    private LinearLayout progressAll = null;
    private ImageView progress = null;
    private ViewGroup.LayoutParams lpParent;
    private ViewGroup.LayoutParams lpChild;
    private int eachProgress = 0;
    public int i=0;
    public char[] answer;
    public void strinng(String[] arr_text1,int a){
        textView1.setText(arr_text1[a].toString());
    }
    public void button_color(Button a,Button b){
        a.setBackground(new ColorDrawable(Color.rgb(127,255,212)));
        b.setBackground(new ColorDrawable(Color.rgb(230,230,250)));
    }
    public void button_color1(Button a,Button b){
        a.setBackground(new ColorDrawable(Color.rgb(230,230,250)));
        b.setBackground(new ColorDrawable(Color.rgb(230,230,250)));
    }

    private Bundle reset = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState  );
        setContentView( R.layout.activity_sdstest );
        Resources res = this.getResources();
        answer = new char[60];
//        [test]: random testing
//        char[] ccc = {'y', 'n'};
//        for(int i=0; i<answer.length; i++){
//            if(Math.random()<0.5){
//                answer[i] = ccc[1];
//            }
//            else{
//                answer[i] = ccc[0];
//            }
//        }
        final String[] arr_text1 = res.getStringArray(R.array.sds_q);
        textView1 = findViewById(R.id.text1);
        BTN_a = findViewById(R.id.text1_a);
        BTN_b = findViewById(R.id.text1_b);
        btn_shang = findViewById(R.id.shang);
        btn_next = findViewById(R.id.next);
        btn_back = findViewById( R.id.fanhui_bt );
        button_color1(BTN_a,BTN_b);
        strinng(arr_text1,i);

        progressAll = findViewById(R.id.progress_all);
        progress = findViewById(R.id.progress);
        lpParent = progressAll.getLayoutParams();
        eachProgress = Math.round(lpParent.width / 60);
        lpChild = progress.getLayoutParams();
        curCountShow = findViewById( R.id.current_count );

        btn_shang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(i==0){
                    Toast.makeText(getApplicationContext(), "已经是第一题了！",Toast.LENGTH_SHORT).show();
                }
                else{
                    i--;
                    strinng(arr_text1,i);
                    if(answer[i]=='y'){
                        button_color(BTN_a,BTN_b);
                    }
                    else if(answer[i]=='n'){
                        button_color(BTN_b,BTN_a);
                    }
                    curCountShow.setText((i+1) + "/60");
                    lpChild.width = eachProgress * i;
                    progress.setLayoutParams( lpChild );
                }

            }
        });
        btn_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (i==59){
                    Intent i = new Intent(SDSTest.this, AllResult.class);
                    i.putExtra("answer-sds", answer);
                    startActivity(i);
                }
                if(i<=59&&(answer[i]!='y'&&answer[i]!='n')){
                    Toast.makeText(getApplicationContext(), "你并没有做出回答！",Toast.LENGTH_SHORT).show();;
                }
                if(i<59 &&(answer[i]=='y'||answer[i]=='n')){
                    i++;
                    strinng(arr_text1,i);
                    if(answer[i]=='y'){
                        button_color(BTN_a,BTN_b);
                    }
                    else if(answer[i]=='n'){
                        button_color(BTN_b,BTN_a);
                    }
                    else{
                        button_color1(BTN_b,BTN_a);
                    }
                    curCountShow.setText((i+1) + "/60");
                    lpChild.width = eachProgress * i;
                    progress.setLayoutParams( lpChild );
                }
                if(i==59){
                    btn_next.setText("提交");

                }

//                else{
//                    i++;
//                    strinng(arr_text1,arr_text1_a,arr_text1_b,arr_text1_c,i);
//                    button_color1(BTN_a,BTN_b,);
//                    if (i==20){
//                        Intent intent=new Intent(SocialTest.this,SocialTestResult.class);
//                        startActivity(intent);
//                    }
//                }


            }
        });

        BTN_a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                answer[i] = 'y';
                button_color(BTN_a,BTN_b);
            }
        });
        BTN_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                answer[i] = 'n';
                button_color(BTN_b,BTN_a);
            }
        });
        btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( SDSTest.this, main.class );
                i.putExtra( "back", "true" );
                startActivity(i);
            }
        } );

    }

}