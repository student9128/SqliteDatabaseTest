package com.kevin.sqlitedatabasetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kevin.sqlitedatabasetest.database.DaoMaster;
import com.kevin.sqlitedatabasetest.database.DaoSession;
import com.kevin.sqlitedatabasetest.database.DataEntity;
import com.kevin.sqlitedatabasetest.database.DataEntityDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/26.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.et_retrieve_name)
    EditText etRetrieveName;
    @BindView(R.id.btn_retrieve_name)
    Button btnRetrieveName;
    @BindView(R.id.et_update_name)
    EditText etUpdateName;
    @BindView(R.id.et_update_age)
    EditText etUpdateAge;
    @BindView(R.id.btn_update_name)
    Button btnUpdateName;
    @BindView(R.id.et_delete_name)
    EditText etDeleteName;
    @BindView(R.id.btn_delete_name)
    Button btnDeleteName;
    @BindView(R.id.et_delete_age)
    EditText etDeleteAge;
    @BindView(R.id.btn_delete_age)
    Button btnDeleteAge;
    @BindView(R.id.et_retrieve_age)
    EditText etRetrieveAge;
    @BindView(R.id.btn_retrieve_age)
    Button btnRetrieveAge;
    @BindView(R.id.et_insert_name)
    EditText etInsertName;
    @BindView(R.id.et_insert_age)
    EditText etInsertAge;
    @BindView(R.id.btn_insert)
    Button btnInsert;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DataEntityDao mDataEntityDao;
    private static final String DB_NAME = "SQLiteGreenTest.db";
    private RecyclerGreenAdapter mAdapter;
    private List<DataEntity> dataEntities = new ArrayList<>();
    private List<DataEntity> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        createDb();
        btnInsert.setOnClickListener(this);
        btnUpdateName.setOnClickListener(this);
        btnRetrieveName.setOnClickListener(this);
        btnRetrieveAge.setOnClickListener(this);
        btnDeleteName.setOnClickListener(this);
        btnDeleteAge.setOnClickListener(this);
        mAdapter = new RecyclerGreenAdapter(this, data);
        rvRecyclerView.setNestedScrollingEnabled(false);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvRecyclerView.setAdapter(mAdapter);
    }

    private void createDb() {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        mDaoMaster = new DaoMaster(mDevOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
        mDataEntityDao = mDaoSession.getDataEntityDao();
    }

    private List<DataEntity> retrieveName(List<DataEntity> entities, String key) {
        entities.clear();
        List<DataEntity> carDataEntities = mDataEntityDao.queryRaw("where name=?", new String[]{key});
        if (carDataEntities.size() > 0) {
            int size = carDataEntities.size();
            for (int i = 0; i < carDataEntities.size(); i++) {
                String name = carDataEntities.get(i).getName();
                String age = carDataEntities.get(i).getAge();
                DataEntity DataEntity = new DataEntity(null, name, age);
                entities.add(DataEntity);
            }
        }
        return entities;
    }

    private List<DataEntity> retrieveAge(List<DataEntity> entities, String key) {
        entities.clear();
        List<DataEntity> carDataEntities = mDataEntityDao.queryRaw("where age=?", new String[]{key});
        if (carDataEntities.size() > 0) {
            int size = carDataEntities.size();
            for (int i = 0; i < carDataEntities.size(); i++) {
                String name = carDataEntities.get(i).getName();
                String age = carDataEntities.get(i).getAge();
                DataEntity DataEntity = new DataEntity(null, name, age);
                entities.add(DataEntity);
            }
        }
        return entities;
    }

    private void insertData(String name, String age) {
        DataEntity dataEntity = new DataEntity(null, name, age);
        mDataEntityDao.insert(dataEntity);
    }

    private void update(String name, String age) {
        DataEntity dataEntity = new DataEntity(null, name, age);
        mDataEntityDao.update(dataEntity);
    }

    private void delete(String name, String age) {
        DataEntity dataEntity = new DataEntity(null, name, age);
        mDataEntityDao.delete(dataEntity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                String insertName = etInsertName.getText().toString().trim();
                String insertAge = etInsertAge.getText().toString().trim();
                insertData(insertName, insertAge);
                break;
            case R.id.btn_update_name:
                String updateName = etUpdateName.getText().toString().trim();
                String updateAge = etUpdateAge.getText().toString().trim();
                update(updateName, updateAge);
                break;
            case R.id.btn_retrieve_name:
                String retrieveName = etRetrieveName.getText().toString().trim();
                List<DataEntity> dataEntity = retrieveName(dataEntities, retrieveName);
                mAdapter.showData(dataEntity);
                break;
            case R.id.btn_retrieve_age:
                String retrieveAge = etRetrieveAge.getText().toString().trim();
                List<DataEntity> dataEntityAge = retrieveAge(this.dataEntities, retrieveAge);
                mAdapter.showData(dataEntityAge);
                break;
            case R.id.btn_delete_name:
                String deleteName = etDeleteName.getText().toString().trim();
                String deleteAge = etDeleteAge.getText().toString().trim();
                delete(deleteName, deleteAge);
                break;
            case R.id.btn_delete_age:
                String deleteName1 = etDeleteName.getText().toString().trim();
                String deleteAge1 = etDeleteAge.getText().toString().trim();
                delete(deleteName1, deleteAge1);
                break;
        }
    }
}
