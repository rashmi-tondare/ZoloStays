package com.assignment.zolostays.view;

import javax.inject.Inject;

import com.assignment.zolostays.R;
import com.assignment.zolostays.databinding.ActivityLoginBinding;
import com.assignment.zolostays.listener.OnInputErrorListener;
import com.assignment.zolostays.viewmodel.LoginViewModel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity implements OnInputErrorListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private TextInputLayout txtInputLayoutPhone, txtInputLayoutPassword;

    @Inject
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel.addInputErrorListener(this);
        binding.setLoginViewModel(loginViewModel);

        txtInputLayoutPhone = (TextInputLayout) findViewById(R.id.txt_input_layout_phone);
        txtInputLayoutPassword = (TextInputLayout) findViewById(R.id.txt_input_layout_password);

        LinearLayout lnrDummy = (LinearLayout) findViewById(R.id.lnr_dummy);
        lnrDummy.requestFocus();
    }

    @Override
    public void onInputError(int inputViewIndicator) {
        clearInputErrors();
        switch (inputViewIndicator) {
            case LoginViewModel.PHONE_NUMBER_INPUT:
                txtInputLayoutPhone.setError(getString(R.string.error_invalid_phone_number));
                break;
            case LoginViewModel.PASSWORD_INPUT:
                txtInputLayoutPassword.setError(getString(R.string.error_invalid_password));
                break;
        }
    }

    @Override
    public void clearInputErrors() {
        txtInputLayoutPhone.setError(null);
        txtInputLayoutPassword.setError(null);
    }
}
