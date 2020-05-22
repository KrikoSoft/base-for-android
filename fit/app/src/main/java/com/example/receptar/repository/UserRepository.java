package com.example.receptar.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.receptar.dao.UserDao;
import com.example.receptar.database.UserDatabase;
import com.example.receptar.java.User;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserRepository {

    private UserDao userDao;

    @Getter
    private LiveData<List<User>> users;

    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        users = userDao.getClients();
    }

    public void insert(User user) {
        new InsertClientAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateClientAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteClientAsyncTask(userDao).execute(user);
    }

    @AllArgsConstructor
    private static class InsertClientAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    @AllArgsConstructor
    private static class UpdateClientAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    @AllArgsConstructor
    private static class DeleteClientAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

}
