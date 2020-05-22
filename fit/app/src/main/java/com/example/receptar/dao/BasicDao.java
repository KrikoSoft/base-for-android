package com.example.receptar.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

public interface BasicDao<T> {

    @Insert
    void insert(T object);

    @Update
    void update(T object);

    @Delete
    void delete(T object);

}
