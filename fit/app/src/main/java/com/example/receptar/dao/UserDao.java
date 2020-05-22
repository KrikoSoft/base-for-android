package com.example.receptar.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.receptar.java.User;

import java.util.List;

@Dao
public interface UserDao extends BasicDao<User> {

    @Query("SELECT * FROM users ORDER BY id")
    LiveData<List<User>> getUsers();

}
