package com.kevin.sqlitedatabasetest.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/26.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */

@Entity
public class DataEntity {
    private String name;
    private String age;
    @Generated(hash = 1737257314)
    public DataEntity(String name, String age) {
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 1892108943)
    public DataEntity() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
