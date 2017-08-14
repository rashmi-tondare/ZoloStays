
package com.assignment.zolostays.viewmodel;

import javax.inject.Inject;

import com.assignment.zolostays.constants.AppConstants;
import com.assignment.zolostays.utils.SharedPrefs;
import com.assignment.zolostays.view.LoginActivity;
import com.assignment.zolostays.view.ProfileActivity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Handler;

import dagger.Module;

/**
 * Created by Rashmi on 14/08/17.
 */

@Module
public class SplashViewModel extends BaseObservable {

    private SharedPrefs sharedPrefs;

    @Inject
    public SplashViewModel() {
        sharedPrefs = SharedPrefs.getInstance();
    }

    public void launchNextActivity(final Activity currentActivity) {
        boolean isLoggedIn = sharedPrefs.get(AppConstants.SHARED_PREFS_IS_LOGGED_IN, false);

        final Intent intent;
        if (isLoggedIn) {
            intent = new Intent(currentActivity, ProfileActivity.class);
        }
        else {
            intent = new Intent(currentActivity, LoginActivity.class);
        }

        Runnable launchNextActivityRunnable = new Runnable() {
            @Override
            public void run() {
                currentActivity.startActivity(intent);
                currentActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                currentActivity.finish();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(launchNextActivityRunnable, 2000);
    }
}
