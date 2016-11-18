package com.wuyue.goldzhangye.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wuyue.goldzhangye.R;
import com.wuyue.goldzhangye.utils.ThemeUtils;
import com.wuyue.yylibrary.T;


/**
 * Created by wuyue on 2016/11/18.
 */

public class BaseActivity extends BaseFragmentActivity implements IBaseView{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 弹出长对话框
     * @param message
     */
    protected void toast(String message){
        T.show(this,message, Toast.LENGTH_LONG);
    }

    /**
     * 根据传入的类打开一个Activity，带特效哦！
     * @param pClass
     */
    protected void startThActivity(Class<?> pClass){
        Intent intent = new Intent();
        intent.setClass(this,pClass);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_next_in,R.anim.trans_next_out);
    }

    /**
     * 根据传入的intent打开一个Activity
     * @param pIntent
     */
    protected void startThActivityByIntent(Intent pIntent){
        startActivity(pIntent);
        overridePendingTransition(R.anim.trans_next_in,R.anim.trans_next_out);
    }

    /**
     * 关闭当前Activity
     */
    public void backTheActivity(){
        finish();
        overridePendingTransition(R.anim.trans_pre_in, R.anim.trans_pre_out);
    }


    /**
     * 适配4.4以上的显示
     */
    protected void applyKitKatTranslucency(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);

            // mTintManager.setStatusBarTintResource(R.color.red_base);//通知栏所需颜色
            mTintManager.setStatusBarTintColor(ThemeUtils.getThemeColor());
        }
    }

    protected void applyKitKatTranslucency(int res) {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);

            // mTintManager.setStatusBarTintResource(R.color.red_base);//通知栏所需颜色
            mTintManager.setStatusBarTintColor(res);
        }

    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void cancelProgress() {

    }

    @Override
    public void showTheToast(int resId) {

    }

    @Override
    public void showTheToast(String message) {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
