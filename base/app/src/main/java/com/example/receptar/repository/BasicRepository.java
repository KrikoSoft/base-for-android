package com.example.receptar.repository;

import android.os.AsyncTask;

import com.example.receptar.dao.BasicDao;

import java.util.concurrent.atomic.AtomicBoolean;

import lombok.AllArgsConstructor;

/**
 * base class for repository which contains the dao that the repository is using
 *
 * @param <T> type of entity the dao and repository works with
 */
public class BasicRepository<T> {

    protected BasicDao<T> basicDao;

    protected BasicRepository(BasicDao<T> basicDao) {
        this.basicDao = basicDao;
    }

    @SuppressWarnings("unchecked")
    /**
     * method which calls insert on a table from specific dao
     */
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
    /**
     * method which calls update on a table from specific dao
     */
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
    /**
     * method which calls delete on a table from specific dao
     */
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
    /**
     * static class which creates async communication with database
     */
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
