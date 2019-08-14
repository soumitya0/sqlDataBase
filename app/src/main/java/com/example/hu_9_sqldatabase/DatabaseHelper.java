package com.example.hu_9_sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";

    public static final String TABLE_NAME="Student_table";
//4 col in table
    public static final String COL_1="ID";
    public static final String COL_2="FIRST_NAME";
    public static final String COL_3="LAST_NAME";
    public static final String COL_4="MARKS";







    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
        // name= database  variable  name  and  my database variable  name is database

       /* THIS CODE WILL HELP YOU TO SEE THE DATA BASE IS CREATED
       *SQLiteDatabase db= this.getWritableDatabase();*/



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     // to create table (Student Table)is data base it will created in onCreat(SQLiteDatabase db)

        db.execSQL("create table "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , FIRST_NAME TEXT, LAST_NAME TEXT,MARKS INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(db);

    }



    //create method to insert data

    public  boolean insertData(String FIRST_NAME,String LAST_NAME,String MARKS){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put(COL_2,FIRST_NAME);
        contentValues.put(COL_3,LAST_NAME);
        contentValues.put(COL_4,MARKS);

        long result=  db.insert(TABLE_NAME,null,contentValues);
if(result== -1){
    return false;
}
else return true;


    }


    //get all data
    //Cursor is class
    //This interface provides random read-write access to the result set returned
    // * by a database query.

    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from "  +  TABLE_NAME,null);
return res;
    }




    // updatedata

    public boolean update(String ID,String FIRST_NAME,String LAST_NAME,String MARKS ){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

       contentValues.put(COL_1,ID);
        contentValues.put(COL_2,FIRST_NAME);
        //contentValues.put(COL_3,LAST_NAME);
        contentValues.put(COL_4,MARKS);

db.update(TABLE_NAME,contentValues, " ID= ?",new String[]{ID});
return true;

    }

    // delete

    public int delete(String ID ){

        SQLiteDatabase db= this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID= ?",new String[]{ID});

    }

}
