package com.wuyue.goldzhangye.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wuyue.goldzhangye.viewimpl.music.MusicContentFragment;

/**
 * Created by wuyue on 2016/12/1.
 */

public class MusicViewpagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;

    public MusicViewpagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return MusicContentFragment.newInstance(position,mTitles[position]);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
