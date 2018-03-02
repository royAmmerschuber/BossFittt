package com.example.roy.bossfit.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Roy on 28/02/2018.
 */
@Dao
public interface DBDAO {
    @Query("select * from user Limit 1")
    User getUsers();
    @Query("select * from `plan` where userFK=:userID")
    List<Plan> getPlans(int userID);

    @Query("select * from exercise where planFK=:planID")
    List<Exercise> getExercises(int planID);

    @Insert
    long insertUser(User... users);
    @Insert
    long insertPlan(Plan... plans);
    @Insert
    long insertExercise(Exercise... exercises);
}
