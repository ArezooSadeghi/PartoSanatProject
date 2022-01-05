package com.example.partosanatproject.retrofit;

import com.example.partosanatproject.model.CaseResult;
import com.example.partosanatproject.model.CaseTypeResult;
import com.example.partosanatproject.model.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PartoSanatService {

    @POST("{path}")
    Call<UserResult> login(@Path("path") String path, @Body UserResult.UserLoginParameter parameter);

    @POST("{path}")
    Call<CaseTypeResult> addCaseType(@Path("path") String path, @Header("userLoginKey") String userLoginKey, @Body CaseTypeResult.CaseTypeInfo caseTypeInfo);

    @PUT("{path}")
    Call<CaseTypeResult> editCaseType(@Path("path") String path, @Header("userLoginKey") String userLoginKey, @Body CaseTypeResult.CaseTypeInfo caseTypeInfo);

    @DELETE("{path}")
    Call<CaseTypeResult> deleteCaseType(@Path("path") String path, @Header("userLoginKey") String userLoginKey, @Query("caseTypeID") int caseTypeID);

    @GET("{path}")
    Call<CaseTypeResult> fetchCaseTypes(@Path("path") String path, @Header("userLoginKey") String userLoginKey);

    @GET("{path}")
    Call<CaseTypeResult> fetchCaseTypeInfo(@Path("path") String path, @Header("userLoginKey") String userLoginKey, @Query("caseTypeID") int caseTypeID);

    @POST("{path}")
    Call<CaseResult> addCase(@Path("path") String path, @Header("userLoginKey") String userLoginKey, @Body CaseResult.CaseInfo caseInfo);

    @PUT("{path}")
    Call<CaseResult> editCase(@Path("path") String path, @Header("userLoginKey") String userLoginKey, @Body CaseResult.CaseInfo caseInfo);

    @GET("{path}")
    Call<CaseResult> fetchCases(@Path("path") String path, @Header("userLoginKey") String userLoginKey, @Query("caseTypeID") int caseTypeID, @Query("search") String search, @Query("showAll") boolean showAll);

    @GET("{path}")
    Call<CaseResult> fetchCaseInfo(@Path("path") String path, @Header("userLoginKey") String userLoginKey, @Query("caseID") int caseID);
}
