package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AdsClass {

    public static int mix_adsInter = 0;
    public static int mix_adsInter_native = 0;
    public static int mix_adsInter_banner = 0;
    public static int mix_adsInter_back = 0;


    //facebook
    public static com.facebook.ads.InterstitialAd interstitialAd_FB_1;
    public static int AutoLoadFBInterID;

    //google
    static com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd;
    public static int AutoGoogleInterID;
    public static AdLoader adLoader;
    public static com.google.android.gms.ads.nativead.NativeAd GoogleNativeBig = null;
    public static int auto_notShow_ads_inter = 0;
    public static int auto_notShow_ads_native = 0;
    public static int auto_notShow_ads_banner = 0;
    public static int auto_notShow_adsBack = 0;


    /*InterNet Check*/
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

    /* Inter Code */
    public static void Interstitial(Activity context, Intent intent, int ActivityFinish) {
        /**
         * ActivityFinish == 0 next activity
         * ActivityFinish == 1 next and finish activity
         * ActivityFinish == 2 finish activity
         */
        if (checkConnection(context)) {
            if (MyHelpers.getCounter_Inter() == 0) {
                Next_Slider_intents(context, intent, ActivityFinish);
                return;
            }
            /**
             * Skip Ads
             */
            if (MyHelpers.getCounter_Inter() != 5000) {
                auto_notShow_ads_inter++;
                if (MyHelpers.getCounter_Inter() + 1 == auto_notShow_ads_inter) {
                    Next_Slider_intents(context, intent, ActivityFinish);
                    auto_notShow_ads_inter = 0;
                    return;
                }

            }
            /**
             * Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                if (MyHelpers.getGoogleEnable().equals("1")) {
                    if (MyHelpers.getmix_ad_counter() != 5000) {
                        mix_adsInter++;
                        if (MyHelpers.getmix_ad_counter() + 1 == mix_adsInter) {
                            Next_Slider_intents(context, intent, ActivityFinish);
                            FacebookInterShowNext(context, new RandomAdListener() {
                                @Override
                                public void onClick() {
                                }
                            });
                            mix_adsInter = 0;
                        } else {
                            googleInterShow(context, () -> {
                                Next_Slider_intents(context, intent, ActivityFinish);
                            });
                        }
                    } else {
                        googleInterShow(context, () -> {
                            Next_Slider_intents(context, intent, ActivityFinish);
                        });
                    }
                } else if (MyHelpers.getFacebookEnable().equals("1")) {
                    if (MyHelpers.getmix_ad_counter() != 5000) {
                        mix_adsInter++;
                        if (MyHelpers.getmix_ad_counter() + 1 == mix_adsInter) {
                            googleInterShow(context, () -> {
                                Next_Slider_intents(context, intent, ActivityFinish);
                            });
                            mix_adsInter = 0;
                        } else {
                            Next_Slider_intents(context, intent, ActivityFinish);
                            FacebookInterShowNext(context, new RandomAdListener() {
                                @Override
                                public void onClick() {
                                }
                            });
                        }
                    } else {
                        Next_Slider_intents(context, intent, ActivityFinish);
                        FacebookInterShowNext(context, new RandomAdListener() {
                            @Override
                            public void onClick() {
                            }
                        });
                    }
                }
                return;
            }
            /**
             * Regular Ads
             */
            if (MyHelpers.getGoogleEnable().equals("1")) {
                googleInterShow(context, () -> {
                    Next_Slider_intents(context, intent, ActivityFinish);
                });
            } else if (MyHelpers.getFacebookEnable().equals("1")) {
                Next_Slider_intents(context, intent, ActivityFinish);
                FacebookInterShowNext(context, new RandomAdListener() {
                    @Override
                    public void onClick() {

                    }
                });
            } else {
                Next_Slider_intents(context, intent, ActivityFinish);
            }
        } else {
            Next_Slider_intents(context, intent, ActivityFinish);
        }
    }

    private static void Next_Slider_intents(Activity context, Intent intent, int ActivityFinish) {
        if (ActivityFinish == 0) {
            context.startActivity(intent);
        } else if (ActivityFinish == 1) {
            context.startActivity(intent);
            context.finish();
        } else if (ActivityFinish == 2) {
            context.finish();
        }
    }

    private static void FacebookInterShowNext(Context context, RandomAdListener randomAdListener) {
        FacebookInterShow(context);
        randomAdListener.onClick();
    }

    private static void FacebookInterShowBack(Context context, RandomBackAdListener randomAdListener) {
        FacebookInterShow(context);
        randomAdListener.onClick();
    }

    /* BackInter Code */
    public static void BackInterstitial(Activity context) {

        if (checkConnection(context)) {

            if (MyHelpers.getBackAdsOnOff().equals("1")) {
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
                    if (MyHelpers.getGoogleEnable().equals("1")) {
                        if (MyHelpers.getmix_ad_counter() != 5000) {
                            mix_adsInter_back++;
                            if (MyHelpers.getmix_ad_counter() + 1 == mix_adsInter_back) {
                                context.finish();
                                FacebookInterShowBack(context, new RandomBackAdListener() {
                                    @Override
                                    public void onClick() {
                                    }
                                });
                                mix_adsInter_back = 0;
                            } else {
                                googleInterShow(context, () -> {
                                    context.finish();
                                });
                            }
                        } else {
                            googleInterShow(context, () -> {
                                context.finish();
                            });
                        }
                    } else if (MyHelpers.getFacebookEnable().equals("1")) {
                        if (MyHelpers.getmix_ad_counter() != 5000) {
                            mix_adsInter_back++;
                            if (MyHelpers.getmix_ad_counter() + 1 == mix_adsInter_back) {
                                googleInterShow(context, () -> {
                                    context.finish();
                                });
                                mix_adsInter_back = 0;
                            } else {
                                context.finish();
                                FacebookInterShowBack(context, new RandomBackAdListener() {
                                    @Override
                                    public void onClick() {
                                    }
                                });
                            }
                        } else {
                            context.finish();
                            FacebookInterShowBack(context, new RandomBackAdListener() {
                                @Override
                                public void onClick() {
                                }
                            });
                        }
                    }
                    return;
                }
                /**
                 * Regular Ads
                 */
                if (MyHelpers.getGoogleEnable().equals("1")) {
                    googleInterShow(context, () -> {
                        context.finish();
                    });
                } else if (MyHelpers.getFacebookEnable().equals("1")) {
                    context.finish();
                    FacebookInterShowBack(context, new RandomBackAdListener() {
                        @Override
                        public void onClick() {
                        }
                    });

                } else {
                    context.finish();
                }
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

    /*Google Inter Load*/
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
                    } else if (AutoGoogleInterID == 3) {
                        //fb code
                        if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                            AdsClass.AutoLoadFBInterID = 1;
                            AdsClass.Google_failed_FacebookInterLoad(context);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Google Inter Show*/
    private static void googleInterShow(Activity context, final GetBackPointer getBackPointer) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(context);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AutoGoogleInterID = 1;
                    GoogleInterstitialAdLoad(context);
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
            if (getBackPointer != null) {
                getBackPointer.returnAction();
                FacebookInterShowNext(context, new RandomAdListener() {
                    @Override
                    public void onClick() {

                    }
                });
            }
        }
    }

    public static void Google_failed_FacebookInterLoad(Context context) {
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
                    }

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    AutoLoadFBInterID = 1;
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(
                    interstitialAd_FB_1.buildLoadAdConfig()
                            .withAdListener(adListener)
                            .build());

        } catch (Exception e) {
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
                    } else if (AutoLoadFBInterID == 3) {
                        if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                            AdsClass.AutoGoogleInterID = 1;
                            AdsClass.Facebook_failed_GoogleInterstitialAdLoad(context);
                        }
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    AutoLoadFBInterID = 1;
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(
                    interstitialAd_FB_1.buildLoadAdConfig()
                            .withAdListener(adListener)
                            .build());

        } catch (Exception e) {

        }
    }

    public static void Facebook_failed_GoogleInterstitialAdLoad(Context context) {
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
                    } else if (AutoGoogleInterID == 3) {
                        //fb code
                        if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                            AdsClass.AutoLoadFBInterID = 1;
                            AdsClass.Google_failed_FacebookInterLoad(context);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*FB Inter Show*/
    public static void FacebookInterShow(Context context) {

        try {
            if (interstitialAd_FB_1 != null) {
                interstitialAd_FB_1.show();
            } else {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show((Activity) context);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);

                            AutoGoogleInterID = 1;
                            GoogleInterstitialAdLoad(context);
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
                        }

                    });
                    AutoGoogleInterID = 1;
                    GoogleInterstitialAdLoad(context);

                }
            }
        } catch (Exception e) {
        }
        FacebookInterLoad(context);
    }

    /* Native Main Code*/
    public static void NativeAds(final Activity activity, final ViewGroup viewGroup, final LinearLayout linearLayout, RelativeLayout addcontain, RelativeLayout ad_native_fb) {
        if (checkConnection(activity)) {
            /**
             * Skip Ads
             */
            if (MyHelpers.getCounter_Native() == 0) {
                return;
            }
            if (MyHelpers.getCounter_Native() != 5000) {
                auto_notShow_ads_native++;
                if (MyHelpers.getCounter_Native() + 1 == auto_notShow_ads_native) {
                    auto_notShow_ads_native = 0;
                    return;
                }
            }
            /**
             * Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                if (MyHelpers.getGoogleEnable().equals("1")) {
                    if (MyHelpers.getmix_ad_counter_native() != 5000) {
                        mix_adsInter_native++;
                        if (MyHelpers.getmix_ad_counter_native() + 1 == mix_adsInter_native) {
                            mix_adsInter_native = 0;
                            FacebookNative(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                        } else {
                            NativeAd_1(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                        }
                    } else {
                        NativeAd_1(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                    }
                } else if (MyHelpers.getFacebookEnable().equals("1")) {
                    if (MyHelpers.getmix_ad_counter_native() != 5000) {
                        mix_adsInter_native++;
                        if (MyHelpers.getmix_ad_counter_native() + 1 == mix_adsInter_native) {
                            mix_adsInter_native = 0;
                            NativeAd_1(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                        } else {
                            FacebookNative(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                        }
                    } else {
                        FacebookNative(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                    }
                }
                return;
            }
            /**
             * Regular Ads
             */
            if (MyHelpers.getGoogleEnable().equals("1")) {
                NativeAd_1(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
            } else if (MyHelpers.getFacebookEnable().equals("1")) {
                FacebookNative(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
            }
        }
    }

    /*Native Load Code Google*/
    public static void NativeAd_1(final Activity activity, final ViewGroup viewGroup, final LinearLayout linearLayout, RelativeLayout addcontain, RelativeLayout ad_native_fb) {
        ad_native_fb.setVisibility(View.GONE);
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;
                    NativeAd_2(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_1(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView2 = (com.google.android.gms.ads.nativead.NativeAdView) nativeAdView.findViewById(R.id.ad_view);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
    }

    public static void NativeAd_2(final Activity activity, final ViewGroup viewGroup, final LinearLayout linearLayout, RelativeLayout addcontain, RelativeLayout ad_native_fb) {
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative1());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;
                    NativeAd_3(activity, viewGroup, linearLayout, addcontain, ad_native_fb);

                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_2(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView2 = (com.google.android.gms.ads.nativead.NativeAdView) nativeAdView.findViewById(R.id.ad_view);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
    }

    public static void NativeAd_3(final Activity activity, final ViewGroup viewGroup, final LinearLayout linearLayout, RelativeLayout addcontain, RelativeLayout ad_native_fb) {
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative2());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);


                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_3(activity, viewGroup, linearLayout, addcontain, ad_native_fb);
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView2 = (com.google.android.gms.ads.nativead.NativeAdView) nativeAdView.findViewById(R.id.ad_view);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
    }

    public static void populateUnifiedNativeAdView(com.google.android.gms.ads.nativead.NativeAd nativeAd, com.google.android.gms.ads.nativead.NativeAdView nativeAdView, Activity activity, ViewGroup viewGroup, RelativeLayout addcontain) {
        nativeAdView.setMediaView((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media));
        ((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.getMediaView().setMediaContent(GoogleNativeBig.getMediaContent());
        nativeAdView.findViewById(R.id.ad_call_to_action).setBackground(ContextCompat.getDrawable(activity, R.drawable.app_btn));
        addcontain.setVisibility(View.VISIBLE);
        try {
            ((TextView) nativeAdView.getHeadlineView()).setText(GoogleNativeBig.getHeadline());
            if (GoogleNativeBig.getBody() == null) {
                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(GoogleNativeBig.getBody());
            }
            if (GoogleNativeBig.getCallToAction() == null) {
                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
                if (MyHelpers.getGooglebutton_name() != null && !MyHelpers.getGooglebutton_name().isEmpty()) {
                    ((Button) nativeAdView.getCallToActionView()).setText(MyHelpers.getGooglebutton_name());
                } else {
                    ((Button) nativeAdView.getCallToActionView()).setText(GoogleNativeBig.getCallToAction());
                }
                ((Button) nativeAdView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyHelpers.getGooglebutton_color())));
            }
            if (GoogleNativeBig.getIcon() == null) {
                nativeAdView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(GoogleNativeBig.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            nativeAdView.setNativeAd(GoogleNativeBig);
            VideoController videoController = nativeAd.getMediaContent().getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception unused) {
        }
        viewGroup.removeAllViews();
        viewGroup.addView(nativeAdView);
    }

    /*Native Load Code FB*/
    private static void FacebookNative(Activity activity, final ViewGroup viewGroup, final LinearLayout linearLayout, RelativeLayout addcontain, RelativeLayout ad_native_fb) {

        ad_native_fb.setVisibility(View.VISIBLE);
        addcontain.setVisibility(View.GONE);
        com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {


                com.facebook.ads.NativeAd nativeAd_2 = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative1());
                NativeAdListener nativeAdListener_2 = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {

                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        com.facebook.ads.NativeAd nativeAd_3 = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative2());
                        NativeAdListener nativeAdListener_3 = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, com.facebook.ads.AdError adError) {


                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAd_3 == null || nativeAd_3 != ad) {
                                    return;
                                }

                                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, ad_native_fb, false);
                                fbPopulateNativeAdView(nativeAd_3, adView);
                                ad_native_fb.removeAllViews();
                                ad_native_fb.addView(adView);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        nativeAd_3.loadAd(nativeAd_3.buildLoadAdConfig().withAdListener(nativeAdListener_3).build());

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (nativeAd_2 == null || nativeAd_2 != ad) {
                            return;
                        }

                        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, ad_native_fb, false);
                        fbPopulateNativeAdView(nativeAd_2, adView);
                        ad_native_fb.removeAllViews();
                        ad_native_fb.addView(adView);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                nativeAd_2.loadAd(nativeAd_2.buildLoadAdConfig().withAdListener(nativeAdListener_2).build());

            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }

                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, ad_native_fb, false);
                fbPopulateNativeAdView(nativeAd, adView);
                ad_native_fb.removeAllViews();
                ad_native_fb.addView(adView);

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());

    }

    public static void fbPopulateNativeAdView(com.facebook.ads.NativeAd nativeAd, LinearLayout adView) {

        nativeAd.unregisterView();

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    /*Banner Main Code*/
    public static void Banner(Context context, RelativeLayout google_banner_container, RelativeLayout fb_banner_container, View main_banner_layout) {
        if (checkConnection(context)) {
            if (MyHelpers.getCounter_Banner() == 0) {
                return;
            }
            /**
             * Skip Ads
             */
            if (MyHelpers.getCounter_Banner() != 5000) {
                auto_notShow_ads_banner++;
                if (MyHelpers.getCounter_Banner() + 1 == auto_notShow_ads_banner) {
                    auto_notShow_ads_banner = 0;
                    return;
                }
            }
            /**
             * Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                if (MyHelpers.getGoogleEnable().equals("1")) {
                    if (MyHelpers.getmix_ad_counter_banner() != 5000) {
                        mix_adsInter_banner++;
                        if (MyHelpers.getmix_ad_counter_banner() + 1 == mix_adsInter_banner) {
                            mix_adsInter_banner = 0;
                            fb_banner_container.setVisibility(View.VISIBLE);
                            google_banner_container.setVisibility(View.GONE);
                            FacebookBanner(context, fb_banner_container, main_banner_layout);
                        } else {
                            fb_banner_container.setVisibility(View.GONE);
                            google_banner_container.setVisibility(View.VISIBLE);
                            GoogleNativeBanner(context, google_banner_container, main_banner_layout);
                        }
                    } else {
                        fb_banner_container.setVisibility(View.GONE);
                        google_banner_container.setVisibility(View.VISIBLE);
                        GoogleNativeBanner(context, google_banner_container, main_banner_layout);
                    }
                } else if (MyHelpers.getFacebookEnable().equals("1")) {
                    if (MyHelpers.getmix_ad_counter_banner() != 5000) {
                        mix_adsInter_banner++;
                        if (MyHelpers.getmix_ad_counter_banner() + 1 == mix_adsInter_banner) {
                            mix_adsInter_banner = 0;
                            fb_banner_container.setVisibility(View.GONE);
                            google_banner_container.setVisibility(View.VISIBLE);
                            GoogleNativeBanner(context, fb_banner_container, main_banner_layout);
                        } else {
                            fb_banner_container.setVisibility(View.VISIBLE);
                            google_banner_container.setVisibility(View.GONE);
                            FacebookBanner(context, google_banner_container, main_banner_layout);
                        }
                    } else {
                        fb_banner_container.setVisibility(View.VISIBLE);
                        google_banner_container.setVisibility(View.GONE);
                        FacebookBanner(context, google_banner_container, main_banner_layout);

                    }
                }
                return;
            }
            /**
             * Regular Ads
             */
            if (MyHelpers.getGoogleEnable().equals("1")) {
                fb_banner_container.setVisibility(View.GONE);
                google_banner_container.setVisibility(View.VISIBLE);
                GoogleNativeBanner(context, google_banner_container, main_banner_layout);
            } else if (MyHelpers.getFacebookEnable().equals("1")) {
                fb_banner_container.setVisibility(View.VISIBLE);
                google_banner_container.setVisibility(View.GONE);
                FacebookBanner(context, fb_banner_container, main_banner_layout);

            }
        }
    }

    /*BannerNative Load Code Google */
    private static void GoogleNativeBanner(Context context, RelativeLayout banner_container, View main_banner_layout) {
        View adView = LayoutInflater.from(context).inflate(R.layout.ad_google_native_small_banner, null);
        final LinearLayout linear_ads_shows = adView.findViewById(R.id.linear_ads_shows_small_banner);
        com.google.android.gms.ads.nativead.NativeAdView adView1 = adView.findViewById(R.id.ad_view_small_banner);
        linear_ads_shows.setVisibility(View.GONE);
        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner())
                .forNativeAd(nativeAds -> {
                    GoogleNativeBig = nativeAds;
                    main_banner_layout.setVisibility(View.VISIBLE);
                    linear_ads_shows.setVisibility(View.VISIBLE);
                    banner_container.setVisibility(View.VISIBLE);
                    if (GoogleNativeBig != null) {
                        populateNativeBanner(GoogleNativeBig, adView1);
                    }
                    banner_container.removeAllViews();
                    banner_container.addView(adView);

                }).withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner1())
                                .forNativeAd(nativeAds -> {
                                    GoogleNativeBig = nativeAds;
                                    main_banner_layout.setVisibility(View.VISIBLE);
                                    linear_ads_shows.setVisibility(View.VISIBLE);
                                    banner_container.setVisibility(View.VISIBLE);
                                    if (GoogleNativeBig != null) {
                                        populateNativeBanner(GoogleNativeBig, adView1);
                                    }
                                    banner_container.removeAllViews();
                                    banner_container.addView(adView);

                                }).withAdListener(new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                                        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner2())
                                                .forNativeAd(nativeAds -> {
                                                    GoogleNativeBig = nativeAds;
                                                    main_banner_layout.setVisibility(View.VISIBLE);
                                                    linear_ads_shows.setVisibility(View.VISIBLE);
                                                    banner_container.setVisibility(View.VISIBLE);
                                                    if (GoogleNativeBig != null) {
                                                        populateNativeBanner(GoogleNativeBig, adView1);
                                                    }
                                                    banner_container.removeAllViews();
                                                    banner_container.addView(adView);

                                                }).withAdListener(new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                                    }

                                                    @Override
                                                    public void onAdClicked() {
                                                        super.onAdClicked();
                                                    }

                                                    @Override
                                                    public void onAdLoaded() {
                                                        super.onAdLoaded();
                                                    }

                                                    @Override
                                                    public void onAdImpression() {
                                                        super.onAdImpression();
                                                    }

                                                    @Override
                                                    public void onAdOpened() {
                                                        super.onAdOpened();
                                                    }

                                                }).build();
                                        adLoader.loadAd(new AdRequest.Builder().build());

                                    }

                                    @Override
                                    public void onAdClicked() {
                                        super.onAdClicked();
                                    }

                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                    }

                                    @Override
                                    public void onAdImpression() {
                                        super.onAdImpression();
                                    }

                                    @Override
                                    public void onAdOpened() {
                                        super.onAdOpened();
                                    }

                                }).build();
                        adLoader.loadAd(new AdRequest.Builder().build());

                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public static void populateNativeBanner(com.google.android.gms.ads.nativead.NativeAd nativeAd, com.google.android.gms.ads.nativead.NativeAdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline_small_banner));
        adView.setBodyView(adView.findViewById(R.id.ad_body_small_banner));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action_small_banner));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon_small_banner));

        ((TextView) Objects.requireNonNull(adView.getHeadlineView())).setText(nativeAd.getHeadline());
        ((TextView) Objects.requireNonNull(adView.getBodyView())).setText(nativeAd.getBody());
        ((TextView) Objects.requireNonNull(adView.getCallToActionView())).setText(nativeAd.getCallToAction());

        if (nativeAd.getIcon() == null) {
            Objects.requireNonNull(adView.getIconView()).setVisibility(View.GONE);
        } else {
            ((ImageView) Objects.requireNonNull(adView.getIconView())).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);
    }

    /*Banner Load Code FB*/
    private static void FacebookBanner(Context context, RelativeLayout banner_container, View main_banner_layout) {

        com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, MyHelpers.getFacebookBanner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {

                com.facebook.ads.AdView adView_2 = new com.facebook.ads.AdView(context, MyHelpers.getFacebookBanner1(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                com.facebook.ads.AdListener adListener_2 = new com.facebook.ads.AdListener() {
                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        com.facebook.ads.AdView adView_3 = new com.facebook.ads.AdView(context, MyHelpers.getFacebookBanner2(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        com.facebook.ads.AdListener adListener_3 = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, com.facebook.ads.AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                main_banner_layout.setVisibility(View.VISIBLE);
                                banner_container.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView_3.buildLoadAdConfig()
                                .withAdListener(adListener_3)
                                .build();
                        adView_3.loadAd(loadAdConfig);
                        banner_container.addView(adView_3);

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        main_banner_layout.setVisibility(View.VISIBLE);
                        banner_container.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };

                com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView_2.buildLoadAdConfig()
                        .withAdListener(adListener_2)
                        .build();
                adView_2.loadAd(loadAdConfig);
                banner_container.addView(adView_2);

            }

            @Override
            public void onAdLoaded(Ad ad) {
                main_banner_layout.setVisibility(View.VISIBLE);
                banner_container.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig()
                .withAdListener(adListener)
                .build();
        adView.loadAd(loadAdConfig);
        banner_container.addView(adView);


    }

    public static void Facebook_Open(Context context, Intent intent) {
        com.facebook.ads.InterstitialAd fb_open_ad = new com.facebook.ads.InterstitialAd(context, MyHelpers.getfacebook_open_ad_id());
        InterstitialAdListener adListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                try {


                    SplashHelp.isShowOpen = false;
                    AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                        @Override
                        public void OnAppOpenFailToLoad() {
                            if (SplashHelp.isShowOpen) {
                                SplashHelp.isShowOpen = false;
                            }
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }

                        @Override
                        public void OnAppOpenClose() {
                            if (SplashHelp.isShowOpen) {
                                SplashHelp.isShowOpen = false;
                            }
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    };
                    SplashHelp.isShowOpen = true;
                    SplashHelp.appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            @Override
            public void onAdLoaded(Ad ad) {
                try {
                    if (fb_open_ad != null) {
                        fb_open_ad.show();
                    }
                } catch (Exception e) {
                }
                FacebookInterLoad(context);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        fb_open_ad.loadAd(fb_open_ad.buildLoadAdConfig().withAdListener(adListener).build());
    }

    public static void Google_open_failed_Facebook_Open(Context context, Intent intent) {
        com.facebook.ads.InterstitialAd fb_open_ad = new com.facebook.ads.InterstitialAd(context, MyHelpers.getfacebook_open_ad_id());
        InterstitialAdListener adListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                try {
                    if (fb_open_ad != null) {
                        fb_open_ad.show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        fb_open_ad.loadAd(fb_open_ad.buildLoadAdConfig().withAdListener(adListener).build());
    }

}
