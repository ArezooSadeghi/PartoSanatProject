package com.example.partosanatproject.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partosanatproject.R;
import com.example.partosanatproject.adapter.DirectoryNodeBinder;
import com.example.partosanatproject.adapter.FileNodeBinder;
import com.example.partosanatproject.databinding.FragmentCaseTypeBinding;
import com.example.partosanatproject.model.CaseTypeResult;
import com.example.partosanatproject.model.Dir;
import com.example.partosanatproject.model.ServerData;
import com.example.partosanatproject.ui.dialog.AddEditCaseTypeDialogFragment;
import com.example.partosanatproject.ui.dialog.ErrorDialogFragment;
import com.example.partosanatproject.ui.dialog.SuccessDialogFragment;
import com.example.partosanatproject.utils.Converter;
import com.example.partosanatproject.utils.PartoSanatPreferences;
import com.example.partosanatproject.viewmodel.CaseTypeViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;

public class CaseTypeFragment extends Fragment {
    private FragmentCaseTypeBinding binding;
    private CaseTypeViewModel viewModel;
    private List<TreeNode> treeNodeList;
    private List<CaseTypeResult.CaseTypeInfo> caseTypeInfoList;
    private TreeViewAdapter adapter;

    public static CaseTypeFragment newInstance() {
        CaseTypeFragment fragment = new CaseTypeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel();
        initVariables();
        fetchCaseTypes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_case_type,
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
        viewModel = new ViewModelProvider(requireActivity()).get(CaseTypeViewModel.class);
    }

    private void initVariables() {
        treeNodeList = new ArrayList<>();
        caseTypeInfoList = new ArrayList<>();
    }

    private void fetchCaseTypes() {
        String userLoginKey = PartoSanatPreferences.getUserLoginKey(getContext());
        String centerName = PartoSanatPreferences.getCenterName(getContext());
        ServerData serverData = viewModel.getServerData(centerName);
        viewModel.getPartoSanatServiceCaseTypeResult(serverData.getIpAddress() + ":" + serverData.getPort());
        String path = "/api/v1/CaseType/List";
        viewModel.fetchCaseTypes(path, userLoginKey);
    }

    private void initViews() {
        binding.recyclerViewCaseType.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupAdapter() {
        adapter = new TreeViewAdapter(treeNodeList, Arrays.asList(new FileNodeBinder(), new DirectoryNodeBinder(getContext(), viewModel)));
        binding.recyclerViewCaseType.setAdapter(adapter);
    }

    private void handleEvents() {
        binding.fabAdd.setOnClickListener(v -> {
            AddEditCaseTypeDialogFragment fragment = AddEditCaseTypeDialogFragment.newInstance();
            fragment.show(getParentFragmentManager(), AddEditCaseTypeDialogFragment.TAG);
        });
    }

    private void handleNodeEvents() {
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    onToggle(!node.isExpand(), holder);
                } else {
                    //TODO
                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                DirectoryNodeBinder.DirectoryNodeHolder directoryNodeHolder = (DirectoryNodeBinder.DirectoryNodeHolder) holder;
                final ImageView ivArrow = directoryNodeHolder.getIvArrow();
                int rotateDegree = isExpand ? -90 : 90;
                ivArrow.animate().rotationBy(rotateDegree).start();
            }
        });
    }

    private void setupObserver() {
        viewModel.getCaseTypesResultSingleLiveEvent().observe(getViewLifecycleOwner(), caseTypeResult -> {
            if (caseTypeResult != null) {
                if (caseTypeResult.getErrorCode().equals("0")) {
                    caseTypeInfoList = Arrays.asList(caseTypeResult.getCaseTypes());
                    for (int i = 0; i < caseTypeResult.getCaseTypes().length; i++) {
                        CaseTypeResult.CaseTypeInfo caseTypeInfo = caseTypeResult.getCaseTypes()[i];
                        if (caseTypeInfo.getParentID() == 0) {
                            String caseType = Converter.letterConverter(caseTypeInfo.getCaseType());
                            TreeNode<Dir> dirNode = new TreeNode<>(new Dir(caseType, caseTypeInfo.getCaseTypeID()));
                            treeNodeList.add(dirNode);
                            addChild(dirNode, caseTypeInfo.getCaseTypeID());
                        }
                    }
                    setupAdapter();
                    handleNodeEvents();

                } else {
                    handleError(caseTypeResult.getError());
                }
            }
        });

        viewModel.getRefresh().observe(getViewLifecycleOwner(), refreshed -> {
            caseTypeInfoList = new ArrayList<>();
            treeNodeList = new ArrayList<>();
            fetchCaseTypes();
        });

        viewModel.getDeleteClicked().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer caseTypeID) {
                deleteCaseType(caseTypeID);
            }
        });

        viewModel.getDeleteCaseTypeResultSingleLiveEvent().observe(getViewLifecycleOwner(), new Observer<CaseTypeResult>() {
            @Override
            public void onChanged(CaseTypeResult caseTypeResult) {
                if (caseTypeResult != null) {
                    if (caseTypeResult.getErrorCode().equals("0")) {
                        showSuccessDialog("حذف گروه موفقیت آمیز بود");
                        caseTypeInfoList = new ArrayList<>();
                        treeNodeList = new ArrayList<>();
                        fetchCaseTypes();
                    } else {
                        handleError(caseTypeResult.getError());
                    }
                }
            }
        });
    }

    private void addChild(TreeNode treeNode, int caseTypeID) {
        for (int i = 0; i < caseTypeInfoList.size(); i++) {
            if (caseTypeInfoList.get(i).getParentID() == caseTypeID) {
                CaseTypeResult.CaseTypeInfo caseTypeInfo = caseTypeInfoList.get(i);
                String caseType = Converter.letterConverter(caseTypeInfo.getCaseType());
                TreeNode<Dir> dirNode = new TreeNode<>(new Dir(caseType, caseTypeInfo.getCaseTypeID()));
                treeNode.addChild(dirNode);
                addChild(dirNode, caseTypeInfo.getCaseTypeID());
            }
        }
    }

    private void handleError(String msg) {
        ErrorDialogFragment fragment = ErrorDialogFragment.newInstance(msg);
        fragment.show(getParentFragmentManager(), ErrorDialogFragment.TAG);
    }

    private void showSuccessDialog(String msg) {
        SuccessDialogFragment fragment = SuccessDialogFragment.newInstance(msg);
        fragment.show(getParentFragmentManager(), SuccessDialogFragment.TAG);
    }

    private void deleteCaseType(int caseTypeID) {
        String userLoginKey = PartoSanatPreferences.getUserLoginKey(getContext());
        String centerName = PartoSanatPreferences.getCenterName(getContext());
        ServerData serverData = viewModel.getServerData(centerName);
        viewModel.getPartoSanatServiceCaseTypeResult(serverData.getIpAddress() + ":" + serverData.getPort());
        String path = "/api/v1/CaseType/Delete";
        viewModel.deleteCaseType(path, userLoginKey, caseTypeID);
    }
}