
package com.assignment.zolostays.view;

import javax.inject.Inject;

import com.assignment.zolostays.R;
import com.assignment.zolostays.databinding.ActivityForgotPasswordBinding;
import com.assignment.zolostays.listener.OnInputErrorListener;
import com.assignment.zolostays.viewmodel.ForgotPasswordViewModel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public class ForgotPasswordActivity extends AppCompatActivity implements OnInputErrorListener {

    @Inject
    ForgotPasswordViewModel forgotPasswordViewModel;

    private TextInputLayout txtInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityForgotPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        forgotPasswordViewModel.addInputErrorListener(this);
        binding.setViewModel(forgotPasswordViewModel);

        txtInputLayoutPassword = (TextInputLayout) findViewById(R.id.txt_input_layout_phone);
    }

    @Override
    public void onInputError(int inputViewIndicator) {
        switch (inputViewIndicator) {
            case ForgotPasswordViewModel.PHONE_NUMBER_INPUT:
                txtInputLayoutPassword.setError(getString(R.string.error_invalid_phone_number));
                break;
        }
    }

    @Override
    public void clearInputErrors() {
        txtInputLayoutPassword.setError(null);
    }
}
