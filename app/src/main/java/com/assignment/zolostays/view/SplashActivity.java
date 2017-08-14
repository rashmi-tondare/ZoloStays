
package com.assignment.zolostays.view;

import javax.inject.Inject;

import com.assignment.zolostays.R;
import com.assignment.zolostays.viewmodel.SplashViewModel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public class SplashActivity extends AppCompatActivity {

    @Inject
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashViewModel.launchNextActivity(this);
    }
}
