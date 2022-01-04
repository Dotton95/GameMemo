package com.example.gamememo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.os.Bundle;

import com.example.gamememo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Memo memo = new Memo();
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMemo(memo);

        db = DB.getInstance(this);

    }
}