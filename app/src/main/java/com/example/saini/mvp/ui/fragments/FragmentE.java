package com.example.saini.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saini.mvp.R;
import com.example.saini.mvp.interfaces.FragmentCallBack;

public class FragmentE extends BaseFragment implements FragmentCallBack {
    View view;
    private final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_e, container, false);
        setUpLayout();
        return view;
    }

    @Override
    public void setUpLayout() {

        view.findViewById(R.id.btn_profile).setOnClickListener(view ->
        {
            addFragment(new ProfileFragment());
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&isResumed())
            setToolbarTitle("Fragment E");
    }

    @Override
    public void onBackPressed(Object o) {

    }

    @Override
    public void onActivityResult(Object o) {

    }
}
