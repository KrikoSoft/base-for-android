package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.receptar.java.LoginData;
import com.example.receptar.java.Recipe;
import com.example.receptar.java.RecipeComment;
import com.example.receptar.repository.RecipeRepository;

import java.util.List;
import java.util.Map;

/**
 * view model for recipe activity
 */
public class RecipeViewModel extends BasicViewModel<Recipe, RecipeRepository> {

    public RecipeViewModel(@NonNull Application application) {
        super(application, new RecipeRepository(application));
    }

    /**
     * @param recipeId id of recipe
     * @return header of recipe (title + author)
     */
    public String getRecipeHeader(int recipeId) {
        Recipe recipe = repository.getRecipeById(recipeId);
        String userName = repository.getRecipeAuthor(recipe.getUserId());
        return recipe.getTitle() + " od " + userName;
    }

    /**
     * @param recipeId id of recipe
     * @return true if logged user is author of recipe
     */
    public boolean isUserRecipe(int recipeId) {
        return repository.getRecipeById(recipeId).getUserId() == LoginData.getLoggedUserId();
    }

    /**
     * @param recipeId id of recipe
     * @return title of recipe
     */
    public String getRecipeTitle(int recipeId) {
        return repository.getRecipeById(recipeId).getTitle();
    }

    /**
     * @param recipeId id of recipe
     * @return user name of recipe author
     */
    public String getRecipeAuthor(int recipeId) {
        return repository.getRecipeAuthor(repository.getRecipeById(recipeId).getUserId());
    }

    /**
     * @param recipeId id of recipe
     * @return text of the recipe
     */
    public String getRecipeText(int recipeId) {
        Recipe recipe = repository.getRecipeById(recipeId);
        return recipe.getText();
    }

    /**
     * @param recipeId id of the recipe
     * @return recipe with id = recipeId
     */
    public Recipe getRecipe(int recipeId) {
        return repository.getRecipeById(recipeId);
    }

    /**
     * deletes recipe by id
     *
     * @param recipeId if of recipe to be deleted
     */
    public void deleteById(int recipeId) {
        Recipe recipe = repository.getRecipeById(recipeId);
        delete(recipe);
    }

    /**
     * @param recipeId id of the recipe
     * @return list of map entries where the key is the  name of the author of the comment
     * and the value is the text of the comment
     */
    public List<Map.Entry<String, RecipeComment>> getRecipeComments(final int recipeId) {
        return repository.getRecipeComments(recipeId);
    }

    /**
     * method which inserts new comment to the database
     *
     * @param comment  text of the comment
     * @param recipeId id of the recipe
     */
    public void addComment(String comment, int recipeId) {
        repository.addRecipeComment(LoginData.getLoggedUserId(), comment, recipeId);
    }
}
