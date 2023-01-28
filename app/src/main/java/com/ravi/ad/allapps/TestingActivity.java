package com.ravi.ad.allapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appwood.mylibrarys.AdsClass;
import com.ravi.ad.allapps.R;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
    }

    @Override
    public void onBackPressed() {
        AdsClass.BackInterstitial(TestingActivity.this);
    }

    public void ADSs(View view) {

        AdsClass.Interstitial(this, new Intent(this, MainActivity.class), 0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //
//        //native
//        //R and B
        AdsClass.NativeAds(this, findViewById(R.id.native_detail),
                findViewById(R.id.banner_native), findViewById(R.id.addcontain)
                , findViewById(R.id.ad_native_fb));

        //banner
        AdsClass.Banner(this, findViewById(R.id.bottomsads).findViewById(com.appwood.mylibrarys.R.id.google_banner_container), findViewById(R.id.bottomsads).findViewById(com.appwood.mylibrarys.R.id.fb_banner_container), findViewById(R.id.bottomsads));
    }



//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
}