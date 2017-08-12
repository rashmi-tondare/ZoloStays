
package com.assignment.zolostays.viewmodel;

import javax.inject.Inject;

import com.assignment.zolostays.BR;
import com.assignment.zolostays.constants.AppConstants;
import com.assignment.zolostays.listener.OnInputErrorListener;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import dagger.Module;

/**
 * Created by Rashmi on 12/08/17.
 */

@Module
public class LoginViewModel extends BaseObservable {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    public static final int PHONE_NUMBER_INPUT = 1;
    public static final int PASSWORD_INPUT = 2;
    public static final int INVALID_CREDENTIALS = 3;

    private String phoneNumber, password;
    public ObservableBoolean loginEnabled;

    @Nullable
    private OnInputErrorListener onInputErrorListener;

    @Inject
    public LoginViewModel() {
        phoneNumber = "";
        password = "";
        loginEnabled = new ObservableBoolean(false);
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
        updateLoginButtonStatus();
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        updateLoginButtonStatus();
    }

    public void addInputErrorListener(OnInputErrorListener onInputErrorListener) {
        this.onInputErrorListener = onInputErrorListener;
    }

    public void removeInputErrorListener() {
        this.onInputErrorListener = null;
    }

    public void onClickLogin(View view) {
        if (!phoneNumber.matches(AppConstants.MOBILE_NUMBER_VALIDATION_PATTERN)) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(PHONE_NUMBER_INPUT);
            }
            return;
        }

        if (password.length() < AppConstants.MINIMUM_PASSWORD_LENGTH) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(PASSWORD_INPUT);
            }
            return;
        }

        if (onInputErrorListener != null) {
            onInputErrorListener.clearInputErrors();
            Snackbar.make(view.getRootView(), "YAY!", Snackbar.LENGTH_LONG).show();
        }
    }

    private void updateLoginButtonStatus() {
        loginEnabled.set(!(TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)));
        loginEnabled.notifyChange();
    }
}
