package com.example.receptar.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

/**
 * basic dao containing methods for insert, update and delete of an object
 *
 * @param <T> object with which the dao works
 */
public interface BasicDao<T> {

    @Insert
    void insert(T object);

    @Update
    void update(T object);

    @Delete
    void delete(T object);

}
