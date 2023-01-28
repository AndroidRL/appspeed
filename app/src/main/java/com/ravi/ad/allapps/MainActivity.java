package com.ravi.ad.allapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.appwood.mylibrarys.AdsClass;
import com.ravi.ad.allapps.R;
import com.ravi.ad.allapps.AdsCode.ExitScreen;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ADS(View view) {
        //1 finish
        AdsClass.Interstitial(this, new Intent(this, TestingActivity.class), 0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AdsClass.NativeAds(this, findViewById(R.id.native_detail),
                findViewById(R.id.banner_native), findViewById(R.id.addcontain)
                , findViewById(R.id.ad_native_fb));

        //banner
        AdsClass.Banner(this, findViewById(R.id.bottomsads).findViewById(com.appwood.mylibrarys.R.id.google_banner_container), findViewById(R.id.bottomsads).findViewById(com.appwood.mylibrarys.R.id.fb_banner_container), findViewById(R.id.bottomsads));
    }

//    @Override
//    public void onBackPressed() {
//        AdsClass.BackInterstitial(this);
//
//    }

    @Override
    public void onBackPressed() {
//        MainActivity.this.startActivity(new Intent(MainActivity.this, ExitScreen.class));
      AdsClass.Interstitial(this, new Intent(this, ExitScreen.class),0);
//        AdsClass.BackInterstitial(this);

    }


}





































