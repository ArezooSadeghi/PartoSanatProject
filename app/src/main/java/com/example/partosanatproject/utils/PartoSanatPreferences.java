package com.example.partosanatproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PartoSanatPreferences {
    private static final String USER_LOGIN_KEY = "userLoginKey";
    private static final String CENTER_NAME = "centerName";

    public static String getUserLoginKey(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(USER_LOGIN_KEY, null);
    }

    public static void setUserLoginKey(Context context, String userLoginKey) {
        SharedPreferences preferences = getSharedPreferences(context);
        preferences.edit().putString(USER_LOGIN_KEY, userLoginKey).apply();
    }

    public static String getCenterName(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(CENTER_NAME, null);
    }

    public static void setCenterName(Context context, String centerName) {
        SharedPreferences preferences = getSharedPreferences(context);
        preferences.edit().putString(CENTER_NAME, centerName).apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(
                context.getPackageName(),
                Context.MODE_PRIVATE);
    }
}
