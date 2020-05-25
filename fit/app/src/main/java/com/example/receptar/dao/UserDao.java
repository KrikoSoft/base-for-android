package com.example.receptar.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.receptar.java.User;

import java.util.List;

@Dao
/**
 * dao for handling users
 */
public interface UserDao extends BasicDao<User> {

    @Query("SELECT * FROM users ORDER BY id")
    LiveData<List<User>> getUsers();

    @Query("SELECT * FROM users WHERE userName = :userName AND password = :encryptedPassword")
    User login(String userName, String encryptedPassword);

    @Query("SELECT count(*) FROM users WHERE userName = :userName")
    int userCount(String userName);

}
