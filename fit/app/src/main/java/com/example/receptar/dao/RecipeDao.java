package com.example.receptar.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.receptar.java.Recipe;
import com.example.receptar.java.RecipeComment;

import java.util.List;

@Dao
public interface RecipeDao extends BasicDao<Recipe> {

    @Query("SELECT * FROM recipes WHERE userId = :userId AND title LIKE :content ORDER BY id")
    List<Recipe> getFilteredUserRecipes(int userId, String content);

    @Query("SELECT * FROM recipes  WHERE id = :recipeId")
    Recipe getRecipe(int recipeId);

    @Query("SELECT userName FROM users u JOIN recipes r ON r.userId = u.id WHERE r.id = :recipeId")
    String getRecipeOwner(int recipeId);

    @Query("SELECT userName FROM users u JOIN comments c ON u.id = c.authorId WHERE c.id = :commentId")
    String getCommentAuthor(int commentId);

    @Query("SELECT * FROM comments WHERE recipeId = :recipeId ORDER BY id")
    List<RecipeComment> getCommentsForRecipe(int recipeId);

    @Insert
    void insertComment(RecipeComment comment);

}
