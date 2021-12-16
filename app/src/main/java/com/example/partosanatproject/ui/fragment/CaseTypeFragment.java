package com.example.partosanatproject.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.partosanatproject.R;
import com.example.partosanatproject.databinding.FragmentCaseTypeBinding;

public class CaseTypeFragment extends Fragment {
    private FragmentCaseTypeBinding binding;

    public static CaseTypeFragment newInstance() {
        CaseTypeFragment fragment = new CaseTypeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_case_type,
                container,
                false);
        return binding.getRoot();
    }
}