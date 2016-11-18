package com.wuyue.goldzhangye.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by wuyue on 2016/11/18.
 */

public class GoldApplication extends Application{

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
