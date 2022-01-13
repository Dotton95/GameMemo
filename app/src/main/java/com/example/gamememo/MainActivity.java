package com.example.gamememo;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EdgeEffect;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gamememo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Memo memo = new Memo();
    private DB db;
    private List<Memo> list = new ArrayList<>();

    public static int listSize;

    private  MemoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMemo(memo);

        db = DB.getInstance(this);

        //Glide.with(getApplicationContext()).load(get_Favicon).into(binding.iv2);

        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.mainRvMemos.setLayoutManager(lm);

        list = db.memoDAO().selectAllMemo();


        adapter = new MemoAdapter();

        binding.mainRvMemos.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));

        helper.attachToRecyclerView(binding.mainRvMemos);


        adapter.setItems((ArrayList<Memo>) list);

        listSize = list.size();

        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            list = db.memoDAO().selectAllMemo();
                            adapter.setItems((ArrayList<Memo>) list);
                            adapter.notifyDataSetChanged();
                            binding.mainRvMemos.setAdapter(adapter);
                            listSize = list.size();
                            Log.d("ddd", "MainActivity로 돌아왔다. ");

                        }else if(result.getResultCode()==Activity.RESULT_CANCELED){
                            Log.d("ddd", "AddActivity를 취소했다. ");
                        }
                    }
                });

        binding.mainRvMemos.setEdgeEffectFactory(new RecyclerView.EdgeEffectFactory(){
            @NonNull
            @Override
            protected EdgeEffect createEdgeEffect(@NonNull RecyclerView view, int direction) {
                return new EdgeEffect(view.getContext()){
                    @Override
                    public boolean draw(Canvas canvas) {
                        return false;
                    }
                };
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

    @Override
    protected void onStop() {
        super.onStop();
        //int size = binding.mainRvMemos.getAdapter().getItemCount();
        ArrayList<Memo> input = adapter.getItems();

        for(int i=1; i<input.size()+1;i++){
            input.get(i-1).sort = i;
        }

        db.memoDAO().deleteAllMemo();
        db.memoDAO().insertAllMemo(input);
    }

}