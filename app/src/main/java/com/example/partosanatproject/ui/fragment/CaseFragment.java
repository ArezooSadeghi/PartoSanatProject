package com.example.partosanatproject.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.partosanatproject.R;
import com.example.partosanatproject.databinding.FragmentCaseBinding;
import com.example.partosanatproject.viewmodel.CaseViewModel;

public class CaseFragment extends Fragment {
    private FragmentCaseBinding binding;
    private CaseViewModel viewModel;

    public static CaseFragment newInstance() {
        CaseFragment fragment = new CaseFragment();
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
                R.layout.fragment_case,
                container,
                false);
        return binding.getRoot();
    }

    private void createViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(CaseViewModel.class);
    }
}