package com.example.receptar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receptar.R;
import com.example.receptar.activity.RecipeActivity;
import com.example.receptar.java.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    public static final String EXTRA_RECIPE_ID = "EXTRA_RECIPE_ID";

    private List<Recipe> recipes = new ArrayList<>();
    private Context context;

    public RecipeAdapter(Context context) {
        super();
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe current = recipes.get(position);
        holder.textViewRecipeTitle.setText(current.getTitle());
        holder.recipeId = current.getId();
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    static class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView textViewRecipeTitle;
        private int recipeId;

        RecipeHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            textViewRecipeTitle = itemView.findViewById(R.id.text_view_recipe_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RecipeActivity.class);
                    intent.putExtra(EXTRA_RECIPE_ID, recipeId);
                    context.startActivity(intent);
                }
            });
        }
    }

}
