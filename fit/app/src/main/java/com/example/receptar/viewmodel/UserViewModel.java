package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.receptar.java.User;
import com.example.receptar.repository.UserRepository;

import java.util.List;

import lombok.Getter;

public class UserViewModel extends AndroidViewModel {

    // TODO VYHODIT

    private UserRepository repository;

    @Getter
    private LiveData<List<User>> users;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        users = repository.getUsers();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

}
