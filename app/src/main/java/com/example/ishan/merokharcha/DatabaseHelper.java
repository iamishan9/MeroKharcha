package com.example.ishan.merokharcha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DisplayContext;
import android.icu.text.IDNA;

/**
 * Created by Ishan on 6/30/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Kharcha.db";
    public static final String TABLE_NAME = "kharcha_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "KharchaType";
    public static final String COL_3 = "Kharcha";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME + " ( ID STRING PRIMARY KEY, KharchaType STRING , Kharcha INTEGER ) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String ID, String KharchaType, Integer Kharcha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,KharchaType);
        contentValues.put(COL_3,Kharcha);



        long result = db.insert(TABLE_NAME,null,contentValues);
if(result == -1)
    return false;
        else
            return true;
    }

    public Cursor showKharcha(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor tyt = db.rawQuery("select * from " + TABLE_NAME,null);
        return tyt;
    }

    public boolean updateData(String ID, String KharchaType, Integer Kharcha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,KharchaType);
        contentValues.put(COL_3,Kharcha);
        db.update(TABLE_NAME,contentValues, "ID = ?",new String[] { ID });
        return true;

    }



}
