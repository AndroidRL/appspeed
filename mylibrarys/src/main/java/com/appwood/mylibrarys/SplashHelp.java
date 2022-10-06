package com.appwood.mylibrarys;

import static ProMex.classs.Utils.Util.DEc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import ProMex.classs.Utils.Util;
import cz.msebera.android.httpclient.Header;

public class SplashHelp {

    public static String extra_switch_1;
    public static String extra_switch_2;
    public static String extra_switch_3;
    public static String extra_switch_4;
    public static String extra_text_1;
    public static String extra_text_2;
    public static String extra_text_3;
    public static String extra_text_4;


    /*Splash*/
    public static void splash_next(String packageName, String VersonCode, Context context, Intent intent) {

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
                    if (MyHelpers.getGoogleEnable().equals("1")) {
                        MyHelpers.SetGoogleBanner(response.getString("google_admob_banner_id"));
                        String[] Google_native_split = response.getString("google_admob_native_id").split(",");
                        if (Google_native_split.length == 1) {
                            MyHelpers.SetGoogleNative(Google_native_split[0]);
                            MyHelpers.SetGoogleNative1(Google_native_split[0]);
                            MyHelpers.SetGoogleNative2(Google_native_split[0]);
                        } else if (Google_native_split.length == 2) {
                            MyHelpers.SetGoogleNative(Google_native_split[0]);
                            MyHelpers.SetGoogleNative1(Google_native_split[1]);
                            MyHelpers.SetGoogleNative2(Google_native_split[0]);
                        } else if (Google_native_split.length == 3) {
                            MyHelpers.SetGoogleNative(Google_native_split[0]);
                            MyHelpers.SetGoogleNative1(Google_native_split[1]);
                            MyHelpers.SetGoogleNative2(Google_native_split[2]);
                        }
                        // color & name set
                        MyHelpers.setGooglebutton_name(response.getString("google_button_name"));
                        if (response.getString("google_button_color") != null && !response.getString("google_button_color").isEmpty()) {
                            MyHelpers.setGooglebutton_color(response.getString("google_button_color"));
                        } else {
                            MyHelpers.setGooglebutton_color("#000000");
                        }
                    }
                    if (response.getString("google_open_id") != null && !response.getString("google_open_id").isEmpty()) {
                        MyHelpers.setGoogle_OpenADS(response.getString("google_open_id"));
                    }
                    String[] Google_inter_split = response.getString("google_admob_interstitial_id").split(",");
                    if (Google_inter_split.length == 1) {
                        MyHelpers.SetGoogleInter(Google_inter_split[0]);
                        MyHelpers.SetGoogleInter1(Google_inter_split[0]);
                        MyHelpers.SetGoogleInter2(Google_inter_split[0]);
                    } else if (Google_inter_split.length == 2) {
                        MyHelpers.SetGoogleInter(Google_inter_split[0]);
                        MyHelpers.SetGoogleInter1(Google_inter_split[1]);
                        MyHelpers.SetGoogleInter2(Google_inter_split[0]);
                    } else if (Google_inter_split.length == 3) {
                        MyHelpers.SetGoogleInter(Google_inter_split[0]);
                        MyHelpers.SetGoogleInter1(Google_inter_split[1]);
                        MyHelpers.SetGoogleInter2(Google_inter_split[2]);
                    }


                    //facebook
                    MyHelpers.setFacebookEnable(response.getString("enable_facebook_id"));
                    if (MyHelpers.getFacebookEnable().equals("1")) {
                        String[] Facebook_banner_split = response.getString("facebook_banner_id").split(",");
                        if (Facebook_banner_split.length == 1) {
                            MyHelpers.setFacebookBanner(Facebook_banner_split[0]);
                            MyHelpers.setFacebookBanner1(Facebook_banner_split[0]);
                            MyHelpers.setFacebookBanner2(Facebook_banner_split[0]);
                        } else if (Facebook_banner_split.length == 2) {
                            MyHelpers.setFacebookBanner(Facebook_banner_split[0]);
                            MyHelpers.setFacebookBanner1(Facebook_banner_split[1]);
                            MyHelpers.setFacebookBanner2(Facebook_banner_split[0]);
                        } else if (Facebook_banner_split.length == 3) {
                            MyHelpers.setFacebookBanner(Facebook_banner_split[0]);
                            MyHelpers.setFacebookBanner1(Facebook_banner_split[1]);
                            MyHelpers.setFacebookBanner2(Facebook_banner_split[2]);
                        }
                        String[] Facebook_native_split = response.getString("facebook_native_id").split(",");
                        if (Facebook_native_split.length == 1) {
                            MyHelpers.SetFacebookNative(Facebook_native_split[0]);
                            MyHelpers.SetFacebookNative1(Facebook_native_split[0]);
                            MyHelpers.SetFacebookNative2(Facebook_native_split[0]);
                        } else if (Facebook_native_split.length == 2) {
                            MyHelpers.SetFacebookNative(Facebook_native_split[0]);
                            MyHelpers.SetFacebookNative1(Facebook_native_split[1]);
                            MyHelpers.SetFacebookNative2(Facebook_native_split[0]);
                        } else if (Facebook_native_split.length == 3) {
                            MyHelpers.SetFacebookNative(Facebook_native_split[0]);
                            MyHelpers.SetFacebookNative1(Facebook_native_split[1]);
                            MyHelpers.SetFacebookNative2(Facebook_native_split[2]);
                        }
                    }
                    if (response.getString("facebook_open_id") != null && !response.getString("facebook_open_id").isEmpty()) {
                        MyHelpers.setfacebook_open_ad_id(response.getString("facebook_open_id"));
                    }
                    String[] Facebook_inter_split = response.getString("facebook_interstitial_id").split(",");
                    if (Facebook_inter_split.length == 1) {
                        MyHelpers.SetFacebookInter(Facebook_inter_split[0]);
                        MyHelpers.SetFacebookInter1(Facebook_inter_split[0]);
                        MyHelpers.SetFacebookInter2(Facebook_inter_split[0]);
                    } else if (Facebook_inter_split.length == 2) {
                        MyHelpers.SetFacebookInter(Facebook_inter_split[0]);
                        MyHelpers.SetFacebookInter1(Facebook_inter_split[1]);
                        MyHelpers.SetFacebookInter2(Facebook_inter_split[0]);
                    } else if (Facebook_inter_split.length == 3) {
                        MyHelpers.SetFacebookInter(Facebook_inter_split[0]);
                        MyHelpers.SetFacebookInter1(Facebook_inter_split[1]);
                        MyHelpers.SetFacebookInter2(Facebook_inter_split[2]);
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
                    if (response.getString("regular_button_counter") != null && !response.getString("regular_button_counter").isEmpty()) {
                        MyHelpers.setCounter(Integer.parseInt(response.getString("regular_button_counter")));  //skip ads number
                    } else {
                        MyHelpers.setCounter(5000);
                    }

                    //MixAds
                    MyHelpers.setmix_ad_on_off(response.getString("extra_switch_1"));

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

                    if (MyHelpers.getGoogleEnable().equals("1")) {
                        AdsClass.mix_adsInter = 0;
                        AdsClass.mix_adsInter_back = 0;

                        AdsClass.AutoGoogleInterID = 1;
                        AdsClass.GoogleInterstitialAdLoad(context);
                        if (MyHelpers.getmix_ad_on_off().equals("1")) {
                            AdsClass.AutoLoadFBInterID = 1;
                            AdsClass.FacebookInterLoad(context);
                        }
                        if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                            AdsClass.AutoLoadFBInterID = 1;
                            AdsClass.Google_failed_FacebookInterLoad(context);
                        }
                        try {
                            AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                                public void onAppOpenAdLoaded(AppOpenAd appOpenAd) {
                                    appOpenAd.show((Activity) context, new FullScreenContentCallback() {
                                        public void onAdShowedFullScreenContent() {
                                        }

                                        public void onAdDismissedFullScreenContent() {
                                            NextIntent(context, intent);
                                        }

                                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                            AdsClass.Google_open_failed_Facebook_Open(context, intent);
                                        }
                                    });
                                }

                                public void onAppOpenAdFailedToLoad(LoadAdError loadAdError) {
                                    AdsClass.Google_open_failed_Facebook_Open(context, intent);

                                }
                            };
                            AppOpenAd.load((Context) context, MyHelpers.getGoogle_OpenADS(), new AdRequest.Builder().build(), 1, loadCallback);
                            MyHelpers.appOpenManager = new AppOpenManager(MyHelpers.getInstance());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (MyHelpers.getFacebookEnable().equals("1")) {
                        AdsClass.AutoLoadFBInterID = 1;
                        AdsClass.mix_adsInter = 1;
                        AdsClass.mix_adsInter_back = 1;
                        AdsClass.FacebookInterLoad(context);
                        if (MyHelpers.getmix_ad_on_off().equals("1")) {
                            AdsClass.AutoLoadFBInterID = 1;
                            AdsClass.FacebookInterLoad(context);
                        }
                        if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                            AdsClass.AutoGoogleInterID = 1;
                            AdsClass.GoogleInterstitialAdLoad(context);
                        }
                        AdsClass.Facebook_Open(context, intent);

                    } else {
                        NextIntent(context, intent);
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

    public static void NextIntent(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).finish();
    }


}
