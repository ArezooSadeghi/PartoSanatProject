package com.example.partosanatproject.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.partosanatproject.R;
import com.example.partosanatproject.adapter.ServerDataAdapter;
import com.example.partosanatproject.databinding.FragmentServerDataBinding;
import com.example.partosanatproject.model.ServerData;
import com.example.partosanatproject.ui.dialog.AddEditServerDataDialogFragment;
import com.example.partosanatproject.viewmodel.LoginViewModel;

import java.util.List;

public class ServerDataFragment extends Fragment {
    private FragmentServerDataBinding binding;
    private LoginViewModel viewModel;

    public static ServerDataFragment newInstance() {
        ServerDataFragment fragment = new ServerDataFragment();
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
                R.layout.fragment_server_data,
                container,
                false);

        initViews();
        handleEvents();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupObserver();
    }

    private void createViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
    }

    private void initViews() {
        binding.recyclerViewServerData.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewServerData.addItemDecoration(new DividerItemDecoration(binding.recyclerViewServerData.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void setupAdapter(List<ServerData> serverDataList) {
        ServerDataAdapter adapter = new ServerDataAdapter(getContext(), serverDataList, viewModel);
        binding.recyclerViewServerData.setAdapter(adapter);
    }

    private void handleEvents() {
        binding.fabAdd.setOnClickListener(v -> {
            AddEditServerDataDialogFragment fragment = AddEditServerDataDialogFragment.newInstance("", "", "", true);
            fragment.show(getChildFragmentManager(), AddEditServerDataDialogFragment.TAG);
        });
    }

    private void setupObserver() {
        viewModel.getDeleteClicked().observe(getViewLifecycleOwner(), serverData -> viewModel.delete(serverData.getCenterName()));

        viewModel.getEditClicked().observe(getViewLifecycleOwner(), serverData -> {
            AddEditServerDataDialogFragment fragment = AddEditServerDataDialogFragment.newInstance(serverData.getCenterName(), serverData.getIpAddress(), serverData.getPort(), false);
            fragment.show(getChildFragmentManager(), AddEditServerDataDialogFragment.TAG);
        });

        viewModel.getServerDataListMutableLiveData().observe(getViewLifecycleOwner(), serverDataList -> {
            setupAdapter(serverDataList);
            if (serverDataList == null || serverDataList.size() == 0) {
                getActivity().finish();
            }
        });
    }
}