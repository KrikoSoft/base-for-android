package com.example.receptar.java;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "comments")
public class RecipeComment {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Recipe.class, parentColumns = "id", childColumns = "recipeId")
    private int recipeId;
    private String commentText;

    public RecipeComment(int recipeId, String commentText) {
        this.recipeId = recipeId;
        this.commentText = commentText;
    }

}