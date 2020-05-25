package com.example.receptar.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receptar.R;
import com.example.receptar.adapter.RecipeAdapter;
import com.example.receptar.viewmodel.BrowseRecipesViewModel;

public class BrowseRecipesFragment extends BasicFragment<BrowseRecipesViewModel> {
    private RecipeAdapter adapter;
    private String filter;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_recipes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.browse_recipes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        viewModel = ViewModelProviders.of(this).get(BrowseRecipesViewModel.class);
        adapter = new RecipeAdapter(getContext(), viewModel.getRepository(), false);
        recyclerView.setAdapter(adapter);

        adapter.setRecipes(viewModel.getFilteredRecipes(""));
        EditText editText = view.findViewById(R.id.edit_text_browse_recipe_filter);
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

        return view;
    }

    private void filterRecipes() {
        adapter.setRecipes(viewModel.getFilteredRecipes(filter));
    }
}

