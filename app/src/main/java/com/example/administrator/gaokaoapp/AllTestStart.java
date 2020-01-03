package com.example.administrator.gaokaoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AllTestStart extends AppCompatActivity implements View.OnClickListener{

    TextView p, p1, tip, title = null;
    Button start = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_test_start);
        p = findViewById(R.id.p0);
        p1 = findViewById(R.id.p1);
        tip = findViewById(R.id.question_tip);
        title = findViewById(R.id.question_title);
        start = findViewById(R.id.test_start);
        start.setOnClickListener(this);
        findViewById( R.id.fanhui_bt ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( AllTestStart.this, main.class );
                i.putExtra( "back", "true" );
                startActivity(i);
            }
        });
        textBinding( getIntent() );
    }

    @Override
    public void onClick(View v){
        switch (getIntent().getStringExtra("question-class")){
            case "bpm": {
                startActivity(new Intent(AllTestStart.this, BpmTest.class));
                break;
            }
            case "16pf": {
                startActivity(new Intent(AllTestStart.this, three_fragment_a01.class));
                break;
            }
            case "society-test": {
                startActivity(new Intent(AllTestStart.this, SocialTest.class));
                break;
            }
            case "temper-type": {
                startActivity(new Intent(AllTestStart.this, temper_type_test.class));
                break;
            }
            case "sds": {
                startActivity(new Intent(AllTestStart.this, SDSTest.class));
                break;
            }
            default: break;
        }
    }

    public void textBinding(Intent i ){
        switch (i.getStringExtra("question-class")){
            case "society-test": {
                p.setText(getResources().getText(R.string.society_start));
                p1.setText(getResources().getText(R.string.society_start_1));
                tip.setText(getResources().getText(R.string.society_start_tip));
                title.setText("社会适应能力测试");
                break;
            }
            case "temper-type": {
                p.setText(getResources().getText(R.string.temper_start));
                p1.setText(getResources().getText(R.string.temper_start_1));
                tip.setText(getResources().getText(R.string.temper_start_tip));
                title.setText("气质类型测试");
                break;
            }
            case "bpm": {
                break;
            }
            case "16pf": {
                p.setText(getResources().getText(R.string.pf_start));
                p1.setText(getResources().getText(R.string.pf_start_1));
                tip.setText(getResources().getText(R.string.pf_start_tip));
                title.setText("人格类型测试");
                break;
            }
            case "sds": {
                p.setText(getResources().getText(R.string.sds_start));
                p1.setText(getResources().getText(R.string.sds_start_1));
                tip.setText(getResources().getText(R.string.sds_start_tip));
                title.setText("职业兴趣测试");
                break;
            }
        }
    }

    @Override
    public void onNewIntent(Intent i){
        textBinding( i );
    }
}
