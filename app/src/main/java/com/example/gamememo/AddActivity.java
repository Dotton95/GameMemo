package com.example.gamememo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.gamememo.databinding.ActivityAddBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    private DB db;
    private int gameCode;
    private String[] gameList = {"선택하기","리그오브레전드","로스트아크","피파온라인4","스팀","블리자드","PC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);

        binding.addSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,gameList));

        binding.addSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gameCode = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        binding.addBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameCode==0||binding.addEdtId.getText()==null||binding.addEdtPwd.getText()==null){
                    Toast.makeText(getApplicationContext(),"종류선택 혹은 입력값을 확인해주세요",Toast.LENGTH_SHORT);
                    return;
                }

                Memo memo = new Memo();
                memo.code = gameCode;
                memo.title = binding.addEdtTitle.getText()==null?"":binding.addEdtTitle.getText().toString();
                memo.id = binding.addEdtId.getText().toString();
                memo.pwd = binding.addEdtPwd.getText().toString();
                memo.pwd2 = binding.addEdtPwd2.getText()==null?"":binding.addEdtPwd2.getText().toString();
                memo.sort = MainActivity.listSize+1;
                db = DB.getInstance(getApplicationContext());
                db.memoDAO().insert(memo);

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