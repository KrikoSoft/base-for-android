package com.example.fitappmobile.java;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "osoby")
@Getter
public class Client {

    @PrimaryKey(autoGenerate = true)
    @Setter
    private int id;
    private String surname;

    public Client(String surname, String name, int age, int height, int weight, int actualWeight, String sex, int workId, String note, double bve, double vefa) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.actualWeight = actualWeight;
        this.sex = sex;
        this.workId = workId;
        this.note = note;
        this.bve = bve;
        this.vefa = vefa;
    }

    private String name;
    private int age;
    private int height;
    private int weight;
    private int actualWeight;
    private String sex;
    private int workId;
    private String note;
    private double bve;
    private double vefa;

    @Override
    public String toString() {
        return surname + " " + name;
    }

}


