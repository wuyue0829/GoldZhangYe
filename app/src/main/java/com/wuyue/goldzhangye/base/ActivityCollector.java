package com.wuyue.goldzhangye.base;

import android.app.Activity;

import java.util.Stack;

/**
 * Activity 栈管理
 * Created by wuyue on 2016/11/18.
 */

public class ActivityCollector {

    private static Stack<Activity> activityStack;
    private static ActivityCollector instance;
    private ActivityCollector(){

    }

    /**
     * Activity单例，防止内存泄露
     * @return
     */
    public static ActivityCollector getInstance(){
        if(null == instance){
            instance = new ActivityCollector();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     * @param activity
     */
    public void addActivity(Activity activity){
        if(null == activityStack){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除Activity
     * @param activity
     */
    public void removeActivty(Activity activity){
        activityStack.remove(activity);
    }

    /**
     * 从新刷新Activity
     */
    public void refreshAllActivity(){
        for(Activity activity:activityStack){
            activity.recreate();
        }
    }

    /**
     * 获取当前Activity（最后一个压入栈的）
     * @return
     */
    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return  activity;
    }

    /**
     * 结束当前的Activity,最上层的Activity
     */
    public void finishActivity(){
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if(null != activity && !activity.isFinishing()){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        for (Activity activity:activityStack){
            if (activity.getClass().equals(cls)){
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 关闭所有的Ativity
     */
    public void finishAllActivity(){
        for (int i = 0,size = activityStack.size() ;i < size; i++) {
            if(null != activityStack.get(i)){
                finishActivity(activityStack.get(i));
                break;
            }
        }
        activityStack.clear();
    }

    /**
     * 获取指定的Activity
     * @param cls
     * @return
     */
    public static Activity getActivity(Class<?> cls){
        if(activityStack != null)
            for (Activity activity :activityStack){
                if (activity.getClass().equals(cls)){
                    return activity;
                }
            }
        return  null;
    }

    /**
     * 关闭所有Acitivity，退出App时使用
     */
    public void AppExit(){
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
