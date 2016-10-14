package com.mac.training.flurryappsample;

import android.app.Application;
import android.util.Log;

import com.flurry.android.FlurryAgent;

/**
 * Created by User on 10/13/2016.
 */

public class MyApplication extends Application {

    private static final String FLURRY_API_KEY = "RWK2DTJT6YDRJ37F95YB";
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        FlurryAgent.setLogEnabled(false);
        FlurryAgent.setLogLevel(Log.VERBOSE);
        FlurryAgent.setLogEvents(true);
        FlurryAgent.init(this, FLURRY_API_KEY);
        Log.i(TAG, "Flurry SDK initialized");

    }
}
