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

    private String title;
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
    private int userId;

    public Recipe(String title, int userId) {
        this.title = title;
        this.userId = userId;
    }

}