package com.example.administrator.gaokaoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        imageButton1 = findViewById( R.id.a2 );
        imageButton1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               imageButton1.setImageResource( R.drawable.start1 );
               Intent intent = new Intent( MainActivity.this,main.class );
                startActivity(intent);
           }
      } );
    }
}
