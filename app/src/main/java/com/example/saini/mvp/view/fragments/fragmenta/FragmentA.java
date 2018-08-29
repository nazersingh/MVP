package com.example.saini.mvp.view.fragments.fragmenta;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saini.mvp.R;
import com.example.saini.mvp.view.base.BaseFragment;
import com.example.saini.mvp.util.PrintLog;

import okhttp3.ResponseBody;

public class FragmentA extends BaseFragment {
    private String TAG = this.getClass().getSimpleName();
    Handler handler;
private FragmentAPresenter fragmentAPresenter;

String mVideoFile="http://techslides.com/demos/sample-videos/small.mp4";
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_a, container, false);
        setUpLayout();
        return view;
    }


    @Override
    public void setUpLayout() {
        handler = new Handler();
        fragmentAPresenter=new FragmentAPresenter(presenterCallback);

       view.findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentAPresenter.callDownload(mVideoFile);

            }
        });
    }

    @Override
    public void setUpView() {

    }

    FragmentAPresenter.PresenterCallback presenterCallback=new FragmentAPresenter.PresenterCallback() {
        @Override
        public void presenterSuccess(ResponseBody responseBody) {

        }

        @Override
        public void presenterFailure(Throwable throwable) {

        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed())
            setToolbarTitle("Fragment A");
    }

    public void onSearchTextChange(String searchString) {
        PrintLog.e(TAG,searchString);
    }
}
