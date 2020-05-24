package com.example.receptar.repository;


import android.app.Application;

import com.example.receptar.dao.RecipeDao;
import com.example.receptar.database.Database;
import com.example.receptar.java.Recipe;
import com.example.receptar.java.RecipeComment;
import com.example.receptar.java.User;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RecipeRepository extends BasicRepository<Recipe> {

    public RecipeRepository(Application application) {
        super(Database.getInstance(application).recipeDao());
    }

    public List<Recipe> getFilteredRecipes(final int userId, final String filter) {
        final AtomicReference<List<Recipe>> recipes = new AtomicReference<>();
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<User>(new Runnable() {
            @Override
            public void run() {
                RecipeDao dao = (RecipeDao) basicDao;
                recipes.set(dao.getFilteredUserRecipes(userId, filter));
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
        return recipes.get();
    }

    public Recipe getRecipeById(final int recipeId) {
        final AtomicReference<Recipe> recipe = new AtomicReference<>();
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<Recipe>(new Runnable() {
            @Override
            public void run() {
                recipe.set(((RecipeDao) basicDao).getRecipe(recipeId));
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
        return recipe.get();
    }

    public String getRecipeAuthor(final int recipeId) {
        final AtomicReference<String> owner = new AtomicReference<>();
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<String>(new Runnable() {
            @Override
            public void run() {
                owner.set(((RecipeDao) basicDao).getRecipeOwner(recipeId));
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
        return owner.get();
    }

    public List<Map.Entry<String, RecipeComment>> getRecipeComments(final int recipeId) {
        final AtomicReference<List<RecipeComment>> comments = new AtomicReference<>();
        final AtomicBoolean mutex = new AtomicBoolean(false);
        final RecipeDao dao = (RecipeDao) basicDao;
        new HandleObjectAsyncTask<User>(new Runnable() {
            @Override
            public void run() {
                comments.set(dao.getCommentsForRecipe(recipeId));
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
        mutex.set(false);
        final List<Map.Entry<String, RecipeComment>> map = new ArrayList<>();
        new HandleObjectAsyncTask<User>(new Runnable() {
            @Override
            public void run() {
                for (RecipeComment c : comments.get()) {
                    String author = dao.getCommentAuthor(c.getId());
                    map.add(new AbstractMap.SimpleEntry<String, RecipeComment>(author, c));
                }
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
        return map;
    }

    public void addRecipeComment(final int userId, final String comment, final int recipeId) {
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<User>(new Runnable() {
            @Override
            public void run() {
                ((RecipeDao) basicDao).insertComment(new RecipeComment(recipeId, comment, userId));
                mutex.set(true);
            }
        }).execute();
        while (!mutex.get()) ;
    }

}

