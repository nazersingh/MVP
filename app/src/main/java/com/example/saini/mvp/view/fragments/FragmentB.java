package com.example.saini.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saini.mvp.R;
import com.example.saini.mvp.util.PrintLog;
import com.example.saini.mvp.view.base.BaseFragment;

public class FragmentB extends BaseFragment {

    private String TAG=this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        setUpLayout();
        return view;
    }

    @Override
    public void setUpLayout() {

    }

    @Override
    public void setUpView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&isResumed())
            setToolbarTitle("Fragment B");

    }
    public void onSearchTextChange(String searchString) {
        PrintLog.e(TAG,searchString);
    }
}
