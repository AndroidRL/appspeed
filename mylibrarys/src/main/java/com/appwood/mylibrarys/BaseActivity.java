package com.appwood.mylibrarys;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import unified.vpn.sdk.AuthMethod;
import unified.vpn.sdk.Callback;
import unified.vpn.sdk.ClientInfo;
import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.HydraTransportConfig;
import unified.vpn.sdk.OpenVpnTransport;
import unified.vpn.sdk.OpenVpnTransportConfig;
import unified.vpn.sdk.SdkNotificationConfig;
import unified.vpn.sdk.SessionConfig;
import unified.vpn.sdk.TrackingConstants;
import unified.vpn.sdk.TrafficRule;
import unified.vpn.sdk.TransportConfig;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.User;
import unified.vpn.sdk.VpnException;
import unified.vpn.sdk.VpnPermissionDeniedException;
import unified.vpn.sdk.VpnState;
import unified.vpn.sdk.WrongStateException;

public class BaseActivity extends AppCompatActivity {

    public static String url = "";
    public static String id = "";
    public static String Country;


    public static UnifiedSdk unifiedSDK;
    public static String selectedCountry;
    public static int vpn_cancel_count = 0;
    public static boolean vpn_flag_notification = true;
    public static boolean vpn = true;
    public static int cancel_count = 0;
    public static boolean cancel_is_by_user = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface vpn_callback {
        void vpn_final_callback(String s);
    }

    public static void vpn_connection(Activity activity, vpn_callback vpn_callback) {
        if (vpn) {
            if (unifiedSDK == null) {
                initHydraSdk();
            }
            unifiedSDK.getBackend().isLoggedIn(new Callback<Boolean>() {
                public void success(Boolean bool) {
                    if (bool.booleanValue()) {
                        connectToVpn(activity, vpn_callback);
                        return;
                    }
                    unifiedSDK.getBackend().login(AuthMethod.anonymous(), new Callback<User>() {
                        public void success(User user) {
                            connectToVpn(activity, vpn_callback);
                        }

                        @Override
                        public void failure(VpnException vpnException) {
                            vpn_callback.vpn_final_callback("vpn_connection - login - try - fail - " + vpnException.getMessage());
                        }
                    });
                }

                @Override
                public void failure(VpnException vpnException) {
                    vpn_callback.vpn_final_callback("vpn_connection - check login fail - " + vpnException.getMessage());
                }
            });
        } else {
            vpn_callback.vpn_final_callback("success");
        }
    }

    public static void initHydraSdk() {
        UnifiedSdk.clearInstances();
        ClientInfo build = ClientInfo.newBuilder().addUrl(url).carrierId(id).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(HydraTransportConfig.create());
        arrayList.add(OpenVpnTransportConfig.tcp());
        arrayList.add(OpenVpnTransportConfig.udp());
        UnifiedSdk.update(arrayList, CompletableCallback.EMPTY);
        unifiedSDK = UnifiedSdk.getInstance(build);
        UnifiedSdk.setLoggingLevel(2);
    }

    public static void connectToVpn(Activity activity, vpn_callback vpn_callback) {
        UnifiedSdk.getVpnState(new Callback<VpnState>() {
            public void success(VpnState vpnState) {
                if (VpnState.CONNECTED == vpnState) {
                    UnifiedSdk.getInstance().getBackend().logout(new CompletableCallback() {
                        @Override
                        public void complete() {
                            UnifiedSdk.getInstance().getVpn().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
                                @Override
                                public void complete() {
                                    vpn_connection(activity, vpn_callback);
                                }

                                @Override
                                public void error(VpnException e) {
                                    vpn_callback.vpn_final_callback("connect - stop fail " + e.getMessage());
                                }
                            });
                        }

                        @Override
                        public void error(VpnException e) {
                            vpn_callback.vpn_final_callback("connect - logout fail" + e.getMessage());
                        }
                    });
                } else {
                    selectedCountry = Country;
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("hydra");
                    arrayList.add(OpenVpnTransport.TRANSPORT_ID_TCP);
                    arrayList.add(OpenVpnTransport.TRANSPORT_ID_UDP);
                    LinkedList linkedList = new LinkedList();
                    linkedList.add("*facebook.com");
                    linkedList.add("*wtfismyip.com");
                    unifiedSDK.getVpn().start(new SessionConfig.Builder().withReason(TrackingConstants.GprReasons.M_UI).withTransportFallback(arrayList).withTransport("hydra").withVirtualLocation(selectedCountry).addDnsRule(TrafficRule.Builder.bypass().fromDomains(linkedList)).build(), new CompletableCallback() {
                        @Override
                        public void complete() {
                            if (vpn_flag_notification) {
                                createNotificationChannel(activity);
                                List<TransportConfig> transportConfigList = new ArrayList<>();
                                transportConfigList.add(HydraTransportConfig.create());
                                transportConfigList.add(OpenVpnTransportConfig.tcp());
                                transportConfigList.add(OpenVpnTransportConfig.udp());
                                UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);
                                SdkNotificationConfig notificationConfig = SdkNotificationConfig.newBuilder().title("service").channelId("vpncallbackmain").disabled().build();
                                UnifiedSdk.update(notificationConfig);
                            } else {
                                List<TransportConfig> transportConfigList = new ArrayList<>();
                                transportConfigList.add(HydraTransportConfig.create());
                                transportConfigList.add(OpenVpnTransportConfig.tcp());
                                transportConfigList.add(OpenVpnTransportConfig.udp());
                                UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);
                                SdkNotificationConfig notificationConfig = SdkNotificationConfig.newBuilder().title("service").channelId("vpncallbackmain").disabled().build();
                                UnifiedSdk.update(notificationConfig);
                            }

                            vpn_callback.vpn_final_callback("success");
                        }

                        @Override
                        public void error(VpnException vpnException) {
                            if (vpnException.getMessage() == null) {
                                vpn_callback.vpn_final_callback("connect - connection fail " + vpnException.getMessage());
                            } else if (vpnException instanceof VpnPermissionDeniedException) {
                                if (cancel_count == vpn_cancel_count) {
                                    cancel_is_by_user = false;
                                    vpn_callback.vpn_final_callback("connect - connection fail " + vpnException.getMessage());
                                } else {
                                    cancel_count = cancel_count + 1;
                                    cancel_is_by_user = true;
                                    vpn_connection(activity, vpn_callback);
                                }
                            } else if (vpnException instanceof WrongStateException) {
                                if (cancel_is_by_user) {
                                    if (cancel_count == vpn_cancel_count) {
                                        cancel_is_by_user = false;
                                        vpn_callback.vpn_final_callback("connect - connection fail " + vpnException.getMessage());
                                    } else {
                                        vpn_connection(activity, vpn_callback);
                                    }
                                } else {
                                    vpn_callback.vpn_final_callback("connect - connection fail " + vpnException.getMessage());
                                }
                            } else {
                                vpn_callback.vpn_final_callback("connect - connection fail " + vpnException.getMessage());
                            }
                        }
                    });
                }
            }

            @Override
            public void failure(VpnException vpnException) {
                vpn_callback.vpn_final_callback("connect - connection fail " + vpnException.getMessage());
            }
        });
    }

    public static void createNotificationChannel(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Sample VPN";
            String description = "VPN notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("vpncallbackmain", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
