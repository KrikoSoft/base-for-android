package com.example.receptar.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.receptar.R;
import com.example.receptar.viewmodel.RecipeViewModel;

import static com.example.receptar.adapter.RecipeAdapter.EXTRA_RECIPE_ID;

public class RecipeActivity extends BasicActivity<RecipeViewModel> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        int recipeId = getIntent().getIntExtra(EXTRA_RECIPE_ID, 0);

        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        setTitle(viewModel.getRecipeHeader(recipeId));
    }

}
