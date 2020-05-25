package com.example.receptar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.receptar.java.Recipe;
import com.example.receptar.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class BrowseRecipesViewModel extends BasicViewModel<Recipe, RecipeRepository> {

    private MutableLiveData<String> recipesFilter;

    public BrowseRecipesViewModel(@NonNull Application application) {
        super(application, new RecipeRepository(application));
        recipesFilter = new MutableLiveData<>("%");
    }

    public List<Recipe> getFilteredRecipes(String newString) {
        recipesFilter.setValue("%" + newString + "%");
        List<Recipe> recipes = repository.getFilteredRecipes(recipesFilter.getValue());
        return recipes == null ? new ArrayList<Recipe>() : recipes;
    }

}
