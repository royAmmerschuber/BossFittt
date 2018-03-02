package com.example.roy.bossfit.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Roy on 28/02/2018.
 */
@Entity
public class Plan {
    @PrimaryKey(autoGenerate = true)
    private long id;
    public long getId() {

        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @ColumnInfo(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ColumnInfo(name="Description")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @ColumnInfo(name="image")
    private String image;
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @ColumnInfo(name="userFK")
    private long userFK;
    public long getUserFK() {
        return userFK;
    }
    public void setUserFK(long userFK) {
        this.userFK = userFK;
    }
}
