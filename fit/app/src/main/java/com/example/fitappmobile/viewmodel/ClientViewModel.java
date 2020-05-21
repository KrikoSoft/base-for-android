package com.example.fitappmobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fitappmobile.java.Client;
import com.example.fitappmobile.repository.ClientRepository;

import java.util.List;

import lombok.Getter;

public class ClientViewModel extends AndroidViewModel {

    private ClientRepository repository;

    @Getter
    private LiveData<List<Client>> clients;

    public ClientViewModel(@NonNull Application application) {
        super(application);
        repository = new ClientRepository(application);
        clients = repository.getClients();
    }

    public void insert(Client client) {
        repository.insert(client);
    }

    public void update(Client client) {
        repository.update(client);
    }

    public void delete(Client client) {
        repository.delete(client);
    }

}
