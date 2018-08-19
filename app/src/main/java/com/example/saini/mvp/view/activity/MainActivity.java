package com.example.saini.mvp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saini.mvp.R;
import com.example.saini.mvp.interfaces.FragmentSearchCallback;
import com.example.saini.mvp.util.Utility;
import com.example.saini.mvp.view.fragments.Splash;
import com.example.saini.mvp.util.LocationHelper;
import com.example.saini.mvp.util.PrintLog;

import java.lang.reflect.Field;

public class MainActivity extends BaseActivity {
    LocationHelper locationHelper;
    String TAG = this.getClass().getSimpleName();
    private long TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed = 0;
    Toolbar searchtollbar;
    private MenuItem item_search;
    private Menu search_menu;
    private boolean isSearching;
    private FragmentSearchCallback<String> fragmentSearchCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        setSearchtollbar();
        addFragment(new Splash());
        locationHelper = new LocationHelper(MainActivity.this, locationFetchedCallback);
    }

    /**
     * Location fetch callback
     */
    LocationHelper.LocationFetchedCallback locationFetchedCallback = new LocationHelper.LocationFetchedCallback() {
        @Override
        public void onLocationFetched(Location location) {
            setSubtitle(locationHelper.getAddress(location.getLatitude(), location.getLongitude()).getAddressLine(0));
        }
    };

    /**
     * Handles the activity results
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LocationHelper.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        PrintLog.d(TAG, "onActivityResult RESULT_OK ..............: ");
                        // All required changes were successfully made
                        locationHelper.getLastLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        PrintLog.d(TAG, "onActivityResult RESULT_CANCELED ..............: ");
                        // The user was asked to change settings, but chose not to
                        break;
                    default:
                        break;
                }
                break;
        }
    }


    public void addFragment(Fragment fragment) {
        onAddFragment(R.id.container, fragment);
    }

    public void replaceFragment(Fragment fragment, boolean b) {
        onReplaceFragment(R.id.container, fragment, b);
    }


    @Override
    public void setUpLayout() {

    }

    @Override
    public void setUpView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            popFragment();
            super.onBackPressed();
            Fragment ffragment = getSupportFragmentManager().findFragmentById(R.id.container);
            ffragment.setUserVisibleHint(true);
        } else {

            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                finish();
            } else {
                mBackPressed = System.currentTimeMillis();
                Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_status:
                Toast.makeText(this, "Home Status Click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    Utility.circleReveal(MainActivity.this, findViewById(R.id.searchtoolbar), 1, true, true);
                else
                    searchtollbar.setVisibility(View.VISIBLE);

                item_search.expandActionView();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Home Settings Click", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * setup Search toolbar
     */
    public void setSearchtollbar() {
        searchtollbar = (Toolbar) findViewById(R.id.searchtoolbar);
        if (searchtollbar != null) {
            searchtollbar.inflateMenu(R.menu.menu_search);
            search_menu = searchtollbar.getMenu();

            searchtollbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        Utility.circleReveal(MainActivity.this, findViewById(R.id.searchtoolbar), 1, true, false);
                    else
                        searchtollbar.setVisibility(View.GONE);
                }
            });
            item_search = search_menu.findItem(R.id.action_filter_search);
            item_search.setOnActionExpandListener(expandListener);
            initSearchView();
        } else
            Log.d("toolbar", "setSearchtollbar: NULL");
    }

    /**
     * collapseSearch Toolbar
     */
    public void collapseSearchToolbar() {
        if (isSearching)
            item_search.collapseActionView();
    }

    /**
     * expand search view listener
     */
    MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            PrintLog.e(TAG, " onMenuItemActionExpand");
            isSearching = true;
            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            PrintLog.e(TAG, " onMenuItemActionCollapse");
            isSearching = false;
            // Do something when collapsed
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Utility.circleReveal(MainActivity.this, findViewById(R.id.searchtoolbar), 1, true, false);
            } else
                searchtollbar.setVisibility(View.GONE);
            return true;
        }
    };

    /**
     *
     */
    public void initSearchView() {
        final SearchView searchView =
                (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();

        // Enable/Disable Submit button in the keyboard
        searchView.setSubmitButtonEnabled(false);

        // Change search close button image
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close);

        // set hint and the text colors
        EditText txtSearch = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint("Search..");
        txtSearch.setHintTextColor(Color.DKGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.colorPrimary));

        // set the cursor
        AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fragmentSearchCallback.onTextSubmit(query);
                searchView.clearFocus();
                /**
                 * on query submit
                 */
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fragmentSearchCallback.onTextChange(newText);
                return true;
            }
        });
    }

    /**
     * set Fragments Search text Callback
     */
    public void setSearchCallback(FragmentSearchCallback<String> fragmentSearchCallback) {
        this.fragmentSearchCallback = fragmentSearchCallback;
    }

}