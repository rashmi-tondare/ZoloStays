
package com.assignment.zolostays.viewmodel;

import javax.inject.Inject;

import com.assignment.zolostays.database.ZoloStaysDbHelper;
import com.assignment.zolostays.listener.OnInputErrorListener;

import android.databinding.BaseObservable;
import android.support.annotation.Nullable;

import dagger.Module;

/**
 * Created by Rashmi on 13/08/17.
 */

@Module
public class BaseViewModel extends BaseObservable {
    @Nullable
    OnInputErrorListener onInputErrorListener;

    //    @Inject
    ZoloStaysDbHelper dbHelper;

    @Inject
    public BaseViewModel() {
        dbHelper = ZoloStaysDbHelper.getInstance();
    }

    public void addInputErrorListener(OnInputErrorListener onInputErrorListener) {
        this.onInputErrorListener = onInputErrorListener;
    }

    public void removeInputErrorListener() {
        this.onInputErrorListener = null;
    }
}
