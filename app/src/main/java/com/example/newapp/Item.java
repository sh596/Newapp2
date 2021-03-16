package com.example.newapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Item implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "starttime")
    public int starttime;

    @ColumnInfo(name = "stopwatch")
    public int stopwatch;

    @ColumnInfo(name = "pri")
    public int priority;

    @ColumnInfo(name = "complite")
    public int complite;

    @ColumnInfo(name = "dayweek")
    public int dayweek;

    @ColumnInfo(name = "year")
    public int year;

    @ColumnInfo(name = "month")
    public int month;

    @ColumnInfo(name = "day")
    public int day;


    public Item(String title, int starttime, int stopwatch, int priority, int complite, int dayweek, int year, int month, int day) {
        this.id = id;
        this.title = title;
        this.starttime = starttime;
        this.stopwatch = stopwatch;
        this.priority = priority;
        this.complite = complite;
        this.dayweek = dayweek;
        this.year = year;
        this.month = month;
        this.day = day;
    }



}
