package com.example.receptar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.receptar.R;
import com.example.receptar.java.LoginData;
import com.example.receptar.java.Recipe;

import java.util.Objects;

public class AddRecipeActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_STEPS = "EXTRA_STEPS";

    private EditText editTextTitle;
    private EditText editTextRecipeSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        editTextTitle = findViewById(R.id.edit_text_recipe_title);
        editTextRecipeSteps = findViewById(R.id.edit_text_recipe_steps);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.icon_close);
    }

    private void saveRecipe() {
        String recipeTitle = editTextTitle.getText().toString();
        if (recipeTitle.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Recept nemá zadaný názov!", Toast.LENGTH_LONG).show();
            return;
        }
        String recipeSteps = editTextRecipeSteps.getText().toString();
        if (recipeSteps.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Recept nemá zadaný postup!", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, recipeTitle);
        intent.putExtra(EXTRA_STEPS, recipeSteps);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_client_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_client) {
            saveRecipe();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
