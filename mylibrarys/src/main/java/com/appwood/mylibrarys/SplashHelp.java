package com.appwood.mylibrarys;

import static ProMex.classs.Utils.Util.DEc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ProMex.classs.Utils.Util;
import cz.msebera.android.httpclient.Header;

public class SplashHelp extends AppCompatActivity {

    public static String extra_switch_1;
    public static String extra_switch_2;
    public static String extra_switch_3;
    public static String extra_switch_4;
    public static String extra_text_1;
    public static String extra_text_2;
    public static String extra_text_3;
    public static String extra_text_4;

    public static Context contextx;
    public static Intent intentx;

    public static boolean isShowOpen = false;
    public static AppOpenManager appOpenManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

    }

    /*Splash*/
    public static void splash_next(String packageName, String VersonCode, Context context, Intent intent) {
        contextx = context;
        intentx = intent;
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader(DEc(Util.pizzuhead), DEc(Util.pizzudians));
        asyncHttpClient.get(DEc(Util.pizzuli) + packageName, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    // google
                    MyHelpers.setGoogleEnable(response.getString("enable_google_admob_id"));
                    //google Banner
                    if (response.getString("google_admob_banner_id") != null && !response.getString("google_admob_banner_id").isEmpty()) {
                        MyHelpers.SetGoogleBanner(response.getString("google_admob_banner_id"));
                    } else {
                        MyHelpers.SetGoogleBanner(null);
                    }
                    if (response.getString("google_admob_banner_id_1") != null && !response.getString("google_admob_banner_id_1").isEmpty()) {
                        MyHelpers.SetGoogleBanner1(response.getString("google_admob_banner_id_1"));
                    } else {
                        MyHelpers.SetGoogleBanner1(null);
                    }
                    if (response.getString("google_admob_banner_id_2") != null && !response.getString("google_admob_banner_id_2").isEmpty()) {
                        MyHelpers.SetGoogleBanner2(response.getString("google_admob_banner_id_2"));
                    } else {
                        MyHelpers.SetGoogleBanner2(null);
                    }
                    //google Native
                    if (response.getString("google_admob_native_id") != null && !response.getString("google_admob_native_id").isEmpty()) {
                        MyHelpers.SetGoogleNative(response.getString("google_admob_native_id"));
                    } else {
                        MyHelpers.SetGoogleNative(null);
                    }
                    if (response.getString("google_admob_native_id_1") != null && !response.getString("google_admob_native_id_1").isEmpty()) {
                        MyHelpers.SetGoogleNative1(response.getString("google_admob_native_id_1"));
                    } else {
                        MyHelpers.SetGoogleNative1(null);
                    }
                    if (response.getString("google_admob_native_id_2") != null && !response.getString("google_admob_native_id_2").isEmpty()) {
                        MyHelpers.SetGoogleNative2(response.getString("google_admob_native_id_2"));
                    } else {
                        MyHelpers.SetGoogleNative2(null);
                    }
                    //google Native btn
                    if (response.getString("google_button_name") != null && !response.getString("google_button_name").isEmpty()) {
                        MyHelpers.setGooglebutton_name(response.getString("google_button_name"));
                    } else {
                        MyHelpers.setGooglebutton_name(null);
                    }
                    if (response.getString("google_button_color") != null && !response.getString("google_button_color").isEmpty()) {
                        MyHelpers.setGooglebutton_color(response.getString("google_button_color"));
                    } else {
                        MyHelpers.setGooglebutton_color("#000000");
                    }
                    // google Open ADS
                    if (response.getString("google_open_id") != null && !response.getString("google_open_id").isEmpty()) {
                        MyHelpers.setGoogle_OpenADS(response.getString("google_open_id"));
                    } else {
                        MyHelpers.setGoogle_OpenADS(null);
                    }
                    //google Interstitial
                    if (response.getString("google_admob_interstitial_id") != null && !response.getString("google_admob_interstitial_id").isEmpty()) {
                        MyHelpers.SetGoogleInter(response.getString("google_admob_interstitial_id"));
                    } else {
                        MyHelpers.SetGoogleInter(null);
                    }
                    if (response.getString("google_admob_interstitial_id_1") != null && !response.getString("google_admob_interstitial_id_1").isEmpty()) {
                        MyHelpers.SetGoogleInter1(response.getString("google_admob_interstitial_id_1"));
                    } else {
                        MyHelpers.SetGoogleInter1(null);
                    }
                    if (response.getString("google_admob_interstitial_id_2") != null && !response.getString("google_admob_interstitial_id_2").isEmpty()) {
                        MyHelpers.SetGoogleInter2(response.getString("google_admob_interstitial_id_2"));
                    } else {
                        MyHelpers.SetGoogleInter2(null);
                    }
                    //Facebook
                    MyHelpers.setFacebookEnable(response.getString("enable_facebook_id"));
                    //Facebook Banner
                    if (response.getString("facebook_banner_id") != null && !response.getString("facebook_banner_id").isEmpty()) {
                        MyHelpers.setFacebookBanner(response.getString("facebook_banner_id"));
                    } else {
                        MyHelpers.setFacebookBanner(null);
                    }
                    if (response.getString("facebook_banner_id_1") != null && !response.getString("facebook_banner_id_1").isEmpty()) {
                        MyHelpers.setFacebookBanner1(response.getString("facebook_banner_id_1"));
                    } else {
                        MyHelpers.setFacebookBanner1(null);
                    }
                    if (response.getString("facebook_banner_id_2") != null && !response.getString("facebook_banner_id_2").isEmpty()) {
                        MyHelpers.setFacebookBanner2(response.getString("facebook_banner_id_2"));
                    } else {
                        MyHelpers.setFacebookBanner2(null);
                    }
                    //Facebook Native
                    if (response.getString("facebook_native_id") != null && !response.getString("facebook_native_id").isEmpty()) {
                        MyHelpers.SetFacebookNative(response.getString("facebook_native_id"));
                    } else {
                        MyHelpers.SetFacebookNative(null);
                    }
                    if (response.getString("facebook_native_id_1") != null && !response.getString("facebook_native_id_1").isEmpty()) {
                        MyHelpers.SetFacebookNative1(response.getString("facebook_native_id_1"));
                    } else {
                        MyHelpers.SetFacebookNative1(null);
                    }
                    if (response.getString("facebook_native_id_2") != null && !response.getString("facebook_native_id_2").isEmpty()) {
                        MyHelpers.SetFacebookNative2(response.getString("facebook_native_id_2"));
                    } else {
                        MyHelpers.SetFacebookNative2(null);
                    }

                    //Facebook Open ADS
                    if (response.getString("facebook_open_id") != null && !response.getString("facebook_open_id").isEmpty()) {
                        MyHelpers.setfacebook_open_ad_id(response.getString("facebook_open_id"));
                    } else {
                        MyHelpers.setfacebook_open_ad_id(null);
                    }
                    //Facebook Interstitial
                    if (response.getString("facebook_interstitial_id") != null && !response.getString("facebook_interstitial_id").isEmpty()) {
                        MyHelpers.SetFacebookInter(response.getString("facebook_interstitial_id"));
                    } else {
                        MyHelpers.SetFacebookInter(null);
                    }
                    if (response.getString("facebook_interstitial_id_1") != null && !response.getString("facebook_interstitial_id_1").isEmpty()) {
                        MyHelpers.SetFacebookInter1(response.getString("facebook_interstitial_id_1"));
                    } else {
                        MyHelpers.SetFacebookInter1(null);
                    }
                    if (response.getString("facebook_interstitial_id_2") != null && !response.getString("facebook_interstitial_id_2").isEmpty()) {
                        MyHelpers.SetFacebookInter2(response.getString("facebook_interstitial_id_2"));
                    } else {
                        MyHelpers.SetFacebookInter2(null);
                    }

                    //Auto Link
                    MyHelpers.setauto_link_on_off(response.getString("enable_auto_quereka_link"));  //on_off Auto link
                    if (MyHelpers.getauto_link_on_off().equals("1")) {
                        MyHelpers.setauto_link_array(response.getString("auto_quereka_link")); //link Array
                        MyHelpers.setauto_link_timer(response.getString("auto_quereka_time")); //open Timer
                        MyHelpers.Autolink();
                    }

                    //Back Button
                    MyHelpers.setBackAdsOnOff(response.getString("enable_back_button"));
                    if (response.getString("back_button_counter") != null && !response.getString("back_button_counter").isEmpty()) {
                        MyHelpers.setBackCounter(Integer.parseInt(response.getString("back_button_counter")));  //skip ads number
                    } else {
                        MyHelpers.setBackCounter(5000);
                    }

                    /**
                     * Skip ads
                     * 1 - Inter
                     * 2 - Native
                     * 3 - Banner
                     *
                     * btn number 0 stop ads
                     * */
                    if (response.getString("regular_button_counter") != null && !response.getString("regular_button_counter").isEmpty()) {
                        MyHelpers.setCounter_Inter(Integer.parseInt(response.getString("regular_button_counter")));
                    } else {
                        MyHelpers.setCounter_Inter(5000);
                    }
                    if (response.getString("skip_native_ad") != null && !response.getString("skip_native_ad").isEmpty()) {
                        MyHelpers.setCounter_Native(Integer.parseInt(response.getString("skip_native_ad")));
                    } else {
                        MyHelpers.setCounter_Native(5000);
                    }
                    if (response.getString("skip_banner_ad") != null && !response.getString("skip_banner_ad").isEmpty()) {
                        MyHelpers.setCounter_Banner(Integer.parseInt(response.getString("skip_banner_ad")));
                    } else {
                        MyHelpers.setCounter_Banner(5000);
                    }
                    /**
                     * MIX ads
                     * 1 - Inter
                     * 2 - Native
                     * 3 - Banner
                     * */
                    MyHelpers.setmix_ad_on_off(response.getString("mix_ad"));

                    if (response.getString("mix_ad_counter") != null && !response.getString("mix_ad_counter").isEmpty()) {
                        MyHelpers.setmix_ad_counter(Integer.parseInt(response.getString("mix_ad_counter")));
                    } else {
                        MyHelpers.setmix_ad_counter(5000);
                    }

                    if (response.getString("mix_ad_native") != null && !response.getString("mix_ad_native").isEmpty()) {
                        MyHelpers.setmix_ad_counter_native(Integer.parseInt(response.getString("mix_ad_native")));
                    } else {
                        MyHelpers.setmix_ad_counter_native(5000);
                    }
                    if (response.getString("mix_ad_banner") != null && !response.getString("mix_ad_banner").isEmpty()) {
                        MyHelpers.setmix_ad_counter_banner(Integer.parseInt(response.getString("mix_ad_banner")));
                    } else {
                        MyHelpers.setmix_ad_counter_banner(5000);
                    }

                    /**
                     * Skip Country
                     */
                    MyHelpers.setSkip_country_on_off(response.getString("off_ad_country"));
                    if (MyHelpers.getSkip_country_on_off().equals("1")) {
                        if (response.getString("off_ad_country_name") != null && !response.getString("off_ad_country_name").isEmpty()) {
                            MyHelpers.setSkip_country_list(response.getString("off_ad_country_name"));
                        } else {
                            MyHelpers.setSkip_country_list(null);
                        }
                    }

                    //Extra Data
                    extra_switch_1 = response.getString("extra_switch_1");
                    extra_switch_2 = response.getString("extra_switch_2");
                    extra_switch_3 = response.getString("extra_switch_3");
                    extra_switch_4 = response.getString("extra_switch_4");
                    extra_text_1 = response.getString("extra_text_1");
                    extra_text_2 = response.getString("extra_text_2");
                    extra_text_3 = response.getString("extra_text_3");
                    extra_text_4 = response.getString("extra_text_4");


                    //Open Other apps
                    MyHelpers.setOtherAppsShow(response.getString("replace_app"));
                    MyHelpers.setOtherAppsShowLink(response.getString("new_app_link"));
                    if (MyHelpers.getOtherAppsShow().equals("1")) {
                        MyHelpers.Entery_UpdateApps = 2;
                        context.startActivity(new Intent(context, UpdateAppActivity.class));
                        return;
                    }
                    //Update our apps
                    MyHelpers.setUpdateApps(response.getString("update_app"));
                    MyHelpers.setAppversioncode(response.getString("version_code"));
                    if (MyHelpers.getUpdateApps().equals("1")) {
                        if (!MyHelpers.getAppversioncode().equals(VersonCode)) {
                            MyHelpers.Entery_UpdateApps = 1;
                            context.startActivity(new Intent(context, UpdateAppActivity.class));
                            return;
                        }
                    }

                    //Show View
                    if (MyHelpers.getSkip_country_on_off().equals("1")) {
                        if (CheckCountry(MyHelpers.getSkip_country_list())) {
                            MyHelpers.setGoogleEnable("0");
                            MyHelpers.setFacebookEnable("0");
                            NextIntent(contextx, intentx);
                        } else {
                            ShowADS();
                        }
                    } else {
                        ShowADS();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

            }
        });
    }

    private static void ShowADS() {
        if (MyHelpers.getGoogleEnable().equals("1")) {
            AdsClass.mix_adsInter = 0;
            AdsClass.mix_adsInter_back = 0;
            AdsClass.AutoGoogleInterID = 1;
            AdsClass.GoogleInterstitialAdLoad(contextx);
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                AdsClass.AutoLoadFBInterID = 1;
                AdsClass.FacebookInterLoad(contextx);
            }
            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                AdsClass.AutoLoadFBInterID = 1;
                AdsClass.Google_failed_FacebookInterLoad(contextx);
            }
            try {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        AdsClass.Google_open_failed_Facebook_Open(contextx, intentx);
                    }

                    @Override
                    public void OnAppOpenClose() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        NextIntent(contextx, intentx);
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (MyHelpers.getFacebookEnable().equals("1")) {
            AdsClass.AutoLoadFBInterID = 1;
            AdsClass.mix_adsInter = 1;
            AdsClass.mix_adsInter_back = 1;
            AdsClass.FacebookInterLoad(contextx);
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                AdsClass.AutoLoadFBInterID = 1;
                AdsClass.FacebookInterLoad(contextx);
            }
            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                AdsClass.AutoGoogleInterID = 1;
                AdsClass.GoogleInterstitialAdLoad(contextx);
            }
            AdsClass.Facebook_Open(contextx, intentx);

        } else {
            NextIntent(contextx, intentx);
        }
    }

    public static void NextIntent(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static String getCountryCode() {
        TelephonyManager tm = (TelephonyManager) contextx.getSystemService(contextx.getApplicationContext().TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }

    public static Boolean CheckCountry(String Country_name) {
        try {
            List<String> COUNTRY = new ArrayList<String>(Arrays.asList(Country_name.split(",")));
            String tm = getCountryCode();
            for (int i = 0; i < COUNTRY.size(); i++) {
                if (COUNTRY.get(i).equals(tm)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
