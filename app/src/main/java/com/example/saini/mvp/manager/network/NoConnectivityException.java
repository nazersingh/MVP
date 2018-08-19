package com.example.saini.mvp.manager.network;

import java.io.IOException;

/**
 * Created by igniva-android-14 on 5/10/17.
 */

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}