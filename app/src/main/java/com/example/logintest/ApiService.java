package com.example.logintest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("v3/5a2f1b90-ca96-425b-81c8-0c4a40c9a3fc")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
