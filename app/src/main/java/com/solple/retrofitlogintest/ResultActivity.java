package com.solple.retrofitlogintest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.solple.retrofitlogintest.vo.MemberVO;

public class ResultActivity extends AppCompatActivity {

    private TextView textView_result;       // 결과

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Result");

        textView_result = findViewById(R.id.textView_result);

        Intent intent = getIntent();        // 로그인 시, 넘겨받은 파라미터를 받는다.
        MemberVO memberVO = intent.getParcelableExtra("memberVO");

        textView_result.setText(memberVO.getUserID() + "님! 반갑습니다!");
    }

}