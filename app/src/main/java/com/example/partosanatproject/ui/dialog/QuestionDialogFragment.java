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
import com.example.partosanatproject.databinding.FragmentQuestionDialogBinding;
import com.example.partosanatproject.viewmodel.CaseTypeViewModel;

public class QuestionDialogFragment extends DialogFragment {
    private FragmentQuestionDialogBinding binding;
    private CaseTypeViewModel viewModel;

    private static final String ARGS_MSG = "msg";
    public static final String TAG = QuestionDialogFragment.class.getSimpleName();

    public static QuestionDialogFragment newInstance(String msg) {
        QuestionDialogFragment fragment = new QuestionDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_MSG, msg);
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
                R.layout.fragment_question_dialog,
                null,
                false);

        initViews();
        handleEvents();

        AlertDialog dialog = new AlertDialog
                .Builder(requireContext())
                .setView(binding.getRoot())
                .create();

        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    private void createViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(CaseTypeViewModel.class);
    }

    private void initViews() {
        assert getArguments() != null;
        String msg = getArguments().getString(ARGS_MSG);
        binding.txtMsg.setText(msg);
    }

    private void handleEvents() {
        binding.btnNo.setOnClickListener(v -> dismiss());

        binding.btnYes.setOnClickListener(v -> {
            viewModel.getYesDeleteClicked().setValue(true);
            dismiss();
        });
    }
}