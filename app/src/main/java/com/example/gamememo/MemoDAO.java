package com.example.gamememo;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemoDAO {

    @Insert(onConflict = REPLACE)
    void insert(Memo memo);

    @Query("SELECT * FROM memo group by code")
    List<Memo> selectGroupAll();

    @Query("UPDATE memo SET title = :title, code = :code,id = :id, pwd = :pwd  WHERE `no` = :no")
    void update(int no, int code,String title, String id, String pwd);

    @Delete
    void delete(Memo memo);
}
