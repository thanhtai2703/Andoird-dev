package com.example.lab04;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public  class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentManagement.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "tbllop";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CLASS_CODE = "malop";
    public static final String COLUMN_CLASS_NAME = "tenlop";
    public static final String COLUMN_STUDENT_COUNT = "siso";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLASS_CODE + " TEXT UNIQUE, " +
                COLUMN_CLASS_NAME + " TEXT, " +
                COLUMN_STUDENT_COUNT + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insertClass(String classCode, String className, int studentCount) {
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues values = new android.content.ContentValues();
        values.put(COLUMN_CLASS_CODE, classCode);
        values.put(COLUMN_CLASS_NAME, className);
        values.put(COLUMN_STUDENT_COUNT, studentCount);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void updateClass(String classCode, String className, int studentCount) {
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues values = new android.content.ContentValues();
        values.put(COLUMN_CLASS_NAME, className);
        values.put(COLUMN_STUDENT_COUNT, studentCount);
        db.update(TABLE_NAME, values, COLUMN_CLASS_CODE + " = ?", new String[]{classCode});
        db.close();
    }
    public void deleteClass(String classCode) {
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_CLASS_CODE + " = ?", new String[]{classCode});
        db.close();
    }
    public Cursor getAllClasses() {
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT rowid as _id, * FROM " + TABLE_NAME, null);
    }
}
