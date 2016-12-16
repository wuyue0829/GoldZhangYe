package com.wuyue.goldzhangye.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wuyue on 2016/12/13.
 */

public class SysConfig {

    private final static String SYS_PARMS = "sys_parms_yy";

    private SharedPreferences config;

    private static SysConfig sysConfig;

    private static Context mContext;

    private SysConfig(Context context){
        mContext = context;
        config = context.getSharedPreferences(SYS_PARMS,0);
    }

    //单例模式
    public static SysConfig getSysconfig(Context context){
        mContext = context;
        if (sysConfig == null) {
            sysConfig = new SysConfig(mContext);
        }
        return sysConfig;
    }

    //获取用户token
    public String getToken(){
        return config.getString("token", "");
    }
    //写入用户token
    public void setToken(String token){
        config.edit().putString("token", token).commit();
    }


    //获取用户userAccount(网易用户名)
    public String getUserAccount(){
        return config.getString("userAccount", "");
    }
    //写入用户userAccount(网易用户名)
    public void setUserAccount(String userAccount){
        config.edit().putString("userAccount", userAccount).commit();
    }

    //获取用户userAccount(网易token)
    public String getUserToken(){
        return config.getString("userToken", "");
    }
    //写入用户userAccount(网易token)u
    public void setUserToken(String userToken){
        config.edit().putString("userToken", userToken).commit();
    }
}
