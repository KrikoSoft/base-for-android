package com.example.receptar.java;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "recipes")
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