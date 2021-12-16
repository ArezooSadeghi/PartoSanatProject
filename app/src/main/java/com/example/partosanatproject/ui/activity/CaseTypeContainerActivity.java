package com.example.partosanatproject.ui.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.partosanatproject.ui.fragment.CaseTypeFragment;

public class CaseTypeContainerActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return CaseTypeFragment.newInstance();
    }

    public static Intent start(Context context) {
        return new Intent(context, CaseTypeContainerActivity.class);
    }
}