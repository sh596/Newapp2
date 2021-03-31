package com.example.newapp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Item.class}, version = 1)
public abstract class RDatabase extends RoomDatabase {
    private static RDatabase INSTANCE;
    public abstract ItemDao itemDao();



    public static RDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, RDatabase.class , "todo-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return  INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
