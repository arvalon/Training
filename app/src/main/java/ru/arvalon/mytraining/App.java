package ru.arvalon.mytraining;

import android.app.Application;
import android.util.Log;

import java.util.Locale;

/**
 * Created by arvalon on 04.05.2016.
 */

public class App extends Application {
    
    public static final String TAG = "vga";

    private static App singleton;

    public static App getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton=this;
        Log.d(TAG,"App singleton is started!");
        Log.d(TAG,"Locale.getDefault(): "+ Locale.getDefault().toString());
        Log.d(TAG,"Locale.getDefault().getCountry(): "+ Locale.getDefault().getCountry());
        Log.d(TAG,"Locale.getDefault().getDisplayCountry(): "+ Locale.getDefault().getDisplayCountry());
        Log.d(TAG,"Locale.getDefault().getDisplayLanguage(): "+ Locale.getDefault().getDisplayLanguage());
        Log.d(TAG,"Locale.getDefault().getLanguage(): "+ Locale.getDefault().getLanguage());
    }
}