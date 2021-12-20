package com.example.partosanatproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.partosanatproject.R;
import com.example.partosanatproject.databinding.FragmentLoginBinding;
import com.example.partosanatproject.model.ServerData;
import com.example.partosanatproject.model.UserResult;
import com.example.partosanatproject.ui.activity.MenuContainerActivity;
import com.example.partosanatproject.ui.activity.ServerDataContainerActivity;
import com.example.partosanatproject.ui.dialog.ErrorDialogFragment;
import com.example.partosanatproject.ui.dialog.WarningDialogFragment;
import com.example.partosanatproject.utils.PartoSanatPreferences;
import com.example.partosanatproject.viewmodel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;
    private String centerName;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login,
                container,
                false);

        handleEvents();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupObserver();
    }

    private void createViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void setupSpinner(List<ServerData> serverDataList) {
        List<String> centerNameList = new ArrayList<>();
        for (ServerData serverData : serverDataList) {
            centerNameList.add(serverData.getCenterName());
        }
        binding.spinner.setItems(centerNameList);
        if (centerNameList.size() > 0) {
            centerName = centerNameList.get(0);
        }
    }

    private void handleError(String msg) {
        ErrorDialogFragment fragment = ErrorDialogFragment.newInstance(msg);
        fragment.show(getParentFragmentManager(), ErrorDialogFragment.TAG);
    }

    private void handleEvents() {
        binding.ivMore.setOnClickListener(view -> {
            Intent starter = ServerDataContainerActivity.start(getContext());
            startActivity(starter);
        });

        binding.edTextUserName.setOnEditorActionListener((textView, actionID, keyEvent) -> {
            if (actionID == 0 || actionID == EditorInfo.IME_ACTION_DONE) {
                binding.edTextPassword.requestFocus();
            }
            return false;
        });

        binding.btnLogin.setOnClickListener(view -> {
            if (Objects.requireNonNull(binding.edTextUserName.getText()).toString().isEmpty() || Objects.requireNonNull(binding.edTextPassword.getText()).toString().isEmpty()) {
                handleError(getString(R.string.fill_required_fields));
            } else {
                binding.progressBarLoading.setVisibility(View.VISIBLE);
                binding.edTextUserName.setEnabled(false);
                binding.edTextPassword.setEnabled(false);
                binding.btnLogin.setEnabled(false);
                binding.ivMore.setEnabled(false);

                String userName = binding.edTextUserName.getText().toString();
                String password = binding.edTextPassword.getText().toString();

                int version = 14000925;
                UserResult.UserLoginParameter parameter = new UserResult().new UserLoginParameter(userName, password, version);
                login(parameter);
            }
        });

        binding.spinner.setOnItemSelectedListener((view, position, id, item) -> centerName = (String) item);
    }

    private void login(UserResult.UserLoginParameter parameter) {
        ServerData serverData = viewModel.getServerData(centerName);
        viewModel.getPartoSanatServiceUserResult(serverData.getIpAddress() + ":" + serverData.getPort());
        String path = "/api/v1/users/Login";
        viewModel.login(path, parameter);
    }

    private void setupObserver() {
        viewModel.getLoginResultSingleLiveEvent().observe(getViewLifecycleOwner(), userResult -> {
            if (userResult != null) {
                if (userResult.getErrorCode().equals("0")) {
                    PartoSanatPreferences.setUserLoginKey(getContext(), userResult.getUsers()[0].getUserLoginKey());
                    PartoSanatPreferences.setCenterName(getContext(), centerName);
                    Intent starter = MenuContainerActivity.start(getContext());
                    startActivity(starter);
                    getActivity().finish();
                } else {
                    binding.progressBarLoading.setVisibility(View.GONE);
                    binding.edTextUserName.setEnabled(true);
                    binding.edTextPassword.setEnabled(true);
                    binding.btnLogin.setEnabled(true);
                    binding.ivMore.setEnabled(true);
                    handleError(userResult.getError());
                }
            }
        });

        viewModel.getNoConnectionExceptionHappenSingleLiveEvent().observe(getViewLifecycleOwner(), msg -> {
            binding.progressBarLoading.setVisibility(View.GONE);
            binding.edTextUserName.setEnabled(true);
            binding.edTextPassword.setEnabled(true);
            binding.btnLogin.setEnabled(true);
            binding.ivMore.setEnabled(true);
            handleError(msg);
        });

        viewModel.getTimeoutExceptionHappenSingleLiveEvent().observe(getViewLifecycleOwner(), msg -> {
            binding.progressBarLoading.setVisibility(View.GONE);
            binding.edTextUserName.setEnabled(true);
            binding.edTextPassword.setEnabled(true);
            binding.btnLogin.setEnabled(true);
            binding.ivMore.setEnabled(true);
            handleError(msg);
        });

        viewModel.getWrongIpAddressSingleLiveEvent().observe(getViewLifecycleOwner(), msg -> {
            binding.progressBarLoading.setVisibility(View.GONE);
            binding.edTextUserName.setEnabled(true);
            binding.edTextPassword.setEnabled(true);
            binding.btnLogin.setEnabled(true);
            binding.ivMore.setEnabled(true);
            handleError(msg);
        });

        viewModel.getServerDataListMutableLiveData().observe(getViewLifecycleOwner(), serverDataList -> {
            setupSpinner(serverDataList);
            if (serverDataList == null || serverDataList.size() == 0) {
                WarningDialogFragment fragment = WarningDialogFragment.newInstance(getString(R.string.required_ip));
                fragment.show(getParentFragmentManager(), WarningDialogFragment.TAG);
            }
        });
    }
}