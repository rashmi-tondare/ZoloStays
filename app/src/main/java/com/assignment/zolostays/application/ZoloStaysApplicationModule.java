package com.assignment.zolostays.application;

import com.assignment.zolostays.view.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Rashmi on 12/08/17.
 */

@Module
public abstract class ZoloStaysApplicationModule {
    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivityGenerator();
}
