package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.receptar.java.User;
import com.example.receptar.repository.UserRepository;

public class MainViewModel extends BasicViewModel<User, UserRepository> {

    public MainViewModel(@NonNull Application application) {
        super(application, new UserRepository(application));
    }

    public User login(String name, String password) {
        return repository.login(name, password);
    }

    public User register(String name, String password) {
        return repository.register(name, password);
    }

}
