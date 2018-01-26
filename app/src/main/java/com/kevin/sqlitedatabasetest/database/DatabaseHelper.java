package com.kevin.sqlitedatabasetest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/23.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 7;
    private static final String DB_NAME = "SQLiteTest.db";
    private static final String TAG = "DatabaseHelper.class";
    private static final String SQLITETABLE = "sqliteTable";
    private static final String AGE = "age";
    private static final String NAME = "name";

    public DatabaseHelper(Context context) {
        this(context, DB_NAME, DB_VERSION);
    }

    public DatabaseHelper(Context context, int version) {
        this(context, DB_NAME, version);
    }

    public DatabaseHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists " + SQLITETABLE + "(id INTEGER primary key autoincrement," + NAME + " TEXT," + AGE + " TEXT)";
        db.execSQL(sql);
        Log.d(TAG, "onCreate: create database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists sqliteTable");
        onCreate(db);
        Log.d(TAG, "upgrade database");
    }
}
