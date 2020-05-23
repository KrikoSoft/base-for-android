package com.example.receptar.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.receptar.java.Recipe;

import java.util.List;

@Dao
public interface RecipeDao extends BasicDao<Recipe> {

    @Query("SELECT * FROM recipes WHERE userId = :userId AND title LIKE :content ORDER BY id")
    List<Recipe> getFilteredUserRecipes(int userId, String content);

}
