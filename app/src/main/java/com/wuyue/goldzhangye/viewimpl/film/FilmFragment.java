package com.wuyue.goldzhangye.viewimpl.film;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuyue.goldzhangye.R;
import com.wuyue.goldzhangye.base.BaseFragment;
import com.wuyue.goldzhangye.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuyue on 2016/11/21.
 */

public class FilmFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;

    // TabLayout中的tab标题
    private String[] mTitles;
    // 填充到ViewPager中的Fragment
    private List<Fragment> mFragments;


    public static FilmFragment newInstance() {

        Bundle args = new Bundle();

        FilmFragment fragment = new FilmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }
    private void initViews(){

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return "首页－Fragment";
    }
}
