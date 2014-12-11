package com.mzule.demo;

import android.app.Application;
import android.os.StrictMode;

/**
 * Created by Lennnna on 12/9/14.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
    }
}
