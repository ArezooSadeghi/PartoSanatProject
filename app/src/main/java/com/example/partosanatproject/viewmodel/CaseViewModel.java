package com.example.partosanatproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.partosanatproject.model.CaseResult;
import com.example.partosanatproject.model.ServerData;
import com.example.partosanatproject.repository.PartoSanatRepository;

public class CaseViewModel extends AndroidViewModel {
    private PartoSanatRepository repository;
    private SingleLiveEvent<CaseResult> addCaseResultSingleLiveEvent;
    private SingleLiveEvent<CaseResult> editCaseResultSingleLiveEvent;
    private SingleLiveEvent<CaseResult> casesResultSingleLiveEvent;
    private SingleLiveEvent<CaseResult> caseInfoResultSingleLiveEvent;

    public CaseViewModel(@NonNull Application application) {
        super(application);
        repository = PartoSanatRepository.getInstance(getApplication());
        addCaseResultSingleLiveEvent = repository.getAddCaseResultSingleLiveEvent();
        editCaseResultSingleLiveEvent = repository.getEditCaseResultSingleLiveEvent();
        casesResultSingleLiveEvent = repository.getCasesResultSingleLiveEvent();
        caseInfoResultSingleLiveEvent = repository.getCaseInfoResultSingleLiveEvent();
    }

    public SingleLiveEvent<CaseResult> getAddCaseResultSingleLiveEvent() {
        return addCaseResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseResult> getEditCaseResultSingleLiveEvent() {
        return editCaseResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseResult> getCasesResultSingleLiveEvent() {
        return casesResultSingleLiveEvent;
    }

    public SingleLiveEvent<CaseResult> getCaseInfoResultSingleLiveEvent() {
        return caseInfoResultSingleLiveEvent;
    }

    public ServerData getServerData(String centerName) {
        return repository.getServerData(centerName);
    }

    public void getPartoSanatServiceCaseResult(String baseUrl) {
        repository.getPartoSanatServiceCaseResult(baseUrl);
    }

    public void addCase(String path, String userLoginKey, CaseResult.CaseInfo caseInfo) {
        repository.addCase(path, userLoginKey, caseInfo);
    }

    public void editCase(String path, String userLoginKey, CaseResult.CaseInfo caseInfo) {
        repository.editCase(path, userLoginKey, caseInfo);
    }

    public void fetchCases(String path, String userLoginKey, int caseTypeID, String search, boolean showAll) {
        repository.fetchCases(path, userLoginKey, caseTypeID, search, showAll);
    }

    public void fetchCaseInfo(String path, String userLoginKey, int caseID) {
        repository.fetchCaseInfo(path, userLoginKey, caseID);
    }
}
