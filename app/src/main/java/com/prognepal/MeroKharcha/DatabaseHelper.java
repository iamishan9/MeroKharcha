package com.prognepal.MeroKharcha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DisplayContext;
import android.icu.text.IDNA;

import static android.os.Build.ID;

/**
 * Created by Ishan on 6/30/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Kharcha.db";
    public static final String TABLE_NAME = "kharcha_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "KharchaType";
    public static final String COL_3 = "Kharcha";
    public static final String COL_4 = "Date";
    public static final String COL_5 = "Budget";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME + " (ID STRING PRIMARY KEY, KharchaType STRING , Kharcha INTEGER, Date INTEGER , Budget INTEGER) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

  /*public void copyData(){
       SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("Update "+TABLE_NAME+" set "+ COL_3 +" = "+ COL_4);

    db.rawQuery("Update "+TABLE_NAME+" set "+ COL_3 +" = "+ COL_4,null);

   }
*/
    /*public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update "+TABLE_NAME+" set "+COL_4+" = "+null);
      //  db.rawQuery("Update "+TABLE_NAME+" set "+COL_4+" = "+null,null);

    }*/

    public boolean insertData(String ID,String KharchaType, Integer Kharcha,Integer Date,Integer Budget){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,KharchaType);
        contentValues.put(COL_3,Kharcha);
        contentValues.put(COL_4,Date);
        contentValues.put(COL_5,Budget);



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

    public boolean updateData(String ID,String KharchaType, Integer Kharcha, Integer Date,Integer Budget){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,KharchaType);
        contentValues.put(COL_3,Kharcha);
        contentValues.put(COL_4,Date);
        contentValues.put(COL_5,Budget);

        db.update(TABLE_NAME,contentValues, "ID = ?",new String[] { ID });
        return true;

    }



}
