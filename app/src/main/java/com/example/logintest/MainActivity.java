package com.example.logintest;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://run.mocky.io/v3/5a2f1b90-ca96-425b-81c8-0c4a40c9a3fc/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = ApiClient.getClient(BASE_URL).create(ApiService.class);

        // 로그인 요청
        LoginRequest loginRequest = new LoginRequest("test", "test1234");

        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getCode() == 200) {
                        Log.d(TAG, "로그인 성공: " + loginResponse.getMessage());
                        Log.d(TAG, "Token: " + loginResponse.getToken());
                    } else {
                        Log.d(TAG, "로그인 실패: " + loginResponse.getMessage());
                    }
                } else {
                    Log.d(TAG, "응답 실패, 코드: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "네트워크 요청 실패: " + t.getMessage(), t);
            }
        });
    }
}

