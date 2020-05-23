package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.receptar.java.Recipe;
import com.example.receptar.repository.RecipeRepository;

public class RecipeViewModel extends BasicViewModel<Recipe, RecipeRepository> {

    public RecipeViewModel(@NonNull Application application) {
        super(application, new RecipeRepository(application));
    }

    public String getRecipeHeader(int recipeId) {
        Recipe recipe = repository.getRecipeById(recipeId);
        String userName = repository.getRecipeOwner(recipeId);
        return recipe.getTitle() + " od " + userName;
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
}
