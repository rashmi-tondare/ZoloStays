
package com.assignment.zolostays.utils;

/**
 * Created by Rashmi on 14/08/17.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.assignment.zolostays.constants.AppConstants;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SharedPrefs {
    private static final String TAG = SharedPrefs.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private Editor editor;

    private List<OnSharedPrefsValueChangeListener> mSharedPrefValueChangeListeners;

    private static SharedPrefs mSharedPrefs;

    private SharedPrefs(Context mAppContext) {
        this.sharedPreferences = mAppContext.getSharedPreferences(AppConstants.SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        this.mSharedPrefValueChangeListeners = new ArrayList<>();

        this.editor = sharedPreferences.edit();
    }

    public static void init(Context context) {
        if (mSharedPrefs != null) {
            throw new RuntimeException("SharedPrefs has already been initialized");
        }

        mSharedPrefs = new SharedPrefs(context);
    }

    public static SharedPrefs getInstance() {
        if (mSharedPrefs == null) {
            throw new RuntimeException("SharedPrefs has not been initialized. Did you forget to call init?");
        }
        return mSharedPrefs;
    }

    public void add(String key, Object value) {
        //Getting the old value of the key
        Object oldValue = null;
        if (value instanceof String) {
            editor.putString(key, (String) value);
            oldValue = get(key, "");
        }
        else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
            oldValue = get(key, false);
        }
        else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
            oldValue = get(key, -1);
        }
        else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
            oldValue = get(key, -1l);
        }
        else {
            if (value != null)
                Log.e(TAG, "Value not inserted, Type " + value.getClass() + " not supported");
            else
                Log.e(TAG, "Cannot insert null values in sharedprefs");
        }
        editor.apply();

        //notifying the observers
        notifyObservers(key, oldValue, value);
    }

    public void removeKey(String key) {
        editor.remove(key)
                .apply();
    }

    private synchronized void notifyObservers(String key, Object oldValue, Object newValue) {
        for (Iterator iterator = mSharedPrefValueChangeListeners.iterator(); iterator.hasNext();) {
            OnSharedPrefsValueChangeListener listener = (OnSharedPrefsValueChangeListener) iterator.next();
            listener.onValueChanged(key, oldValue, newValue);
        }
    }

    /**
     * The default value defines what type of value is to be retrieved.
     *
     * @param key      - The key to look for.
     * @param defValue - The default value of no result was found.
     * @return
     */
    public <T> T get(String key, T defValue) {
        Object returnValue = null;
        if (defValue instanceof String) {
            returnValue = sharedPreferences.getString(key, (String) defValue);

        }
        else if (defValue instanceof Boolean) {
            returnValue = sharedPreferences.getBoolean(key, (Boolean) defValue);
        }
        else if (defValue instanceof Integer) {
            returnValue = sharedPreferences.getInt(key, (Integer) defValue);
        }
        else if (defValue instanceof Long) {
            returnValue = sharedPreferences.getLong(key, (Long) defValue);
        }
        return (T) defValue.getClass().cast(returnValue);
    }

    public void resetSharedPrefs() {
        Log.d(TAG, "Resetting shared preference");
        editor.clear();
        editor.apply();
    }

    public interface OnSharedPrefsValueChangeListener {
        void onValueChanged(String key, Object oldValue, Object newValue);
    }

    public void attach(OnSharedPrefsValueChangeListener onSharedPrefsValueChangeListener) {
        if (mSharedPrefValueChangeListeners != null) {
            this.mSharedPrefValueChangeListeners.add(onSharedPrefsValueChangeListener);
        }
    }

    public void detach(OnSharedPrefsValueChangeListener onSharedPrefsValueChangeListener) {
        if (mSharedPrefValueChangeListeners != null) {
            this.mSharedPrefValueChangeListeners.remove(onSharedPrefsValueChangeListener);
        }
    }
}
