package com.ravi.ad.allapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.appwood.mylibrarys.BannerClass;
import com.appwood.mylibrarys.InterClass;
import com.appwood.mylibrarys.NativeClass;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
    }

    @Override
    public void onBackPressed() {
        InterClass.BackInterstitial(TestingActivity.this);
    }

    public void ADSs(View view) {
        InterClass.Interstitial(this, new Intent(this, MainActivity.class), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NativeClass.NativeAds(this, findViewById(R.id.native_detail),
                findViewById(R.id.banner_native), findViewById(R.id.addcontain)
                , findViewById(R.id.ad_native_fb), findViewById(R.id.native_ad_layout), findViewById(R.id.custom_native), findViewById(R.id.applovin_native));
        BannerClass.Banner(this, findViewById(R.id.bottomsads), findViewById(R.id.google_banner), findViewById(R.id.fb_banner), findViewById(R.id.applovin_banner));
    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
}