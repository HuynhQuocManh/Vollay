package com.ebizword.bigcosm.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.view.Menu;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.ebizword.bigcosm.utils.ForegroundListner;
import com.ebizword.bigcosm.utils.LruBitmapCache;



import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class AppController extends Application{


    public static final String TAG = AppController.class.getSimpleName();
    private static double total = 0;
    public static ArrayList<Menu> menuListGlobal = new ArrayList<Menu>();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    LruBitmapCache mLruBitmapCache;
    private SSLSocketFactory sf;
    SSLContext sslContext;

    private static AppController mInstance;

    public static double getTotal() {
        return total;
    }

    public static void setTotal(double total) {
        AppController.total = total;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        configSSL();
        mInstance = this;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        ForegroundListner.init(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            getLruBitmapCache();
            mImageLoader = new ImageLoader(this.mRequestQueue, mLruBitmapCache);
        }
        return this.mImageLoader;
    }

    public LruBitmapCache getLruBitmapCache() {
        if (mLruBitmapCache == null)
            mLruBitmapCache = new LruBitmapCache();
        return this.mLruBitmapCache;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

  //  private void configSSL() {
//        CertificateFactory cf = null;
//        try {
//            cf = CertificateFactory.getInstance("X.509");
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        }
//
//        // Generate the certificate using the certificate file under res/raw/cert.cer
//        InputStream caInput = new BufferedInputStream(getResources().openRawResource(R.raw.qooservices));
//        Certificate ca = null;
//        try {
//            assert cf != null;
//            ca = cf.generateCertificate(caInput);
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        }
//        try {
//            caInput.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Create a KeyStore containing our trusted CAs
//        String keyStoreType = KeyStore.getDefaultType();
//        KeyStore trusted = null;
//        try {
//            trusted = KeyStore.getInstance(keyStoreType);
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
//        try {
//            assert trusted != null;
//            trusted.load(null, null);
//        } catch (IOException | CertificateException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        try {
//            trusted.setCertificateEntry("ca", ca);
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
//
//        // Create a TrustManager that trusts the CAs in our KeyStore
//        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//        TrustManagerFactory tmf = null;
//        try {
//            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        try {
//            assert tmf != null;
//            tmf.init(trusted);
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
//
//        // Create an SSLContext that uses our TrustManager
//        try {
//            sslContext = SSLContext.getInstance("TLS");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        try {
//            assert sslContext != null;
//            sslContext.init(null, tmf.getTrustManagers(), null);
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//
//////
//        HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
//        try {
//            sslContext.init(null, new X509TrustManager[]{new NullX509TrustManager()}, new SecureRandom());
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//        ///////
//        sf = sslContext.getSocketFactory();
//    }
  //  }

}

