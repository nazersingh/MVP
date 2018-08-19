package com.example.saini.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saini.mvp.R;
import com.example.saini.mvp.ui.fragments.Splash;
import com.example.saini.mvp.util.LocationHelper;
import com.example.saini.mvp.util.PrintLog;

public class MainActivity extends BaseActivity {
    LocationHelper locationHelper;
    String TAG = this.getClass().getSimpleName();
    private long TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(new Splash());
        locationHelper = new LocationHelper(MainActivity.this, locationFetchedCallback);
    }


    LocationHelper.LocationFetchedCallback locationFetchedCallback = new LocationHelper.LocationFetchedCallback() {
        @Override
        public void onLocationFetched(Location location) {
            ((TextView) findViewById(R.id.tv_location)).setText(" location is " + location + "   address is  " + locationHelper.getAddress(location.getLatitude(), location.getLongitude()));
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
}