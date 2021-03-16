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

    static final Migration MIGRATION_1_2 = new Migration(1, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };
    static final Migration MIGRATION_2_3 = new Migration(1, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE new_item(" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "title TEXT NOT NULL DEFAULT ''," +
                    "starttime INTEGER NOT NULL," +
                    "stopwatch INTEGER NOT NULL," +
                    "pri INTEGER NOT NULL," +
                    "complite INTEGER NOT NULL," +
                    "dayweek INTEGER NOT NULL," +
                    "year INTEGER NOT NULL," +
                    "month INTEGER NOT NULL," +
                    "day INTEGER NOT NULL)");
            database.execSQL("INSERT INTO new_item (id, title, starttime, pri, complite,dayweek,year,month,day)" +
                    "SELECT id, title, starttime, pri, 'check',dayweek,year,month,day FROM item");
            database.execSQL("DROP TABLE item");
            database.execSQL("ALTER TABLE new_item RENAME TO item");
        }
    };




    public static RDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, RDatabase.class , "todo-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return  INSTANCE;
    }

    //디비객체제거
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
