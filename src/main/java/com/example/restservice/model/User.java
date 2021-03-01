package com.example.restservice.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Created by Archie on 2021-03-01.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {


    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id", length = 32)
    private String userId;


    /**
     * 年龄
     */
    @Column(name = "age")
    private int age;


    /**
     * 用户名
     */
    @Column(name = "name")
    private String name;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId='" + userId + '\'' +
            ", age=" + age +
            ", name='" + name + '\'' +
            '}';
    }
}
