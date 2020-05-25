package com.example.receptar.java;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "recipes")
/**
 * entity class for recipe, which tells the dao the table name and column names of recipe entity in our database
 */
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
    private int userId;
    private String title;
    private String text;

    public Recipe(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

}