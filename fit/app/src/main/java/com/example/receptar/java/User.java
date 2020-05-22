package com.example.receptar.java;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.receptar.utils.DbUtils;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "users")
@Getter
@Setter
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return DbUtils.passwordEncrypt(password);
    }

}


