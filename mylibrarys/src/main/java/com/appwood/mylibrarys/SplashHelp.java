package com.appwood.mylibrarys;

import static ProMex.classs.Utils.Util.DEc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ProMex.classs.Utils.Util;
import cz.msebera.android.httpclient.Header;
import top.oneconnectapi.app.OpenVpnApi;

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
    public static Runnable myRunnable;

    public static ArrayList<Countries> free_servers = new ArrayList<>();
    public static Countries selectedCountry = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    /*Splash*/
    public static void splash_next(String packageName, String VersonCode, Context context, Intent intent) {
        Handler handler = new Handler();
        myRunnable = new Runnable() {
            public void run() {
                if (MyHelpers.getCallBacks().equals("Yes")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ShowADS();
                        }
                    },2000);
                } else {
                    handler.postDelayed(myRunnable, 1000);
                }
            }
        };
        handler.postDelayed(myRunnable, 1000);

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
                    if (MyHelpers.getGoogleEnable().equals("1")) {
                        //banner
                        MyHelpers.SetGoogleBanner(response.getString("google_admob_banner_id"));
                        MyHelpers.SetGoogleBanner1(response.getString("google_admob_banner_id_1"));
                        MyHelpers.SetGoogleBanner2(response.getString("google_admob_banner_id_2"));
                        //native
                        MyHelpers.SetGoogleNative(response.getString("google_admob_native_id"));
                        MyHelpers.SetGoogleNative1(response.getString("google_admob_native_id_1"));
                        MyHelpers.SetGoogleNative2(response.getString("google_admob_native_id_2"));

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
                    } else {
                        MyHelpers.setGoogle_OpenADS(null);
                    }
                    //google inter
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
                    //facebook
                    MyHelpers.setFacebookEnable(response.getString("enable_facebook_id"));
                    if (MyHelpers.getFacebookEnable().equals("1")) {
                        //banner
                        MyHelpers.setFacebookBanner(response.getString("facebook_banner_id"));
                        MyHelpers.setFacebookBanner1(response.getString("facebook_banner_id_1"));
                        MyHelpers.setFacebookBanner2(response.getString("facebook_banner_id_2"));

                        //native
                        MyHelpers.SetFacebookNative(response.getString("facebook_native_id"));
                        MyHelpers.SetFacebookNative1(response.getString("facebook_native_id_1"));
                        MyHelpers.SetFacebookNative2(response.getString("facebook_native_id_2"));
                    }
                    if (response.getString("facebook_open_id") != null && !response.getString("facebook_open_id").isEmpty()) {
                        MyHelpers.setfacebook_open_ad_id(response.getString("facebook_open_id"));
                    } else {
                        MyHelpers.setfacebook_open_ad_id(null);
                    }
                    //facebook inter
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
                    if (response.getString("regular_button_counter") != null && !response.getString("regular_button_counter").isEmpty()) {
                        MyHelpers.setCounter(Integer.parseInt(response.getString("regular_button_counter")));  //skip ads number
                    } else {
                        MyHelpers.setCounter(5000);
                    }

                    //MixAds
                    MyHelpers.setmix_ad_on_off(response.getString("mix_ad"));
                    if (response.getString("mix_ad_counter") != null && !response.getString("mix_ad_counter").isEmpty()) {
                        MyHelpers.setmix_ad_counter(Integer.parseInt(response.getString("mix_ad_counter")));
                    } else {
                        MyHelpers.setmix_ad_counter(5000);
                    }
                    extra_switch_1 = response.getString("extra_switch_1");
                    extra_switch_2 = response.getString("extra_switch_2");
                    extra_switch_3 = response.getString("extra_switch_3");
                    extra_switch_4 = response.getString("extra_switch_4");
                    extra_text_1 = response.getString("extra_text_1");
                    extra_text_2 = response.getString("extra_text_2"); // connection
                    extra_text_3 = response.getString("extra_text_3");
                    extra_text_4 = response.getString("extra_text_4"); //pack name,Kay


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

                    //servers
                    if (extra_switch_4.equals("1")) {
                       if (CheckCountry()){
                           ShowADS();
                       }else {
                           List<String> DATA = new ArrayList<String>(Arrays.asList(extra_text_4.split(",")));
                           MyHelpers.pack = DATA.get(0);
                           MyHelpers.Kyyy = DATA.get(1);
                           Thread thread = new Thread(new Runnable() {
                               @Override
                               public void run() {
                                   try {
                                       OneConnects oneConnect = new OneConnects();
                                       oneConnect.initialize(context, DEc(MyHelpers.Kyyy));
                                       try {
                                           MyHelpers.FREE_SERVERS = oneConnect.fetch(true);
                                           MyHelpers.PREMIUM_SERVERS = oneConnect.fetch(false);
                                           selectedCountry = SelectedCountry();
                                           prepare(context);
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                   } catch (Exception e) {
                                       e.printStackTrace();
                                   }
                               }
                           });
                           thread.start();
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

    private static Countries SelectedCountry() {
        free_servers.clear();
        if (extra_text_2.equals("all")) {
            try {
                JSONArray jsonArray_free = new JSONArray(MyHelpers.FREE_SERVERS);
                for (int i = 0; i < jsonArray_free.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray_free.get(i);
                    free_servers.add(new Countries(object.getString("serverName"), object.getString("flag_url"), object.getString("ovpnConfiguration"), object.getString("vpnUserName"), object.getString("vpnPassword")));
                }
                JSONArray jsonArray_vpi = new JSONArray(MyHelpers.PREMIUM_SERVERS);
                for (int i = 0; i < jsonArray_vpi.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray_vpi.get(i);
                    free_servers.add(new Countries(object.getString("serverName"), object.getString("flag_url"), object.getString("ovpnConfiguration"), object.getString("vpnUserName"), object.getString("vpnPassword")));
                }
                return free_servers.get(getRandom(0, free_servers.size() - 1));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (extra_text_2.equals("v")) {
            try {
                JSONArray jsonArray_vpi = new JSONArray(MyHelpers.PREMIUM_SERVERS);
                for (int i = 0; i < jsonArray_vpi.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray_vpi.get(i);
                    free_servers.add(new Countries(object.getString("serverName"), object.getString("flag_url"), object.getString("ovpnConfiguration"), object.getString("vpnUserName"), object.getString("vpnPassword")));
                }
                return free_servers.get(getRandom(0, free_servers.size() - 1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (extra_text_2.equals("f")) {
            try {
                JSONArray jsonArray_free = new JSONArray(MyHelpers.FREE_SERVERS);
                for (int i = 0; i < jsonArray_free.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray_free.get(i);
                    free_servers.add(new Countries(object.getString("serverName"), object.getString("flag_url"), object.getString("ovpnConfiguration"), object.getString("vpnUserName"), object.getString("vpnPassword")));
                }
                return free_servers.get(getRandom(0, free_servers.size() - 1));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            try {
                List<String> myList = new ArrayList<String>(Arrays.asList(extra_text_2.split(",")));
                if (myList.get(0).equals("v")) {
                    JSONArray jsonArray_vpi = new JSONArray(MyHelpers.PREMIUM_SERVERS);
                    for (int i = 0; i < jsonArray_vpi.length(); i++) {
                        JSONObject object = (JSONObject) jsonArray_vpi.get(i);
                        free_servers.add(new Countries(object.getString("serverName"), object.getString("flag_url"), object.getString("ovpnConfiguration"), object.getString("vpnUserName"), object.getString("vpnPassword")));
                    }
                    return free_servers.get(Integer.parseInt(myList.get(getRandom(1, myList.size() - 1))));
                } else if (myList.get(0).equals("f")) {
                    JSONArray jsonArray_free = new JSONArray(MyHelpers.FREE_SERVERS);
                    for (int i = 0; i < jsonArray_free.length(); i++) {
                        JSONObject object = (JSONObject) jsonArray_free.get(i);
                        free_servers.add(new Countries(object.getString("serverName"), object.getString("flag_url"), object.getString("ovpnConfiguration"), object.getString("vpnUserName"), object.getString("vpnPassword")));
                    }
                    return free_servers.get(Integer.parseInt(myList.get(getRandom(1, myList.size() - 1))));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return null;
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
                AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                    public void onAppOpenAdLoaded(AppOpenAd appOpenAd) {
                        appOpenAd.show((Activity) contextx, new FullScreenContentCallback() {
                            public void onAdShowedFullScreenContent() {
                            }

                            public void onAdDismissedFullScreenContent() {
                                NextIntent(contextx, intentx);
                            }

                            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                AdsClass.Google_open_failed_Facebook_Open(contextx, intentx);
                            }
                        });
                    }

                    public void onAppOpenAdFailedToLoad(LoadAdError loadAdError) {
                        AdsClass.Google_open_failed_Facebook_Open(contextx, intentx);

                    }
                };
                AppOpenAd.load((Context) contextx, MyHelpers.getGoogle_OpenADS(), new AdRequest.Builder().build(), 1, loadCallback);
                MyHelpers.appOpenManager = new AppOpenManager(MyHelpers.getInstance());

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


    public static void prepare(Context context) {
        Intent intent = VpnService.prepare(context);
        if (intent != null) {
            ((Activity) context).startActivityForResult(intent, 123);
        } else {
            start();
        }
    }

    public static void start() {
        try {
            ActiveServer.saveServer(selectedCountry, contextx);
            OpenVpnApi.startVpn(contextx, selectedCountry.getOvpn(), selectedCountry.getCountry(), selectedCountry.getOvpnUserName(), selectedCountry.getOvpnUserPassword());
            MyHelpers.setCallBacks("Yes");
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static int getRandom(int min, int max) {
        int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }

    public static String getCountryCode() {
        TelephonyManager tm = (TelephonyManager) contextx.getSystemService(contextx.getApplicationContext().TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }

   public static Boolean CheckCountry() {
       try {
           List<String> COUNTRY = new ArrayList<String>(Arrays.asList(extra_text_3.split(",")));
           String tm = getCountryCode();
           for (int i = 0; i < COUNTRY.size(); i++) {
               if (COUNTRY.get(i).equals(tm)){
                   return true;
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }

}
