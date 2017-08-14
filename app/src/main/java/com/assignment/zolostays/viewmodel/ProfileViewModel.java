package com.assignment.zolostays.viewmodel;

import javax.inject.Inject;

import com.android.databinding.library.baseAdapters.BR;
import com.assignment.zolostays.model.User;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.view.View;

import dagger.Module;

/**
 * Created by Rashmi on 14/08/17.
 */

@Module
public class ProfileViewModel extends BaseViewModel {

    private String phoneNumber, email, name;
    public ObservableBoolean updateEnabled;

    @Inject
    public ProfileViewModel() {
        phoneNumber = "";
        email = "";
        name = "";

        updateEnabled = new ObservableBoolean(false);
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void displayUserDetails(String phoneNumber) {
        User user = dbHelper.getUserByPhoneNumber(phoneNumber);
        setPhoneNumber(user.getPhoneNumber());
        setEmail(user.getEmailId());
        setName(user.getName());
    }

    public void onLogoutClicked(View view) {

    }

    public void onUpdateClicked(View view) {

    }
}
