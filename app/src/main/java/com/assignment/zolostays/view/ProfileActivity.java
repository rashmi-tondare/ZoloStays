package com.assignment.zolostays.view;

import javax.inject.Inject;

import com.assignment.zolostays.R;
import com.assignment.zolostays.constants.AppConstants;
import com.assignment.zolostays.databinding.ActivityProfileBinding;
import com.assignment.zolostays.listener.OnInputErrorListener;
import com.assignment.zolostays.viewmodel.ProfileViewModel;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public class ProfileActivity extends AppCompatActivity implements OnInputErrorListener {

    @Inject
    ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        profileViewModel.addInputErrorListener(this);
        binding.setProfileViewModel(profileViewModel);

        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra(AppConstants.INTENT_PHONE_NUMBER);
        profileViewModel.displayUserDetails(phoneNumber);
    }

    @Override
    public void onInputError(int inputViewIndicator) {

    }

    @Override
    public void clearInputErrors() {

    }
}
