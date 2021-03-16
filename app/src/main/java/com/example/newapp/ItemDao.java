package com.example.newapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ItemDao {
    @Query("DELETE FROM item")
    void alldelete();
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Query("SELECT * FROM item WHERE id = :id")
    List<Item> getid(int id);



    @Query("SELECT * FROM item WHERE pri = :i")
    List<Item> thing (int i);


    @Query("SELECT * FROM item WHERE dayweek = :i")
    List<Item> getdayweek (int i);

    @Query("SELECT * FROM item WHERE dayweek = '0' AND year = :y AND month = :m AND day = :d")
    List<Item> getday (int y, int m, int d);


    @Insert
    void insert(Item item);

    @Update
    void update(Item item);

    @Delete
    void deleto(Item item);

}
