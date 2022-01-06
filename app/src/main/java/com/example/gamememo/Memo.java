package com.example.gamememo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "memo")
public class Memo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int no;

    @ColumnInfo(name = "sort")
    public int sort;

    @ColumnInfo(name = "code")
    public int code;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "pwd")
    public String pwd;

    public Memo(String title, String id, String pwd) {
        this.title = title;
        this.id = id;
        this.pwd = pwd;
    }
}
