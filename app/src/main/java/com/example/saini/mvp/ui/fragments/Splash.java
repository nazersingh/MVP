package com.example.saini.mvp.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saini.mvp.R;

public class Splash extends BaseFragment {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private boolean isSplashTimerComplete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash, container, false);
        setUpLayout();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarHide();
    }

    @Override
    public void setUpLayout() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isSplashTimerComplete = true;
                onResume();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSplashTimerComplete) {
            popFragment();
            addFragment(new HomeScreen());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
