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
    private int id;
    public int getId() {

        return id;
    }
    public void setId(int id) {
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

    @ColumnInfo(name="profileFK")
    private int profileFK;
    public int getProfileFK() {
        return profileFK;
    }
    public void setProfileFK(int profileFK) {
        this.profileFK = profileFK;
    }
}
