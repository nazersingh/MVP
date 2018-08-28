package com.example.saini.mvp.model;

import com.example.saini.mvp.interfaces.GenericCallBack;
import com.example.saini.mvp.manager.network.RetrofitClient;
import com.example.saini.mvp.util.PrintLog;

import okhttp3.ResponseBody;

public class FragmentAModel {
    private static String TAG="FragmentAModel";

    public interface FragmentModelCallback
    {
         void onModelSuccess();
        void onModelFailure(Throwable throwable);
    }

public void callDownload(String s,FragmentModelCallback fragmentModelCallback)
{
    RetrofitClient.getInstance().callDownLoadFile(s, new GenericCallBack() {
        @Override
        public void onSuccessListener(Object o) {
            PrintLog.e(TAG, "onSuccessListener");
        }

        @Override
        public void onFail(Object o) {
            PrintLog.e(TAG, "onFail");
        }
    });
}

}
