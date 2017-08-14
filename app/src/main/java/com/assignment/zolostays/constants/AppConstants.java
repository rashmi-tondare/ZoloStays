package com.assignment.zolostays.constants;

/**
 * Created by Rashmi on 12/08/17.
 */

public final class AppConstants {
    public static final String DATABASE_NAME = "zolostays-db";
    public static final String SHARED_PREFS_NAME = "zolostays-shared-prefs";

    public static final String MOBILE_NUMBER_VALIDATION_PATTERN = "^[789]\\d{9}$";
    public static final String EMAIL_VALIDATION_PATTERN = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public static final String FULLNAME_VALIDATION_PATTERN = "^[a-zA-Z ]+$";

    public static final int MINIMUM_PASSWORD_LENGTH = 5;

    public static final String INTENT_PHONE_NUMBER = "phone_number";

    public static final String SHARED_PREFS_IS_LOGGED_IN = "is_logged_in";
    public static final String SHARED_PREFS_PHONE_NUMBER = "phone_number";
}
