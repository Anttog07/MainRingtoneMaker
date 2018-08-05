package com.appsworld.ringtone.maker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.appsworld.ringtone.maker.R;
import com.appsworld.ringtone.maker.Ringdroid.Constants;
import com.google.android.gms.ads.MobileAds;


public class SplashActivity extends AppCompatActivity {

    private ImageView mBellImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MobileAds.initialize(this, Constants.ADMOB_APP_ID);

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        mBellImageView = (ImageView) findViewById(R.id.image_view_bell);
        mBellImageView.startAnimation(shake);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBellImageView.clearAnimation();
                Intent i = new Intent(SplashActivity.this, RingdroidSelectActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}
