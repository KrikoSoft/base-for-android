package com.example.receptar.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.receptar.dao.BasicDao;

import lombok.AllArgsConstructor;

public class BasicRepository<T> {

    BasicDao<T> basicDao;

    BasicRepository(BasicDao<T> basicDao) {
        this.basicDao = basicDao;
    }

    @SuppressWarnings("unchecked")
    public void insert(final T object) {
        new HandleObjectAsyncTask<T>(new Runnable() {
            @Override
            public void run() {
                basicDao.insert(object);
            }
        }).execute();
    }

    @SuppressWarnings("unchecked")
    public void update(final T object) {
        new HandleObjectAsyncTask<T>(new Runnable() {
            @Override
            public void run() {
                basicDao.update(object);
            }
        }).execute();
    }

    @SuppressWarnings("unchecked")
    public void delete(final T object) {
        new HandleObjectAsyncTask<T>(new Runnable() {
            @Override
            public void run() {
                basicDao.delete(object);
            }
        }).execute();
    }

    @AllArgsConstructor
    protected static class HandleObjectAsyncTask<T> extends AsyncTask<T, Void, Void> {
        private Runnable task;

        @SafeVarargs
        @Override
        protected final Void doInBackground(T... ts) {
            task.run();
            return null;
        }
    }

//    @AllArgsConstructor
//    private static class InsertObjectAsyncTask<T> extends AsyncTask<T, Void, Void> {
//        private BasicDao<T> basicDao;
//
//        @SafeVarargs
//        @Override
//        protected final Void doInBackground(T... objects) {
//            basicDao.insert(objects[0]);
//            return null;
//        }
//    }
//
//    @AllArgsConstructor
//    private static class UpdateObjectAsyncTask<T> extends AsyncTask<T, Void, Void> {
//        private BasicDao<T> basicDao;
//
//        @SafeVarargs
//        @Override
//        protected final Void doInBackground(T... objects) {
//            basicDao.update(objects[0]);
//            return null;
//        }
//    }
//
//    @AllArgsConstructor
//    private static class DeleteObjectAsyncTask<T> extends AsyncTask<T, Void, Void> {
//        private BasicDao<T> userDao;
//
//        @SafeVarargs
//        @Override
//        protected final Void doInBackground(T... objects) {
//            userDao.delete(objects[0]);
//            return null;
//        }
//    }

}
