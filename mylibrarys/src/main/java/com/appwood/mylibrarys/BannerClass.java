package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.bumptech.glide.Glide;
import com.criteo.publisher.CriteoBannerAdListener;
import com.criteo.publisher.CriteoBannerView;
import com.criteo.publisher.CriteoErrorCode;
import com.criteo.publisher.model.AdSize;
import com.criteo.publisher.model.BannerAdUnit;
import com.facebook.ads.Ad;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.Objects;

public class BannerClass {


    public static int auto_notShow_ads_banner = 0;
    public static AdLoader adLoader;
    public static com.google.android.gms.ads.nativead.NativeAd GoogleNativeBig = null;

    /*AooLovin Ads*/
    public static MaxNativeAdView nativeAdView;
    public static MaxNativeAdLoader nativeAdLoader;
    public static MaxAd nativeAd = null;

    public static int mix_ads_banner = 0;

    public static View main_banner;
    public static RelativeLayout google_banner;
    public static RelativeLayout fb_banner;
    public static LinearLayout applovin_banner;
    public static Context context;

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
     * BANNER ADS CODE START
     */
    /*Banner Main Code*/
    public static void Banner(Context context1, View main_banner1, RelativeLayout google_banner1, RelativeLayout fb_banner1, LinearLayout applovin_banner1) {
        main_banner = main_banner1;
        google_banner = google_banner1;
        fb_banner = fb_banner1;
        applovin_banner = applovin_banner1;
        context = context1;

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
                if (MyHelpers.getmix_ad_native().equals("0")) {
                    RegularBannerAds();
                } else {
                    if (MyHelpers.getmix_ad_inter().length() == 2) {
                        Mix2AdsBanner(MyHelpers.getmix_ad_inter());  // 2 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 3) {
                        Mix3AdsBanner(MyHelpers.getmix_ad_inter()); // 3 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 4) {
                        Mix4AdsBanner(MyHelpers.getmix_ad_inter()); // 4 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 5) {
                        Mix5AdsBanner(MyHelpers.getmix_ad_inter()); // 5 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 6) {
                        Mix6AdsBanner(MyHelpers.getmix_ad_inter()); // 6 ads
                    }
                }
                return;
            }
            /**
             * Regular Ads
             */
            RegularBannerAds();
        }

    }


    /*BannerNative Load Code Google */
    private static void GoogleBanner() {
        fb_banner.setVisibility(View.GONE);
        applovin_banner.setVisibility(View.GONE);
        google_banner.setVisibility(View.VISIBLE);
        main_banner.setVisibility(View.VISIBLE);

        View adView = LayoutInflater.from(context).inflate(R.layout.ad_google_native_small_banner, null);
        final LinearLayout linear_ads_shows = adView.findViewById(R.id.linear_ads_shows_small_banner);
        com.google.android.gms.ads.nativead.NativeAdView adView1 = adView.findViewById(R.id.ad_view_small_banner);
        linear_ads_shows.setVisibility(View.GONE);
        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner()).forNativeAd(nativeAds -> {
            GoogleNativeBig = nativeAds;
            main_banner.setVisibility(View.VISIBLE);
            linear_ads_shows.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.VISIBLE);
            if (GoogleNativeBig != null) {
                populateNativeBanner(GoogleNativeBig, adView1);
            }
            google_banner.removeAllViews();
            google_banner.addView(adView);

        }).withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner1()).forNativeAd(nativeAds -> {
                    GoogleNativeBig = nativeAds;
                    main_banner.setVisibility(View.VISIBLE);
                    linear_ads_shows.setVisibility(View.VISIBLE);
                    google_banner.setVisibility(View.VISIBLE);
                    if (GoogleNativeBig != null) {
                        populateNativeBanner(GoogleNativeBig, adView1);
                    }
                    google_banner.removeAllViews();
                    google_banner.addView(adView);

                }).withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner2()).forNativeAd(nativeAds -> {
                            GoogleNativeBig = nativeAds;
                            main_banner.setVisibility(View.VISIBLE);
                            linear_ads_shows.setVisibility(View.VISIBLE);
                            google_banner.setVisibility(View.VISIBLE);
                            if (GoogleNativeBig != null) {
                                populateNativeBanner(GoogleNativeBig, adView1);
                            }
                            google_banner.removeAllViews();
                            google_banner.addView(adView);

                        }).withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                FailsAds("g");
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
            ((ImageView) Objects.requireNonNull(adView.getIconView())).setImageDrawable(nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);
    }

    /*Banner Load Code FB*/
    private static void FacebookBanner() {
        fb_banner.setVisibility(View.VISIBLE);
        main_banner.setVisibility(View.VISIBLE);
        applovin_banner.setVisibility(View.GONE);
        google_banner.setVisibility(View.GONE);
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
                                FailsAds("f");
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                main_banner.setVisibility(View.VISIBLE);
                                fb_banner.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView_3.buildLoadAdConfig().withAdListener(adListener_3).build();
                        adView_3.loadAd(loadAdConfig);
                        fb_banner.addView(adView_3);

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        main_banner.setVisibility(View.VISIBLE);
                        fb_banner.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };

                com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView_2.buildLoadAdConfig().withAdListener(adListener_2).build();
                adView_2.loadAd(loadAdConfig);
                fb_banner.addView(adView_2);

            }

            @Override
            public void onAdLoaded(Ad ad) {
                main_banner.setVisibility(View.VISIBLE);
                fb_banner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig().withAdListener(adListener).build();
        adView.loadAd(loadAdConfig);
        fb_banner.removeAllViews();
        fb_banner.addView(adView);


    }

    /*APP LOVIN BANNER ADS*/
    private static void AppLovinBannerAd() {
        fb_banner.setVisibility(View.GONE);
        google_banner.setVisibility(View.GONE);
        applovin_banner.setVisibility(View.VISIBLE);
        main_banner.setVisibility(View.VISIBLE);
        MaxAdView adView = new MaxAdView(MyHelpers.getAppLovinBanner(), context);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int dpHeightInPx = (int) (50 * context.getResources().getDisplayMetrics().density);
        adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                applovin_banner.removeAllViews();
                applovin_banner.addView(adView);
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

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
        adView.loadAd();
        adView.startAutoRefresh();
    }

    /*Criteo BANNER ADS*/
    public static void CriteoAdsBanner() {
        fb_banner.setVisibility(View.GONE);
        applovin_banner.setVisibility(View.VISIBLE);
        google_banner.setVisibility(View.GONE);
        main_banner.setVisibility(View.VISIBLE);
        CriteoBannerView bannerView = new CriteoBannerView(context, new BannerAdUnit(MyHelpers.getCriteoBanner(), new AdSize(320, 50)));
        bannerView.setCriteoBannerAdListener(new CriteoBannerAdListener() {
            @Override
            public void onAdReceived(@NonNull CriteoBannerView view) {
                applovin_banner.removeAllViews();
                applovin_banner.addView(bannerView);
            }

            @Override
            public void onAdFailedToReceive(@NonNull CriteoErrorCode criteoErrorCode) {
                FailsAds("c");
            }
        });

        bannerView.loadAd();
    }

    /*Unity Banner Ads*/
    private static void UnityBanners() {
        fb_banner.setVisibility(View.GONE);
        applovin_banner.setVisibility(View.VISIBLE);
        main_banner.setVisibility(View.VISIBLE);
        google_banner.setVisibility(View.GONE);
        BannerView bannerView = new BannerView((Activity) context, MyHelpers.getUnityBannerID(), new UnityBannerSize(320, 50));
        bannerView.setListener(new BannerView.IListener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {
            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                FailsAds("u");

            }

            @Override
            public void onBannerClick(BannerView bannerAdView) {

            }

            @Override
            public void onBannerLeftApplication(BannerView bannerAdView) {

            }
        });
        bannerView.load();
        applovin_banner.removeAllViews();
        applovin_banner.addView(bannerView);

    }

    /*Custom Ads*/
    private static void CustomAds() {
        fb_banner.setVisibility(View.GONE);
        applovin_banner.setVisibility(View.VISIBLE);
        google_banner.setVisibility(View.GONE);
        main_banner.setVisibility(View.VISIBLE);
        int ads_number = MyHelpers.getRandomNumber(0, SplashHelp.adsModals.size() - 1);
        LinearLayout banner_view = (LinearLayout) ((Activity) context).getLayoutInflater().inflate(R.layout.custom_banner, (ViewGroup) null);
        TextView btn_install = (TextView) banner_view.findViewById(R.id.btn_install_banner);
        RelativeLayout full_click = banner_view.findViewById(R.id.full_click_banner);
        TextView app_name = banner_view.findViewById(R.id.app_name_banner);
        TextView app_shot = banner_view.findViewById(R.id.app_shot_banner);
        ImageView app_icon = banner_view.findViewById(R.id.app_icon_banner);
        Glide.with(context).load(SplashHelp.adsModals.get(ads_number).getApp_logo()).into(app_icon);
        app_name.setText(SplashHelp.adsModals.get(ads_number).getAd_app_name());
        app_shot.setText(SplashHelp.adsModals.get(ads_number).getApp_description());
        btn_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        full_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        applovin_banner.removeAllViews();
        applovin_banner.addView(banner_view);
    }

    /**
     * Mix Ads
     */
    private static void Mix2AdsBanner(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        if (MyHelpers.getmix_ad_counter() != 5000) {
            mix_ads_banner++;
            if (MyHelpers.getmix_ad_counter() + 1 == mix_ads_banner) {
                MixAdsShow(second_ads);
                mix_ads_banner = 0;
            } else {
                MixAdsShow(first_ads);
            }
        } else {
            if (mix_ads_banner == 0) {
                mix_ads_banner = 1;
                MixAdsShow(first_ads);
            } else if (mix_ads_banner == 1) {
                mix_ads_banner = 0;
                MixAdsShow(second_ads);
            }
        }
    }

    private static void Mix3AdsBanner(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        if (mix_ads_banner == 0) {
            mix_ads_banner = 1;
            MixAdsShow(first_ads);
        } else if (mix_ads_banner == 1) {
            mix_ads_banner = 2;
            MixAdsShow(second_ads);
        } else if (mix_ads_banner == 2) {
            mix_ads_banner = 0;
            MixAdsShow(three_ads);
        }
    }

    private static void Mix4AdsBanner(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        if (mix_ads_banner == 0) {
            mix_ads_banner = 1;
            MixAdsShow(first_ads);
        } else if (mix_ads_banner == 1) {
            mix_ads_banner = 2;
            MixAdsShow(second_ads);
        } else if (mix_ads_banner == 2) {
            mix_ads_banner = 3;
            MixAdsShow(three_ads);
        } else if (mix_ads_banner == 3) {
            mix_ads_banner = 0;
            MixAdsShow(four_ads);

        }
    }

    private static void Mix5AdsBanner(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        char five_ads = s.charAt(4);
        if (mix_ads_banner == 0) {
            mix_ads_banner = 1;
            MixAdsShow(first_ads);
        } else if (mix_ads_banner == 1) {
            mix_ads_banner = 2;
            MixAdsShow(second_ads);
        } else if (mix_ads_banner == 2) {
            mix_ads_banner = 3;
            MixAdsShow(three_ads);
        } else if (mix_ads_banner == 3) {
            mix_ads_banner = 4;
            MixAdsShow(four_ads);
        } else if (mix_ads_banner == 4) {
            mix_ads_banner = 0;
            MixAdsShow(five_ads);
        }
    }

    private static void Mix6AdsBanner(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        char five_ads = s.charAt(4);
        char six_ads = s.charAt(5);
        if (mix_ads_banner == 0) {
            mix_ads_banner = 1;
            MixAdsShow(first_ads);
        } else if (mix_ads_banner == 1) {
            mix_ads_banner = 2;
            MixAdsShow(second_ads);
        } else if (mix_ads_banner == 2) {
            mix_ads_banner = 3;
            MixAdsShow(three_ads);
        } else if (mix_ads_banner == 3) {
            mix_ads_banner = 4;
            MixAdsShow(four_ads);
        } else if (mix_ads_banner == 4) {
            mix_ads_banner = 5;
            MixAdsShow(five_ads);
        } else if (mix_ads_banner == 5) {
            mix_ads_banner = 0;
            MixAdsShow(six_ads);
        }
    }

    private static void MixAdsShow(char ads) {
        String value = String.valueOf(ads);
        if (value.equals("g") && MyHelpers.getlive_status().equals("1")) {
            GoogleBanner();
        } else if (value.equals("f") && MyHelpers.getlive_status().equals("1")) {
            FacebookBanner();
        } else if (value.equals("c")) {
            AppLovinBannerAd();
        } else if (value.equals("a")) {
            CriteoAdsBanner();
        } else if (value.equals("u")) {
            UnityBanners();
        } else if (value.equals("o")) {
            CustomAds();
        }
    }

    /**
     * Regular Ads
     */
    private static void RegularBannerAds() {
        if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            GoogleBanner();
        } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            FacebookBanner();
        } else if (MyHelpers.getCriteoEnable().equals("1")) {
            CriteoAdsBanner();
        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            AppLovinBannerAd();
        } else if (MyHelpers.getUnityEnable().equals("1")) {
            UnityBanners();
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustomAds();
        }
    }

    /**
     * Fail Code
     */
    public static void FailsAds(String Skip) {
        if (Skip.equals("g")) {
            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                fb_banner.setVisibility(View.VISIBLE);
                main_banner.setVisibility(View.VISIBLE);
                applovin_banner.setVisibility(View.GONE);
                google_banner.setVisibility(View.GONE);
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
                                        FacebookandGoogleFails();
                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        main_banner.setVisibility(View.VISIBLE);
                                        fb_banner.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };

                                com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView_3.buildLoadAdConfig().withAdListener(adListener_3).build();
                                adView_3.loadAd(loadAdConfig);
                                fb_banner.addView(adView_3);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                main_banner.setVisibility(View.VISIBLE);
                                fb_banner.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView_2.buildLoadAdConfig().withAdListener(adListener_2).build();
                        adView_2.loadAd(loadAdConfig);
                        fb_banner.addView(adView_2);

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        main_banner.setVisibility(View.VISIBLE);
                        fb_banner.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig().withAdListener(adListener).build();
                adView.loadAd(loadAdConfig);
                fb_banner.removeAllViews();
                fb_banner.addView(adView);
            } else {
                FacebookandGoogleFails();
            }
        } else if (Skip.equals("f")) {
            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                fb_banner.setVisibility(View.GONE);
                applovin_banner.setVisibility(View.GONE);
                google_banner.setVisibility(View.VISIBLE);
                main_banner.setVisibility(View.VISIBLE);
                View adView = LayoutInflater.from(context).inflate(R.layout.ad_google_native_small_banner, null);
                final LinearLayout linear_ads_shows = adView.findViewById(R.id.linear_ads_shows_small_banner);
                com.google.android.gms.ads.nativead.NativeAdView adView1 = adView.findViewById(R.id.ad_view_small_banner);
                linear_ads_shows.setVisibility(View.GONE);
                adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner()).forNativeAd(nativeAds -> {
                    GoogleNativeBig = nativeAds;
                    main_banner.setVisibility(View.VISIBLE);
                    linear_ads_shows.setVisibility(View.VISIBLE);
                    google_banner.setVisibility(View.VISIBLE);
                    if (GoogleNativeBig != null) {
                        populateNativeBanner(GoogleNativeBig, adView1);
                    }
                    google_banner.removeAllViews();
                    google_banner.addView(adView);

                }).withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner1()).forNativeAd(nativeAds -> {
                            GoogleNativeBig = nativeAds;
                            main_banner.setVisibility(View.VISIBLE);
                            linear_ads_shows.setVisibility(View.VISIBLE);
                            google_banner.setVisibility(View.VISIBLE);
                            if (GoogleNativeBig != null) {
                                populateNativeBanner(GoogleNativeBig, adView1);
                            }
                            google_banner.removeAllViews();
                            google_banner.addView(adView);

                        }).withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                                adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner2()).forNativeAd(nativeAds -> {
                                    GoogleNativeBig = nativeAds;
                                    main_banner.setVisibility(View.VISIBLE);
                                    linear_ads_shows.setVisibility(View.VISIBLE);
                                    google_banner.setVisibility(View.VISIBLE);
                                    if (GoogleNativeBig != null) {
                                        populateNativeBanner(GoogleNativeBig, adView1);
                                    }
                                    google_banner.removeAllViews();
                                    google_banner.addView(adView);

                                }).withAdListener(new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                        FacebookandGoogleFails();
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
            } else {
                FacebookandGoogleFails();
            }
        } else if (Skip.equals("c")) {
            GoogleandFacebookShow("c");
        } else if (Skip.equals("a")) {
            GoogleandFacebookShow("a");
        } else if (Skip.equals("u")) {
            GoogleandFacebookShow("u");
        } else {
            CustomAds();
        }
    }

    private static void FacebookandGoogleFails() {
        if (MyHelpers.getCriteoBanner() != null && !MyHelpers.getCriteoBanner().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            main_banner.setVisibility(View.VISIBLE);
            CriteoBannerView bannerView = new CriteoBannerView(context, new BannerAdUnit(MyHelpers.getCriteoBanner(), new AdSize(320, 50)));
            bannerView.setCriteoBannerAdListener(new CriteoBannerAdListener() {
                @Override
                public void onAdReceived(@NonNull CriteoBannerView view) {
                    applovin_banner.removeAllViews();
                    applovin_banner.addView(bannerView);
                }

                @Override
                public void onAdFailedToReceive(@NonNull CriteoErrorCode criteoErrorCode) {
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        fb_banner.setVisibility(View.GONE);
                        applovin_banner.setVisibility(View.VISIBLE);
                        google_banner.setVisibility(View.GONE);
                        main_banner.setVisibility(View.VISIBLE);
                        MaxAdView adView = new MaxAdView(MyHelpers.getAppLovinBanner(), context);
                        int width = ViewGroup.LayoutParams.MATCH_PARENT;
                        int dpHeightInPx = (int) (50 * context.getResources().getDisplayMetrics().density);
                        adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
                        adView.setListener(new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {

                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                applovin_banner.removeAllViews();
                                applovin_banner.addView(adView);
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {

                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                    fb_banner.setVisibility(View.GONE);
                                    applovin_banner.setVisibility(View.VISIBLE);
                                    main_banner.setVisibility(View.VISIBLE);
                                    google_banner.setVisibility(View.GONE);
                                    FailsUnityBannerShow();
                                } else {
                                    CustomAds();
                                }
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                            }
                        });
                        adView.loadAd();
                        adView.startAutoRefresh();

                    } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        fb_banner.setVisibility(View.GONE);
                        applovin_banner.setVisibility(View.VISIBLE);
                        main_banner.setVisibility(View.VISIBLE);
                        google_banner.setVisibility(View.GONE);
                        FailsUnityBannerShow();
                    } else {
                        CustomAds();
                    }
                }
            });
            bannerView.loadAd();

        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            main_banner.setVisibility(View.VISIBLE);

            MaxAdView adView = new MaxAdView(MyHelpers.getAppLovinBanner(), context);
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int dpHeightInPx = (int) (50 * context.getResources().getDisplayMetrics().density);
            adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    applovin_banner.removeAllViews();
                    applovin_banner.addView(adView);
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        fb_banner.setVisibility(View.GONE);
                        applovin_banner.setVisibility(View.VISIBLE);
                        main_banner.setVisibility(View.VISIBLE);
                        google_banner.setVisibility(View.GONE);
                        FailsUnityBannerShow();
                    } else {
                        CustomAds();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            adView.loadAd();
            adView.startAutoRefresh();


        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            main_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            FailsUnityBannerShow();
        } else {
            CustomAds();
        }
    }

    private static void GoogleandFacebookShow(String show_ad_name) {

        if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.GONE);
            google_banner.setVisibility(View.VISIBLE);
            main_banner.setVisibility(View.VISIBLE);
            View adView = LayoutInflater.from(context).inflate(R.layout.ad_google_native_small_banner, null);
            final LinearLayout linear_ads_shows = adView.findViewById(R.id.linear_ads_shows_small_banner);
            com.google.android.gms.ads.nativead.NativeAdView adView1 = adView.findViewById(R.id.ad_view_small_banner);
            linear_ads_shows.setVisibility(View.GONE);
            adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner()).forNativeAd(nativeAds -> {
                GoogleNativeBig = nativeAds;
                main_banner.setVisibility(View.VISIBLE);
                linear_ads_shows.setVisibility(View.VISIBLE);
                google_banner.setVisibility(View.VISIBLE);
                if (GoogleNativeBig != null) {
                    populateNativeBanner(GoogleNativeBig, adView1);
                }
                google_banner.removeAllViews();
                google_banner.addView(adView);

            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                    adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner1()).forNativeAd(nativeAds -> {
                        GoogleNativeBig = nativeAds;
                        main_banner.setVisibility(View.VISIBLE);
                        linear_ads_shows.setVisibility(View.VISIBLE);
                        google_banner.setVisibility(View.VISIBLE);
                        if (GoogleNativeBig != null) {
                            populateNativeBanner(GoogleNativeBig, adView1);
                        }
                        google_banner.removeAllViews();
                        google_banner.addView(adView);

                    }).withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                            adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner2()).forNativeAd(nativeAds -> {
                                GoogleNativeBig = nativeAds;
                                main_banner.setVisibility(View.VISIBLE);
                                linear_ads_shows.setVisibility(View.VISIBLE);
                                google_banner.setVisibility(View.VISIBLE);
                                if (GoogleNativeBig != null) {
                                    populateNativeBanner(GoogleNativeBig, adView1);
                                }
                                google_banner.removeAllViews();
                                google_banner.addView(adView);

                            }).withAdListener(new AdListener() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                    if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                                        fb_banner.setVisibility(View.VISIBLE);
                                        main_banner.setVisibility(View.VISIBLE);
                                        applovin_banner.setVisibility(View.GONE);
                                        google_banner.setVisibility(View.GONE);
                                        com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, MyHelpers.getFacebookBanner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                                            @Override
                                            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                                if (show_ad_name.equals("c")) {
                                                    CitreFailShowOtherads();
                                                } else if (show_ad_name.equals("a")) {
                                                    AppLovinFailShowOtherads();
                                                } else if (show_ad_name.equals("u")) {
                                                    UnityFailShowOtherads();
                                                } else {
                                                    CustomAds();
                                                }

                                            }

                                            @Override
                                            public void onAdLoaded(Ad ad) {
                                                main_banner.setVisibility(View.VISIBLE);
                                                fb_banner.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onAdClicked(Ad ad) {

                                            }

                                            @Override
                                            public void onLoggingImpression(Ad ad) {

                                            }
                                        };
                                        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig().withAdListener(adListener).build();
                                        adView.loadAd(loadAdConfig);
                                        fb_banner.removeAllViews();
                                        fb_banner.addView(adView);

                                    } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                        fb_banner.setVisibility(View.GONE);
                                        applovin_banner.setVisibility(View.VISIBLE);
                                        google_banner.setVisibility(View.GONE);
                                        main_banner.setVisibility(View.VISIBLE);
                                        MaxAdView adView = new MaxAdView(MyHelpers.getAppLovinBanner(), context);
                                        int width = ViewGroup.LayoutParams.MATCH_PARENT;
                                        int dpHeightInPx = (int) (50 * context.getResources().getDisplayMetrics().density);
                                        adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
                                        adView.setListener(new MaxAdViewAdListener() {
                                            @Override
                                            public void onAdExpanded(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdCollapsed(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdLoaded(MaxAd ad) {
                                                applovin_banner.removeAllViews();
                                                applovin_banner.addView(adView);
                                            }

                                            @Override
                                            public void onAdDisplayed(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdHidden(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdClicked(MaxAd ad) {

                                            }

                                            @Override
                                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                                if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                                    fb_banner.setVisibility(View.GONE);
                                                    applovin_banner.setVisibility(View.VISIBLE);
                                                    main_banner.setVisibility(View.VISIBLE);
                                                    google_banner.setVisibility(View.GONE);
                                                    FailsUnityBannerShow();
                                                } else {
                                                    CustomAds();
                                                }
                                            }

                                            @Override
                                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                                            }
                                        });
                                        adView.loadAd();
                                        adView.startAutoRefresh();

                                    } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                        fb_banner.setVisibility(View.GONE);
                                        applovin_banner.setVisibility(View.VISIBLE);
                                        main_banner.setVisibility(View.VISIBLE);
                                        google_banner.setVisibility(View.GONE);
                                        FailsUnityBannerShow();
                                    } else {
                                        CustomAds();
                                    }
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

        } else if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            fb_banner.setVisibility(View.VISIBLE);
            main_banner.setVisibility(View.VISIBLE);
            applovin_banner.setVisibility(View.GONE);
            google_banner.setVisibility(View.GONE);
            com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, MyHelpers.getFacebookBanner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (show_ad_name.equals("c")) {
                        CitreFailShowOtherads();
                    } else if (show_ad_name.equals("a")) {
                        AppLovinFailShowOtherads();
                    } else if (show_ad_name.equals("u")) {
                        UnityFailShowOtherads();
                    } else {
                        CustomAds();
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    main_banner.setVisibility(View.VISIBLE);
                    fb_banner.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig().withAdListener(adListener).build();
            adView.loadAd(loadAdConfig);
            fb_banner.removeAllViews();
            fb_banner.addView(adView);

        } else {
            if (show_ad_name.equals("c")) {
                CitreFailShowOtherads();
            } else if (show_ad_name.equals("a")) {
                AppLovinFailShowOtherads();
            } else if (show_ad_name.equals("u")) {
                UnityFailShowOtherads();
            } else {
                CustomAds();
            }
        }

    }

    private static void CitreFailShowOtherads() {
        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            main_banner.setVisibility(View.VISIBLE);

            MaxAdView adView = new MaxAdView(MyHelpers.getAppLovinBanner(), context);
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int dpHeightInPx = (int) (50 * context.getResources().getDisplayMetrics().density);
            adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    applovin_banner.removeAllViews();
                    applovin_banner.addView(adView);
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        fb_banner.setVisibility(View.GONE);
                        applovin_banner.setVisibility(View.VISIBLE);
                        main_banner.setVisibility(View.VISIBLE);
                        google_banner.setVisibility(View.GONE);
                        FailsUnityBannerShow();
                    } else {
                        CustomAds();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            adView.loadAd();
            adView.startAutoRefresh();
        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            main_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            FailsUnityBannerShow();
        } else {
            CustomAds();
        }

    }

    private static void AppLovinFailShowOtherads() {
        if (MyHelpers.getCriteoBanner() != null && !MyHelpers.getCriteoBanner().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            main_banner.setVisibility(View.VISIBLE);
            CriteoBannerView bannerView = new CriteoBannerView(context, new BannerAdUnit(MyHelpers.getCriteoBanner(), new AdSize(320, 50)));
            bannerView.setCriteoBannerAdListener(new CriteoBannerAdListener() {
                @Override
                public void onAdReceived(@NonNull CriteoBannerView view) {
                    applovin_banner.removeAllViews();
                    applovin_banner.addView(bannerView);
                }

                @Override
                public void onAdFailedToReceive(@NonNull CriteoErrorCode criteoErrorCode) {
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        fb_banner.setVisibility(View.GONE);
                        applovin_banner.setVisibility(View.VISIBLE);
                        main_banner.setVisibility(View.VISIBLE);
                        google_banner.setVisibility(View.GONE);
                        FailsUnityBannerShow();
                    } else {
                        CustomAds();
                    }
                }
            });
            bannerView.loadAd();
        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            main_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            FailsUnityBannerShow();
        } else {
            CustomAds();
        }
    }

    private static void UnityFailShowOtherads() {
        if (MyHelpers.getCriteoBanner() != null && !MyHelpers.getCriteoBanner().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            main_banner.setVisibility(View.VISIBLE);
            CriteoBannerView bannerView = new CriteoBannerView(context, new BannerAdUnit(MyHelpers.getCriteoBanner(), new AdSize(320, 50)));
            bannerView.setCriteoBannerAdListener(new CriteoBannerAdListener() {
                @Override
                public void onAdReceived(@NonNull CriteoBannerView view) {
                    applovin_banner.removeAllViews();
                    applovin_banner.addView(bannerView);
                }

                @Override
                public void onAdFailedToReceive(@NonNull CriteoErrorCode criteoErrorCode) {
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        fb_banner.setVisibility(View.GONE);
                        applovin_banner.setVisibility(View.VISIBLE);
                        google_banner.setVisibility(View.GONE);
                        main_banner.setVisibility(View.VISIBLE);
                        MaxAdView adView = new MaxAdView(MyHelpers.getAppLovinBanner(), context);
                        int width = ViewGroup.LayoutParams.MATCH_PARENT;
                        int dpHeightInPx = (int) (50 * context.getResources().getDisplayMetrics().density);
                        adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
                        adView.setListener(new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {

                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                applovin_banner.removeAllViews();
                                applovin_banner.addView(adView);
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {

                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                CustomAds();
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                            }
                        });
                        adView.loadAd();
                        adView.startAutoRefresh();
                    } else {
                        CustomAds();
                    }
                }
            });
            bannerView.loadAd();

        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            fb_banner.setVisibility(View.GONE);
            applovin_banner.setVisibility(View.VISIBLE);
            google_banner.setVisibility(View.GONE);
            main_banner.setVisibility(View.VISIBLE);
            MaxAdView adView = new MaxAdView(MyHelpers.getAppLovinBanner(), context);
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int dpHeightInPx = (int) (50 * context.getResources().getDisplayMetrics().density);
            adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    applovin_banner.removeAllViews();
                    applovin_banner.addView(adView);
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    CustomAds();
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            adView.loadAd();
            adView.startAutoRefresh();
        } else {
            CustomAds();
        }
    }

    private static void FailsUnityBannerShow() {
        BannerView bannerView = new BannerView((Activity) context, MyHelpers.getUnityBannerID(), new UnityBannerSize(320, 50));
        bannerView.setListener(new BannerView.IListener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {
            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                CustomAds();
            }

            @Override
            public void onBannerClick(BannerView bannerAdView) {

            }

            @Override
            public void onBannerLeftApplication(BannerView bannerAdView) {

            }
        });
        bannerView.load();
        applovin_banner.removeAllViews();
        applovin_banner.addView(bannerView);
    }


}
