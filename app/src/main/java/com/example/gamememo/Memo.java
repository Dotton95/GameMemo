package com.example.gamememo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "memo")
public class Memo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int no;

    @ColumnInfo(name = "code")
    private int code;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "pwd")
    private String pwd;

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }
}
