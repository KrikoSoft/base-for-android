package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.receptar.java.User;
import com.example.receptar.repository.UserRepository;

import java.util.List;

import lombok.Getter;

public class UserViewModel extends BasicViewModel<User, UserRepository> {

    @Getter
    private LiveData<List<User>> users;

    public UserViewModel(@NonNull Application application) {
        super(application, new UserRepository(application));
        users = repository.getUsers();
    }

}
