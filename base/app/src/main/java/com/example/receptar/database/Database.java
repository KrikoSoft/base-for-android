package com.example.receptar.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.receptar.dao.RecipeDao;
import com.example.receptar.dao.UserDao;
import com.example.receptar.java.Recipe;
import com.example.receptar.java.RecipeComment;
import com.example.receptar.java.User;

@androidx.room.Database(entities = {User.class, Recipe.class, RecipeComment.class}, version = 1, exportSchema = false)
/**
 * database class which contains an instance of database and with which we can access the dao
 */
public abstract class Database extends RoomDatabase {

    private static Database instance;

    /**
     * method to get the instance of database. if instance is null we create new
     *
     * @param context apllication context
     * @return the database instance
     */
    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "recipe_book")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    public abstract RecipeDao recipeDao();

}
