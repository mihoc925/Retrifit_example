package com.solple.retrofitlogintest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.solple.retrofitlogintest.login.ILgoinService;
import com.solple.retrofitlogintest.login.TestLogin;
import com.solple.retrofitlogintest.vo.MemberVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editTextID ,editTextPW;    // 아이디, 비밀번호 입력창
    private Button btnLogin;                    // 로그인 버튼
    private Retrofit retrofit;                    // 웹서버와 통신할 Retrofit

    private RadioGroup radio_group;
    String checked = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Retrofit Login");

        setRetrofitInit();  // Retrofit 초기화

        editTextID = findViewById(R.id.editText_id);
        editTextPW = findViewById(R.id.editText_pw);
        btnLogin = findViewById(R.id.btn_login);
        radio_group = findViewById(R.id.radio_group);



        radio_group.setOnCheckedChangeListener((radioGroup, i) -> {
            checked = i + "";
            Log.e("TAG", "onCreate: " + checked);
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();    // 버튼 클릭시, 로그인을 진행합니다.
            }
        });
    }


    // todo        http://13.125.248.219:8080/sign/signup/?UserID=1111&password=1111&type=1

    private void setRetrofitInit(){
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://13.125.248.219:8080/sign/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void login(){
        String id = editTextID.getText().toString();
        String pw = editTextPW.getText().toString();
        String type = checked;

//        ILgoinService service = retrofit.create(ILgoinService.class);
        TestLogin service = retrofit.create(TestLogin.class);

        Call<String> call = service.getMember(id, pw, type);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("TAG", "onResponse: "+response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다!", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t);
            }
        });

        // JSon 형태로 받을때 ====================================================================

//        Call<MemberVO> call = service.getMember(id, pw, type);

//        call.enqueue(new Callback<MemberVO>() {
//            @Override
//            public void onResponse(Call<MemberVO> call, Response<MemberVO> response) {
//
//                MemberVO memberVO = response.body();    // 웹서버로부터 응답받은 데이터가 들어있다.
//
//                Log.e("TAG", "onResponse: " +response.toString());
//
//                if(memberVO != null){       // 회원입니다.
//                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//                    intent.putExtra("memberVO", memberVO);
//                    startActivity(intent);
//                }else{                      // 회원이 아닙니다.
//                    Toast.makeText(getApplicationContext(), "회원이 아닙니다!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MemberVO> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다!", Toast.LENGTH_SHORT).show();
//                Log.e("TAG", "onFailure: " + t);
//            }
//        });
    }
}