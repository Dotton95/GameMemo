package com.example.gamememo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.gamememo.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
    }
}