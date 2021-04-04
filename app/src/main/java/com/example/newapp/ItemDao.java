package com.example.newapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface ItemDao {

    //특정 요일에 맞는 데이터을 구하는 쿼리
    @Query("SELECT * FROM item WHERE dayweek = :i AND pri = :p")
    List<Item> getdayweek (int i,int p);
    //완료여부를 판단하여 특정 요일에 맞는 데이터을 구하는 쿼리
    @Query("SELECT * FROM item WHERE dayweek = :i AND pri = :p AND complete = :c")
    List<Item> getdayweekcom (int i, int p, int c);
    //완료여부를 판단하여 특정 날짜에 맞는 데이터을 구하는 쿼리
    @Query("SELECT * FROM item WHERE dayweek = '0' AND year = :y AND month = :m AND day = :d AND pri = :i AND complete = :c")
    List<Item> getdaycom (int y, int m, int d, int i, int c);
    //특정 날짜에 맞는 데이터을 구하는 쿼리
    @Query("SELECT * FROM item WHERE dayweek = '0' AND year = :y AND month = :m AND day = :d AND pri = :i ")
    List<Item> getday (int y, int m, int d, int i);
    @Query("SELECT * FROM item WHERE starttime = NOT 0")
    List<Item> getstarttime ();
    //특정 데이터 삭제
    @Query("DELETE FROM item WHERE id = :id")
    void deleteid(int id);
    //완료여부 변경
    @Query("UPDATE item SET complete = :com WHERE id = :id")
    void updatecom(int com, int id);
    //시간 측정
    @Query("UPDATE item SET stopwatch = :stop WHERE id = :id")
    void updatestop(int stop, int id);

    //추가
    @Insert
    void insert(Item item);

}
