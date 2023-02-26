package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.bumptech.glide.Glide;
import com.criteo.publisher.CriteoErrorCode;
import com.criteo.publisher.advancednative.CriteoMediaView;
import com.criteo.publisher.advancednative.CriteoNativeAd;
import com.criteo.publisher.advancednative.CriteoNativeAdListener;
import com.criteo.publisher.advancednative.CriteoNativeLoader;
import com.criteo.publisher.advancednative.CriteoNativeRenderer;
import com.criteo.publisher.advancednative.RendererHelper;
import com.criteo.publisher.model.NativeAdUnit;
import com.facebook.ads.Ad;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.ArrayList;
import java.util.List;

public class NativeClass {

    public static com.google.android.gms.ads.nativead.NativeAd GoogleNativeBig = null;
    public static com.google.android.gms.ads.nativead.NativeAd GoogleNativeBig1 = null;
    public static com.google.android.gms.ads.nativead.NativeAd GoogleNativeBig2 = null;
    public static com.google.android.gms.ads.nativead.NativeAd GoogleNativeBig3 = null;
    public static int auto_notShow_ads_native = 0;

    public static int mix_ads_native = 0;
    public static int auto_native_show_id = 0;

    public static MaxNativeAdLoader nativeAdLoader;
    public static MaxAd nativeAd;

    public static Activity activity;
    public static ViewGroup viewGroup;
    public static LinearLayout linearLayout;
    public static RelativeLayout google_native;
    public static RelativeLayout facebook_native;
    public static FrameLayout criteo_native;
    public static FrameLayout applovin_native;
    public static LinearLayout custom_native;

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
     * NATIVE ADS CODE START
     */
    public static void NativeAds(Activity activity1, ViewGroup viewGroup1, LinearLayout linearLayout1, RelativeLayout google_native1, RelativeLayout facebook_native1, FrameLayout criteo_native1, LinearLayout custom_native1, FrameLayout applovin_native1) {
        activity = activity1;
        viewGroup = viewGroup1;
        linearLayout = linearLayout1;
        google_native = google_native1;
        facebook_native = facebook_native1;
        criteo_native = criteo_native1;
        custom_native = custom_native1;
        applovin_native = applovin_native1;

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
                if (MyHelpers.getmix_ad_native().equals("0")) {
                    RegularAds();
                } else {
                    if (MyHelpers.getmix_ad_inter().length() == 2) {
                        Mix2Ads(MyHelpers.getmix_ad_inter());  // 2 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 3) {
                        Mix3Ads(MyHelpers.getmix_ad_inter()); // 3 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 4) {
                        Mix4Ads(MyHelpers.getmix_ad_inter()); // 4 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 5) {
                        Mix5Ads(MyHelpers.getmix_ad_inter()); // 5 ads
                    }
                }
                return;
            }
            /**
             * Regular Ads
             */
            RegularAds();

        }
    }

    private static void GoogleADSNativeShow() {
        /**
         * 1 = Mix Ads
         * 2 = 1 and 2 Ads
         * 3 = 1, 2 amd 3 Ads
         */
        if (MyHelpers.Google_native_number == 1) {
            NativeAd_1();
        } else if (MyHelpers.Google_native_number == 2) {
            if (auto_native_show_id == 0) {
                auto_native_show_id = 1;
                GoogleNativeAd_1();
            } else {
                auto_native_show_id = 0;
                GoogleNativeAd_2();
            }
        } else if (MyHelpers.Google_native_number == 3) {
            if (auto_native_show_id == 0) {
                auto_native_show_id = 1;
                GoogleNativeAd_1();
            } else if (auto_native_show_id == 1) {
                auto_native_show_id = 2;
                GoogleNativeAd_2();
            } else {
                auto_native_show_id = 0;
                GoogleNativeAd_3();
            }
        }


    }

    /*Native Load Code Google (MIX ID)*/
    public static void NativeAd_1() {
        google_native.setVisibility(View.VISIBLE);
        facebook_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;
                    NativeAd_2();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_1();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
    }

    public static void NativeAd_2() {
        google_native.setVisibility(View.VISIBLE);
        facebook_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative1());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;
                    NativeAd_3();

                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_2();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
    }

    public static void NativeAd_3() {
        google_native.setVisibility(View.VISIBLE);
        facebook_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative2());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;
                    FailsNativeAds("g");
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_3();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
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

    /*Google Native Show ID 1*/
    public static void GoogleNativeAd_1() {
        google_native.setVisibility(View.VISIBLE);
        facebook_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
        if (GoogleNativeBig1 == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig1 = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig1, nativeAdView, activity, viewGroup, google_native);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig1 = null;
                    if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty()) {
                        FacebookNative();
                    }
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig1 = null;
                    GoogleNativeAd_1();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        populateUnifiedNativeAdView(GoogleNativeBig1, nativeAdView, activity, viewGroup, google_native);
    }

    /*Google Native Show ID 2*/
    public static void GoogleNativeAd_2() {
        google_native.setVisibility(View.VISIBLE);
        facebook_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
        if (GoogleNativeBig2 == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative1());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig2 = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig2, nativeAdView, activity, viewGroup, google_native);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig2 = null;
                    if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty()) {
                        FacebookNative();
                    }
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig2 = null;
                    GoogleNativeAd_2();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        populateUnifiedNativeAdView(GoogleNativeBig2, nativeAdView, activity, viewGroup, google_native);
    }

    /*Google Native Show ID 3*/
    public static void GoogleNativeAd_3() {
        google_native.setVisibility(View.VISIBLE);
        facebook_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
        if (GoogleNativeBig3 == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative2());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig3 = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig3, nativeAdView, activity, viewGroup, google_native);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig3 = null;
                    FailsNativeAds("g");
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig3 = null;
                    GoogleNativeAd_3();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        populateUnifiedNativeAdView(GoogleNativeBig3, nativeAdView, activity, viewGroup, google_native);
    }


    /*Native Load Code FB*/
    private static void FacebookNative() {
        facebook_native.setVisibility(View.VISIBLE);
        google_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
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
                                FailsNativeAds("f");
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                                if (nativeAd_3 == null || nativeAd_3 != ad) {
                                    return;
                                }

                                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, facebook_native, false);
                                fbPopulateNativeAdView(nativeAd_3, adView);
                                facebook_native.removeAllViews();
                                facebook_native.addView(adView);
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
                        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, facebook_native, false);
                        fbPopulateNativeAdView(nativeAd_2, adView);
                        facebook_native.removeAllViews();
                        facebook_native.addView(adView);
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
                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, facebook_native, false);
                fbPopulateNativeAdView(nativeAd, adView);
                facebook_native.removeAllViews();
                facebook_native.addView(adView);

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
        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    /*Criteo Native*/
    public static void CriteoAdsNative() {
        google_native.setVisibility(View.GONE);
        facebook_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.VISIBLE);
        custom_native.setVisibility(View.GONE);
        CriteoNativeLoader nativeLoader = new CriteoNativeLoader(new NativeAdUnit(MyHelpers.getCriteoNative()), new MyNativeAdListener(), new MyNativeRenderer());
        nativeLoader.loadAd();
    }

    public static class MyNativeAdListener implements CriteoNativeAdListener {
        @Override
        public void onAdReceived(CriteoNativeAd nativeAd) {
            View nativeView = nativeAd.createNativeRenderedView(activity, criteo_native);
            criteo_native.removeAllViews();
            criteo_native.addView(nativeView);
        }

        @Override
        public void onAdFailedToReceive(CriteoErrorCode errorCode) {
            FailsNativeAds("c");
        }

        @Override
        public void onAdImpression() {
            // called when native view is visible on the screen and impression is registered
        }

        @Override
        public void onAdClicked() {
            // called when user clicks on Criteo ad
        }

        @Override
        public void onAdLeftApplication() {
            // called when clicking on Criteo ad result in user leaving your application
        }

        @Override
        public void onAdClosed() {
            // called when Criteo ad is closed
        }
    }

    public static class MyNativeRenderer implements CriteoNativeRenderer {

        @Override
        public View createNativeView(Context context, ViewGroup viewGroup) {
            return LayoutInflater.from(context).inflate(R.layout.ad_native_c, viewGroup, false);
        }

        @Override
        public void renderNativeView(RendererHelper rendererHelper, View view, CriteoNativeAd criteoNativeAd) {
            view.<TextView>findViewById(R.id.ad_title).setText(criteoNativeAd.getTitle());
            view.<TextView>findViewById(R.id.ad_description).setText(criteoNativeAd.getDescription());
            view.<TextView>findViewById(R.id.ad_attribution).setText(String.format("Ads by %s", criteoNativeAd.getAdvertiserDomain()));
            view.<Button>findViewById(R.id.ad_calltoaction).setText(criteoNativeAd.getCallToAction());
            rendererHelper.setMediaInView(criteoNativeAd.getProductMedia(), view.<CriteoMediaView>findViewById(R.id.ad_media));
        }
    }

    /*AppLivin Native*/
    private static void APPLovinNative() {
        google_native.setVisibility(View.GONE);
        facebook_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        applovin_native.setVisibility(View.VISIBLE);

        nativeAdLoader = new MaxNativeAdLoader(MyHelpers.getAppLovinNative(), activity);
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }
                // Save ad for cleanup.
                nativeAd = ad;
                // Add ad view to view.
                applovin_native.removeAllViews();
                applovin_native.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                FailsNativeAds("a");
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                // Optional click callback
            }
        });
        nativeAdLoader.loadAd();

    }

    /*Custom Native*/
    private static void CustomADSNative() {
        google_native.setVisibility(View.GONE);
        facebook_native.setVisibility(View.GONE);
        criteo_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.VISIBLE);
        int ads_number = MyHelpers.getRandomNumber(0, SplashHelp.adsModals.size() - 1);
        LinearLayout native_view = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.custom_native, (ViewGroup) null);
        AppCompatButton btn_install = native_view.findViewById(R.id.btn_install);
        RelativeLayout full_click = native_view.findViewById(R.id.full_click);
        TextView app_name = native_view.findViewById(R.id.app_name);
        TextView app_shot = native_view.findViewById(R.id.app_shot);
        ImageView app_icon = native_view.findViewById(R.id.app_icon);
        ImageView ads_banner = native_view.findViewById(R.id.ads_banner);
        Glide.with(activity).load(SplashHelp.adsModals.get(ads_number).getApp_logo()).into(app_icon);
        Glide.with(activity).load(SplashHelp.adsModals.get(ads_number).getApp_banner()).into(ads_banner);
        app_name.setText(SplashHelp.adsModals.get(ads_number).getAd_app_name());
        app_shot.setText(SplashHelp.adsModals.get(ads_number).getApp_description());
        btn_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        full_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        custom_native.removeAllViews();
        custom_native.addView(native_view);

    }


    /**
     * Regular Ads
     */
    private static void RegularAds() {
        if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            GoogleADSNativeShow();
        } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            FacebookNative();
        } else if (MyHelpers.getCriteoEnable().equals("1")) {
            CriteoAdsNative();
        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            APPLovinNative();
        } else if (MyHelpers.getUnityEnable().equals("1")) {
            GoogleandFacebookShow("u");
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustomADSNative();
        }
    }

    /**
     * Mix Ads
     */
    private static void Mix2Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        if (MyHelpers.getmix_ad_counter() != 5000) {
            mix_ads_native++;
            if (MyHelpers.getmix_ad_counter() + 1 == mix_ads_native) {
                MixAdsShowNative(second_ads);
                mix_ads_native = 0;
            } else {
                MixAdsShowNative(first_ads);
            }
        } else {
            if (mix_ads_native == 0) {
                mix_ads_native = 1;
                MixAdsShowNative(first_ads);
            } else if (mix_ads_native == 1) {
                mix_ads_native = 0;
                MixAdsShowNative(second_ads);
            }
        }
    }

    private static void Mix3Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        if (mix_ads_native == 0) {
            mix_ads_native = 1;
            MixAdsShowNative(first_ads);
        } else if (mix_ads_native == 1) {
            mix_ads_native = 2;
            MixAdsShowNative(second_ads);
        } else if (mix_ads_native == 2) {
            mix_ads_native = 0;
            MixAdsShowNative(three_ads);
        }
    }

    private static void Mix4Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        if (mix_ads_native == 0) {
            mix_ads_native = 1;
            MixAdsShowNative(first_ads);
        } else if (mix_ads_native == 1) {
            mix_ads_native = 2;
            MixAdsShowNative(second_ads);
        } else if (mix_ads_native == 2) {
            mix_ads_native = 3;
            MixAdsShowNative(three_ads);
        } else if (mix_ads_native == 3) {
            mix_ads_native = 0;
            MixAdsShowNative(four_ads);

        }
    }

    private static void Mix5Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        char five_ads = s.charAt(4);
        if (mix_ads_native == 0) {
            mix_ads_native = 1;
            MixAdsShowNative(first_ads);
        } else if (mix_ads_native == 1) {
            mix_ads_native = 2;
            MixAdsShowNative(second_ads);
        } else if (mix_ads_native == 2) {
            mix_ads_native = 3;
            MixAdsShowNative(three_ads);
        } else if (mix_ads_native == 3) {
            mix_ads_native = 4;
            MixAdsShowNative(four_ads);
        } else if (mix_ads_native == 4) {
            mix_ads_native = 0;
            MixAdsShowNative(five_ads);
        }

    }

    private static void MixAdsShowNative(char ads) {
        String value = String.valueOf(ads);
        if (value.equals("g") && MyHelpers.getlive_status().equals("1")) {
            GoogleADSNativeShow();
        } else if (value.equals("f") && MyHelpers.getlive_status().equals("1")) {
            FacebookNative();
        } else if (value.equals("c")) {
            CriteoAdsNative();
        } else if (value.equals("a")) {
            APPLovinNative();
        } else if (value.equals("o")) {
            CustomADSNative();
        }
    }

    /**
     * Fail Ads
     */

    public static void FailsNativeAds(String Skip) {
        if (Skip.equals("g")) {
            if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                facebook_native.setVisibility(View.VISIBLE);
                google_native.setVisibility(View.GONE);
                criteo_native.setVisibility(View.GONE);
                custom_native.setVisibility(View.GONE);
                com.facebook.ads.NativeAd nativeAdfb = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative());
                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        GoogleandFacebookFails();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (nativeAdfb == null || nativeAdfb != ad) {
                            return;
                        }
                        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, facebook_native, false);
                        fbPopulateNativeAdView(nativeAdfb, adView);
                        facebook_native.removeAllViews();
                        facebook_native.addView(adView);

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                nativeAdfb.loadAd(nativeAdfb.buildLoadAdConfig().withAdListener(nativeAdListener).build());
            } else {
                GoogleandFacebookFails();
            }
        } else if (Skip.equals("f")) {
            if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                google_native.setVisibility(View.VISIBLE);
                facebook_native.setVisibility(View.GONE);
                criteo_native.setVisibility(View.GONE);
                custom_native.setVisibility(View.GONE);
                if (GoogleNativeBig == null) {
                    AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative());
                    builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                        public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                            com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                            GoogleNativeBig = nativeAd;
                            populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
                        }
                    });
                    builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
                    builder2.withAdListener(new AdListener() {
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            GoogleNativeBig = null;
                            GoogleandFacebookFails();
                        }

                        public void onAdClicked() {
                            super.onAdClicked();
                            GoogleNativeBig = null;
                            NativeAd_1();
                        }
                    }).build().loadAd(new AdRequest.Builder().build());
                    return;
                }
                com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
            } else {
                GoogleandFacebookFails();
            }
        } else if (Skip.equals("c")) {
            GoogleandFacebookShow("c");

        } else if (Skip.equals("a")) {
            GoogleandFacebookShow("a");
        } else {
            CustomADSNative();
        }
    }

    private static void GoogleandFacebookFails() {
        if (MyHelpers.getCriteoNative() != null && !MyHelpers.getCriteoNative().isEmpty()) {
            google_native.setVisibility(View.GONE);
            facebook_native.setVisibility(View.GONE);
            criteo_native.setVisibility(View.VISIBLE);
            custom_native.setVisibility(View.GONE);
            CriteoNativeLoader nativeLoader = new CriteoNativeLoader(new NativeAdUnit(MyHelpers.getCriteoNative()), new GoogleFailMyNativeAdListener(), new MyNativeRenderer());
            nativeLoader.loadAd();
        } else if (MyHelpers.getAppLovinNative() != null && !MyHelpers.getAppLovinNative().isEmpty()) {
            FailsOtherAppLovinShow();
        } else {
            CustomADSNative();
        }
    }

    private static void GoogleandFacebookShow(String show_ad_name) {
        if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            google_native.setVisibility(View.VISIBLE);
            facebook_native.setVisibility(View.GONE);
            criteo_native.setVisibility(View.GONE);
            custom_native.setVisibility(View.GONE);
            if (GoogleNativeBig == null) {
                AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative());
                builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                    public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                        GoogleNativeBig = nativeAd;
                        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
                    }
                });
                builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
                builder2.withAdListener(new AdListener() {
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        GoogleNativeBig = null;
                        if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty()) {
                            facebook_native.setVisibility(View.VISIBLE);
                            google_native.setVisibility(View.GONE);
                            criteo_native.setVisibility(View.GONE);
                            custom_native.setVisibility(View.GONE);
                            com.facebook.ads.NativeAd nativeAdfb = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative());
                            NativeAdListener nativeAdListener = new NativeAdListener() {
                                @Override
                                public void onMediaDownloaded(Ad ad) {
                                }

                                @Override
                                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                    if (show_ad_name.equals("c")) {
                                        CriteoFailsOpenOtherAds();
                                    } else if (show_ad_name.equals("a")) {
                                        AppLovinFailsOpenOtherAds();
                                    } else if (show_ad_name.equals("u")) {
                                        UnityFailShowOtherAds();
                                    } else {
                                        CustomADSNative();
                                    }
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    if (nativeAdfb == null || nativeAdfb != ad) {
                                        return;
                                    }
                                    LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, facebook_native, false);
                                    fbPopulateNativeAdView(nativeAdfb, adView);
                                    facebook_native.removeAllViews();
                                    facebook_native.addView(adView);

                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            };
                            nativeAdfb.loadAd(nativeAdfb.buildLoadAdConfig().withAdListener(nativeAdListener).build());
                        } else if (MyHelpers.getAppLovinNative() != null && !MyHelpers.getAppLovinNative().isEmpty()) {
                            google_native.setVisibility(View.GONE);
                            facebook_native.setVisibility(View.GONE);
                            criteo_native.setVisibility(View.VISIBLE);
                            custom_native.setVisibility(View.GONE);
                            nativeAdLoader = new MaxNativeAdLoader(MyHelpers.getAppLovinNative(), activity);
                            nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                                @Override
                                public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                                    // Clean up any pre-existing native ad to prevent memory leaks.
                                    if (nativeAd != null) {
                                        nativeAdLoader.destroy(nativeAd);
                                    }
                                    // Save ad for cleanup.
                                    nativeAd = ad;
                                    // Add ad view to view.
                                    criteo_native.removeAllViews();
                                    criteo_native.addView(nativeAdView);
                                }

                                @Override
                                public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                                    CustomADSNative();
                                }

                                @Override
                                public void onNativeAdClicked(final MaxAd ad) {
                                    // Optional click callback
                                }
                            });
                            nativeAdLoader.loadAd();
                        } else {
                            CustomADSNative();
                        }
                    }

                    public void onAdClicked() {
                        super.onAdClicked();
                        GoogleNativeBig = null;
                        NativeAd_1();
                    }
                }).build().loadAd(new AdRequest.Builder().build());
                return;
            }
            com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
            populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, google_native);
        } else if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            facebook_native.setVisibility(View.VISIBLE);
            google_native.setVisibility(View.GONE);
            criteo_native.setVisibility(View.GONE);
            custom_native.setVisibility(View.GONE);
            com.facebook.ads.NativeAd nativeAdfb = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (show_ad_name.equals("c")) {
                        CriteoFailsOpenOtherAds();
                    } else if (show_ad_name.equals("a")) {
                        AppLovinFailsOpenOtherAds();
                    } else if (show_ad_name.equals("u")) {
                        UnityFailShowOtherAds();
                    } else {
                        CustomADSNative();
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAdfb == null || nativeAdfb != ad) {
                        return;
                    }
                    LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, facebook_native, false);
                    fbPopulateNativeAdView(nativeAdfb, adView);
                    facebook_native.removeAllViews();
                    facebook_native.addView(adView);

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            nativeAdfb.loadAd(nativeAdfb.buildLoadAdConfig().withAdListener(nativeAdListener).build());
        } else {
            if (show_ad_name.equals("c")) {
                CriteoFailsOpenOtherAds();
            } else if (show_ad_name.equals("a")) {
                AppLovinFailsOpenOtherAds();
            } else if (show_ad_name.equals("u")) {
                UnityFailShowOtherAds();
            } else {
                CustomADSNative();
            }
        }
    }

    private static void UnityFailShowOtherAds() {
        if (MyHelpers.getCriteoNative() != null && !MyHelpers.getCriteoNative().isEmpty()) {
            google_native.setVisibility(View.GONE);
            facebook_native.setVisibility(View.GONE);
            criteo_native.setVisibility(View.VISIBLE);
            custom_native.setVisibility(View.GONE);
            CriteoNativeLoader nativeLoader = new CriteoNativeLoader(new NativeAdUnit(MyHelpers.getCriteoNative()), new GoogleFailMyNativeAdListener(), new MyNativeRenderer());
            nativeLoader.loadAd();
        } else {
            FailsOtherAppLovinShow();
        }
    }

    private static void CriteoFailsOpenOtherAds() {
        FailsOtherAppLovinShow();
    }

    private static void AppLovinFailsOpenOtherAds() {
        if (MyHelpers.getCriteoNative() != null && !MyHelpers.getCriteoNative().isEmpty()) {
            google_native.setVisibility(View.GONE);
            facebook_native.setVisibility(View.GONE);
            criteo_native.setVisibility(View.VISIBLE);
            custom_native.setVisibility(View.GONE);
            CriteoNativeLoader nativeLoader = new CriteoNativeLoader(new NativeAdUnit(MyHelpers.getCriteoNative()), new AppLovinFailMyNativeAdListener(), new MyNativeRenderer());
            nativeLoader.loadAd();
        } else {
            CustomADSNative();
        }
    }

    private static void FailsOtherAppLovinShow() {
        if (MyHelpers.getAppLovinNative() != null && !MyHelpers.getAppLovinNative().isEmpty()) {
            google_native.setVisibility(View.GONE);
            facebook_native.setVisibility(View.GONE);
            criteo_native.setVisibility(View.GONE);
            applovin_native.setVisibility(View.VISIBLE);
            custom_native.setVisibility(View.GONE);
            nativeAdLoader = new MaxNativeAdLoader(MyHelpers.getAppLovinNative(), activity);
            nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                @Override
                public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                    // Clean up any pre-existing native ad to prevent memory leaks.
                    if (nativeAd != null) {
                        nativeAdLoader.destroy(nativeAd);
                    }
                    // Save ad for cleanup.
                    nativeAd = ad;
                    // Add ad view to view.
                    applovin_native.removeAllViews();
                    applovin_native.addView(nativeAdView);
                }

                @Override
                public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                    CustomADSNative();
                }

                @Override
                public void onNativeAdClicked(final MaxAd ad) {
                    // Optional click callback
                }
            });
            nativeAdLoader.loadAd();
        } else {
            CustomADSNative();
        }
    }

    public static class GoogleFailMyNativeAdListener implements CriteoNativeAdListener {
        @Override
        public void onAdReceived(CriteoNativeAd nativeAd) {
            View nativeView = nativeAd.createNativeRenderedView(activity, criteo_native);
            criteo_native.removeAllViews();
            criteo_native.addView(nativeView);
        }

        @Override
        public void onAdFailedToReceive(CriteoErrorCode errorCode) {
            FailsOtherAppLovinShow();
        }

        @Override
        public void onAdImpression() {
            // called when native view is visible on the screen and impression is registered
        }

        @Override
        public void onAdClicked() {
            // called when user clicks on Criteo ad
        }

        @Override
        public void onAdLeftApplication() {
            // called when clicking on Criteo ad result in user leaving your application
        }

        @Override
        public void onAdClosed() {
            // called when Criteo ad is closed
        }
    }

    public static class AppLovinFailMyNativeAdListener implements CriteoNativeAdListener {
        @Override
        public void onAdReceived(CriteoNativeAd nativeAd) {
            View nativeView = nativeAd.createNativeRenderedView(activity, criteo_native);
            criteo_native.removeAllViews();
            criteo_native.addView(nativeView);
        }

        @Override
        public void onAdFailedToReceive(CriteoErrorCode errorCode) {
            CustomADSNative();
        }

        @Override
        public void onAdImpression() {
            // called when native view is visible on the screen and impression is registered
        }

        @Override
        public void onAdClicked() {
            // called when user clicks on Criteo ad
        }

        @Override
        public void onAdLeftApplication() {
            // called when clicking on Criteo ad result in user leaving your application
        }

        @Override
        public void onAdClosed() {
            // called when Criteo ad is closed
        }
    }


}
