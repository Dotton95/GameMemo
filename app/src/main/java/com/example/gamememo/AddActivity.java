package com.example.gamememo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.gamememo.databinding.ActivityAddBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        ArrayList<String> gameList = new ArrayList<>(Arrays.asList(
                new String[]{"선택하기","리그오브레전드","로스트아크","피파온라인4","스팀","블리자드","PC"}
        ));

        binding.addSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,gameList));

        binding.addSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        binding.addBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
        binding.addBtnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }
}