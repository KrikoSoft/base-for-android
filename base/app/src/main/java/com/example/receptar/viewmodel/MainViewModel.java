package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.receptar.java.User;
import com.example.receptar.repository.UserRepository;

/**
 * view model for main activity
 */
public class MainViewModel extends BasicViewModel<User, UserRepository> {

    public MainViewModel(@NonNull Application application) {
        super(application, new UserRepository(application));
    }

    /**
     * @param name     name of the user
     * @param password user password
     * @return already registered user with the name and the password if one exists,
     * null if the user does not exist or password and username don't match
     */
    public User login(String name, String password) {
        return repository.login(name, password);
    }

    /**
     * registers a user to the app and logs them in
     *
     * @param name     name of the user
     * @param password user password
     * @return newly registered user with disred name and password,
     * null if the user already exists
     */
    public User register(String name, String password) {
        return repository.register(name, password);
    }

}
