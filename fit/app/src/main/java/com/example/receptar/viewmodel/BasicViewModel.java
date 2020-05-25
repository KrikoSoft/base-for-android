package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.receptar.repository.BasicRepository;

import lombok.Getter;

/**
 * @param <T>  objects in repository
 * @param <BR> repository of objects
 */
public class BasicViewModel<T, BR extends BasicRepository<T>> extends AndroidViewModel {

    @Getter
    protected BR repository;

    protected BasicViewModel(@NonNull Application application, BR repository) {
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
