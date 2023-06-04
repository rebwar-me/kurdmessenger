package com.rai.kurdmessenger.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.rai.kurdmessenger.db.smsDB;

/**
 * Created by Windows on 30-01-2015.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;
    private static smsDB mDatabase;
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;
    public static final String TAG=MyApplication.class.getSimpleName();


    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }


    public synchronized static smsDB getWritableDatabase() {
        if (mDatabase == null) {
            mDatabase = new smsDB(getAppContext());
        }
        return mDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mDatabase=new smsDB(this);

    }
    public static synchronized MyApplication getInstance() {
        return sInstance;
    }


    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static void saveToPreferences(Context context, String preferenceName, boolean preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    public static boolean readFromPreferences(Context context, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }
}
