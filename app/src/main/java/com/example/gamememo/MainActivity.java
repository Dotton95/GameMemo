package com.example.gamememo;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.gamememo.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //private Memo memo = new Memo();
    private DB db;


    private  MemoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //binding.setMemo(memo);

        db = DB.getInstance(this);

        //Glide.with(getApplicationContext()).load(get_Favicon).into(binding.iv2);

        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.mainRvMemos.setLayoutManager(lm);

        ArrayList<Memo> list = new ArrayList<>();
        Memo m1 = new Memo("본계","zxzx1zx","159a159!");
        Memo m2 = new Memo("부계","zxzx2zx","159a159!");
        Memo m3 = new Memo("부부계","zxzx3zx","159a159!");

        list.add(m1);
        list.add(m2);
        list.add(m3);


        adapter = new MemoAdapter();

        binding.mainRvMemos.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));

        helper.attachToRecyclerView(binding.mainRvMemos);

        adapter.setItems(list);


        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Log.d("ddd", "MainActivity로 돌아왔다. ");
                        }else if(result.getResultCode()==Activity.RESULT_CANCELED){
                            Log.d("ddd", "AddActivity를 취소했다. ");
                        }
                    }
                });



        binding.mainIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityResult.launch(intent);
            }
        });
    }
}