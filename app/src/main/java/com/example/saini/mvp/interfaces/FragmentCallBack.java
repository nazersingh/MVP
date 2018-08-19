package com.example.saini.mvp.interfaces;

public interface FragmentCallBack <T>{
     void onBackPressed(T t);
     void onActivityResult(T t);
}
