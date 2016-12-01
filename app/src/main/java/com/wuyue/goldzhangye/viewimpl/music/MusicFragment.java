package com.wuyue.goldzhangye.viewimpl.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuyue.goldzhangye.R;
import com.wuyue.goldzhangye.adapter.MusicViewpagerAdapter;
import com.wuyue.goldzhangye.api.MusicApiUtils;
import com.wuyue.goldzhangye.base.BaseFragment;
import com.wuyue.goldzhangye.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuyue on 2016/12/1.
 */

public class MusicFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;
    // TabLayout中的tab标题
    private String[] mTitles;

    private MusicViewpagerAdapter mViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    public static MusicFragment newInstance(){
        Bundle args = new Bundle();
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();

    }

     private void initViews(){
         mTitles = MusicApiUtils.Music_Title;
         // 初始化ViewPager的适配器，并设置给它
         mViewPagerAdapter = new MusicViewpagerAdapter(getChildFragmentManager(),mTitles);
         viewpager.setAdapter(mViewPagerAdapter);
         // 设置ViewPager最大缓存的页面个数
         viewpager.setOffscreenPageLimit(4);
         // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
         viewpager.addOnPageChangeListener(this);

         tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         tabLayout.setTabMode(TabLayout.MODE_FIXED);
         tabLayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
         tabLayout.setTabTextColors(getResources().getColor(R.color.text_gray_6),ThemeUtils.getThemeColor());
         tabLayout.setupWithViewPager(viewpager);
         tabLayout.setTabsFromPagerAdapter(mViewPagerAdapter);
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
        return "动态";
    }
}
