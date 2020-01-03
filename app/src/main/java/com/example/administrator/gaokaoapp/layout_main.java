package com.example.administrator.gaokaoapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class layout_main extends AppCompatActivity {

    //在这里写没有用！！！！！！！！！！！！！！！！！去main里面写！！！

   /*//定义三个Fragment的对象~
    private one_fragment fg1;
    private two_fragment fg2;
    private three_fragment fg3;
    //定义三个图片按钮~
    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_main );
        /*initView();*/
    }
    /* private void initView() {
        imageButton1 = findViewById( R.id.wode );
        imageButton2 = findViewById( R.id.gaokaozixun );
        imageButton3 = findViewById( R.id.zhuanyeceping );

        imageButton1.setOnClickListener( l );
        imageButton2.setOnClickListener( l );
        imageButton3.setOnClickListener( l );
        imageButton2.performClick();
    }

    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.wode:
                    xuanze(0);
                    break;

                case R.id.gaokaozixun:
                    xuanze(1);
                    break;

                case R.id.zhuanyeceping:
                    xuanze(2);
                    break;

                default:
                    break;
            }
        }

        private void xuanze(int index) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            clean();
            switch (index) {
                case 0:
                    imageButton1.setImageResource(R.drawable.wode_button_pressed);
                    if (fg1 == null) {
                        fg1 = new one_fragment();
                        transaction.add(R.id.a1,fg1);
                    }
                    hideFragments(transaction);
                    transaction.show(fg1);
                    break;

                case 1:
                    imageButton2.setImageResource(R.drawable.gaokaozixun_button_pressed);
                    if (fg2 == null) {
                        fg2 = new two_fragment();
                        transaction.add(R.id.a1, fg2);
                    }
                    hideFragments(transaction);
                    transaction.show(fg2);
                    break;

                case 2:
                    imageButton3.setImageResource(R.drawable.zhuanyeceping_button_pressed);
                    if (fg3 == null) {
                        fg3 = new three_fragment();
                        transaction.add(R.id.a1, fg3);
                    }
                    hideFragments(transaction);
                    transaction.show(fg3);
                    break;
            }
            transaction.commit();
        }

        private void hideFragments(FragmentTransaction transaction) {
            if (fg1 != null) {
                transaction.hide(fg1);
            }
            if (fg2 != null) {
                transaction.hide(fg2);
            }
            if (fg3 != null) {
                transaction.hide(fg3);
            }
        }

        private void clean() {
            imageButton1.setImageResource(R.drawable.wode_button);
            imageButton2.setImageResource(R.drawable.gaokaozixun_button);
            imageButton3.setImageResource(R.drawable.zhuanyeceping_button);
        }
    };*/
}
