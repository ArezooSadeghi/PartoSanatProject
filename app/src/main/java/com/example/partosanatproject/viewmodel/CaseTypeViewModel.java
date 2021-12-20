package com.example.partosanatproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.partosanatproject.model.CaseTypeResult;
import com.example.partosanatproject.model.ServerData;
import com.example.partosanatproject.repository.PartoSanatRepository;

public class CaseTypeViewModel extends AndroidViewModel {
    private PartoSanatRepository repository;
    private SingleLiveEvent<CaseTypeResult> caseTypesResultSingleLiveEvent;
    private SingleLiveEvent<CaseTypeResult> addCaseTypeResultSingleLiveEvent;
    private SingleLiveEvent<CaseTypeResult> deleteCaseTypeResultSingleLiveEvent;
    private SingleLiveEvent<Boolean> refresh = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> deleteClicked = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> editClicked = new SingleLiveEvent<>();

    public CaseTypeViewModel(@NonNull Application application) {
        super(application);
        repository = PartoSanatRepository.getInstance(getApplication());
        caseTypesResultSingleLiveEvent = repository.getCaseTypesResultSingleLiveEvent();
        addCaseTypeResultSingleLiveEvent = repository.getAddCaseTypeResultSingleLiveEvent();
        deleteCaseTypeResultSingleLiveEvent = repository.getDeleteCaseTypeResultSingleLiveEvent();
    }

    public SingleLiveEvent<CaseTypeResult> getCaseTypesResultSingleLiveEvent() {
        return caseTypesResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseTypeResult> getAddCaseTypeResultSingleLiveEvent() {
        return addCaseTypeResultSingleLiveEvent;
    }

    public SingleLiveEvent<Boolean> getRefresh() {
        return refresh;
    }

    public SingleLiveEvent<Integer> getDeleteClicked() {
        return deleteClicked;
    }

    public SingleLiveEvent<Integer> getEditClicked() {
        return editClicked;
    }

    public SingleLiveEvent<CaseTypeResult> getDeleteCaseTypeResultSingleLiveEvent() {
        return deleteCaseTypeResultSingleLiveEvent;
    }

    public void getPartoSanatServiceCaseTypeResult(String baseUrl) {
        repository.getPartoSanatServiceCaseTypeResult(baseUrl);
    }

    public void fetchCaseTypes(String path, String userLoginKey) {
        repository.fetchCaseTypes(path, userLoginKey);
    }

    public void addCaseType(String path, String userLoginKey, CaseTypeResult.CaseTypeInfo caseTypeInfo) {
        repository.addCaseType(path, userLoginKey, caseTypeInfo);
    }

    public void deleteCaseType(String path, String userLoginKey, int caseTypeID) {
        repository.deleteCaseType(path, userLoginKey, caseTypeID);
    }

    public ServerData getServerData(String centerName) {
        return repository.getServerData(centerName);
    }
}
