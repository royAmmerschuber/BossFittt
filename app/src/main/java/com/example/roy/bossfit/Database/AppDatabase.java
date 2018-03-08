package com.example.roy.bossfit.Database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Roy on 28/02/2018.
 * Database based on room
 */
@android.arch.persistence.room.Database(entities={User.class,Plan.class,Exercise.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
            if(INSTANCE.DBDao().getUserCount()>0){
                seed(INSTANCE);
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    //Non-Static----------------------------------------------------------------
    public abstract DBDAO DBDao();

    private static void seed(AppDatabase db){
        User u=new User();
        u.setName("max muster");
        DBDAO dao=db.DBDao();
        dao.insertUser(u);
    }
}
