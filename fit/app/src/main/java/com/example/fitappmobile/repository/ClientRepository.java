package com.example.fitappmobile.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.fitappmobile.dao.ClientDao;
import com.example.fitappmobile.database.ClientDatabase;
import com.example.fitappmobile.java.Client;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ClientRepository {

    private ClientDao clientDao;

    @Getter
    private LiveData<List<Client>> clients;

    public ClientRepository(Application application) {
        ClientDatabase database = ClientDatabase.getInstance(application);
        clientDao = database.clientDao();
        clients = clientDao.getClients();
    }

    public void insert(Client client) {
        new InsertClientAsyncTask(clientDao).execute(client);
    }

    public void update(Client client) {
        new UpdateClientAsyncTask(clientDao).execute(client);
    }

    public void delete(Client client) {
        new DeleteClientAsyncTask(clientDao).execute(client);
    }

    @AllArgsConstructor
    private static class InsertClientAsyncTask extends AsyncTask<Client, Void, Void> {
        private ClientDao clientDao;

        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.insert(clients[0]);
            return null;
        }
    }

    @AllArgsConstructor
    private static class UpdateClientAsyncTask extends AsyncTask<Client, Void, Void> {
        private ClientDao clientDao;

        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.update(clients[0]);
            return null;
        }
    }

    @AllArgsConstructor
    private static class DeleteClientAsyncTask extends AsyncTask<Client, Void, Void> {
        private ClientDao clientDao;

        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.delete(clients[0]);
            return null;
        }
    }

}
