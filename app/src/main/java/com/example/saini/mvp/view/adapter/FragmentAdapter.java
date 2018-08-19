package com.example.saini.mvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList;
    public FragmentAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragmentArrayList)
    {
    super(fragmentManager);
    this.fragmentArrayList=fragmentArrayList;
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }
}
