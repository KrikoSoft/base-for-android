package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.receptar.repository.BasicRepository;

import lombok.Getter;

/**
 * base class for view model containing repository for the entity
 *
 * @param <T>  entity handled by the repository
 * @param <BR> repository which handles the entity
 */
public class BasicViewModel<T, BR extends BasicRepository<T>> extends AndroidViewModel {

    @Getter
    protected BR repository;

    protected BasicViewModel(@NonNull Application application, BR repository) {
        super(application);
        this.repository = repository;
    }

    /**
     * method to insert new entity
     *
     * @param object new entity
     */
    public void insert(T object) {
        repository.insert(object);
    }

    /**
     * method to update an entity.
     * entity must have set primary key!
     * values are set to values in object sent as parameter
     *
     * @param object object to update
     */
    public void update(T object) {
        repository.update(object);
    }

    /**
     * method to delete an entity
     * entity must have set primary key
     *
     * @param object object to delete
     */
    public void delete(T object) {
        repository.delete(object);
    }

}
