package com.example.saini.mvp.interfaces;

/**
 * Created by nazer on 8/13/2017.
 */

interface GenericCallBack<T> {
    public void onSuccessListener(T t);
    public void onFail(T t);
}
