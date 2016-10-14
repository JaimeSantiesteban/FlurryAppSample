package com.mac.training.flurryappsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdBanner;
import com.flurry.android.ads.FlurryAdBannerListener;
import com.flurry.android.ads.FlurryAdErrorType;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RelativeLayout mBanner;
    private FlurryAdBanner mFlurryAdBanner = null;
    private String mAdSpaceName = "BANNER_ADSPACE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBanner = (RelativeLayout) findViewById(R.id.banner_layout);
        mFlurryAdBanner = new FlurryAdBanner(this, mBanner, mAdSpaceName);

        // optional allow us to get callbacks for ad events,
        mFlurryAdBanner.setListener(bannerAdListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(this);
        // fetch and display ad for this ad space as soon as it is ready.
        mFlurryAdBanner.fetchAndDisplayAd();
    }

    @Override
    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
        mFlurryAdBanner.destroy();
    }

    FlurryAdBannerListener bannerAdListener = new FlurryAdBannerListener() {

        @Override
        public void onFetched(FlurryAdBanner adBanner) {
            Log.d(TAG, "...onFetched");
            mFlurryAdBanner.displayAd();
        }

        @Override
        public void onRendered(FlurryAdBanner flurryAdBanner) {
            Log.d(TAG, "...onRendered");
        }

        @Override
        public void onShowFullscreen(FlurryAdBanner flurryAdBanner) {
            Log.d(TAG, "...onShowFullscreen");

        }

        @Override
        public void onCloseFullscreen(FlurryAdBanner flurryAdBanner) {
            Log.d(TAG, "...onCloseFullscreen");
        }

        @Override
        public void onAppExit(FlurryAdBanner flurryAdBanner) {
            Log.d(TAG, "...onAppExit");
        }

        @Override
        public void onClicked(FlurryAdBanner flurryAdBanner) {
            Log.d(TAG, "...onClicked");
        }

        @Override
        public void onVideoCompleted(FlurryAdBanner flurryAdBanner) {
            Log.d(TAG, "...onVideoCompleted");
        }

        @Override
        public void onError(FlurryAdBanner adBanner, FlurryAdErrorType flurryAdErrorType, int errorCode) {
            Log.d(TAG, "...onError");
            mFlurryAdBanner.destroy();
            Log.e(TAG, "Banner ad load error - Error type: " + flurryAdErrorType + " Code: " + errorCode);
        }
        //..
        //the remainder of the listener callback methods
    };
}
