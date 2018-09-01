package com.example.kson.greendaostudy.api;

import com.example.kson.greendaostudy.entity.UserBean;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/01
 * Description:
 */
public interface UserApi {

    @GET("user/login")
    Call<UserBean> login(@Query("mobile") String m, @Query("password") String p);

    @POST("user/login")
    @FormUrlEncoded
    Call<UserBean>  loginPost(@Query("mobile") String m, @Query("password") String p);


}
