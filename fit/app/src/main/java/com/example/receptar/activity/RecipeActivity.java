package com.example.receptar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.receptar.R;
import com.example.receptar.java.LoginData;
import com.example.receptar.java.Recipe;
import com.example.receptar.viewmodel.RecipeViewModel;

import static com.example.receptar.adapter.RecipeAdapter.EXTRA_RECIPE_ID;

public class RecipeActivity extends BasicActivity<RecipeViewModel> {

    public static final int REQUEST_EDIT_RECIPE = 1;

    public static final String EXTRA_REQUEST_CODE = "EXTRA_REQUEST_CODE";
    private int recipeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        recipeId = getIntent().getIntExtra(EXTRA_RECIPE_ID, 0);

        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        updateContent();
    }

    private void updateContent() {
        setTitle(viewModel.getRecipeHeader(recipeId));

        TextView recipeTextView = findViewById(R.id.text_view_recipe_text);
        recipeTextView.setText(viewModel.getRecipeText(recipeId));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_recipe) {
            editRecipe();
            return true;
        } else if (item.getItemId() == R.id.delete_recipe) {
            deleteRecipe();
            finish();
            Toast.makeText(getApplicationContext(), "Recept odstránený!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void editRecipe() {
        Intent intent = new Intent(getApplicationContext(), EditRecipeActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_EDIT_RECIPE);
        startActivityForResult(intent, REQUEST_EDIT_RECIPE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_EDIT_RECIPE && resultCode == RESULT_OK) {
            String title = intent.getStringExtra(EditRecipeActivity.EXTRA_TITLE);
            String steps = intent.getStringExtra(EditRecipeActivity.EXTRA_STEPS);
            int recipeId = intent.getIntExtra(EXTRA_RECIPE_ID, 0);
            Recipe updatedRecipe = new Recipe(LoginData.getLoggedUserId(), title, steps);
            updatedRecipe.setId(recipeId);
            viewModel.update(updatedRecipe);
            Toast.makeText(getApplicationContext(), "Recept uložený!", Toast.LENGTH_SHORT).show();
            updateContent();
        }
    }

    public void deleteRecipe() {
        viewModel.deleteById(recipeId);
    }

}
