package my.dotton.gamememo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.gamememo.R;
import com.example.gamememo.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    private DB db;
    private int gameCode;
    private String[] gameList = {"선택하기","리그오브레전드","로스트아크","피파온라인4","스팀","블리자드","PC"};

    private boolean idCheck = false;
    private boolean pwdCheck = false;
    private boolean gameCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);

        //addSpinner Setting
        binding.addSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item,gameList));
        binding.addSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    gameCheck = true;
                    gameCode = position;
                }else {
                    gameCheck = false;
                }
                if(!idCheck||!pwdCheck||!gameCheck){
                    binding.addBtnOk.setEnabled(false);
                    binding.addBtnOk.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.gray)));
                }else {
                    binding.addBtnOk.setEnabled(true);
                    binding.addBtnOk.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.very_peri)));
                }
            }
        });

        //addEdt Setting
        binding.addEdtId.addTextChangedListener(addTextWatcher("계정 ID를 입력해주세요","id"));
        binding.addEdtPwd.addTextChangedListener(addTextWatcher("계정 PASSWARD를 입력해주세요","pwd"));


        //addBtn Setting
        binding.addBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    //Custom TextWatcher 중복코드제거
    public TextWatcher addTextWatcher(String errorMessage,String sep){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==0||s.toString().trim().equals("")){
                    if(sep.equals("id")){
                        binding.addLayoutId.setError(errorMessage);
                        idCheck = false;
                    }
                    else if(sep.equals("pwd")) {
                        binding.addLayoutPwd.setError(errorMessage);
                        pwdCheck = false;
                    }
                }else {
                    if(sep.equals("id")) {
                        binding.addLayoutId.setError(null);
                        idCheck = true;
                    }
                    else if(sep.equals("pwd")) {
                        binding.addLayoutPwd.setError(null);
                        pwdCheck = true;
                    }
                }

                if(!idCheck||!pwdCheck||!gameCheck){
                    binding.addBtnOk.setEnabled(false);
                    binding.addBtnOk.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.gray)));
                }else {
                    binding.addBtnOk.setEnabled(true);
                    binding.addBtnOk.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.very_peri)));
                }
            }
        };
    }
}