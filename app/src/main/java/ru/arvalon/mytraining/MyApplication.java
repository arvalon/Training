package ru.arvalon.mytraining;

import android.app.Application;
import android.util.Log;

import java.util.Locale;

/**
 * Created by arvalon on 04.05.2016.
 */
// FryDay commit
    //FryDay commit 2
//FryDay commit 3
    //Fryday commit from Yoga
public class MyApplication extends Application {

    private static  MyApplication singleton;
    public static MyApplication getInstance(){
        return singleton;
    }

    //---->ПРАВИЛЬНО ПИШЕТСЯ exercise !!! <----
    @Override
    public void onCreate() {
        super.onCreate();
        singleton=this;
        Log.d("happy","MyApplication singleton is started!");
        Log.d("happy","Locale.getDefault(): "+ Locale.getDefault().toString());
        Log.d("happy","Locale.getDefault().getCountry(): "+ Locale.getDefault().getCountry());
        Log.d("happy","Locale.getDefault().getDisplayCountry(): "+ Locale.getDefault().getDisplayCountry());
        Log.d("happy","Locale.getDefault().getDisplayLanguage(): "+ Locale.getDefault().getDisplayLanguage());
        Log.d("happy","Locale.getDefault().getLanguage(): "+ Locale.getDefault().getLanguage());
    }
}