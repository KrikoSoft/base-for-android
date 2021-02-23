package com.example.receptar.java;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "comments")
/**
 * entity class for comment, which tells the dao the table name and column names of comment entity in our database
 */
public class RecipeComment {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Recipe.class, parentColumns = "id", childColumns = "recipeId")
    private int recipeId;
    private String commentText;
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "authorId")
    private int authorId;

    public RecipeComment(int recipeId, String commentText, int authorId) {
        this.recipeId = recipeId;
        this.commentText = commentText;
        this.authorId = authorId;
    }

}