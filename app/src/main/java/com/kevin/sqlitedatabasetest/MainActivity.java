package com.kevin.sqlitedatabasetest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kevin.sqlitedatabasetest.database.DatabaseDao;
import com.kevin.sqlitedatabasetest.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity.class";
    @BindView(R.id.btn_create)
    Button btnCreate;
    @BindView(R.id.et_retrieve_name)
    EditText etRetrieveName;
    @BindView(R.id.btn_retrieve_name)
    Button btnRetrieveName;
    @BindView(R.id.et_retrieve_age)
    EditText etRetrieveAge;
    @BindView(R.id.btn_retrieve_age)
    Button btnRetrieveAge;
    @BindView(R.id.et_update_name)
    EditText etUpdateName;
    @BindView(R.id.btn_update_name)
    Button btnUpdateName;
    @BindView(R.id.et_update_age)
    EditText etUpdateAge;
    @BindView(R.id.et_delete_name)
    EditText etDeleteName;
    @BindView(R.id.btn_delete_name)
    Button btnDeleteName;
    @BindView(R.id.et_delete_age)
    EditText etDeleteAge;
    @BindView(R.id.btn_delete_age)
    Button btnDeleteAge;
    @BindView(R.id.et_insert_name)
    EditText etInsertName;
    @BindView(R.id.et_insert_age)
    EditText etInsertAge;
    @BindView(R.id.btn_insert)
    Button btnInsert;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    @BindView(R.id.btn_green_dao)
    Button btnGreenDao;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;

    private DatabaseDao databaseDao;
    private List<DataBean> retrieveData = new ArrayList<>();
    private List<DataBean> data = new ArrayList<>();
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnCreate.setOnClickListener(this);
        btnRetrieveName.setOnClickListener(this);
        btnRetrieveAge.setOnClickListener(this);
        btnUpdateName.setOnClickListener(this);
        btnDeleteName.setOnClickListener(this);
        btnDeleteAge.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnGreenDao.setOnClickListener(this);
        databaseDao = new DatabaseDao(this);
        mAdapter = new RecyclerAdapter(this, data);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvRecyclerView.setNestedScrollingEnabled(false);
        rvRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                break;
            case R.id.btn_insert:
                String insertName = etInsertName.getText().toString().toString();
                String insertAge = etInsertAge.getText().toString().toString();
                databaseDao.insert(insertName, insertAge);
                break;
            case R.id.btn_retrieve_name:
                String retrieveName = etRetrieveName.getText().toString().toString();
                if (!TextUtils.isEmpty(retrieveName)) {

                    List<DataBean> dataBeans = databaseDao.retrieveName(retrieveData, retrieveName);
                    mAdapter.showData(dataBeans);
                } else {
                    Toast.makeText(this, "Please text the name", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_retrieve_age:
                String retrieveAge = etRetrieveAge.getText().toString().toString();
                if (!TextUtils.isEmpty(retrieveAge)) {
                    List<DataBean> dataBeans = databaseDao.retrieveAge(retrieveData, retrieveAge);
                    mAdapter.showData(dataBeans);
                } else {
                    Toast.makeText(this, "Please text the age", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_update_name:
                String updateName = etUpdateName.getText().toString().toString();
                String updateAge = etUpdateAge.getText().toString().toString();
                if (!TextUtils.isEmpty(updateAge)) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(this);
                    SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
                    String str = "H";
                    String age = "123";
                    String sql = "update sqliteTable set name='" + str + "' where age='" + age + "'";
//                    writableDatabase.execSQL(sql);
                    databaseDao.updateName(updateAge, updateName);
                } else {
                    Toast.makeText(this, "Please text the age", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_delete_name:
                String deleteName = etDeleteName.getText().toString().trim();
                if (!TextUtils.isEmpty(deleteName)) {
                    databaseDao.deleteName(deleteName);
                } else {
                    Toast.makeText(this, "Please text the name", Toast.LENGTH_SHORT).show();
                }
            case R.id.btn_delete_age:
                String deleteAge = etDeleteAge.getText().toString().trim();
                if (!TextUtils.isEmpty(deleteAge)) {
                    databaseDao.deleteAge(deleteAge);
                } else {
                    Toast.makeText(this, "Please text the age", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_green_dao:
                startActivity(new Intent(this, GreenDaoActivity.class));
                break;
            default:
                break;
        }
    }
}
