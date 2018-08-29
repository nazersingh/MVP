package com.example.saini.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.saini.mvp.R;
import com.example.saini.mvp.interfaces.FragmentSearchCallback;
import com.example.saini.mvp.view.activity.MainActivity;
import com.example.saini.mvp.view.adapter.FragmentAdapter;
import com.example.saini.mvp.view.base.BaseFragment;
import com.example.saini.mvp.view.fragments.fragmenta.FragmentA;

import java.util.ArrayList;
import java.util.Objects;

public class HomeScreen extends BaseFragment implements ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener, FragmentSearchCallback<String> {

    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    ViewPager viewPager;
    View view;
    BottomNavigationView bottomNavigationView;
    private final String TAG = this.getClass().getSimpleName();
    Menu menu;
    SearchView searchView;
    boolean isSearching;
    FragmentAdapter fragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setUpLayout();
//        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void setUpLayout() {
        setToolbarShow();
        setToolbarTitle("Fragment A");
        ((MainActivity) Objects.requireNonNull(getContext())).setSearchCallback(this);

        fragmentArrayList.add(new FragmentA());
        fragmentArrayList.add(new FragmentB());
        fragmentArrayList.add(new FragmentC());
        fragmentArrayList.add(new FragmentD());
        fragmentArrayList.add(new FragmentE());

        viewPager = view.findViewById(R.id.view_pager);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        viewPager.setOffscreenPageLimit(fragmentArrayList.size());
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragmentArrayList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public void setUpView() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.action_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.action_three:
                viewPager.setCurrentItem(2);
                break;
            case R.id.action_four:
                viewPager.setCurrentItem(3);
                break;
            case R.id.action_five:
                viewPager.setCurrentItem(4);
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        if (isSearching)
//            menu.findItem(R.id.action_search).collapseActionView();
        ((MainActivity) getContext()).collapseSearchToolbar();
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

        bottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        inflater.inflate(R.menu.toolbar_menu, menu);
//        this.menu = menu;
//        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setQueryHint(Html.fromHtml(
//                "<small><small>Search..</small></small>"));
//        searchView.setOnQueryTextListener(onQueryTextListener);
//        menu.findItem(R.id.action_search).setOnActionExpandListener(expandListener);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                return super.onOptionsItemSelected(item);
//            case R.id.action_notification:
//                return super.onOptionsItemSelected(item);
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
//        @Override
//        public boolean onMenuItemActionExpand(MenuItem menuItem) {
//            menu.findItem(R.id.action_notification).setVisible(false);
//            isSearching=true;
//            return true;
//        }
//
//        @Override
//        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
//            isSearching=false;
//            menu.findItem(R.id.action_notification).setVisible(true);
//            return true;
//        }
//    };
//
//    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
//        @Override
//        public boolean onQueryTextSubmit(String s) {
//            return false;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String s) {
//            return false;
//        }
//    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            if (fragmentAdapter != null) {
                ((MainActivity) Objects.requireNonNull(getContext())).setSearchCallback(this);
                fragmentAdapter.getItem(viewPager.getCurrentItem()).setUserVisibleHint(true);
            }
        }
    }


    @Override
    public void onTextChange(String s) {
        callSearchOnfragments(s);
    }

    @Override
    public void onTextSubmit(String s) {
        callSearchOnfragments(s);
    }

    /**
     * update fragment on type
     * @param s
     */
    private void callSearchOnfragments(String s) {
        if (fragmentAdapter.getItem(viewPager.getCurrentItem()) instanceof FragmentA)
            ((FragmentA) fragmentAdapter.getItem(viewPager.getCurrentItem())).onSearchTextChange(s);
        else if (fragmentAdapter.getItem(viewPager.getCurrentItem()) instanceof FragmentB)
            ((FragmentB) fragmentAdapter.getItem(viewPager.getCurrentItem())).onSearchTextChange(s);
        else if (fragmentAdapter.getItem(viewPager.getCurrentItem()) instanceof FragmentC)
            ((FragmentC) fragmentAdapter.getItem(viewPager.getCurrentItem())).onSearchTextChange(s);
        else if (fragmentAdapter.getItem(viewPager.getCurrentItem()) instanceof FragmentD)
            ((FragmentD) fragmentAdapter.getItem(viewPager.getCurrentItem())).onSearchTextChange(s);
        else if (fragmentAdapter.getItem(viewPager.getCurrentItem()) instanceof FragmentE)
            ((FragmentE) fragmentAdapter.getItem(viewPager.getCurrentItem())).onSearchTextChange(s);
    }


}
