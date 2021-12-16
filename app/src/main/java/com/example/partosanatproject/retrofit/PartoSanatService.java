package com.example.partosanatproject.retrofit;

import com.example.partosanatproject.model.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PartoSanatService {

    @POST("{path}")
    Call<UserResult> login(@Path("path") String path, @Body UserResult.UserLoginParameter parameter);
}
