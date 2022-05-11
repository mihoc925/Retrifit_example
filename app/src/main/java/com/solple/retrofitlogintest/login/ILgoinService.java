package com.solple.retrofitlogintest.login;

import com.solple.retrofitlogintest.vo.MemberVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ILgoinService {

    @GET("signup/")
    Call<MemberVO> getMember(
            @Query("UserID") String UserID,
            @Query("password") String password,
            @Query("type") String type);
}