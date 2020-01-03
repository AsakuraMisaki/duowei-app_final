package com.example.administrator.gaokaoapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase db){
        String sql="create table user(account varchar(20),password varcahr(20))";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}
}
// 使用该类
// DatabaseHelper dbHelper=new DatabaseHelper(deletepage.this,"test_db",null,1);
//        SQLiteDatabase db=dbHelper.getWritableDatabase();