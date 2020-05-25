package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.receptar.java.LoginData;
import com.example.receptar.java.Recipe;
import com.example.receptar.java.RecipeComment;
import com.example.receptar.repository.RecipeRepository;

import java.util.List;
import java.util.Map;

public class RecipeViewModel extends BasicViewModel<Recipe, RecipeRepository> {

    public RecipeViewModel(@NonNull Application application) {
        super(application, new RecipeRepository(application));
    }

    public String getRecipeHeader(int recipeId) {
        Recipe recipe = repository.getRecipeById(recipeId);
        String userName = repository.getRecipeAuthor(recipe.getUserId());
        return recipe.getTitle() + " od " + userName;
    }

    public boolean isUserRecipe(int recipeId) {
        return repository.getRecipeById(recipeId).getUserId() == LoginData.getLoggedUserId();
    }

    public String getRecipeTitle(int recipeId) {
        return repository.getRecipeById(recipeId).getTitle();
    }

    public String getRecipeAuthor(int recipedId) {
        return repository.getRecipeAuthor(repository.getRecipeById(recipedId).getUserId());
    }

    public String getRecipeText(int recipeId) {
        Recipe recipe = repository.getRecipeById(recipeId);
        return recipe.getText();
    }

    public Recipe getRecipe(int recipeId) {
        return repository.getRecipeById(recipeId);
    }

    public void deleteById(int recipeId) {
        Recipe recipe = repository.getRecipeById(recipeId);
        delete(recipe);
    }

    public List<Map.Entry<String, RecipeComment>> getRecipeComments(final int recipeId) {
        return repository.getRecipeComments(recipeId);
    }

    public void addComment(String comment, int recipeId) {
        repository.addRecipeComment(LoginData.getLoggedUserId(), comment, recipeId);
    }
}
