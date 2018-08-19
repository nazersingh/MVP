package com.example.saini.mvp.util;

import android.util.Log;

import com.example.saini.mvp.BuildConfig;

public class PrintLog {

    private static boolean isDebug = BuildConfig.DEBUG;

    public static void e(String TAG, String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void i(String TAG, String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }
    public static void d(String TAG, String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }
}
