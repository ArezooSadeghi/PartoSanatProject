package com.example.partosanatproject.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.partosanatproject.model.CaseTypeResult;
import com.example.partosanatproject.model.ServerData;
import com.example.partosanatproject.model.UserResult;
import com.example.partosanatproject.retrofit.CaseTypeDeserializer;
import com.example.partosanatproject.retrofit.NoConnectivityException;
import com.example.partosanatproject.retrofit.PartoSanatService;
import com.example.partosanatproject.retrofit.RetrofitInstance;
import com.example.partosanatproject.retrofit.UserResultDeserializer;
import com.example.partosanatproject.room.ServerDataDao;
import com.example.partosanatproject.room.ServerDataRoomDatabase;
import com.example.partosanatproject.viewmodel.SingleLiveEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartoSanatRepository {
    public static PartoSanatRepository instance;
    private Context context;
    private PartoSanatService partoSanatService;
    private ServerDataDao serverDataDao;
    private SingleLiveEvent<UserResult> loginResultSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> noConnectionExceptionHappenSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> timeoutExceptionHappenSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> wrongIpAddressSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<CaseTypeResult> caseTypesResultSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<CaseTypeResult> caseTypeInfoResultSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<CaseTypeResult> addCaseTypeResultSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<CaseTypeResult> editCaseTypeResultSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<CaseTypeResult> deleteCaseTypeResultSingleLiveEvent = new SingleLiveEvent<>();
    private LiveData<List<ServerData>> serverDataListMutableLiveData;
    private static final String TAG = PartoSanatRepository.class.getSimpleName();

    public void getPartoSanatServiceUserResult(String baseUrl) {
        RetrofitInstance.getNewBaseUrl(baseUrl);
        partoSanatService = RetrofitInstance
                .getRI(new TypeToken<UserResult>() {
                }.getType(), new UserResultDeserializer(), context).create(PartoSanatService.class);
    }

    public void getPartoSanatServiceCaseTypeResult(String baseUrl) {
        RetrofitInstance.getNewBaseUrl(baseUrl);
        partoSanatService = RetrofitInstance
                .getRI(new TypeToken<CaseTypeResult>() {
                }.getType(), new CaseTypeDeserializer(), context).create(PartoSanatService.class);
    }

    private PartoSanatRepository(Context context) {
        this.context = context.getApplicationContext();
        ServerDataRoomDatabase db = ServerDataRoomDatabase.getDatabase(context);
        serverDataDao = db.serverDataDao();
        serverDataListMutableLiveData = serverDataDao.getServerDataList();
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

    public LiveData<List<ServerData>> getServerDataListMutableLiveData() {
        return serverDataListMutableLiveData;
    }

    public SingleLiveEvent<CaseTypeResult> getCaseTypesResultSingleLiveEvent() {
        return caseTypesResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseTypeResult> getCaseTypeInfoResultSingleLiveEvent() {
        return caseTypeInfoResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseTypeResult> getAddCaseTypeResultSingleLiveEvent() {
        return addCaseTypeResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseTypeResult> getDeleteCaseTypeResultSingleLiveEvent() {
        return deleteCaseTypeResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseTypeResult> getEditCaseTypeResultSingleLiveEvent() {
        return editCaseTypeResultSingleLiveEvent;
    }

    public void login(String path, UserResult.UserLoginParameter parameter) {
        partoSanatService.login(path, parameter).enqueue(new Callback<UserResult>() {

            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (response.isSuccessful()) {
                    loginResultSingleLiveEvent.setValue(response.body());
                } else {
                    try {
                        Gson gson = new Gson();
                        UserResult userResult = gson.fromJson(response.errorBody().string(), UserResult.class);
                        loginResultSingleLiveEvent.setValue(userResult);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
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

    public void fetchCaseTypes(String path, String userLoginKey) {
        partoSanatService.fetchCaseTypes(path, userLoginKey).enqueue(new Callback<CaseTypeResult>() {
            @Override
            public void onResponse(Call<CaseTypeResult> call, Response<CaseTypeResult> response) {
                if (response.isSuccessful()) {
                    caseTypesResultSingleLiveEvent.setValue(response.body());
                } else {
                    try {
                        Gson gson = new Gson();
                        CaseTypeResult caseTypeResult = gson.fromJson(response.errorBody().string(), CaseTypeResult.class);
                        caseTypesResultSingleLiveEvent.setValue(caseTypeResult);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<CaseTypeResult> call, Throwable t) {
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

    public void fetchCaseTypeInfo(String path, String userLoginKey, int caseTypeID) {
        partoSanatService.fetchCaseTypeInfo(path, userLoginKey, caseTypeID).enqueue(new Callback<CaseTypeResult>() {
            @Override
            public void onResponse(Call<CaseTypeResult> call, Response<CaseTypeResult> response) {
                if (response.isSuccessful()) {
                    caseTypeInfoResultSingleLiveEvent.setValue(response.body());
                } else {
                    try {
                        Gson gson = new Gson();
                        CaseTypeResult caseTypeResult = gson.fromJson(response.errorBody().string(), CaseTypeResult.class);
                        caseTypeInfoResultSingleLiveEvent.setValue(caseTypeResult);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<CaseTypeResult> call, Throwable t) {
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

    public void addCaseType(String path, String userLoginKey, CaseTypeResult.CaseTypeInfo caseTypeInfo) {
        partoSanatService.addCaseType(path, userLoginKey, caseTypeInfo).enqueue(new Callback<CaseTypeResult>() {
            @Override
            public void onResponse(Call<CaseTypeResult> call, Response<CaseTypeResult> response) {
                if (response.isSuccessful()) {
                    addCaseTypeResultSingleLiveEvent.setValue(response.body());
                } else {
                    try {
                        Gson gson = new Gson();
                        CaseTypeResult caseTypeResult = gson.fromJson(response.errorBody().string(), CaseTypeResult.class);
                        addCaseTypeResultSingleLiveEvent.setValue(caseTypeResult);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<CaseTypeResult> call, Throwable t) {
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

    public void editCaseType(String path, String userLoginKey, CaseTypeResult.CaseTypeInfo caseTypeInfo) {
        partoSanatService.editCaseType(path, userLoginKey, caseTypeInfo).enqueue(new Callback<CaseTypeResult>() {
            @Override
            public void onResponse(Call<CaseTypeResult> call, Response<CaseTypeResult> response) {
                if (response.isSuccessful()) {
                    editCaseTypeResultSingleLiveEvent.setValue(response.body());
                } else {
                    try {
                        Gson gson = new Gson();
                        CaseTypeResult caseTypeResult = gson.fromJson(response.errorBody().string(), CaseTypeResult.class);
                        editCaseTypeResultSingleLiveEvent.setValue(caseTypeResult);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<CaseTypeResult> call, Throwable t) {
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

    public void deleteCaseType(String path, String userLoginKey, int caseTypeID) {
        partoSanatService.deleteCaseType(path, userLoginKey, caseTypeID).enqueue(new Callback<CaseTypeResult>() {
            @Override
            public void onResponse(Call<CaseTypeResult> call, Response<CaseTypeResult> response) {
                if (response.isSuccessful()) {
                    deleteCaseTypeResultSingleLiveEvent.setValue(response.body());
                } else {
                    try {
                        Gson gson = new Gson();
                        CaseTypeResult caseTypeResult = gson.fromJson(response.errorBody().string(), CaseTypeResult.class);
                        deleteCaseTypeResultSingleLiveEvent.setValue(caseTypeResult);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<CaseTypeResult> call, Throwable t) {
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

    public void insert(ServerData serverData) {
        new insertAsyncTask(serverDataDao).execute(serverData);
    }

    private static class insertAsyncTask extends AsyncTask<ServerData, Void, Void> {

        private ServerDataDao mAsyncTaskDao;

        insertAsyncTask(ServerDataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ServerData... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete(String centerName) {
        new deleteAsyncTask(serverDataDao).execute(centerName);
    }

    private static class deleteAsyncTask extends AsyncTask<String, Void, Void> {

        private ServerDataDao mAsyncTaskDao;

        deleteAsyncTask(ServerDataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public ServerData getServerData(String centerName) {

        try {
            return new getAsyncTask(serverDataDao).execute(centerName).get();
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage());
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    private static class getAsyncTask extends AsyncTask<String, Void, ServerData> {

        private ServerDataDao mAsyncTaskDao;

        getAsyncTask(ServerDataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected ServerData doInBackground(final String... params) {
            return mAsyncTaskDao.getServerData(params[0]);
        }

        @Override
        protected void onPostExecute(ServerData serverData) {
            super.onPostExecute(serverData);
        }
    }
}
