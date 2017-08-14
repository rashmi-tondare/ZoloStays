
package com.assignment.zolostays.view;

import javax.inject.Inject;

import com.assignment.zolostays.R;
import com.assignment.zolostays.databinding.ActivityProfileBinding;
import com.assignment.zolostays.listener.OnInputErrorListener;
import com.assignment.zolostays.viewmodel.ProfileViewModel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public class ProfileActivity extends AppCompatActivity implements OnInputErrorListener {

    @Inject
    ProfileViewModel profileViewModel;
    private TextInputLayout txtInputLayoutName;
    private TextInputLayout txtInputLayoutEmail;
    private TextInputLayout txtInputLayoutPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        profileViewModel.addInputErrorListener(this);
        binding.setProfileViewModel(profileViewModel);

        txtInputLayoutPhone = (TextInputLayout) findViewById(R.id.txt_input_layout_phone);
        txtInputLayoutEmail = (TextInputLayout) findViewById(R.id.txt_input_layout_email);
        txtInputLayoutName = (TextInputLayout) findViewById(R.id.txt_input_layout_name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        profileViewModel.displayUserDetails();
    }

    @Override
    public void onInputError(int inputViewIndicator) {
        clearInputErrors();
        switch (inputViewIndicator) {
            case ProfileViewModel.PHONE_NUMBER_INPUT:
                txtInputLayoutPhone.setError(getString(R.string.error_invalid_phone_number));
                break;
            case ProfileViewModel.EMAIL_INPUT:
                txtInputLayoutEmail.setError(getString(R.string.error_invalid_email));
                break;
            case ProfileViewModel.NAME_INPUT:
                txtInputLayoutName.setError(getString(R.string.error_invalid_name));
                break;
        }
    }

    @Override
    public void clearInputErrors() {
        txtInputLayoutPhone.setError(null);
        txtInputLayoutEmail.setError(null);
        txtInputLayoutName.setError(null);
    }
}
