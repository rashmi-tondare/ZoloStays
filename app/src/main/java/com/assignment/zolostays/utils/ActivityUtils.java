
package com.assignment.zolostays.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Rashmi on 13/08/17.
 */

public class ActivityUtils {
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getWindow().getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }
}
