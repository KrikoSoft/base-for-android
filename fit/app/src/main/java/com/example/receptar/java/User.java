package com.example.receptar.java;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.receptar.utils.DbUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "users")
/**
 * entity class for user, which tells the dao the table name and column names of user entity in our database
 */
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


