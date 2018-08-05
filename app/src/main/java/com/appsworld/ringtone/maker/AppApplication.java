package com.appsworld.ringtone.maker;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize the AdMob app
        MobileAds.initialize(this, "ca-app-pub-6122553172772262~3580810106");
    }
}
