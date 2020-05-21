package com.example.fitappmobile.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitappmobile.java.Client;

import java.util.List;

@Dao
public interface ClientDao {

    @Insert
    void insert(Client client);

    @Update
    void update(Client client);

    @Delete
    void delete(Client client);

    @Query("SELECT * FROM osoby ORDER BY name")
    LiveData<List<Client>> getClients();

}
