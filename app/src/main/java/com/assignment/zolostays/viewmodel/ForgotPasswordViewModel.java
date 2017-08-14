
package com.assignment.zolostays.viewmodel;

import javax.inject.Inject;

import com.android.databinding.library.baseAdapters.BR;
import com.assignment.zolostays.R;
import com.assignment.zolostays.constants.AppConstants;
import com.assignment.zolostays.utils.ActivityUtils;
import com.assignment.zolostays.view.LoginActivity;
import com.assignment.zolostays.view.RegistrationActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import dagger.Module;

/**
 * Created by Rashmi on 14/08/17.
 */

@Module
public class ForgotPasswordViewModel extends BaseViewModel {

    public static final int PHONE_NUMBER_INPUT = 1;

    private String phoneNumber;
    public ObservableBoolean resetPasswordEnabled;

    @Inject
    public ForgotPasswordViewModel() {
        phoneNumber = "";
        resetPasswordEnabled = new ObservableBoolean(false);
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
        updateResetPasswordButtonStatus();
    }

    private void updateResetPasswordButtonStatus() {
        resetPasswordEnabled.set(!TextUtils.isEmpty(phoneNumber));
        resetPasswordEnabled.notifyChange();
    }

    public void onLoginClicked(View view) {
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        view.getContext().startActivity(intent);
    }

    public void onResetPasswordClicked(View view) {
        Context context = view.getContext();
        ActivityUtils.hideKeyboard((Activity) context);

        phoneNumber = phoneNumber.trim();
        if (!phoneNumber.matches(AppConstants.MOBILE_NUMBER_VALIDATION_PATTERN)) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(PHONE_NUMBER_INPUT);
            }
            return;
        }

        if (dbHelper.getUserByPhoneNumber(phoneNumber) == null) {
            if (onInputErrorListener != null) {
                onInputErrorListener.clearInputErrors();
            }

            Intent intent = new Intent(context, RegistrationActivity.class);
            intent.putExtra(AppConstants.INTENT_PHONE_NUMBER, phoneNumber);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

            Toast.makeText(context, R.string.toast_register_first, Toast.LENGTH_LONG).show();
        }
        else {
            Snackbar.make(view.getRootView(), R.string.snackbar_password_reset, Snackbar.LENGTH_LONG).show();
        }
    }

}
