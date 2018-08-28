package com.example.saini.mvp.interfaces;

/**
 * Created by nazer on 8/13/2017.
 */

public interface GenericCallBack<T> {
    public void onSuccessListener(T t);
    public void onFail(T t);
}
