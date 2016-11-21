package com.wuyue.goldzhangye.base;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.wuyue.goldzhangye.R;
import com.wuyue.yylibrary.T;

/**
 * Created by wuyue on 2016/11/21.
 */

public abstract class BaseFragment extends Fragment implements IBaseView{

    private Toast mToast;

    public void toast(String msg){
        T.show(getActivity(),msg);
    }

    public void onResume() {
        super.onResume();
        if(!hasMultiFragment()) {
            //  MobclickAgent.onPageStart(setFragmentName()); //统计页面，"MainScreen"为页面名称，可自定义
        }
    }
    public void onPause() {
        super.onPause();
        if(!hasMultiFragment()) {
            //  MobclickAgent.onPageEnd(setFragmentName());
        }
    }
    public abstract boolean hasMultiFragment();
    protected abstract String setFragmentName();



    @Override
    public void showProgress(String message) {
        //  mDialogLoad.setMessage(message);
        // mDialogLoad.showDialog();
    }

    @Override
    public void showProgress() {
        showProgress("");
    }

    @Override
    public void cancelProgress() {

        //mDialogLoad.cancelDialog();
    }

    @Override
    public void showTheToast(int resId) {
        showTheToast(getString(resId));
    }

    @Override
    public void showTheToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }


    /**
     * 根据传入的类(class)打开指定的activity
     * @param pClass
     */
    protected void startThActivity(Class<?> pClass) {
        Intent _Intent = new Intent();
        _Intent.setClass(getActivity(), pClass);
        startActivity(_Intent);
        getActivity().overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

    protected void startThActivityByIntent(Intent pIntent){
        startActivity(pIntent);
        getActivity().overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

}
