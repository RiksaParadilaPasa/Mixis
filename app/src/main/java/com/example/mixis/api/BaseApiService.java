package com.example.mixis.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface BaseApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest (@Field("name") String name,
                                        @Field("username") String username,
                                        @Field("password") String password,
                                        @Field("confirm_password") String confirm_password);

    @FormUrlEncoded
    @POST("user/update")
    Call<ResponseBody> ProfileUpdateRequest(@Header("Authorization") String token,
                                            @Header("Accept") String header,
                                            @Field("name") String name,
                                            @Field("no_telp") String no_telp,
                                            @Field("tempat_lahir") String tempat_lahir,
                                            @Field("tanggal_lahir") String tanggal_lahir,
                                            @Field("email") String email,
                                            @Field("password") String password);

    @GET("artikel")
    Call<ResponseBody> ArtikelRequest(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("artikel/tambah")
    Call<ResponseBody> AddArtikelRequest(@Header("Authorization") String token,
                                            @Header("Accept") String header,
                                            @Field("title") String judul,
                                            @Field("description") String deskripsi,
                                            @Field("image") String image);

    @POST("artikel")
    Call<ResponseBody> DeleteArtikelRequest(
                                            @Header("Authorization") String token);

}
