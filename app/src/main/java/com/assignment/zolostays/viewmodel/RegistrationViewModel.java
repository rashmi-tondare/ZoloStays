
package com.assignment.zolostays.viewmodel;

import javax.inject.Inject;

import com.android.databinding.library.baseAdapters.BR;
import com.assignment.zolostays.R;
import com.assignment.zolostays.constants.AppConstants;
import com.assignment.zolostays.model.User;
import com.assignment.zolostays.utils.ActivityUtils;
import com.assignment.zolostays.utils.PasswordUtils;
import com.assignment.zolostays.view.LoginActivity;

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
 * Created by Rashmi on 13/08/17.
 */

@Module
public class RegistrationViewModel extends BaseViewModel {

    public static final int PHONE_NUMBER_INPUT = 1;
    public static final int EMAIL_INPUT = 2;
    public static final int NAME_INPUT = 3;
    public static final int PASSWORD_INPUT = 4;

    private String phoneNumber, email, name, password;
    public ObservableBoolean registerEnabled;

    @Inject
    public RegistrationViewModel() {
        phoneNumber = "";
        email = "";
        name = "";
        password = "";

        registerEnabled = new ObservableBoolean(false);
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
        updateRegisterButtonStatus();
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
        updateRegisterButtonStatus();
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        updateRegisterButtonStatus();
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        updateRegisterButtonStatus();
    }

    private void updateRegisterButtonStatus() {
        registerEnabled.set(!(TextUtils.isEmpty(phoneNumber)
                              || TextUtils.isEmpty(email)
                              || TextUtils.isEmpty(name)
                              || TextUtils.isEmpty(password)));
        registerEnabled.notifyChange();
    }

    public void onLoginClicked(View view) {
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        view.getContext().startActivity(intent);
    }

    public void onRegisterClicked(View view) {
        Context context = view.getContext();

        phoneNumber = phoneNumber.trim();
        if (!phoneNumber.matches(AppConstants.MOBILE_NUMBER_VALIDATION_PATTERN)) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(PHONE_NUMBER_INPUT);
            }
            return;
        }

        email = email.trim();
        if (!email.matches(AppConstants.EMAIL_VALIDATION_PATTERN)) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(EMAIL_INPUT);
            }
            return;
        }

        name = name.trim();
        if (!name.matches(AppConstants.FULLNAME_VALIDATION_PATTERN)) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(NAME_INPUT);
            }
            return;
        }

        if (password.length() < AppConstants.MINIMUM_PASSWORD_LENGTH) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(PASSWORD_INPUT);
            }
            return;
        }

        ActivityUtils.hideKeyboard((Activity) context);

        if (onInputErrorListener != null) {
            onInputErrorListener.clearInputErrors();
        }
        if (dbHelper.getUserByPhoneNumber(phoneNumber) != null) {
            Snackbar.make(view.getRootView(), R.string.error_user_exists, Snackbar.LENGTH_SHORT).show();
        }
        else {
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setEmailId(email);
            user.setName(name);
            user.setPassword(PasswordUtils.generateHash(password));

            if (dbHelper.insertUser(user) > 0) {
                Toast.makeText(context, R.string.snackbar_user_registered, Toast.LENGTH_LONG).show();
                onLoginClicked(view);
            }
        }
    }
}
