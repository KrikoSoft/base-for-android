package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.receptar.java.LoginData;
import com.example.receptar.java.Recipe;
import com.example.receptar.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRecipesViewModel extends BasicViewModel<Recipe, RecipeRepository> {

    private MutableLiveData<String> recipesFilter;

    public UserRecipesViewModel(@NonNull Application application) {
        super(application, new RecipeRepository(application));
        recipesFilter = new MutableLiveData<>("%");
    }

    public List<Recipe> getFilteredRecipes(String newString) {
        recipesFilter.setValue("%" + newString + "%");
        List<Recipe> recipes = repository.getFilteredRecipes(LoginData.getLoggedUserId(), recipesFilter.getValue());
        return recipes == null ? new ArrayList<Recipe>() : recipes;
    }

}
