
package com.assignment.zolostays.application;

import com.assignment.zolostays.view.ForgotPasswordActivity;
import com.assignment.zolostays.view.LoginActivity;
import com.assignment.zolostays.view.ProfileActivity;
import com.assignment.zolostays.view.RegistrationActivity;
import com.assignment.zolostays.view.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Rashmi on 12/08/17.
 */

@Module
public abstract class ZoloStaysApplicationModule {
    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivityGenerator();

    @ContributesAndroidInjector
    abstract RegistrationActivity contributeRegistrationActivityGenerator();

    @ContributesAndroidInjector
    abstract ForgotPasswordActivity contributeForgotPasswordActivityGenerator();

    @ContributesAndroidInjector
    abstract ProfileActivity contributeProfileActivityGenerator();

    @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivityGenerator();
}
