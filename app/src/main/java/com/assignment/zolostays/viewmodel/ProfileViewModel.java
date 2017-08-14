
package com.assignment.zolostays.viewmodel;

import javax.inject.Inject;

import com.android.databinding.library.baseAdapters.BR;
import com.assignment.zolostays.R;
import com.assignment.zolostays.constants.AppConstants;
import com.assignment.zolostays.model.User;
import com.assignment.zolostays.utils.ActivityUtils;
import com.assignment.zolostays.utils.SharedPrefs;
import com.assignment.zolostays.view.SplashActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import dagger.Module;

/**
 * Created by Rashmi on 14/08/17.
 */

@Module
public class ProfileViewModel extends BaseViewModel {

    public static final int PHONE_NUMBER_INPUT = 1;
    public static final int EMAIL_INPUT = 2;
    public static final int NAME_INPUT = 3;

    private User user;
    public ObservableBoolean updateEnabled;
    private SharedPrefs sharedPrefs;
    private String phoneNumber, email, name;

    @Inject
    public ProfileViewModel() {
        user = new User();

        updateEnabled = new ObservableBoolean(true);
        sharedPrefs = SharedPrefs.getInstance();
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
        notifyPropertyChanged(BR.phoneNumber);
        updateButtonStatus();
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
        notifyPropertyChanged(BR.email);
        updateButtonStatus();
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
        notifyPropertyChanged(BR.name);
        updateButtonStatus();
    }

    private void updateButtonStatus() {
        updateEnabled.set(!(TextUtils.isEmpty(phoneNumber)
                            || TextUtils.isEmpty(email)
                            || TextUtils.isEmpty(name)));
        updateEnabled.notifyChange();
    }

    public void displayUserDetails() {
        long id = sharedPrefs.get(AppConstants.SHARED_PREFS_USER_ID, 0L);
        this.user = dbHelper.getUserById(id);
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmailId();
        this.name = user.getName();
        notifyPropertyChanged(BR.phoneNumber);
        notifyPropertyChanged(BR.email);
        notifyPropertyChanged(BR.name);
    }

    public void onLogoutClicked(View view) {
        final Context context = view.getContext();
        new AlertDialog.Builder(context)
                .setTitle(R.string.dialog_title_are_you_sure)
                .setMessage(R.string.dialog_msg_logout)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        sharedPrefs.resetSharedPrefs();

                        Intent intent = new Intent(context, SplashActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                })
                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    public void onUpdateClicked(View view) {
        Context context = view.getContext();

        if (!phoneNumber.matches(AppConstants.MOBILE_NUMBER_VALIDATION_PATTERN)) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(PHONE_NUMBER_INPUT);
            }
            return;
        }

        if (!email.matches(AppConstants.EMAIL_VALIDATION_PATTERN)) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(EMAIL_INPUT);
            }
            return;
        }

        if (!name.matches(AppConstants.FULLNAME_VALIDATION_PATTERN)) {
            if (onInputErrorListener != null) {
                onInputErrorListener.onInputError(NAME_INPUT);
            }
            return;
        }

        if (onInputErrorListener != null) {
            onInputErrorListener.clearInputErrors();
        }

        if (!user.getPhoneNumber().equals(phoneNumber)) {
            User temp = dbHelper.getUserByPhoneNumber(phoneNumber);
            if (temp != null) {
                Snackbar.make(view.getRootView(), R.string.snackbar_user_exists, Snackbar.LENGTH_LONG).show();
                return;
            }
        }

        ActivityUtils.hideKeyboard((Activity) context);
        user.setPhoneNumber(phoneNumber);
        user.setEmailId(email);
        user.setName(name);
        dbHelper.updateUser(user);
        displayUserDetails();

        Snackbar.make(view.getRootView(), R.string.snackbar_details_updated, Snackbar.LENGTH_LONG).show();
    }
}
