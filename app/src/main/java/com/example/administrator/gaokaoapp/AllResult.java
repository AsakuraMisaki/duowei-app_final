package com.example.administrator.gaokaoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllResult extends AppCompatActivity {

    Intent getType;
    TextView t1;
    TextView t2;
    TextView rT;
    Button again;
    Button awakeAgain;
    Intent i;
    ImageButton back;
    int society_rank=1;
    int enterprise_rank=6;
    int convention_rank=2;
    int realistic_rank=3;
    int Investigation_rank=4;
    int art_rank=5;

    class HtmlShow{
        @SuppressLint("JavascriptInterface")
        private String str;
        @JavascriptInterface
        public int getar(){
            return art_rank;
        }
        @JavascriptInterface
        public int getir(){
            return Investigation_rank;
        }
        @JavascriptInterface
        public int getrr(){
            return  realistic_rank;
        }
        @JavascriptInterface
        public int getcr(){
            return convention_rank;
        }
        @JavascriptInterface
        public int geter(){
            return enterprise_rank;
        }
        @JavascriptInterface
        public String getContent(){
            str="initialize in java";
            return str;
        }
        @JavascriptInterface
        public int getsr(){
            return society_rank;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_result );
        WebView webView=(WebView)findViewById(R.id.WebView);
//      webView.loadUrl("https://www.baidu.com/");
        webView.loadUrl("file:///android_asset/test.html");//加载asset文件夹下html
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        HtmlShow hs=new HtmlShow();
        webView.addJavascriptInterface(hs,"MyContent");
        t1 = findViewById( R.id.zhilizhongxia );
        t2 = findViewById( R.id.jutixinxi );
        rT = findViewById( R.id.result_title );
        back = findViewById( R.id.fanhui_bt );
        again = findViewById( R.id.again );
        awakeAgain = findViewById( R.id.awake_again );
        awakeAgain.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility( View.GONE );
                again.setVisibility( View.VISIBLE );
            }
        } );
//        Toast.makeText( AllResult.this, "create", Toast.LENGTH_SHORT ).show();
        getType = getIntent();
        try{
            init();
        }
        catch (Exception e){
            String errInfo = "";
            for(int i= 0; i<e.getStackTrace().length; i++){
                errInfo += e.getStackTrace()[i] + " $$ ";
            }
            Toast.makeText(AllResult.this, errInfo, Toast.LENGTH_LONG).show();
        }

    }

    public void init(){
        findViewById( R.id.WebView ).setVisibility( View.GONE );
        i = new Intent( AllResult.this, AllTestStart.class );
        //bpm
        if(getType.hasExtra( "answer-bpm" )){
            int[] answer = getType.getIntArrayExtra( "answer-bpm" );
            rT.setText( "瑞文智力测试" );
            bpmAnswerHandle(answer);
            again.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i.putExtra("question-class", "bpm");
                    startActivity(i);
                }
            } );
            back.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent( AllResult.this, BpmTest.class ) );
                }
            } );
        }

        //16pf
        if(getType.hasExtra( "answer-16pf" )){
            char[] answer = getType.getCharArrayExtra( "answer-16pf" );
            rT.setText( "人格类型测试" );
            pfAnswerHandle( answer );
            again.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i.putExtra("question-class", "16pf");
                    startActivity(i);
                }
            } );
            back.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent( AllResult.this, three_fragment_a01.class ) );
                }
            } );
        }

        //sds
        if(getType.hasExtra( "answer-sds" )){
            findViewById( R.id.WebView ).setVisibility( View.VISIBLE );
            rT.setText( "职业兴趣测试" );
            char[] answer = getType.getCharArrayExtra( "answer-sds" );
            sdsAnswerHandle( answer );
            again.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i.putExtra("question-class", "sds");
                    startActivity(i);
                }
            } );
            back.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent( AllResult.this, SDSTest.class ) );
                }
            } );
        }

        //temper
        if(getType.hasExtra( "answer-temper" )){
            int[] answer = getType.getIntArrayExtra( "answer-temper" );
            rT.setText( "气质类型测试" );
            temperAnswerHandle(answer);
            again.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i.putExtra("question-class", "temper-type");
                    startActivity(i);
                }
            } );
            back.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent( AllResult.this, temper_type_test.class ) );
                }
            } );
        }

        //social
        if(getType.hasExtra( "answer-social" )){
            char[] answer = getType.getCharArrayExtra( "answer-social" );
            rT.setText( "社会适应测试" );
            socialAnswerHandle(answer);
            again.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i.putExtra("question-class", "society-test");
                    startActivity(i);
                }
            } );
            back.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent( AllResult.this, SocialTest.class ) );
                }
            } );
        }
    }

    //temper
    public void temperAnswerHandle(int[] a){
        int danzhi = a[1]+a[5]+a[8]+a[13]+a[16]+a[20]+a[26]+a[30]+a[35]+a[37]+a[41]+a[47]+a[49]+a[53]+a[57];
        int duoxue = a[3]+a[7]+a[10]+a[18]+a[22]+a[24]+a[28]+a[33]+a[39]+a[43]+a[45]+a[51]+a[55]+a[59];
        int nianye = a[0]+a[6]+a[9]+a[12]+a[17]+a[21]+a[25]+a[29]+a[32]+a[38]+a[42]+a[44]+a[54]+a[56];
        int yiyu   = a[2]+a[4]+a[11]+a[14]+a[19]+a[23]+a[27]+a[31]+a[34]+a[36]+a[40]+a[46]+a[50]+a[52]+a[58];



        int[] b = new int[] {danzhi,duoxue,nianye,yiyu};
        String s = "";
        for(int i=0; i<b.length; i++){
            s+= b[i]+ "  ";
        }
        //rT.setText( s );
        t1.setText( "典型的" );
        Arrays.sort(b);
        t2.setText( "人的体内有四种体液，即黄胆汁、血液、粘液和墨胆汁。其中黄胆汁生于胆，血液生于心脏，粘液生于脑，墨胆汁生于胃。如果在液体的混合比例中黄胆汁占优势的人是热和干的配合，热而燥就像夏天一样，这就是胆汁质；血液占优势的人，是湿和热的配合，其特点是湿而润，好像春天一样，这就是多血质型；粘液占优势的人是冷和湿的配合，其特点是冷酷无情，像冬天一样，这就是粘液质型；墨胆汁占优势的人是冷和干的配合，像秋天一样冷而燥，这就是抑郁质。" );

        //1.
        if(b[3]-b[2]>4) {
            if(b[3]>20) {
                //System.out.println("典型的");
                t1.setText("典型的");
            }
            else if(b[3]>10) {
                //System.out.println("一般型的");
                t1.setText("一般型的");
            }

            // System.out.println(b[3]==danzhi?"胆汁":(b[3]==duoxue?"多血":(b[3]==nianye?"粘液":"抑郁")));
            t1.setText(b[3]==danzhi?"胆汁":(b[3]==duoxue?"多血":(b[3]==nianye?"粘液":"抑郁")));

        }

        //2.
        if(b[3]-b[2]<3 && b[2]-b[1]>4 && b[2]-b[0]>4) {
            if(b[3]==danzhi && b[2]==duoxue || b[3]==duoxue && b[2]==danzhi) {
                // System.out.println("胆汁--多血混合型");
                t1.setText("胆汁--多血混合型");
            }
            else if(b[3]==danzhi && b[2]==nianye || b[3]==nianye && b[2]==danzhi) {
                // System.out.println("胆汁--粘液混合型");
                t1.setText("胆汁--粘液混合型");
            }
            else if(b[3]==danzhi && b[2]==yiyu || b[3]==nianye && b[2]==yiyu) {
                // System.out.println("胆汁--抑郁混合型");
                t1.setText("胆汁--抑郁混合型");
            }
            else if(b[3]==duoxue && b[2]==nianye || b[3]==nianye && b[2]==duoxue) {
                //  System.out.println("多血--粘液混合型");
                t1.setText("多血--粘液混合型");
            }
            else if(b[3]==duoxue && b[2]==yiyu || b[3]==yiyu && b[2]==duoxue) {
                // System.out.println("多血--抑郁混合型");
                t1.setText("多血--抑郁混合型");
            }
            else if(b[3]==nianye && b[2]==yiyu || b[3]==yiyu && b[2]==nianye) {
                // System.out.println("粘液--抑郁混合型");
                t1.setText("粘液--抑郁混合型");
            }
        }

        //3.
        if(b[1]-b[0]<3 && b[2]-b[1]<3 && b[3]-b[2]<3) {
            if(b[0]==danzhi) {
                // System.out.println("多血--粘液--抑郁混合型");
                t1.setText("多血--粘液--抑郁混合型");
            }
            else if(b[0]==duoxue) {
                //  System.out.println("胆汁--粘液--抑郁混合型");
                t1.setText("胆汁--粘液--抑郁混合型");
            }
            else if(b[0]==nianye) {
                //  System.out.println("胆汁--多血--抑郁混合型");
                t1.setText("胆汁--多血--抑郁混合型");
            }
            else {
                //  System.out.println("胆汁--多血--粘液混合型");
                t1.setText("胆汁--多血--粘液混合型");
            }
        }
        }
    //

    //social
    public void socialAnswerHandle(char[] c){
        int score = 1;
        int[][] b = new int[][] {{2,1,3},{2,3,1},{3,2,1,},{2,1,3},{1,3,2},
                {1,2,3},{1,2,3},{3,1,2},{3,1,3},{1,2,3},
                {1,2,3},{1,3,2},{3,2,1},{3,1,2},{1,2,3},
                {3,1,2},{3,1,2},{3,1,2},{2,3,1},{3,2,1}};
        for(int i =0;i<20;i++) {
            int temp = c[i]=='a'?0:(c[i]=='b'?1:2);
            score = score + b[i][temp];
        }
        t1.setText("得分: "+ score+"");
        if(score<60&&score>49) {
            //System.out.println("1");
            t2.setText( "如果你的得分为49~60分，说明你的社会适应能力很强，你能够很快的适应新的学习，工作，生活环境，与人交往轻松、大方，给人的印象良好。你无论是到什么样的环境，都可以应付自如。" );
        }
        else if(score>37) {
            //System.out.println("2");
            t2.setText( "如果你的得分为37~48分，表明你的适应能力较强。你能够较好的适应环境的变化，态度积极，乐于与外界交往，有较强的调试能力。" );
        }
        else if(score>25) {
            //System.out.println("3");
            t2.setText( "如果你的得分在25~36分，说明你的适应能力属于一般。这表明你在进入新的环境后，经过一段时间的努力基本上就能够达到适应。" );
        }
        else {
            //System.out.println("4");
            t2.setText( "如果你的得分在25分以下，表明你的适应能力较差，你需要在今后的学习、生活、工作中，有意识的培养自己在这方面的能力，以提高心理承受力和适应能力。" );
        }
    }
    //social


    //sds
    public void sdsAnswerHandle(char[] answer){


        final int maxcount = 60;
        final int typeStrNum = 3;
        final int eachSplit = 10;
        final int[] relatedAnswer = { 7, 19, 29, 39, 41, 51, 57, 5, 18, 40, 2, 13, 22, 36, 43, 14, 23, 44, 47, 48, 6, 8, 20, 30, 31, 42, 21, 55, 56, 58, 11, 24, 28, 35, 38, 46, 60, 3, 16, 25, 26, 37, 52, 59, 1, 12, 15, 27, 45, 53, 4, 9, 10, 17, 33, 34, 49, 50, 54, 32};

        String type = "";
        char[] correctAnswer = new char[maxcount];
        final int[] subTerm = {7,19,29,39,41,51,57,2,13,22,36,43,6,8,20,30,31,42,11,24,28,35,38,46,60,26,37,52,59,4,9,10,17,33,34,49,50,54};
        for(int i = 0; i<maxcount; i++) {
            correctAnswer[i] = 'n';
        }
        for(int i=0; i<subTerm.length; i++) {
            int s= subTerm[i];
            correctAnswer[s-1] = 'y';
        }


        HashMap<String, Integer> map = new HashMap<>();
        String[] classes = {"C", "R", "I", "E", "S", "A"};

        for(int i = 0; i<maxcount/eachSplit; i++) {
            int tempGrade = 0;
            for(int j = i*eachSplit; j<i*eachSplit+eachSplit; j++) {
                if(answer[relatedAnswer[j]-1] == correctAnswer[relatedAnswer[j]-1]) {
                    tempGrade++;
                }
            }
            map.put(classes[i], tempGrade);
        }

        List<Map.Entry<String, Integer>> sortedList = hashToSortedList(map);
        for(int i=1; i<classes.length+1; i++){
            switch (sortedList.get( i-1 ).getKey()){
                case "C": {
                    convention_rank = i;
                    break;
                }
                case "I": {
                    Investigation_rank = i;
                    break;
                }
                case "R": {
                    convention_rank = i;
                    break;
                }
                case "E": {
                    enterprise_rank = i;
                    break;
                }
                case "A": {
                    art_rank = i;
                    break;
                }
                case "S": {
                    society_rank = i;
                    break;
                }
                default:{
                    try {
                        throw new Exception( "illegal rel" );
                    }
                    catch (Exception e){
                        Toast.makeText( AllResult.this, e.toString(),Toast.LENGTH_SHORT ).show();
                    }
                    break;
                }
            }
        }
//        log: grade in list print
//        String tttt = "";
//        for (int i=0; i<classes.length; i++){
//            tttt += sortedList.get( i ).getKey() + sortedList.get( i ).getValue() + " ||||| ";
//        }
//        Toast.makeText( AllResult.this, tttt, Toast.LENGTH_LONG).show();

//        String[] sdsCode = getResources().getStringArray(R.array.sds_a_number);
//        String[] sdsText = getResources().getStringArray( R.array.sds_a_text );
        String staticInfo = getResources().getString( R.string.sds_static );
        t1.setText( "参考资料及结果");
        t2.setText( staticInfo );
//        for(int i=0; i<sdsCode.length; i++){
//            if(sdsCode[i].equals(type)){
//                t1.setText( sdsCode[i] + "--适合职业:");
//                t2.setText( sdsText[i] +  staticInfo);
//            }
//        }

    }

    public List<Map.Entry<String, Integer>> hashToSortedList(HashMap<String, Integer> map){
        List<Map.Entry<String, Integer>> list = new LinkedList<>( map.entrySet() );
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });
        return list;
    }
    //

    //bpm
    public void bpmAnswerHandle(int[] answer){
        final int maxCount = 72;
        int grade = 0;
        final int[] correctAnswer = {4,5,1,2,6,3,6,6,1,3,4,5,4,5,1,6,2,1,3,4,6,3,5,2,2,6,1,2,1,3,5,6,4,3,4,5,8,2,3,8,7,4,5,1,7,6,1,2,3,4,3,7,8,6,5,4,1,2,5,6,7,6,8,2,1,5,1,6,3,2,4,2};
        for(int i = 0; i<maxCount; i++){
            if(answer[i] == correctAnswer[i]){
                grade++;
            }
        }
        t2.setText( "\t\t每题一分，你的得分是" + grade + getResources().getString( R.string.bpm_static ));
        if(grade >=71){
            t1.setText( "智商超优，反应迅速" );
        }
        else if(grade>=69 && grade<71){
            t1.setText("智商优秀，反应灵敏" );
        }
        else if(grade>=67 && grade<69){
            t1.setText( "智商中上，反应较快" );
        }
        else if(grade>64 && grade<67){
            t1.setText( "智商中等，反应一般" );
        }
        else if(grade>61 && grade<64){
            t1.setText( "智商中下，反应较慢" );
        }
        else if(grade>55 && grade<61){
            t1.setText( "智商迟钝，反应很慢" );
        }
        else if(grade>41 && grade<55){
            t1.setText( "智商低能，反应太慢" );
        }
        else{
            t1.setText( "智商低能，反应太慢" );
        }


    }
    //

    //16pf
    public void pfAnswerHandle(char[] answer){
        t1.setText( getResources().getStringArray( R.array.pf_a_str )[0] );
        t2.setText( getResources().getString( R.string.pf_static ) );
    }
}
