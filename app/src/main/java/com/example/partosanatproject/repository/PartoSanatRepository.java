package com.example.partosanatproject.repository;

import android.content.Context;
import android.util.Log;

import com.example.partosanatproject.model.UserResult;
import com.example.partosanatproject.retrofit.NoConnectivityException;
import com.example.partosanatproject.retrofit.PartoSanatService;
import com.example.partosanatproject.retrofit.RetrofitInstance;
import com.example.partosanatproject.retrofit.UserResultDeserializer;
import com.example.partosanatproject.viewmodel.SingleLiveEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartoSanatRepository {
    public static PartoSanatRepository instance;
    private Context context;
    private PartoSanatService partoSanatService;

    private static final String TAG = PartoSanatRepository.class.getSimpleName();

    private SingleLiveEvent<UserResult> loginResultSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> noConnectionExceptionHappenSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> timeoutExceptionHappenSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> wrongIpAddressSingleLiveEvent = new SingleLiveEvent<>();

    public void getPartoSanatServiceUserResult(String baseUrl) {
        RetrofitInstance.getNewBaseUrl(baseUrl);
        partoSanatService = RetrofitInstance
                .getRI(new TypeToken<UserResult>() {
                }.getType(), new UserResultDeserializer(), context).create(PartoSanatService.class);
    }

    private PartoSanatRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public static PartoSanatRepository getInstance(Context context) {
        if (instance == null) {
            instance = new PartoSanatRepository(context.getApplicationContext());
        }
        return instance;
    }

    public SingleLiveEvent<UserResult> getLoginResultSingleLiveEvent() {
        return loginResultSingleLiveEvent;
    }

    public SingleLiveEvent<String> getNoConnectionExceptionHappenSingleLiveEvent() {
        return noConnectionExceptionHappenSingleLiveEvent;
    }

    public SingleLiveEvent<String> getTimeoutExceptionHappenSingleLiveEvent() {
        return timeoutExceptionHappenSingleLiveEvent;
    }

    public SingleLiveEvent<String> getWrongIpAddressSingleLiveEvent() {
        return wrongIpAddressSingleLiveEvent;
    }

    public void login(String path, UserResult.UserLoginParameter parameter) {
        Log.d("Arezoo", "Login");
        partoSanatService.login(path, parameter).enqueue(new Callback<UserResult>() {

            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                Log.d("Arezoo", "onResponse");
                if (response.isSuccessful()) {
                    Log.d("Arezoo", "isSuccessful");
                    loginResultSingleLiveEvent.setValue(response.body());
                } else {
                    Log.d("Arezoo", "else");
                    try {
                        Gson gson = new Gson();
                        UserResult userResult = gson.fromJson(response.errorBody().string(), UserResult.class);
                        loginResultSingleLiveEvent.setValue(userResult);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                        Log.d("Arezoo", "msg: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                if (t instanceof NoConnectivityException) {
                    noConnectionExceptionHappenSingleLiveEvent.setValue(t.getMessage());
                } else if (t instanceof SocketTimeoutException) {
                    timeoutExceptionHappenSingleLiveEvent.setValue("اتصال به اینترنت با خطا مواجه شد");
                } else {
                    wrongIpAddressSingleLiveEvent.setValue("سرور موجود نمی باشد");
                }
            }
        });
    }
}
