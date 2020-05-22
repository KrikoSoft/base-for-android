package com.example.receptar.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import com.example.receptar.dao.BasicDao;

import lombok.AllArgsConstructor;

public class BasicRepository<T> {

    BasicDao<T> basicDao;

    BasicRepository(Application application, BasicDao<T> basicDao) {
        this.basicDao = basicDao;
    }

    @SuppressWarnings("unchecked")
    public void insert(T object) {
        new InsertObjectAsyncTask(basicDao).execute(object);
    }

    @SuppressWarnings("unchecked")
    public void update(T object) {
        new UpdateObjectAsyncTask(basicDao).execute(object);
    }

    @SuppressWarnings("unchecked")
    public void delete(T object) {
        new DeleteObjectAsyncTask(basicDao).execute(object);
    }

    @SuppressLint("StaticFieldLeak")
    @AllArgsConstructor
    private class InsertObjectAsyncTask extends AsyncTask<T, Void, Void> {
        private BasicDao<T> basicDao;

        @SafeVarargs
        @Override
        protected final Void doInBackground(T... objects) {
            basicDao.insert(objects[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    @AllArgsConstructor
    private class UpdateObjectAsyncTask extends AsyncTask<T, Void, Void> {
        private BasicDao<T> basicDao;

        @SafeVarargs
        @Override
        protected final Void doInBackground(T... objects) {
            basicDao.update(objects[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    @AllArgsConstructor
    private class DeleteObjectAsyncTask extends AsyncTask<T, Void, Void> {
        private BasicDao<T> userDao;

        @SafeVarargs
        @Override
        protected final Void doInBackground(T... objects) {
            userDao.delete(objects[0]);
            return null;
        }
    }

}
