package com.kevin.sqlitedatabasetest.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/26.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */

@Entity
public class DataEntity {
    @Id
    private Long id;
    private String name;
    private String age;
    @Generated(hash = 1668938553)
    public DataEntity(Long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 1892108943)
    public DataEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
