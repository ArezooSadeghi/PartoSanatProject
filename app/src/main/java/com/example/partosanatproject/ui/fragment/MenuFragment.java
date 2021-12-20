package com.example.partosanatproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.partosanatproject.R;
import com.example.partosanatproject.databinding.FragmentMenuBinding;
import com.example.partosanatproject.ui.activity.CaseTypeContainerActivity;
import com.example.partosanatproject.ui.activity.LoginContainerActivity;
import com.example.partosanatproject.utils.PartoSanatPreferences;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
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
                R.layout.fragment_menu,
                container,
                false);

        handleEvents();

        return binding.getRoot();
    }

    private void handleEvents() {
        binding.ivMore.setOnClickListener(view -> {
            PowerMenu powerMenu = new PowerMenu.Builder(requireContext())
                    .addItem(new PowerMenuItem("خروج از حساب کاربری"))
                    .addItem(new PowerMenuItem("گروه ها"))
                    .build();

            powerMenu.setOnMenuItemClickListener((position, item) -> {
                if (position == 0) {
                    PartoSanatPreferences.setUserLoginKey(getContext(), null);
                    Intent starter = LoginContainerActivity.start(getContext());
                    startActivity(starter);
                    powerMenu.dismiss();
                } else {
                    Intent starter = CaseTypeContainerActivity.start(getContext());
                    startActivity(starter);
                    powerMenu.dismiss();
                }
            });
            powerMenu.showAsDropDown(view);
        });
    }
}