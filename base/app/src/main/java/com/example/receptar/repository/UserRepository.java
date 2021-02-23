package com.example.receptar.repository;

import android.app.Application;

import com.example.receptar.dao.UserDao;
import com.example.receptar.database.Database;
import com.example.receptar.java.User;
import com.example.receptar.utils.DbUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * repository for class for manipulation with user entities
 */
public class UserRepository extends BasicRepository<User> {

    public UserRepository(Application application) {
        super(Database.getInstance(application).userDao());
    }

    /**
     * @param name     name of the user
     * @param password user password
     * @return already registered user with the name and the password if one exists,
     * null if the user does not exist or password and username don't match
     */
    public User login(final String name, final String password) {
        final AtomicReference<User> user = new AtomicReference<>();
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<User>(new Runnable() {
            @Override
            public void run() {
                user.set(((UserDao) basicDao).login(name, DbUtils.passwordEncrypt(password)));
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
        return user.get();
    }

    /**
     * registers a user to the app and logs them in
     *
     * @param name     name of the user
     * @param password user password
     * @return newly registered user with disred name and password,
     * null if the user already exists
     */
    public User register(final String name, final String password) {
        final AtomicReference<User> user = new AtomicReference<>();
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<User>(new Runnable() {
            @Override
            public void run() {
                UserDao dao = (UserDao) basicDao;
                if (dao.userCount(name) == 0) {
                    dao.insert(new User(name, password));
                    user.set(dao.login(name, DbUtils.passwordEncrypt(password)));
                }
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
        return user.get();
    }

}
