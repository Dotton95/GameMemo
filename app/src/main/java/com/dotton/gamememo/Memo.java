package com.dotton.gamememo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "memo")
public class Memo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int no=0;

    @ColumnInfo(name = "sort")
    public int sort=0;

    @ColumnInfo(name = "code")
    public int code=0;

    @ColumnInfo(name = "title")
    public String title="";

    @ColumnInfo(name = "id")
    public String id="";

    @ColumnInfo(name = "pwd")
    public String pwd="";

    @ColumnInfo(name = "pwd2")
    public String pwd2="";
}
