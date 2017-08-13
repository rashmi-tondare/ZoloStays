package com.assignment.zolostays.application;

import android.app.Application;

import dagger.Module;

/**
 * Created by Rashmi on 13/08/17.
 */

@Module
public class ZoloStaysAppProviderModule {
    private Application mApplication;

    public ZoloStaysAppProviderModule(Application application) {
        this.mApplication = application;
    }

//    @Provides
//    @Singleton
//    ZoloStaysDbHelper provideZoloStaysDbHelper() {
//        return new ZoloStaysDbHelper(mApplication);
//    }
}
