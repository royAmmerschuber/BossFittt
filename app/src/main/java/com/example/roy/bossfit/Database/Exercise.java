package com.example.roy.bossfit.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Roy on 28/02/2018.
 */
@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @ColumnInfo(name="name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {

        this.name = name;
    }

    @ColumnInfo(name="sets")
    private int sets;
    public int getSets() {
        return sets;
    }
    public void setSets(int sets) {
        this.sets = sets;
    }

    @ColumnInfo(name="repetitions")
    private int repetitions;
    public int getRepetitions() {
        return repetitions;
    }
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    @ColumnInfo(name="weight")
    private float weight;
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @ColumnInfo(name = "planFK")
    private long planFK;
    public long getPlanFK() {
        return planFK;
    }
    public void setPlanFK(long planFK) {
        this.planFK = planFK;
    }

    @ColumnInfo(name="image")
    private String image;
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
