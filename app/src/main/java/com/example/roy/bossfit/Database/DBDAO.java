package com.example.roy.bossfit.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Roy on 28/02/2018.
 */
@Dao
public interface DBDAO {
    /**
     * select list based on top elemen
     *
     */
    @Query("select * from user Limit 1")
    User getUsers();
    @Query("select * from `plan` where userFK=:userID")
    List<Plan> getPlans(int userID);
    @Query("select * from `plan` where id=:planID")
    Plan getPlan(int planID);
    @Query("select * from exercise where planFK=:planID")
    List<Exercise> getExercises(int planID);

    /**
     * amount of users
     * @return
     */
    @Query("select count(*) from user")
    int getUserCount();

    /**
     * inserts returns ids
     * @param users
     * @return
     */
    @Insert
    long[] insertUser(User... users);
    @Insert
    long[] insertPlan(Plan... plans);

    @Insert
    long[] insertExercise(Exercise... exercises);
    @Update
    /**
     * plan updater
     */
    void updatePlans(Plan... plan);

    /**
     * deletes exercises of plan
     * @param planID
     */
    @Query("delete from exercise where planFK=:planID")
    void deleteExercises(int planID);

    /**
     * deletes plan of id
     */
    @Query("delete from `Plan` where id=:id")
    void deletePlan(int id);
}
