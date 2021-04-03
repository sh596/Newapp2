package com.example.newapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Item implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")//제목
    public String title;

    @ColumnInfo(name = "stopwatch")//시간 기록
    public int stopwatch;

    @ColumnInfo(name = "starttime")//시간 기록
    public int starttime;

    @ColumnInfo(name = "pri")//우선순위
    public int priority;

    @ColumnInfo(name = "complete")//완료여부
    public int complete;

    @ColumnInfo(name = "dayweek")//요일
    public int dayweek;

    @ColumnInfo(name = "year")//년도
    public int year;

    @ColumnInfo(name = "month")//달
    public int month;

    @ColumnInfo(name = "day")//일
    public int day;


    public Item(String title, int stopwatch, int starttime, int priority, int complete, int dayweek, int year, int month, int day) {
        this.id = id;
        this.title = title;
        this.stopwatch = stopwatch;
        this.starttime = starttime;
        this.priority = priority;
        this.complete = complete;
        this.dayweek = dayweek;
        this.year = year;
        this.month = month;
        this.day = day;
    }



}
