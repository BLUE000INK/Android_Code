package com.example.note_anything01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDBHelper  extends SQLiteOpenHelper {

    //数据库名
    private final static String DATABASE_NAME = "notesdb";
    private final static int DATABASE_VERSION = 1;

    //建表
    private final static String SQL_CREATE_DATABASE = "CREATE TABLE " + Notes.Note.TABLE_NAME + " (" +
            Notes.Note._ID + " VARCHAR(32) PRIMARY KEY NOT NULL," +
            Notes.Note.COLUMN_NAME_TITLE + " TEXT UNIQUE NOT NULL,"+
            Notes.Note.COLUMN_NAME_CONTENT + " TEXT)";
    //删表
    private final static String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS "
            + Notes.Note.TABLE_NAME;


    public NotesDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATABASE);
        onCreate(db);
    }
}
