package com.example.saini.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saini.mvp.R;
import com.example.saini.mvp.util.PrintLog;

public class FragmentD extends BaseFragment {

    private String TAG=this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);
        setUpLayout();
        return view;
    }

    @Override
    public void setUpLayout() {

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser&&isResumed())
            setToolbarTitle("Fragment D");
    }
    public void onSearchTextChange(String searchString) {
        PrintLog.e(TAG,searchString);
    }
}
