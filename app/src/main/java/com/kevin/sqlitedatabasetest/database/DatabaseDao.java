package com.kevin.sqlitedatabasetest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kevin.sqlitedatabasetest.DataBean;

import java.util.List;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/23.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class DatabaseDao {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String SQLITETABLE = "sqliteTable";
    private static final String TAG = "DatabaseDao.class";

    public DatabaseDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void insert(String x, String age) {
        String sql = "insert into " + SQLITETABLE + "(name,age) values(?,?)";
        sqLiteDatabase = databaseHelper.getWritableDatabase();
//        String x = "x";
        sqLiteDatabase.execSQL(sql, new String[]{x, age});
    }

    public List<DataBean> retrieveName(List<DataBean> dataBeans, String name) {
        dataBeans.clear();
        String s = "select * from " + SQLITETABLE + " where name =?";
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(s, new String[]{name});
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                String name1 = cursor.getString(cursor.getColumnIndex("name"));
                String age1 = cursor.getString(cursor.getColumnIndex("age"));
                Log.d(TAG, "retrieveName: name=" + name1 + ",age=" + age1);
                DataBean dataBean = new DataBean();
                dataBean.setName(name1);
                dataBean.setAge(age1);
                dataBeans.add(dataBean);
            }
        }
        return dataBeans;
    }

    public List<DataBean> retrieveAge(List<DataBean> dataBeans, String age) {
        String sql = "select * from " + SQLITETABLE + " where age=?";
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{age});
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                String name1 = cursor.getString(cursor.getColumnIndex("name"));
                String age1 = cursor.getString(cursor.getColumnIndex("age"));
                Log.d(TAG, "retrieveAge: name=" + name1 + ",age=" + age1);
                DataBean dataBean = new DataBean();
                dataBean.setName(name1);
                dataBean.setAge(age1);
                dataBeans.add(dataBean);
            }
        }
        return dataBeans;
    }

    public void updateName(String age, String newName) {
        String sql = "update sqliteTable set name='" + newName + "' where age='" + age + "'";
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public void deleteName(String name) {
        String sql = "delete from " + SQLITETABLE + " where name=" + name;
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public void deleteAge(String age) {
        String sql = "delete from " + SQLITETABLE + " where age=" + age+"";
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }
}
