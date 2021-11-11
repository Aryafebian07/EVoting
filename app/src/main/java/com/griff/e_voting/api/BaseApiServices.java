package com.griff.e_voting.api;

import com.griff.e_voting.model.ResponseKandidat;
import com.griff.e_voting.model.ResponseLogin;
import com.griff.e_voting.model.ResponsePemilu;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiServices {

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseLogin> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/semuapemilu")
    Call<ResponsePemilu> semuapemilu(
            @Field("jurusan_id") int jurusan_id
    );

    @FormUrlEncoded
    @POST("api/semuakandidat")
    Call<ResponseKandidat> semuakandidat(
            @Field("pemilu_id") int pemilu_id
    );

    @FormUrlEncoded
    @POST("api/votekandidat")
    Call<ResponseKandidat> votekandidat(
            @Field("user_id") int user_id,
            @Field("pemilu_id") int pemilu_id,
            @Field("kandidat_id") int kandidat_id
    );
}
