package com.example.partosanatproject.ui.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.partosanatproject.ui.fragment.LoginFragment;

public class LoginContainerActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return LoginFragment.newInstance();
    }

    public static Intent start(Context context) {
        return new Intent(context, LoginContainerActivity.class);
    }
}