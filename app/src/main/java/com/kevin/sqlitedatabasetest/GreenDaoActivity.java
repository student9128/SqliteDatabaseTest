package com.kevin.sqlitedatabasetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kevin.sqlitedatabasetest.database.DaoMaster;
import com.kevin.sqlitedatabasetest.database.DaoSession;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/26.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class GreenDaoActivity extends AppCompatActivity {
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
    }
}
