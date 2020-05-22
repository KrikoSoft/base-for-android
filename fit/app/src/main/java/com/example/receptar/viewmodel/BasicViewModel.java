package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.receptar.repository.BasicRepository;

public class BasicViewModel<T, BR extends BasicRepository<T>> extends AndroidViewModel {

    BR repository;

    BasicViewModel(@NonNull Application application, BR repository) {
        super(application);
        this.repository = repository;
    }

    public void insert(T object) {
        repository.insert(object);
    }

    public void update(T object) {
        repository.update(object);
    }

    public void delete(T object) {
        repository.delete(object);
    }

}
