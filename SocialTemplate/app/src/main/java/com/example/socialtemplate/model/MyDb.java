package com.example.socialtemplate.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {ProfileDetail.class, PersonPost.class},version = 1,exportSchema = false)
public abstract class MyDb extends RoomDatabase {
    private static MyDb INSTANCE;

    public abstract MyDao myDao();
    private static Object sLock = new Object();
    public static MyDb getInstance(Context cntx){
        synchronized (sLock){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(cntx.getApplicationContext(),MyDb.class, "social.db").build();
            }
            return  INSTANCE;
        }
    }
}
