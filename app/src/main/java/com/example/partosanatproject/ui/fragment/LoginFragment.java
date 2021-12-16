package com.example.partosanatproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.partosanatproject.R;
import com.example.partosanatproject.databinding.FragmentLoginBinding;
import com.example.partosanatproject.model.UserResult;
import com.example.partosanatproject.ui.activity.MenuContainerActivity;
import com.example.partosanatproject.ui.dialog.ErrorDialogFragment;
import com.example.partosanatproject.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

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

    private void handleEvents() {
        binding.btnLogin.setOnClickListener(v -> {
            binding.loadingLayout.setVisibility(View.VISIBLE);
            binding.btnLogin.setEnabled(false);
            String userName = binding.edTextUserName.getText().toString();
            String password = binding.edTextPassword.getText().toString();
            UserResult.UserLoginParameter parameter = new UserResult().new UserLoginParameter(userName, password, 14000925);
            viewModel.getPartoSanatServiceUserResult("192.168.3.252:8008");
            String path = "/api/v1/users/Login";
            viewModel.login(path, parameter);
        });
    }

    private void handleError(String msg) {
        ErrorDialogFragment fragment = ErrorDialogFragment.newInstance(msg);
        fragment.show(getParentFragmentManager(), ErrorDialogFragment.TAG);
    }

    private void setupObserver() {
        viewModel.getLoginResultSingleLiveEvent().observe(getViewLifecycleOwner(), new Observer<UserResult>() {
            @Override
            public void onChanged(UserResult userResult) {
                binding.loadingLayout.setVisibility(View.GONE);
                binding.btnLogin.setEnabled(true);
                if (userResult != null) {
                    if (userResult.getErrorCode().equals("0")) {
                        Intent starter = MenuContainerActivity.start(getContext());
                        startActivity(starter);
                    } else {
                        handleError(userResult.getError());
                    }
                }
            }
        });
    }
}