//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.appwood.mylibrarys;

import static ProMex.classs.Utils.Util.DEc;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import ProMex.classs.Utils.Util;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OneConnects {
    private String api_key;
    private Context context;
    private String url = "https://developer.oneconnect.top";

    public String fetch(boolean free) throws IOException {
        OkHttpClient client = getUnsafeOkHttpClient();
        RequestBody formBody = (new Builder()).add("package_name", DEc(MyHelpers.pack)).add("api_key", this.api_key).add("action", "fetchUserServers").add("type", free ? "free" : "pro").build();
        Request request = (new okhttp3.Request.Builder()).url(this.url + "/view/front/controller.php").post(formBody).build();

        try {
            Response response = client.newCall(request).execute();
            Throwable var6 = null;

            String var7;
            try {
                var7 = response.body().string();
            } catch (Throwable var17) {
                var6 = var17;
                throw var17;
            } finally {
                if (response != null) {
                    if (var6 != null) {
                        try {
                            response.close();
                        } catch (Throwable var16) {
                            var6.addSuppressed(var16);
                        }
                    } else {
                        response.close();
                    }
                }

            }

            return var7;
        } catch (Exception var19) {
            Log.e("RESPONSEERROR", var19.getMessage());
            return "";
        }
    }

    public OneConnects() {
    }

    public void initialize(Context context, String api_key) {
        this.api_key = api_key;
        this.context = context;
    }

    public void setEndPoint(String url) {
        this.url = url;
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }


                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init((KeyManager[])null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> {
                return true;
            });
            return builder.build();
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }
}
