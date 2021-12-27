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
    private SingleLiveEvent<CaseTypeResult> caseTypeInfoResultSingleLiveEvent;
    private SingleLiveEvent<CaseTypeResult> addCaseTypeResultSingleLiveEvent;
    private SingleLiveEvent<CaseTypeResult> editCaseTypeResultSingleLiveEvent;
    private SingleLiveEvent<CaseTypeResult> deleteCaseTypeResultSingleLiveEvent;
    private SingleLiveEvent<String> noConnectionExceptionHappenSingleLiveEvent;
    private SingleLiveEvent<String> timeoutExceptionHappenSingleLiveEvent;
    private SingleLiveEvent<Boolean> refresh = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> deleteClicked = new SingleLiveEvent<>();
    private SingleLiveEvent<Boolean> yesDeleteClicked = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> editClicked = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> addSubGroupClicked = new SingleLiveEvent<>();

    public CaseTypeViewModel(@NonNull Application application) {
        super(application);
        repository = PartoSanatRepository.getInstance(getApplication());
        caseTypesResultSingleLiveEvent = repository.getCaseTypesResultSingleLiveEvent();
        caseTypeInfoResultSingleLiveEvent = repository.getCaseTypeInfoResultSingleLiveEvent();
        addCaseTypeResultSingleLiveEvent = repository.getAddCaseTypeResultSingleLiveEvent();
        editCaseTypeResultSingleLiveEvent = repository.getEditCaseTypeResultSingleLiveEvent();
        deleteCaseTypeResultSingleLiveEvent = repository.getDeleteCaseTypeResultSingleLiveEvent();
        noConnectionExceptionHappenSingleLiveEvent = repository.getNoConnectionExceptionHappenSingleLiveEvent();
        timeoutExceptionHappenSingleLiveEvent = repository.getTimeoutExceptionHappenSingleLiveEvent();
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

    public SingleLiveEvent<CaseTypeResult> getEditCaseTypeResultSingleLiveEvent() {
        return editCaseTypeResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseTypeResult> getDeleteCaseTypeResultSingleLiveEvent() {
        return deleteCaseTypeResultSingleLiveEvent;
    }

    public SingleLiveEvent<String> getNoConnectionExceptionHappenSingleLiveEvent() {
        return noConnectionExceptionHappenSingleLiveEvent;
    }

    public SingleLiveEvent<String> getTimeoutExceptionHappenSingleLiveEvent() {
        return timeoutExceptionHappenSingleLiveEvent;
    }

    public SingleLiveEvent<Boolean> getRefresh() {
        return refresh;
    }

    public SingleLiveEvent<Integer> getDeleteClicked() {
        return deleteClicked;
    }

    public SingleLiveEvent<Boolean> getYesDeleteClicked() {
        return yesDeleteClicked;
    }

    public SingleLiveEvent<Integer> getEditClicked() {
        return editClicked;
    }

    public SingleLiveEvent<Integer> getAddSubGroupClicked() {
        return addSubGroupClicked;
    }

    public void getPartoSanatServiceCaseTypeResult(String baseUrl) {
        repository.getPartoSanatServiceCaseTypeResult(baseUrl);
    }

    public void fetchCaseTypes(String path, String userLoginKey) {
        repository.fetchCaseTypes(path, userLoginKey);
    }

    public void fetchCaseTypeInfo(String path, String userLoginKey, int caseTypeID) {
        repository.fetchCaseTypeInfo(path, userLoginKey, caseTypeID);
    }

    public void addCaseType(String path, String userLoginKey, CaseTypeResult.CaseTypeInfo caseTypeInfo) {
        repository.addCaseType(path, userLoginKey, caseTypeInfo);
    }

    public void editCaseType(String path, String userLoginKey, CaseTypeResult.CaseTypeInfo caseTypeInfo) {
        repository.editCaseType(path, userLoginKey, caseTypeInfo);
    }

    public void deleteCaseType(String path, String userLoginKey, int caseTypeID) {
        repository.deleteCaseType(path, userLoginKey, caseTypeID);
    }

    public ServerData getServerData(String centerName) {
        return repository.getServerData(centerName);
    }
}
