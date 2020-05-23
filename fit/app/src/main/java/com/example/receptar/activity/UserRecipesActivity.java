package com.example.receptar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receptar.R;
import com.example.receptar.adapter.RecipeAdapter;
import com.example.receptar.java.LoginData;
import com.example.receptar.java.Recipe;
import com.example.receptar.viewmodel.UserRecipesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class UserRecipesActivity extends BasicActivity<UserRecipesViewModel> {

    public static final int REQUEST_ADD_RECIPE = 1;
    private RecipeAdapter adapter;
    private String filter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recipes);

        RecyclerView recyclerView = findViewById(R.id.recipes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new RecipeAdapter(getApplicationContext());
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
                filter = charSequence.toString();
                filterRecipes();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });

        FloatingActionButton addRecipeButton = findViewById(R.id.button_add_recipe);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), EditRecipeActivity.class), REQUEST_ADD_RECIPE);
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.icon_close);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_ADD_RECIPE && resultCode == RESULT_OK) {
            String title = intent.getStringExtra(EditRecipeActivity.EXTRA_TITLE);
            String steps = intent.getStringExtra(EditRecipeActivity.EXTRA_STEPS);
            viewModel.insert(new Recipe(LoginData.getLoggedUserId(), title, steps));
            filterRecipes();
            Toast.makeText(getApplicationContext(), "Recept uložený!", Toast.LENGTH_SHORT).show();
        }
    }

    private void filterRecipes() {
        adapter.setRecipes(viewModel.getFilteredRecipes(filter));
    }

}
