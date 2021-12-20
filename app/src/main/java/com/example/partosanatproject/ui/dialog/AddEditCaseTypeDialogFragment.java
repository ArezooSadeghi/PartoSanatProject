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
import com.example.partosanatproject.databinding.FragmentAddEditCaseTypeDialogBinding;
import com.example.partosanatproject.model.CaseTypeResult;
import com.example.partosanatproject.model.ServerData;
import com.example.partosanatproject.utils.PartoSanatPreferences;
import com.example.partosanatproject.viewmodel.CaseTypeViewModel;

import java.util.Objects;

public class AddEditCaseTypeDialogFragment extends DialogFragment {
    private FragmentAddEditCaseTypeDialogBinding binding;
    private CaseTypeViewModel viewModel;

    public static final String TAG = AddEditCaseTypeDialogFragment.class.getSimpleName();

    public static AddEditCaseTypeDialogFragment newInstance() {
        AddEditCaseTypeDialogFragment fragment = new AddEditCaseTypeDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel();
        setupObserver();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                R.layout.fragment_add_edit_case_type_dialog,
                null,
                false);

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

    private void createViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(CaseTypeViewModel.class);
    }

    private void handleEvents() {
        binding.btnClose.setOnClickListener(v -> dismiss());

        binding.btnSave.setOnClickListener(v -> {
            if (Objects.requireNonNull(binding.edTxtCaseType.getText()).toString().isEmpty()) {
                handleError("لطفا نام گروه را وارد کنید");
            } else {
                String caseType = binding.edTxtCaseType.getText().toString();
                boolean notRequiredCustomer = binding.checkBoxNeedRegisterCustomer.isChecked();
                CaseTypeResult.CaseTypeInfo caseTypeInfo = new CaseTypeResult().new CaseTypeInfo();
                caseTypeInfo.setCaseType(caseType);
                caseTypeInfo.setNotRequiredCustomer(notRequiredCustomer);
                addCaseType(caseTypeInfo);
            }
        });
    }

    private void addCaseType(CaseTypeResult.CaseTypeInfo caseTypeInfo) {
        String userLoginKey = PartoSanatPreferences.getUserLoginKey(getContext());
        String centerName = PartoSanatPreferences.getCenterName(getContext());
        ServerData serverData = viewModel.getServerData(centerName);
        viewModel.getPartoSanatServiceCaseTypeResult(serverData.getIpAddress() + ":" + serverData.getPort());
        String path = "/api/v1/CaseType/Add";
        viewModel.addCaseType(path, userLoginKey, caseTypeInfo);
    }

    private void handleError(String msg) {
        ErrorDialogFragment fragment = ErrorDialogFragment.newInstance(msg);
        fragment.show(getParentFragmentManager(), ErrorDialogFragment.TAG);
    }

    private void showSuccessDialog(String msg) {
        SuccessDialogFragment fragment = SuccessDialogFragment.newInstance(msg);
        fragment.show(getParentFragmentManager(), SuccessDialogFragment.TAG);
    }

    private void setupObserver() {
        viewModel.getAddCaseTypeResultSingleLiveEvent().observe(this, caseTypeResult -> {
            if (caseTypeResult != null) {
                if (caseTypeResult.getErrorCode().equals("0")) {
                    showSuccessDialog("گروه با موفقیت ایجاد شد");
                    viewModel.getRefresh().setValue(true);
                } else {
                    handleError(caseTypeResult.getError());
                }
                dismiss();
            }
        });
    }
}