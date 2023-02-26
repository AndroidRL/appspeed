package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.criteo.publisher.CriteoErrorCode;
import com.criteo.publisher.CriteoInterstitial;
import com.criteo.publisher.CriteoInterstitialAdListener;
import com.criteo.publisher.model.InterstitialAdUnit;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

public class InterClass {


    //Extra
    public static Activity context1;
    public static int main_activity_finish;
    public static Intent main_intent;

    //Google
    public static com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd;
    public static com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd1;
    public static com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd2;
    public static com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd3;
    public static int auto_notShow_ads_inter = 0;
    public static int auto_inter_show_id = 0;
    public static int auto_notShow_adsBack = 0;
    public static int AutoGoogleInterID;

    //facebook
    public static com.facebook.ads.InterstitialAd interstitialAd_FB_1;
    public static int AutoLoadFBInterID;

    //App Lovin
    public static MaxInterstitialAd interstitialAd;

    //mix ads
    public static int mix_adsInter = 0;

    /**
     * INTERNET CHECK CODE
     */
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    /**
     * INTERSTITIAL ADS CODE START
     */
    public static void Interstitial(Activity context, Intent intent, Integer ActivityFinish) {
        main_activity_finish = ActivityFinish;
        main_intent = intent;
        context1 = context;
        /**
         * ActivityFinish == 0 next activity
         * ActivityFinish == 1 next and finish activity
         * ActivityFinish == 2 finish activity
         */
        if (InterClass.checkConnection(context)) {
            if (MyHelpers.getCounter_Inter() == 0) {
                Next_Slider_intents(context);
                return;
            }
            /**
             * Skip Ads
             */
            if (MyHelpers.getCounter_Inter() != 5000) {
                auto_notShow_ads_inter++;
                if (MyHelpers.getCounter_Inter() + 1 == auto_notShow_ads_inter) {
                    Next_Slider_intents(context);
                    auto_notShow_ads_inter = 0;
                    return;
                }
            }
            /**
             * Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                if (MyHelpers.getmix_ad_inter().equals("0")) {
                    RegularADS(context);
                } else {
                    if (MyHelpers.getmix_ad_inter().length() == 2) {
                        Mix2Ads(MyHelpers.getmix_ad_inter());  // 2 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 3) {
                        Mix3Ads(MyHelpers.getmix_ad_inter()); // 3 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 4) {
                        Mix4Ads(MyHelpers.getmix_ad_inter()); // 4 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 5) {
                        Mix5Ads(MyHelpers.getmix_ad_inter()); // 5 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 6) {
                        Mix6Ads(MyHelpers.getmix_ad_inter()); // 6 ads
                    }
                }
                return;
            }
            /**
             * Regular Ads
             */
            RegularADS(context);
        } else {
            Next_Slider_intents(context);
        }
    }

    private static void Next_Slider_intents(Activity context) {
        if (main_activity_finish == 0) {
            context.startActivity(main_intent);
        } else if (main_activity_finish == 1) {
            context.startActivity(main_intent);
            context.finish();
        } else if (main_activity_finish == 2) {
            context.finish();
        }
    }

    /*Google Inter Show*/
    private static void GoogleADSShow(Activity context) {
        /**
         * 1 = Mix Ads
         * 2 = 1 and 2 Ads
         * 3 = 1, 2 amd 3 Ads
         */
        if (MyHelpers.Google_inter_number == 1) {
            //Google ID 1
            googleInterShow(context, () -> {
                Next_Slider_intents(context);
            });
        } else if (MyHelpers.Google_inter_number == 2) {
            if (auto_inter_show_id == 0) {
                auto_inter_show_id = 1;
                googleInterShow1(context, () -> {
                    Next_Slider_intents(context);
                });
            } else {
                auto_inter_show_id = 0;
                googleInterShow2(context, () -> {
                    Next_Slider_intents(context);
                });
            }
        } else if (MyHelpers.Google_inter_number == 3) {
            if (auto_inter_show_id == 0) {
                auto_inter_show_id = 1;
                googleInterShow1(context, () -> {
                    Next_Slider_intents(context);
                });
            } else if (auto_inter_show_id == 1) {
                auto_inter_show_id = 2;
                googleInterShow2(context, () -> {
                    Next_Slider_intents(context);
                });
            } else {
                auto_inter_show_id = 0;
                googleInterShow3(context, () -> {
                    Next_Slider_intents(context);
                });
            }
        }
    }


    /*Back Btn Inter Code */
    public static void BackInterstitial(Activity context) {
        if (InterClass.checkConnection(context)) {
            if (MyHelpers.getBackAdsOnOff().equals("1")) {
                main_activity_finish = 2;
                /**
                 * Skip Ads
                 */
                if (MyHelpers.getBackCounter() != 5000) {
                    auto_notShow_adsBack++;
                    if (MyHelpers.getBackCounter() + 1 == auto_notShow_adsBack) {
                        context.finish();
                        auto_notShow_adsBack = 0;
                        return;
                    }
                }
                /**
                 * Mix Ads
                 */
                if (MyHelpers.getmix_ad_on_off().equals("1")) {
                    if (MyHelpers.getmix_ad_inter().equals("0")) {
                        RegularADS(context);
                    } else {
                        if (MyHelpers.getmix_ad_inter().length() == 2) {
                            Mix2Ads(MyHelpers.getmix_ad_inter());  // 2 ads
                        } else if (MyHelpers.getmix_ad_inter().length() == 3) {
                            Mix3Ads(MyHelpers.getmix_ad_inter()); // 3 ads
                        } else if (MyHelpers.getmix_ad_inter().length() == 4) {
                            Mix4Ads(MyHelpers.getmix_ad_inter()); // 4 ads
                        } else if (MyHelpers.getmix_ad_inter().length() == 5) {
                            Mix5Ads(MyHelpers.getmix_ad_inter()); // 5 ads
                        } else if (MyHelpers.getmix_ad_inter().length() == 6) {
                            Mix6Ads(MyHelpers.getmix_ad_inter()); // 6 ads
                        }
                    }
                    return;
                }
                /**
                 * Regular Ads
                 */
                RegularADS(context);
            } else {
                context.finish();
            }
        } else {
            context.finish();
        }
    }

    public interface GetBackPointer {
        void returnAction();
    }

    /*Google Inter Load MIX*/
    public static void GoogleInterstitialAdLoad(Context context) {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            String GOOGGLEINTEID = null;
            if (AutoGoogleInterID == 1) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter();
            } else if (AutoGoogleInterID == 2) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter1();
            } else if (AutoGoogleInterID == 3) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter2();
            }
            mInterstitialAd.load(context, GOOGGLEINTEID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    mInterstitialAd = interstitialAd;
                    AutoGoogleInterID = 1;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    if (AutoGoogleInterID == 1) {
                        AutoGoogleInterID = 2;
                        GoogleInterstitialAdLoad(context);
                    } else if (AutoGoogleInterID == 2) {
                        AutoGoogleInterID = 3;
                        GoogleInterstitialAdLoad(context);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Google Inter Show MIX*/
    private static void googleInterShow(Activity context, final GetBackPointer getBackPointer) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(context);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AutoGoogleInterID = 1;
                    FailsAds(context, "g");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    AutoGoogleInterID = 1;
                    GoogleInterstitialAdLoad(context);
                    if (getBackPointer != null) {
                        getBackPointer.returnAction();
                    }
                }

            });
            AutoGoogleInterID = 1;
            GoogleInterstitialAdLoad(context);

        } else {
            FailsAds(context, "g");
//
//            if (getBackPointer != null) {
//                getBackPointer.returnAction();
//            }
        }
    }

    /*Google Inter Load 1 ID*/
    public static void GoogleInterstitialAdLoad1(Context context) {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd1.load(context, MyHelpers.getGoogleInter(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    mInterstitialAd1 = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleInterstitialAdLoad2(context);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Google Inter Show 1 ID*/
    private static void googleInterShow1(Activity context, final GetBackPointer getBackPointer) {
        if (mInterstitialAd1 != null) {
            mInterstitialAd1.show(context);
            mInterstitialAd1.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    GoogleInterstitialAdLoad1(context);
                    FailsAds(context, "g");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    GoogleInterstitialAdLoad1(context);
                    if (getBackPointer != null) {
                        getBackPointer.returnAction();
                    }
                }
            });
            GoogleInterstitialAdLoad1(context);
        } else {
//            if (getBackPointer != null) {
//                getBackPointer.returnAction();
            FailsAds(context, "g");
//
//            }
        }
    }

    /*Google Inter Load 2 ID*/
    public static void GoogleInterstitialAdLoad2(Context context) {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd2.load(context, MyHelpers.getGoogleInter1(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    mInterstitialAd2 = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleInterstitialAdLoad3(context);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Google Inter Show 2 ID*/
    private static void googleInterShow2(Activity context, final GetBackPointer getBackPointer) {
        if (mInterstitialAd2 != null) {
            mInterstitialAd2.show(context);
            mInterstitialAd2.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    GoogleInterstitialAdLoad2(context);
                    FailsAds(context, "g");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    GoogleInterstitialAdLoad2(context);
                    if (getBackPointer != null) {
                        getBackPointer.returnAction();
                    }
                }
            });
            GoogleInterstitialAdLoad2(context);
        } else {
//            if (getBackPointer != null) {
//                getBackPointer.returnAction();
            FailsAds(context, "g");
        }
//        }
    }

    /*Google Inter Load 3 ID*/
    public static void GoogleInterstitialAdLoad3(Context context) {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd3.load(context, MyHelpers.getGoogleInter2(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    mInterstitialAd3 = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    //                    //fb code
                    if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                        InterClass.AutoLoadFBInterID = 1;

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Google Inter Show 3 ID*/
    private static void googleInterShow3(Activity context, final GetBackPointer getBackPointer) {
        if (mInterstitialAd3 != null) {
            mInterstitialAd3.show(context);
            mInterstitialAd3.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    GoogleInterstitialAdLoad3(context);
                    FailsAds(context, "g");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    GoogleInterstitialAdLoad3(context);
                    if (getBackPointer != null) {
                        getBackPointer.returnAction();
                    }
                }
            });
            GoogleInterstitialAdLoad3(context);
        } else {
//            if (getBackPointer != null) {
//                getBackPointer.returnAction();
            FailsAds(context, "g");
//            }
        }
    }

    /*FB Inter Load*/
    public static void FacebookInterLoad(Context context) {
        try {
            String FBINTER = null;
            if (AutoLoadFBInterID == 1) {
                FBINTER = MyHelpers.getFacebookInter();
            } else if (AutoLoadFBInterID == 2) {
                FBINTER = MyHelpers.getFacebookInter1();
            } else if (AutoLoadFBInterID == 3) {
                FBINTER = MyHelpers.getFacebookInter2();
            }
            interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(context, FBINTER);
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    Next_Slider_intents(context1);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    interstitialAd_FB_1 = null;
                    if (AutoLoadFBInterID == 1) {
                        AutoLoadFBInterID = 2;
                        FacebookInterLoad(context);
                    } else if (AutoLoadFBInterID == 2) {
                        AutoLoadFBInterID = 3;
                        FacebookInterLoad(context);
                    } else {
                        FailsAds(context, "f");
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    AutoLoadFBInterID = 1;
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

        } catch (Exception e) {

        }
    }

    /*APP LOVIN Inter SHOW */
    public static void APPLovinShow() {

        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) context1);
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
                Next_Slider_intents(context1);
            }

            @Override
            public void onAdClicked(MaxAd ad) {


            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                FailsAds(context1, "a");


            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });
        interstitialAd.loadAd();

    }

    /*Unity Inter*/
    public static void UnityShow(Activity context) {

        UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show((Activity) context1, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
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
                        Next_Slider_intents(context);

                    }
                });
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                FailsAds(context, "u");
            }
        });

    }

    /*Criteo*/
    public static void CriteoAdsInter(Activity context) {
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
                FailsAds(context, "c");
            }

            @Override
            public void onAdClicked() {
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdClosed() {
                Next_Slider_intents(context);

            }

            @Override
            public void onAdLeftApplication() {
            }
        });
        interstitial.loadAd();
    }

    /*Custom Inter*/
    private static void CustomADSInter(Activity context) {
        MyHelpers.CustomIntent = main_intent;
        if (main_activity_finish == 0) {
            context.startActivity(new Intent(context, CustomAdsInterActivity.class));
        } else if (main_activity_finish == 1) {
            context.startActivity(new Intent(context, CustomAdsInterActivity.class));
            context.finish();
        } else if (main_activity_finish == 2) {
            context.finish();
        }
    }

    /**
     * Regular Ads
     */
    private static void RegularADS(Activity context) {
        if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            GoogleADSShow(context);
        } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            FacebookInterLoad(context1);
        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            APPLovinShow();
        } else if (MyHelpers.getCriteoEnable().equals("1")) {
            CriteoAdsInter(context);
        } else if (MyHelpers.getUnityEnable().equals("1")) {
            UnityShow(context);
        } else if (MyHelpers.get_q_link_btn_on_off().equals("1")) {
            Next_Slider_intents(context);
            MyHelpers.BtnAutolink();
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustomADSInter(context);
        } else {
            Next_Slider_intents(context);
        }
    }

    /**
     * Fail Ads
     */
    public static void FailsAds(Context context, String Skip) {
        if (Skip.equals("g")) {
            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                try {
                    interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(context, MyHelpers.getFacebookInter());
                    InterstitialAdListener adListener = new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            Next_Slider_intents(context1);
                        }

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {
                            GoogleandFacebookFailsSameInter();
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
                } catch (Exception e) {
                }
            } else {
                GoogleandFacebookFailsSameInter();
            }
        } else if (Skip.equals("f")) {
            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                try {
                    AdRequest adRequest = new AdRequest.Builder().build();
                    mInterstitialAd.load(context, MyHelpers.getGoogleInter(), adRequest, new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                            super.onAdLoaded(interstitialAd);
                            mInterstitialAd = interstitialAd;
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent();
                                    Next_Slider_intents(context1);
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                    super.onAdFailedToShowFullScreenContent(adError);
                                    Next_Slider_intents(context1);
                                }
                            });
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(context1);
                            }
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            GoogleandFacebookFailsSameInter();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                GoogleandFacebookFailsSameInter();
            }
        } else if (Skip.equals("c")) {
            GoogleandFacebookShow("c");
        } else if (Skip.equals("a")) {
            GoogleandFacebookShow("a");
        } else if (Skip.equals("u")) {
            GoogleandFacebookShow("u");
        } else {
            CustomADSInter(context1);
        }
    }

    private static void GoogleandFacebookFailsSameInter() {
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
                    /*APP LOVIN*/
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) context1);
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
                                Next_Slider_intents((Activity) context1);
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                /*UNITY*/
                                if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {

                                    UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
                                        @Override
                                        public void onUnityAdsAdLoaded(String placementId) {
                                            UnityAds.show((Activity) context1, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
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
                                                    Next_Slider_intents((Activity) context1);

                                                }
                                            });
                                        }

                                        @Override
                                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                                            CustomADSInter((Activity) context1);
                                        }
                                    });

                                } else {
                                    CustomADSInter((Activity) context1);
                                }


                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                            }
                        });
                        interstitialAd.loadAd();
                    } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        FailsOtherUnitShow();
                    } else {
                        CustomADSInter((Activity) context1);
                    }
                }

                @Override
                public void onAdClicked() {
                    // called when user clicks on an opened Criteo Interstitial ad
                }

                @Override
                public void onAdOpened() {
                    // called when Criteo Interstitial ad is opened and covering the screen
                }

                @Override
                public void onAdClosed() {
                    Next_Slider_intents(context1);
                }

                @Override
                public void onAdLeftApplication() {
                    // called when clicking on Criteo ad result in user leaving your application
                }
            });
            interstitial.loadAd();
        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) context1);
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

                    Next_Slider_intents((Activity) context1);
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*UNITY*/
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        FailsOtherUnitShow();
                    } else {
                        CustomADSInter((Activity) context1);
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                }
            });
            interstitialAd.loadAd();
        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            FailsOtherUnitShow();
        } else {
            CustomADSInter((Activity) context1);
        }
    }

    private static void GoogleandFacebookShow(String show_ad_name) {
        if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            try {
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAd.load(context1, MyHelpers.getGoogleInter(), adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                Next_Slider_intents(context1);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                Next_Slider_intents(context1);
                            }
                        });
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(context1);
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        try {
                            interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(context1, MyHelpers.getFacebookInter());
                            InterstitialAdListener adListener = new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    Next_Slider_intents(context1);
                                }

                                @Override
                                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                    if (show_ad_name.equals("c")) {
                                        CriteoFailsOpenOtherAds(context1);
                                    } else if (show_ad_name.equals("a")) {
                                        AppLovinFailsOpenOtherAds(context1);
                                    } else if (show_ad_name.equals("u")) {
                                        UnityFailsOpenOtherAds(context1);
                                    } else {
                                        CustomADSInter(context1);
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
                        } catch (Exception e) {
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            try {
                interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(context1, MyHelpers.getFacebookInter());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        Next_Slider_intents(context1);
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        if (show_ad_name.equals("c")) {
                            CriteoFailsOpenOtherAds(context1);
                        } else if (show_ad_name.equals("a")) {
                            AppLovinFailsOpenOtherAds(context1);
                        } else if (show_ad_name.equals("u")) {
                            UnityFailsOpenOtherAds(context1);
                        } else {
                            CustomADSInter(context1);
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
            } catch (Exception e) {
            }
        } else {
            if (show_ad_name.equals("c")) {
                CriteoFailsOpenOtherAds(context1);
            } else if (show_ad_name.equals("a")) {
                AppLovinFailsOpenOtherAds(context1);
            } else if (show_ad_name.equals("u")) {
                UnityFailsOpenOtherAds(context1);
            } else {
                CustomADSInter(context1);
            }

        }
    }

    private static void CriteoFailsOpenOtherAds(Context context) {

        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) context1);
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

                    Next_Slider_intents((Activity) context1);
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*UNITY*/
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        FailsOtherUnitShow();
                    } else {
                        CustomADSInter((Activity) context1);
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                }
            });
            interstitialAd.loadAd();

        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            FailsOtherUnitShow();
        } else {
            CustomADSInter((Activity) context);
        }
    }

    private static void AppLovinFailsOpenOtherAds(Context context) {
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
                        FailsOtherUnitShow();
                    } else {
                        CustomADSInter((Activity) context);
                    }
                }

                @Override
                public void onAdClicked() {
                    // called when user clicks on an opened Criteo Interstitial ad
                }

                @Override
                public void onAdOpened() {
                    // called when Criteo Interstitial ad is opened and covering the screen
                }

                @Override
                public void onAdClosed() {
                    Next_Slider_intents(context1);
                }

                @Override
                public void onAdLeftApplication() {
                    // called when clicking on Criteo ad result in user leaving your application
                }
            });
            interstitial.loadAd();
        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            FailsOtherUnitShow();
        } else {
            CustomADSInter((Activity) context);
        }
    }

    private static void UnityFailsOpenOtherAds(Context context) {
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
                        interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) context1);
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
                                Next_Slider_intents((Activity) context1);
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                /*UNITY*/
                                CustomADSInter((Activity) context);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                            }
                        });
                        interstitialAd.loadAd();

                    } else {
                        CustomADSInter((Activity) context);
                    }
                }

                @Override
                public void onAdClicked() {
                    // called when user clicks on an opened Criteo Interstitial ad
                }

                @Override
                public void onAdOpened() {
                    // called when Criteo Interstitial ad is opened and covering the screen
                }

                @Override
                public void onAdClosed() {
                    Next_Slider_intents(context1);
                }

                @Override
                public void onAdLeftApplication() {
                    // called when clicking on Criteo ad result in user leaving your application
                }
            });
            interstitial.loadAd();
        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) context1);
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
                    Next_Slider_intents((Activity) context1);
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*UNITY*/
                    CustomADSInter((Activity) context);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();
        } else {
            CustomADSInter((Activity) context);
        }
    }

    private static void FailsOtherUnitShow() {
        UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show((Activity) context1, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
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
                        Next_Slider_intents((Activity) context1);

                    }
                });
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                CustomADSInter((Activity) context1);
            }
        });

    }

    /**
     * Mix Ads
     */
    private static void Mix2Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        if (MyHelpers.getmix_ad_counter() != 5000) {
            mix_adsInter++;
            if (MyHelpers.getmix_ad_counter() + 1 == mix_adsInter) {
                MixAdsShow(second_ads);
                mix_adsInter = 0;
            } else {
                MixAdsShow(first_ads);
            }
        } else {
            if (mix_adsInter == 0) {
                mix_adsInter = 1;
                MixAdsShow(first_ads);
            } else if (mix_adsInter == 1) {
                mix_adsInter = 0;
                MixAdsShow(second_ads);
            }
        }
    }

    private static void Mix3Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        if (mix_adsInter == 0) {
            mix_adsInter = 1;
            MixAdsShow(first_ads);
        } else if (mix_adsInter == 1) {
            mix_adsInter = 2;
            MixAdsShow(second_ads);
        } else if (mix_adsInter == 2) {
            mix_adsInter = 0;
            MixAdsShow(three_ads);
        }
    }

    private static void Mix4Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        if (mix_adsInter == 0) {
            mix_adsInter = 1;
            MixAdsShow(first_ads);
        } else if (mix_adsInter == 1) {
            mix_adsInter = 2;
            MixAdsShow(second_ads);
        } else if (mix_adsInter == 2) {
            mix_adsInter = 3;
            MixAdsShow(three_ads);
        } else if (mix_adsInter == 3) {
            mix_adsInter = 0;
            MixAdsShow(four_ads);

        }
    }

    private static void Mix5Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        char five_ads = s.charAt(4);
        if (mix_adsInter == 0) {
            mix_adsInter = 1;
            MixAdsShow(first_ads);
        } else if (mix_adsInter == 1) {
            mix_adsInter = 2;
            MixAdsShow(second_ads);
        } else if (mix_adsInter == 2) {
            mix_adsInter = 3;
            MixAdsShow(three_ads);
        } else if (mix_adsInter == 3) {
            mix_adsInter = 4;
            MixAdsShow(four_ads);
        } else if (mix_adsInter == 4) {
            mix_adsInter = 0;
            MixAdsShow(five_ads);
        }
    }

    private static void Mix6Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        char five_ads = s.charAt(4);
        char six_ads = s.charAt(5);
        if (mix_adsInter == 0) {
            mix_adsInter = 1;
            MixAdsShow(first_ads);
        } else if (mix_adsInter == 1) {
            mix_adsInter = 2;
            MixAdsShow(second_ads);
        } else if (mix_adsInter == 2) {
            mix_adsInter = 3;
            MixAdsShow(three_ads);
        } else if (mix_adsInter == 3) {
            mix_adsInter = 4;
            MixAdsShow(four_ads);
        } else if (mix_adsInter == 4) {
            mix_adsInter = 5;
            MixAdsShow(five_ads);
        } else if (mix_adsInter == 5) {
            mix_adsInter = 0;
            MixAdsShow(six_ads);
        }
    }

    private static void MixAdsShow(char ads) {
        String value = String.valueOf(ads);
        if (value.equals("g") && MyHelpers.getlive_status().equals("1")) {
            GoogleADSShow(context1);
        } else if (value.equals("f") && MyHelpers.getlive_status().equals("1")) {
            FacebookInterLoad(context1);
        } else if (value.equals("c")) {
            CriteoAdsInter(context1);
        } else if (value.equals("a")) {
            APPLovinShow();
        } else if (value.equals("u")) {
            UnityShow(context1);
        } else if (value.equals("o")) {
            CustomADSInter(context1);
        }
    }

}
