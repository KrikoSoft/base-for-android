package com.example.receptar.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.receptar.dao.RecipeDao;
import com.example.receptar.dao.UserDao;
import com.example.receptar.java.Recipe;
import com.example.receptar.java.RecipeComment;
import com.example.receptar.java.User;

import lombok.NonNull;

@androidx.room.Database(entities = {User.class, Recipe.class, RecipeComment.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract RecipeDao recipeDao();

    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "recipe_book")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private RecipeDao recipeDao;

        private PopulateDbAsyncTask(Database db) {
            userDao = db.userDao();
            recipeDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("admin", "admin"));
            return null;
        }
    }

}
