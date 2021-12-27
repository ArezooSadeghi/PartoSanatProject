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
    private String userLoginKey, centerName;
    private ServerData serverData;
    private int caseTypeID, parentID;

    private static final String ARGS_CASE_TYPE_ID = "caseTypeID";
    private static final String ARGS_PARENT_ID = "parentID";
    public static final String TAG = AddEditCaseTypeDialogFragment.class.getSimpleName();

    public static AddEditCaseTypeDialogFragment newInstance(int caseTypeID, int parentID) {
        AddEditCaseTypeDialogFragment fragment = new AddEditCaseTypeDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_CASE_TYPE_ID, caseTypeID);
        args.putInt(ARGS_PARENT_ID, parentID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel();
        initVariables();
        if (caseTypeID > 0)
            fetchCaseTypeInfo(caseTypeID);
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

        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    private void createViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(CaseTypeViewModel.class);
    }

    private void initVariables() {
        userLoginKey = PartoSanatPreferences.getUserLoginKey(getContext());
        centerName = PartoSanatPreferences.getCenterName(getContext());
        serverData = viewModel.getServerData(centerName);
        caseTypeID = getArguments().getInt(ARGS_CASE_TYPE_ID);
        parentID = getArguments().getInt(ARGS_PARENT_ID);
    }

    private void handleEvents() {
        binding.btnClose.setOnClickListener(v -> dismiss());

        binding.btnSave.setOnClickListener(v -> {
            if (Objects.requireNonNull(binding.edTxtCaseType.getText()).toString().isEmpty())
                handleError(getString(R.string.fill_case_type_name));
            else {
                String caseType = binding.edTxtCaseType.getText().toString();
                boolean notRequiredCustomer = binding.checkBoxNeedRegisterCustomer.isChecked();
                CaseTypeResult.CaseTypeInfo caseTypeInfo = new CaseTypeResult().new CaseTypeInfo();
                caseTypeInfo.setCaseType(caseType);
                caseTypeInfo.setNotRequiredCustomer(notRequiredCustomer);
                if (caseTypeID > 0) {
                    caseTypeInfo.setCaseTypeID(caseTypeID);
                    editCaseType(caseTypeInfo);
                } else {
                    caseTypeInfo.setParentID(parentID);
                    addCaseType(caseTypeInfo);
                }
            }
        });
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

        viewModel.getEditCaseTypeResultSingleLiveEvent().observe(this, caseTypeResult -> {
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

        viewModel.getCaseTypeInfoResultSingleLiveEvent().observe(this, caseTypeResult -> {
            if (caseTypeResult != null) {
                if (caseTypeResult.getErrorCode().equals("0")) {
                    if (caseTypeResult.getCaseTypes().length != 0) {
                        binding.edTxtCaseType.setText(caseTypeResult.getCaseTypes()[0].getCaseType());
                        binding.checkBoxNeedRegisterCustomer.setChecked(caseTypeResult.getCaseTypes()[0].isNotRequiredCustomer());
                    }
                } else {
                    handleError(caseTypeResult.getError());
                }
            }
        });
    }

    private void fetchCaseTypeInfo(int caseTypeID) {
        serverData = viewModel.getServerData(centerName);
        viewModel.getPartoSanatServiceCaseTypeResult(serverData.getIpAddress() + ":" + serverData.getPort());
        String path = "/api/v1/CaseType/Info";
        viewModel.fetchCaseTypeInfo(path, userLoginKey, caseTypeID);
    }

    private void addCaseType(CaseTypeResult.CaseTypeInfo caseTypeInfo) {
        viewModel.getPartoSanatServiceCaseTypeResult(serverData.getIpAddress() + ":" + serverData.getPort());
        String path = "/api/v1/CaseType/Add";
        viewModel.addCaseType(path, userLoginKey, caseTypeInfo);
    }

    private void editCaseType(CaseTypeResult.CaseTypeInfo caseTypeInfo) {
        viewModel.getPartoSanatServiceCaseTypeResult(serverData.getIpAddress() + ":" + serverData.getPort());
        String path = "/api/v1/CaseType/Edit";
        viewModel.editCaseType(path, userLoginKey, caseTypeInfo);
    }

    private void handleError(String msg) {
        ErrorDialogFragment fragment = ErrorDialogFragment.newInstance(msg);
        fragment.show(getParentFragmentManager(), ErrorDialogFragment.TAG);
    }

    private void showSuccessDialog(String msg) {
        SuccessDialogFragment fragment = SuccessDialogFragment.newInstance(msg);
        fragment.show(getParentFragmentManager(), SuccessDialogFragment.TAG);
    }
}