package com.assignment.zolostays.application;

import javax.inject.Inject;

import com.assignment.zolostays.BuildConfig;
import com.assignment.zolostays.database.ZoloStaysDbHelper;
import com.facebook.stetho.Stetho;

import android.app.Activity;
import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Rashmi on 12/08/17.
 */

public class ZoloStaysApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerZoloStaysApplicationComponent.builder()
                .applicationModule(new ZoloStaysAppProviderModule(this))
                .build()
                .inject(this);

        ZoloStaysDbHelper.init(this);

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
