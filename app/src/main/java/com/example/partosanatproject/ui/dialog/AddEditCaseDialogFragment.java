package com.example.partosanatproject.ui.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.partosanatproject.R;
import com.example.partosanatproject.databinding.FragmentAddEditCaseDialogBinding;
import com.example.partosanatproject.viewmodel.CaseViewModel;

public class AddEditCaseDialogFragment extends DialogFragment {
    private FragmentAddEditCaseDialogBinding binding;
    private CaseViewModel caseViewModel;

    public static final String TAG = AddEditCaseDialogFragment.class.getSimpleName();

    public static AddEditCaseDialogFragment newInstance() {
        AddEditCaseDialogFragment fragment = new AddEditCaseDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                R.layout.fragment_add_edit_case_dialog,
                null,
                false);

        AlertDialog dialog = new AlertDialog
                .Builder(getContext())
                .setView(binding.getRoot())
                .create();

        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    private void createViewModel() {
        caseViewModel = new ViewModelProvider(requireActivity()).get(CaseViewModel.class);
    }
}