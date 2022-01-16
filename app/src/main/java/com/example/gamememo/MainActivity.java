package com.example.gamememo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.EdgeEffect;
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
import com.example.gamememo.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Memo memo = new Memo();
    private DB db;
    private List<Memo> list = new ArrayList<>();

    //메모생성시 sort증가를 위한 변수
    public static int listSize;

    private  MemoAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMemo(memo);

        //DB 연결
        db = DB.getInstance(this);

        recyclerviewSetting();
        initRecyclerviewItems();
        initStartActivityResult();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //화면에 있는 아이템 가져와서 sort 정렬순서 초기화 후 DB 저장
        ArrayList<Memo> input = adapter.getItems();
        for(int i=1; i<input.size()+1;i++){
            input.get(i-1).sort = i;
        }
        db.memoDAO().deleteAllMemo();
        db.memoDAO().insertAllMemo(input);
    }

    private void recyclerviewSetting(){
        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.mainRvMemos.setLayoutManager(lm);

        adapter = new MemoAdapter();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        helper.attachToRecyclerView(binding.mainRvMemos);

        //RecyclerView EdgeEffect 끄기
        binding.mainRvMemos.setEdgeEffectFactory(new RecyclerView.EdgeEffectFactory(){
            @NonNull
            @Override
            protected EdgeEffect createEdgeEffect(@NonNull RecyclerView view, int direction) {
                return new EdgeEffect(view.getContext()){
                    @Override
                    public boolean draw(Canvas canvas) { return false; }
                };
            }
        });
    }
    private void initRecyclerviewItems(){
        list = db.memoDAO().selectAllMemo();
        adapter.setItems((ArrayList<Memo>) list);
        binding.mainRvMemos.setAdapter(adapter);
        listSize = list.size();
    }
    private void initStartActivityResult(){
        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            initRecyclerviewItems();
                        }
                        else if(result.getResultCode()==Activity.RESULT_CANCELED){ }
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