
package com.assignment.zolostays.view;

import javax.inject.Inject;

import com.assignment.zolostays.R;
import com.assignment.zolostays.constants.AppConstants;
import com.assignment.zolostays.databinding.ActivityRegistrationBinding;
import com.assignment.zolostays.listener.OnInputErrorListener;
import com.assignment.zolostays.utils.ActivityUtils;
import com.assignment.zolostays.viewmodel.RegistrationViewModel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public class RegistrationActivity extends AppCompatActivity implements OnInputErrorListener {

    private TextInputLayout txtInputLayoutPhone;
    private TextInputLayout txtInputLayoutPassword;
    private TextInputLayout txtInputLayoutName;
    private TextInputLayout txtInputLayoutEmail;

    @Inject
    RegistrationViewModel registrationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityRegistrationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        registrationViewModel.addInputErrorListener(this);
        binding.setRegistrationViewModel(registrationViewModel);

        ActivityUtils.hideKeyboard(this);

        txtInputLayoutPhone = (TextInputLayout) findViewById(R.id.txt_input_layout_phone);
        txtInputLayoutEmail = (TextInputLayout) findViewById(R.id.txt_input_layout_email);
        txtInputLayoutName = (TextInputLayout) findViewById(R.id.txt_input_layout_name);
        txtInputLayoutPassword = (TextInputLayout) findViewById(R.id.txt_input_layout_password);
    }

    @Override
    public void onInputError(int inputViewIndicator) {
        clearInputErrors();
        switch (inputViewIndicator) {
            case RegistrationViewModel.PHONE_NUMBER_INPUT:
                txtInputLayoutPhone.setError(getString(R.string.error_invalid_phone_number));
                break;
            case RegistrationViewModel.EMAIL_INPUT:
                txtInputLayoutEmail.setError(getString(R.string.error_invalid_email));
                break;
            case RegistrationViewModel.NAME_INPUT:
                txtInputLayoutName.setError(getString(R.string.error_invalid_name));
                break;
            case RegistrationViewModel.PASSWORD_INPUT:
                txtInputLayoutPassword.setError(getString(R.string.error_password_restriction, AppConstants.MINIMUM_PASSWORD_LENGTH));
                break;
        }

    }

    @Override
    public void clearInputErrors() {
        txtInputLayoutPhone.setError(null);
        txtInputLayoutEmail.setError(null);
        txtInputLayoutName.setError(null);
        txtInputLayoutPassword.setError(null);
    }
}
