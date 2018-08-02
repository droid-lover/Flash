package com.jarvis.flash.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.jarvis.flash.networking.APIs;

public class FlashApp extends MultiDexApplication{

    public static final String TAG = FlashApp.class.getSimpleName();

    private static FlashApp mInstance;
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    private APIs apIs;


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getBaseContext());
        mInstance = this;
    }

    public static synchronized FlashApp getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}
