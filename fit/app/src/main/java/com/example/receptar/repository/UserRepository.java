package com.example.receptar.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.receptar.dao.UserDao;
import com.example.receptar.database.UserDatabase;
import com.example.receptar.java.User;

import java.util.List;

import lombok.Getter;

public class UserRepository extends BasicRepository<User> {

    @Getter
    private LiveData<List<User>> users;

    public UserRepository(Application application) {
        super(application, UserDatabase.getInstance(application).userDao());
        users = ((UserDao) basicDao).getUsers();
    }

}
