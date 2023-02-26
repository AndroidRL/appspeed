package com.appwood.mylibrarys;

import static ProMex.classs.Utils.Util.DEc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.criteo.publisher.Criteo;
import com.criteo.publisher.CriteoErrorCode;
import com.criteo.publisher.CriteoInitException;
import com.criteo.publisher.CriteoInterstitial;
import com.criteo.publisher.CriteoInterstitialAdListener;
import com.criteo.publisher.model.InterstitialAdUnit;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ProMex.classs.Utils.Util;
import ProMex.classs.Utils.apiii;
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
    public static String PackName;

    public static ArrayList<AdsModal> adsModals = new ArrayList<>();

    public static CriteoInterstitial Criteosinterstitial = null;

    public static boolean customads_status = false;
    public static boolean OpenAdsStatus = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    public static void splash_next(String packageName, String VersonCode, Context context, Intent intent) {
        PackName = packageName;
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

                    /**
                     * Google
                     */
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
                    //google Native Btn Name
                    if (response.getString("google_button_name") != null && !response.getString("google_button_name").isEmpty()) {
                        MyHelpers.setGooglebutton_name(response.getString("google_button_name"));
                    } else {
                        MyHelpers.setGooglebutton_name(null);
                    }
                    //google Native Btn color
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
                        InterClass.AutoGoogleInterID = 1;
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
                    /**
                     * Facebook
                     */
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

                    /**
                     *  Atme and qureka Link
                     */
                    /*auto link*/
                    MyHelpers.setauto_link_on_off(response.getString("enable_auto_quereka_link"));  //on_off Auto link
                    if (MyHelpers.getauto_link_on_off().equals("1")) {
                        MyHelpers.setauto_link_array(response.getString("auto_quereka_link")); //link Array
                        MyHelpers.setauto_link_timer(response.getString("auto_quereka_time")); //open Timer
                        MyHelpers.Autolink();
                    }
                    /*btn link*/
                    MyHelpers.set_q_link_btn_on_off(response.getString("enable_quereka_link"));  //on_off Q link btn
                    if (MyHelpers.get_q_link_btn_on_off().equals("1")) {
                        MyHelpers.set_q_link_array(response.getString("quereka_link")); //link Array
                    }

                    /**
                     * App Lovin
                     */
                    MyHelpers.setAppLovinEnable(response.getString("enable_applovin_id"));  //on_off App Lovin
                    if (response.getString("applovin_banner") != null && !response.getString("applovin_banner").isEmpty()) {   //Banner
                        MyHelpers.setAppLovinBanner(response.getString("applovin_banner"));
                    } else {
                        MyHelpers.setAppLovinBanner(null);
                    }
                    if (response.getString("applovin_native") != null && !response.getString("applovin_native").isEmpty()) {   //Native
                        MyHelpers.setAppLovinNative(response.getString("applovin_native"));
                    } else {
                        MyHelpers.setAppLovinNative(null);
                    }
                    if (response.getString("applovin_interstitial") != null && !response.getString("applovin_interstitial").isEmpty()) {   //Inter
                        MyHelpers.setAppLovinInter(response.getString("applovin_interstitial"));

                    } else {
                        MyHelpers.setAppLovinInter(null);
                    }

                    /**
                     * Criteo
                     */
                    MyHelpers.setCriteoEnable(response.getString("enable_appnext_id"));  //on_off Criteo
                    if (response.getString("appnext_id_1") != null && !response.getString("appnext_id_1").isEmpty()) {   //Criteo Banner
                        String[] Criteo_appid_bannerid = response.getString("appnext_id_1").split(",");
                        MyHelpers.setCriteoAppID(Criteo_appid_bannerid[0]);
                        MyHelpers.setCriteoBanner(Criteo_appid_bannerid[1]);
                        IntegationADS();
                    } else {
                        MyHelpers.setCriteoBanner(null);
                        MyHelpers.setCriteoAppID(null);
                    }
                    if (response.getString("appnext_id_2") != null && !response.getString("appnext_id_2").isEmpty()) {   //Criteo Native
                        MyHelpers.setCriteoNative(response.getString("appnext_id_2"));
                    } else {
                        MyHelpers.setCriteoNative(null);
                    }
                    if (response.getString("appnext_id_3") != null && !response.getString("appnext_id_3").isEmpty()) {   //Criteo Inter
                        MyHelpers.setCriteoInter(response.getString("appnext_id_3"));
                    } else {
                        MyHelpers.setCriteoInter(null);
                    }

                    /**
                     *
                     * Unity
                     */
                    MyHelpers.setUnityEnable(response.getString("enable_unity_id"));  //on_off Unity
                    if (response.getString("unity_game_id") != null && !response.getString("unity_game_id").isEmpty()) {   //Unity ID
                        MyHelpers.setUnityAppID(response.getString("unity_game_id"));
                        UnityAds.initialize(MyHelpers.instance, MyHelpers.getUnityAppID(), false);
                    } else {
                        MyHelpers.setUnityAppID(null);
                    }
                    if (response.getString("unity_banner") != null && !response.getString("unity_banner").isEmpty()) { //Unity Banner ID
                        MyHelpers.setUnityBannerID(response.getString("unity_banner"));
                    } else {
                        MyHelpers.setUnityBannerID(null);
                    }
                    if (response.getString("unity_interstitial") != null && !response.getString("unity_interstitial").isEmpty()) {  //Unity Inter ID
                        MyHelpers.setUnityInterID(response.getString("unity_interstitial"));
                    } else {
                        MyHelpers.setUnityInterID(null);
                    }

                    /**
                     * Custom
                     */
                    MyHelpers.setCustomEnable(response.getString("custom_ads_switch"));  //on_off Custom ads

                    /**
                     * Button
                     */
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

                    if (response.getString("mix_ad_name") != null && !response.getString("mix_ad_name").isEmpty()) {
                        MyHelpers.setmix_ad_name(response.getString("mix_ad_name"));
                        String[] Ads_number = MyHelpers.getmix_ad_name().split(",");
                        MyHelpers.setmix_ad_banner(Ads_number[0]);
                        MyHelpers.setmix_ad_native(Ads_number[1]);
                        MyHelpers.setmix_ad_inter(Ads_number[2]);
                    } else {
                        MyHelpers.setmix_ad_name(null);
                    }
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

                    /**
                     * App Live Status
                     */
                    MyHelpers.setlive_status(response.getString("live"));
                    /**
                     * Extra data
                     */
                    extra_switch_1 = response.getString("extra_switch_1");
                    extra_switch_2 = response.getString("extra_switch_2");
                    extra_switch_3 = response.getString("extra_switch_3");
                    extra_switch_4 = response.getString("extra_switch_4");
                    extra_text_1 = response.getString("extra_text_1");
                    extra_text_2 = response.getString("extra_text_2");
                    extra_text_3 = response.getString("extra_text_3");
                    extra_text_4 = response.getString("extra_text_4");

                    /**
                     * Other App Open
                     */
                    MyHelpers.setOtherAppsShow(response.getString("replace_app"));
                    MyHelpers.setOtherAppsShowLink(response.getString("new_app_link"));
                    if (MyHelpers.getOtherAppsShow().equals("1")) {
                        MyHelpers.Entery_UpdateApps = 2;
                        context.startActivity(new Intent(context, UpdateAppActivity.class));
                        return;
                    }

                    /**
                     * Update Our App
                     */
                    MyHelpers.setUpdateApps(response.getString("update_app"));
                    MyHelpers.setAppversioncode(response.getString("version_code"));
                    if (MyHelpers.getUpdateApps().equals("1")) {
                        if (!MyHelpers.getAppversioncode().equals(VersonCode)) {
                            MyHelpers.Entery_UpdateApps = 1;
                            context.startActivity(new Intent(context, UpdateAppActivity.class));
                            return;
                        }
                    }

                    /**
                     * Only google mix
                     */
                    if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                        //Inter Ads
                        if (MyHelpers.getGoogleInter().equals(MyHelpers.getGoogleInter1()) && MyHelpers.getGoogleInter().equals(MyHelpers.getGoogleInter2()) && MyHelpers.getGoogleInter1().equals(MyHelpers.getGoogleInter2())) {
                            MyHelpers.Google_inter_number = 1;
                            InterClass.AutoGoogleInterID = 1;
                            InterClass.GoogleInterstitialAdLoad(contextx);
                        } else {
                            if (MyHelpers.getGoogleInter2() == null) {
                                MyHelpers.Google_inter_number = 2;
                                InterClass.GoogleInterstitialAdLoad1(contextx);
                                InterClass.GoogleInterstitialAdLoad2(contextx);
                            } else {
                                MyHelpers.Google_inter_number = 3;
                                InterClass.GoogleInterstitialAdLoad1(contextx);
                                InterClass.GoogleInterstitialAdLoad2(contextx);
                                InterClass.GoogleInterstitialAdLoad3(contextx);
                            }
                        }
                        //Native ADS
                        if (MyHelpers.getGoogleNative().equals(MyHelpers.getGoogleNative1()) && MyHelpers.getGoogleNative().equals(MyHelpers.getGoogleNative2()) && MyHelpers.getGoogleNative1().equals(MyHelpers.getGoogleNative2())) {
                            MyHelpers.Google_native_number = 1;
                        } else {
                            if (MyHelpers.getGoogleNative2() == null) {
                                MyHelpers.Google_native_number = 2;
                            } else {
                                MyHelpers.Google_native_number = 3;
                            }
                        }
                    }

                    /**
                     * Next App
                     */
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
        if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            try {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        FailsAds("g");
                    }

                    @Override
                    public void OnAppOpenClose() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {

            InterClass.AutoLoadFBInterID = 1;
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    FailsAds("f");
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                    NextIntent(contextx, intentx);
                }

                @Override
                public void onAdClicked(MaxAd ad) {


                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    FailsAds("a");
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();

        } else if (MyHelpers.getCriteoEnable().equals("1")) {

            Criteosinterstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
            Criteosinterstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                @Override
                public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                    if (Criteosinterstitial.isAdLoaded()) {
                        Criteosinterstitial.show();
                    }
                }

                @Override
                public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                    FailsAds("c");
                }

                @Override
                public void onAdClicked() {
                }

                @Override
                public void onAdOpened() {

                }

                @Override
                public void onAdClosed() {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onAdLeftApplication() {
                }
            });
            Criteosinterstitial.loadAd();

        } else if (MyHelpers.getUnityEnable().equals("1")) {

            UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String placementId) {
                    UnityAds.show((Activity) contextx, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                        @Override
                        public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                        }

                        @Override
                        public void onUnityAdsShowStart(String placementId) {


                        }

                        @Override
                        public void onUnityAdsShowClick(String placementId) {

                        }

                        @Override
                        public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                            NextIntent(contextx, intentx);

                        }
                    });
                }

                @Override
                public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                    FailsAds("u");
                }
            });

        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustomIntent();
        } else {
            NextIntent(contextx, intentx);
        }
    }

    private static void CustomIntent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (customads_status) {
                    MyHelpers.CustomIntent = intentx;
                    contextx.startActivity(new Intent(contextx, CustomAdsInterActivity.class));
                } else {
                    CustomIntent();
                }
            }
        }, 1000);
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

    public static void APICalls() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader(DEc(Util.pizzuhead), DEc(Util.pizzudians));
        asyncHttpClient.get(DEc(Util.custom) + PackName, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject firstEvent = (JSONObject) response.get(i);
                        adsModals.add(new AdsModal(firstEvent.getString("app_name"), firstEvent.getString("enable_ads"), firstEvent.getString("ad_app_name"), firstEvent.getString("app_description"), firstEvent.getString("app_logo"), firstEvent.getString("app_banner"), firstEvent.getString("extra_switch_1"), firstEvent.getString("extra_switch_2"), firstEvent.getString("extra_switch_3"), firstEvent.getString("extra_switch_4"), firstEvent.getString("extra_text_1"), firstEvent.getString("extra_text_2"), firstEvent.getString("extra_text_3"), firstEvent.getString("extra_text_4")));
                    }
                    customads_status = true;
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

    /**
     * Fails Ads
     */
    public static void FailsAds(String Skip) {

        if (Skip.equals("g")) {
            if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        //Fail Code

                        GoogleandFacebookFails();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (interstitialAd_FB_1 != null) {
                            interstitialAd_FB_1.show();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());
            } else {
                GoogleandFacebookFails();
            }
        } else if (Skip.equals("f")) {
            if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        GoogleandFacebookFails();
                    }

                    @Override
                    public void OnAppOpenClose() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
            } else {
                GoogleandFacebookFails();
            }

        } else if (Skip.equals("c")) {
            if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty()) {
                            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
                            InterstitialAdListener adListener = new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                    //Fail Code
                                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                                        interstitialAd.setListener(new MaxAdListener() {
                                            @Override
                                            public void onAdLoaded(MaxAd ad) {
                                                if (interstitialAd.isReady()) {
                                                    interstitialAd.showAd();
                                                }
                                            }

                                            @Override
                                            public void onAdDisplayed(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdHidden(MaxAd ad) {
                                                NextIntent(contextx, intentx);
                                            }

                                            @Override
                                            public void onAdClicked(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                                //Fail Code
                                                if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                                    FailsAdsUnityShow();
                                                } else {
                                                    CustomIntent();
                                                }
                                            }

                                            @Override
                                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                                //
                                            }
                                        });
                                        interstitialAd.loadAd();
                                    } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                        FailsAdsUnityShow();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    if (interstitialAd_FB_1 != null) {
                                        interstitialAd_FB_1.show();
                                    }
                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            };
                            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

                        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                            interstitialAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(MaxAd ad) {
                                    if (interstitialAd.isReady()) {
                                        interstitialAd.showAd();
                                    }
                                }

                                @Override
                                public void onAdDisplayed(MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(MaxAd ad) {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdClicked(MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(String adUnitId, MaxError error) {
                                    //Fail Code
                                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                        FailsAdsUnityShow();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                    //
                                }
                            });
                            interstitialAd.loadAd();
                        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void OnAppOpenClose() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);

            } else if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        //Fail Code
                        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                            interstitialAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(MaxAd ad) {
                                    if (interstitialAd.isReady()) {
                                        interstitialAd.showAd();
                                    }
                                }

                                @Override
                                public void onAdDisplayed(MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(MaxAd ad) {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdClicked(MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(String adUnitId, MaxError error) {
                                    //Fail Code
                                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                        FailsAdsUnityShow();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                    //
                                }
                            });
                            interstitialAd.loadAd();
                        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (interstitialAd_FB_1 != null) {
                            interstitialAd_FB_1.show();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

            } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {

                MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                interstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                        }
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        //Fail Code
                        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                        //
                    }
                });
                interstitialAd.loadAd();

            } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                FailsAdsUnityShow();
            } else {
                CustomIntent();
            }

        } else if (Skip.equals("a")) {

            if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty()) {
                            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
                            InterstitialAdListener adListener = new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                    //Fail Code
                                    if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {
                                        CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
                                        interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                                            @Override
                                            public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                                                if (interstitial.isAdLoaded()) {
                                                    interstitial.show();
                                                }
                                            }

                                            @Override
                                            public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                                                if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                                    FailsAdsUnityShow();
                                                } else {
                                                    CustomIntent();
                                                }
                                            }

                                            @Override
                                            public void onAdClicked() {
                                            }

                                            @Override
                                            public void onAdOpened() {
                                            }

                                            @Override
                                            public void onAdClosed() {
                                                NextIntent(contextx, intentx);
                                            }

                                            @Override
                                            public void onAdLeftApplication() {
                                            }
                                        });
                                        interstitial.loadAd();
                                    } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                        FailsAdsUnityShow();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    if (interstitialAd_FB_1 != null) {
                                        interstitialAd_FB_1.show();
                                    }
                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            };
                            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

                        } else if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {
                            CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
                            interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                                @Override
                                public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                                    if (interstitial.isAdLoaded()) {
                                        interstitial.show();
                                    }
                                }

                                @Override
                                public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                        FailsAdsUnityShow();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdClicked() {
                                }

                                @Override
                                public void onAdOpened() {
                                }

                                @Override
                                public void onAdClosed() {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdLeftApplication() {
                                }
                            });
                            interstitial.loadAd();
                        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void OnAppOpenClose() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);

            } else if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        NextIntent(contextx, intentx);
                    }


                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        //Fail Code
                        if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {
                            CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
                            interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                                @Override
                                public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                                    if (interstitial.isAdLoaded()) {
                                        interstitial.show();
                                    }
                                }

                                @Override
                                public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                        FailsAdsUnityShow();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdClicked() {
                                }

                                @Override
                                public void onAdOpened() {
                                }

                                @Override
                                public void onAdClosed() {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdLeftApplication() {
                                }
                            });
                            interstitial.loadAd();
                        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (interstitialAd_FB_1 != null) {
                            interstitialAd_FB_1.show();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

            } else if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {
                CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
                interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                    @Override
                    public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                        if (interstitial.isAdLoaded()) {
                            interstitial.show();
                        }
                    }

                    @Override
                    public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void onAdClicked() {
                    }

                    @Override
                    public void onAdOpened() {
                    }

                    @Override
                    public void onAdClosed() {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onAdLeftApplication() {
                    }
                });
                interstitial.loadAd();
            } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                FailsAdsUnityShow();
            } else {
                CustomIntent();
            }


        } else if (Skip.equals("u")) {

            if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty()) {
                            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
                            InterstitialAdListener adListener = new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                    //Fail Code
                                    if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {
                                        CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
                                        interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                                            @Override
                                            public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                                                if (interstitial.isAdLoaded()) {
                                                    interstitial.show();
                                                }
                                            }

                                            @Override
                                            public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                                                if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                                    MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                                                    interstitialAd.setListener(new MaxAdListener() {
                                                        @Override
                                                        public void onAdLoaded(MaxAd ad) {
                                                            if (interstitialAd.isReady()) {
                                                                interstitialAd.showAd();
                                                            }
                                                        }

                                                        @Override
                                                        public void onAdDisplayed(MaxAd ad) {

                                                        }

                                                        @Override
                                                        public void onAdHidden(MaxAd ad) {
                                                            NextIntent(contextx, intentx);
                                                        }

                                                        @Override
                                                        public void onAdClicked(MaxAd ad) {

                                                        }

                                                        @Override
                                                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                                                            //Fail Code
                                                            CustomIntent();
                                                        }

                                                        @Override
                                                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                                            //
                                                        }
                                                    });
                                                    interstitialAd.loadAd();
                                                } else {
                                                    CustomIntent();
                                                }
                                            }

                                            @Override
                                            public void onAdClicked() {
                                            }

                                            @Override
                                            public void onAdOpened() {
                                            }

                                            @Override
                                            public void onAdClosed() {
                                                NextIntent(contextx, intentx);
                                            }

                                            @Override
                                            public void onAdLeftApplication() {
                                            }
                                        });
                                        interstitial.loadAd();
                                    } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                                        interstitialAd.setListener(new MaxAdListener() {
                                            @Override
                                            public void onAdLoaded(MaxAd ad) {
                                                if (interstitialAd.isReady()) {
                                                    interstitialAd.showAd();
                                                }
                                            }

                                            @Override
                                            public void onAdDisplayed(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdHidden(MaxAd ad) {
                                                NextIntent(contextx, intentx);
                                            }

                                            @Override
                                            public void onAdClicked(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                                //Fail Code
                                                CustomIntent();
                                            }

                                            @Override
                                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                                //
                                            }
                                        });
                                        interstitialAd.loadAd();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    if (interstitialAd_FB_1 != null) {
                                        interstitialAd_FB_1.show();
                                    }
                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            };
                            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

                        } else if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {
                            CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
                            interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                                @Override
                                public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                                    if (interstitial.isAdLoaded()) {
                                        interstitial.show();
                                    }
                                }

                                @Override
                                public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                                        interstitialAd.setListener(new MaxAdListener() {
                                            @Override
                                            public void onAdLoaded(MaxAd ad) {
                                                if (interstitialAd.isReady()) {
                                                    interstitialAd.showAd();
                                                }
                                            }

                                            @Override
                                            public void onAdDisplayed(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdHidden(MaxAd ad) {
                                                NextIntent(contextx, intentx);
                                            }

                                            @Override
                                            public void onAdClicked(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                                //Fail Code
                                                CustomIntent();
                                            }

                                            @Override
                                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                                //
                                            }
                                        });
                                        interstitialAd.loadAd();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdClicked() {
                                }

                                @Override
                                public void onAdOpened() {
                                }

                                @Override
                                public void onAdClosed() {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdLeftApplication() {
                                }
                            });
                            interstitial.loadAd();
                        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                            interstitialAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(MaxAd ad) {
                                    if (interstitialAd.isReady()) {
                                        interstitialAd.showAd();
                                    }
                                }

                                @Override
                                public void onAdDisplayed(MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(MaxAd ad) {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdClicked(MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(String adUnitId, MaxError error) {
                                    //Fail Code
                                    CustomIntent();
                                }

                                @Override
                                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                    //
                                }
                            });
                            interstitialAd.loadAd();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void OnAppOpenClose() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);

            } else if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        //Fail Code
                        if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {
                            CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
                            interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                                @Override
                                public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                                    if (interstitial.isAdLoaded()) {
                                        interstitial.show();
                                    }
                                }

                                @Override
                                public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                                        interstitialAd.setListener(new MaxAdListener() {
                                            @Override
                                            public void onAdLoaded(MaxAd ad) {
                                                if (interstitialAd.isReady()) {
                                                    interstitialAd.showAd();
                                                }
                                            }

                                            @Override
                                            public void onAdDisplayed(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdHidden(MaxAd ad) {
                                                NextIntent(contextx, intentx);
                                            }

                                            @Override
                                            public void onAdClicked(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                                //Fail Code
                                                CustomIntent();
                                            }

                                            @Override
                                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                                //
                                            }
                                        });
                                        interstitialAd.loadAd();
                                    } else {
                                        CustomIntent();
                                    }
                                }

                                @Override
                                public void onAdClicked() {
                                }

                                @Override
                                public void onAdOpened() {
                                }

                                @Override
                                public void onAdClosed() {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdLeftApplication() {
                                }
                            });
                            interstitial.loadAd();
                        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                            interstitialAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(MaxAd ad) {
                                    if (interstitialAd.isReady()) {
                                        interstitialAd.showAd();
                                    }
                                }

                                @Override
                                public void onAdDisplayed(MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(MaxAd ad) {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdClicked(MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(String adUnitId, MaxError error) {
                                    //Fail Code
                                    CustomIntent();
                                }

                                @Override
                                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                    //
                                }
                            });
                            interstitialAd.loadAd();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (interstitialAd_FB_1 != null) {
                            interstitialAd_FB_1.show();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

            } else if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {
                CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
                interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                    @Override
                    public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                        if (interstitial.isAdLoaded()) {
                            interstitial.show();
                        }
                    }

                    @Override
                    public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {
                        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                            interstitialAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(MaxAd ad) {
                                    if (interstitialAd.isReady()) {
                                        interstitialAd.showAd();
                                    }
                                }

                                @Override
                                public void onAdDisplayed(MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(MaxAd ad) {
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdClicked(MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(String adUnitId, MaxError error) {
                                    //Fail Code
                                    CustomIntent();
                                }

                                @Override
                                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                    //
                                }
                            });
                            interstitialAd.loadAd();
                        } else {
                            CustomIntent();
                        }
                    }

                    @Override
                    public void onAdClicked() {
                    }

                    @Override
                    public void onAdOpened() {
                    }

                    @Override
                    public void onAdClosed() {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onAdLeftApplication() {
                    }
                });
                interstitial.loadAd();
            } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                interstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                        }
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        //Fail Code
                        CustomIntent();
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                        //
                    }
                });
                interstitialAd.loadAd();
            } else {
                CustomIntent();
            }

        }
    }

    private static void GoogleandFacebookFails() {

        if (MyHelpers.getCriteoInter() != null && !MyHelpers.getCriteoInter().isEmpty()) {

            CriteoInterstitial interstitial = new CriteoInterstitial(new InterstitialAdUnit(MyHelpers.getCriteoInter()));
            interstitial.setCriteoInterstitialAdListener(new CriteoInterstitialAdListener() {
                @Override
                public void onAdReceived(CriteoInterstitial criteoInterstitial) {
                    if (interstitial.isAdLoaded()) {
                        interstitial.show();
                    }
                }

                @Override
                public void onAdFailedToReceive(CriteoErrorCode criteoErrorCode) {

                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {

                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                        interstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (interstitialAd.isReady()) {
                                    interstitialAd.showAd();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                NextIntent(contextx, intentx);
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                //Fail Code
                                if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                    FailsAdsUnityShow();
                                } else {
                                    CustomIntent();
                                }
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                //
                            }
                        });
                        interstitialAd.loadAd();
                    } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {

                        FailsAdsUnityShow();
                    } else {
                        CustomIntent();
                    }
                }

                @Override
                public void onAdClicked() {
                }

                @Override
                public void onAdOpened() {
                }

                @Override
                public void onAdClosed() {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onAdLeftApplication() {
                }
            });
            interstitial.loadAd();
        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {

            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    //Fail Code
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        FailsAdsUnityShow();
                    } else {
                        CustomIntent();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    //
                }
            });
            interstitialAd.loadAd();
        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {

            FailsAdsUnityShow();
        } else {
            CustomIntent();
        }
    }

    private static void FailsAdsUnityShow() {
        UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show((Activity) contextx, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                    }

                    @Override
                    public void onUnityAdsShowStart(String placementId) {


                    }

                    @Override
                    public void onUnityAdsShowClick(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                        NextIntent(contextx, intentx);

                    }
                });
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                CustomIntent();
            }
        });
    }

    public static void IntegationADS() {
        /*CRITEO Ads*/
        try {
            new Criteo.Builder(MyHelpers.instance, MyHelpers.getCriteoAppID()).init();
        } catch (CriteoInitException e) {
        }
    }


}
