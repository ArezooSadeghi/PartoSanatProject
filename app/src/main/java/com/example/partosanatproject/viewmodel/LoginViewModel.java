package com.example.partosanatproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.partosanatproject.model.ServerData;
import com.example.partosanatproject.model.UserResult;
import com.example.partosanatproject.repository.PartoSanatRepository;

public class LoginViewModel extends AndroidViewModel {
    private PartoSanatRepository repository;
    private SingleLiveEvent<UserResult> loginResultSingleLiveEvent;
    private SingleLiveEvent<String> noConnectionExceptionHappenSingleLiveEvent;
    private SingleLiveEvent<String> timeoutExceptionHappenSingleLiveEvent;
    private SingleLiveEvent<String> wrongIpAddressSingleLiveEvent;

    private SingleLiveEvent<ServerData> editClicked = new SingleLiveEvent<>();
    private SingleLiveEvent<ServerData> deleteClicked = new SingleLiveEvent<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = PartoSanatRepository.getInstance(getApplication());
        loginResultSingleLiveEvent = repository.getLoginResultSingleLiveEvent();
        noConnectionExceptionHappenSingleLiveEvent = repository.getNoConnectionExceptionHappenSingleLiveEvent();
        timeoutExceptionHappenSingleLiveEvent = repository.getTimeoutExceptionHappenSingleLiveEvent();
        wrongIpAddressSingleLiveEvent = repository.getWrongIpAddressSingleLiveEvent();
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

    public SingleLiveEvent<ServerData> getEditClicked() {
        return editClicked;
    }

    public SingleLiveEvent<ServerData> getDeleteClicked() {
        return deleteClicked;
    }

    public void login(String path, UserResult.UserLoginParameter parameter) {
        repository.login(path, parameter);
    }

    public void getPartoSanatServiceUserResult(String baseUrl) {
        repository.getPartoSanatServiceUserResult(baseUrl);
    }

    public void insert(ServerData serverData) {
        repository.insert(serverData);
    }

    public void delete(String centerName) {
        repository.delete(centerName);
    }

    public ServerData getServerData(String centerName) {
        return repository.getServerData(centerName);
    }


}
