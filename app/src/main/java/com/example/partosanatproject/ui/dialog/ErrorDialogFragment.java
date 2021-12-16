package com.example.partosanatproject.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.partosanatproject.R;
import com.example.partosanatproject.databinding.FragmentErrorDialogBinding;

public class ErrorDialogFragment extends DialogFragment {
    private FragmentErrorDialogBinding binding;

    private static final String ARGS_MSG = "msg";
    public static final String TAG = ErrorDialogFragment.class.getSimpleName();

    public static ErrorDialogFragment newInstance(String msg) {
        ErrorDialogFragment fragment = new ErrorDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_MSG, msg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                R.layout.fragment_error_dialog,
                null,
                false);

        initViews();
        handleEvents();

        AlertDialog dialog = new AlertDialog
                .Builder(getContext())
                .setView(binding.getRoot())
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    private void initViews() {
        assert getArguments() != null;
        String msg = getArguments().getString(ARGS_MSG);
        binding.txtMsg.setText(msg);
    }

    private void handleEvents() {
        binding.btnClose.setOnClickListener(v -> {
            dismiss();
        });
    }
}