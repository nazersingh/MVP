package com.example.saini.mvp.view.fragments.fragmenta;

import okhttp3.ResponseBody;

public class FragmentAPresenter implements FragmentAModel.FragmentModelCallback{
    private PresenterCallback fragmentAPresenter;
private FragmentAModel fragmentAModel;
    public FragmentAPresenter(PresenterCallback fragmentAPresenter) {
        this.fragmentAPresenter=fragmentAPresenter;
        fragmentAModel=new FragmentAModel();
    }

    @Override
    public void onModelSuccess() {
//        fragmentAPresenter. presenterSuccess();
    }

    @Override
    public void onModelFailure(Throwable throwable) {
        fragmentAPresenter.presenterFailure(throwable);
    }

    public interface PresenterCallback {
    void presenterSuccess(ResponseBody responseBody);
    void presenterFailure(Throwable throwable);
    }

    public void callDownload(String url)
    {
        fragmentAModel.callDownload(url,this);
    }

}
