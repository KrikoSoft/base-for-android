package com.example.receptar.repository;

import android.os.AsyncTask;

import com.example.receptar.dao.BasicDao;

import java.util.concurrent.atomic.AtomicBoolean;

import lombok.AllArgsConstructor;

public class BasicRepository<T> {

    BasicDao<T> basicDao;

    BasicRepository(BasicDao<T> basicDao) {
        this.basicDao = basicDao;
    }

    @SuppressWarnings("unchecked")
    public void insert(final T object) {
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<T>(new Runnable() {
            @Override
            public void run() {
                basicDao.insert(object);
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
    }

    @SuppressWarnings("unchecked")
    public void update(final T object) {
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<T>(new Runnable() {
            @Override
            public void run() {
                basicDao.update(object);
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
    }

    @SuppressWarnings("unchecked")
    public void delete(final T object) {
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<T>(new Runnable() {
            @Override
            public void run() {
                basicDao.delete(object);
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
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

}
