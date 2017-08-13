package com.assignment.zolostays.database;

import java.util.List;

import com.assignment.zolostays.constants.AppConstants;
import com.assignment.zolostays.model.DaoMaster;
import com.assignment.zolostays.model.DaoSession;
import com.assignment.zolostays.model.User;
import com.assignment.zolostays.model.UserDao;

import android.content.Context;

/**
 * Created by Rashmi on 13/08/17.
 */

//@Singleton
public class ZoloStaysDbHelper {

    private final DaoSession mDaoSession;
    private static ZoloStaysDbHelper zoloStaysDbHelper;

//    @Inject
    private ZoloStaysDbHelper(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, AppConstants.DATABASE_NAME);
        mDaoSession = new DaoMaster(devOpenHelper.getWritableDb()).newSession();
    }

    public static void init(Context context) {
        if (zoloStaysDbHelper != null) {
            throw new RuntimeException("ZoloStaysDbHelper has already been initialized");
        }
        zoloStaysDbHelper = new ZoloStaysDbHelper(context);
    }

    public static ZoloStaysDbHelper getInstance() {
        if (zoloStaysDbHelper == null) {
            throw new RuntimeException("ZoloStaysDbHelper has not been initialized. Did you forget to call init?");
        }
        return zoloStaysDbHelper;
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        List<User> users = mDaoSession.getUserDao().queryBuilder()
                .where(UserDao.Properties.PhoneNumber.eq(phoneNumber))
                .list();

        return users.isEmpty() ? null : users.get(0);
    }

    public Long insertUser(User user) {
        return mDaoSession.getUserDao().insert(user);
    }
}
