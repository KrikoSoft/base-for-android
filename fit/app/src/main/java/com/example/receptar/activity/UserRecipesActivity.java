package com.example.receptar.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receptar.R;
import com.example.receptar.adapter.RecipeAdapter;
import com.example.receptar.java.LoginData;
import com.example.receptar.java.Recipe;
import com.example.receptar.viewmodel.UserRecipesViewModel;

public class UserRecipesActivity extends BasicActivity<UserRecipesViewModel> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recipes);

        RecyclerView recyclerView = findViewById(R.id.recipes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RecipeAdapter adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(UserRecipesViewModel.class);
        adapter.setRecipes(viewModel.getFilteredRecipes(""));
        EditText editText = findViewById(R.id.edit_text_recipe_filter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // we change filter
                filterRecipes(adapter, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });
    }

    private void filterRecipes(RecipeAdapter adapter, String filter) {
        adapter.setRecipes(viewModel.getFilteredRecipes(filter));
    }

}
