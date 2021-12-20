package com.example.partosanatproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.partosanatproject.ui.fragment.LoginFragment;
import com.example.partosanatproject.utils.PartoSanatPreferences;

public class LoginContainerActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PartoSanatPreferences.getUserLoginKey(this) != null) {
            Intent starter = MenuContainerActivity.start(this);
            startActivity(starter);
            finish();
        }
    }

    public static Intent start(Context context) {
        return new Intent(context, LoginContainerActivity.class);
    }
}