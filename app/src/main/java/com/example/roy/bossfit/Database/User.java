package com.example.roy.bossfit.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Roy on 28/02/2018.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id=id;
    }

    @ColumnInfo(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
