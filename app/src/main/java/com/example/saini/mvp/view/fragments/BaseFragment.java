package com.example.saini.mvp.view.fragments;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.saini.mvp.view.activity.MainActivity;

public abstract class BaseFragment extends Fragment {

    public abstract void setUpLayout();

    @NonNull
    public void setToolbarTitle(String title)
    {
        ((MainActivity)getActivity()).setTitle(title);
    }
    public void setToolbarSubtitleTitle(String title)
    {
        ((MainActivity)getContext()).setSubtitle(title);
    }
    public void setToolbarHide()
    {
        ((MainActivity)getContext()).hideToolbar();
    }
    public void setToolbarShow()
    {
        ((MainActivity)getContext()).showToolbar();
    }
    public void addFragment(Fragment fragment)
    {
        ((MainActivity)getContext()).addFragment(fragment);
    }
    public void popFragment()
    {
        ((MainActivity)getContext()).popFragment();
    }
    public void replaceFragment(Fragment fragment, boolean isBackStack)
    {
        ((MainActivity)getContext()).replaceFragment(fragment,true);
    }
    public void clearBackStack()
    {
        ((MainActivity)getContext()).clearBackStack();
    }


}
