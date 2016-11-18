package com.wuyue.goldzhangye.base;

import android.content.Context;

/**
 * 公共View接口
 * Created by wuyue on 2016/11/18.
 */

public interface IBaseView {

    /**
     * 显示可以取消的进度条
     * @param message
     */
    void showProgress(String message);


    /**
     * 显示可以取消没有文字的进度条
     */
    void showProgress();


    /**
     * 取消显示进度条
     */
    void cancelProgress();


    /**
     * 弹出提示框
     */
    void showTheToast(int resId);


    /**
     * 弹出提示框
     */
    void showTheToast(String message);


    /**
     * 获取当前上下文对象
     * @return
     */
    Context getContext();
}
