package com.appwood.mylibrarys;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;

public class CustomAdsInterActivity extends AppCompatActivity {

    private ImageView appIcon;
    private TextView appName;
    private TextView appShot;
    private ImageView close;
    private AppCompatButton btnInstall;
    private AppCompatButton btnCancel;
    private ImageView adBanner;
    private RelativeLayout mainView;

    int ads_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_inter);
        ads_number = MyHelpers.getRandomNumber(0, SplashHelp.adsModals.size() - 1);
        initView();
        btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstallApps();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyHelpers.CustomIntent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyHelpers.CustomIntent);
                finish();
            }
        });
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstallApps();
            }
        });
    }

    private void initView() {
        appIcon = (ImageView) findViewById(R.id.app_icon);
        adBanner = (ImageView) findViewById(R.id.ad_banner);
        appName = (TextView) findViewById(R.id.app_name);
        appShot = (TextView) findViewById(R.id.app_shot);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.btn_layout).setVisibility(View.VISIBLE);
            }
        }, 1500);

        appName.setText(SplashHelp.adsModals.get(ads_number).getAd_app_name());
        appShot.setText(SplashHelp.adsModals.get(ads_number).getApp_description());
        Glide.with(this)
                .load(SplashHelp.adsModals.get(ads_number).getApp_logo())
                .into(appIcon);
        Glide.with(this)
                .load(SplashHelp.adsModals.get(ads_number).getApp_banner())
                .into(adBanner);

        close = (ImageView) findViewById(R.id.close);
        btnInstall = (AppCompatButton) findViewById(R.id.btn_install);
        btnCancel = (AppCompatButton) findViewById(R.id.btn_cancel);
        mainView = (RelativeLayout) findViewById(R.id.main_view);
    }

    @Override
    public void onBackPressed() {

    }

    private void InstallApps() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
        }
    }
}