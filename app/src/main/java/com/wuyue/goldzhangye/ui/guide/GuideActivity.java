package com.wuyue.goldzhangye.ui.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.wuyue.goldzhangye.MainActivity;
import com.wuyue.goldzhangye.R;
import com.wuyue.goldzhangye.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyue on 2016/10/28.
 */

public class GuideActivity extends BaseActivity {
    private static final String TAG = "GuideActivity";

    private ViewPager viewPager;
    private List<Fragment> list=new ArrayList<>();
    private GuideOneFragment guideOneFragment;
    private GuideTwoFragment guideTwoFragment;
    private GuideThreeFragment guideThreeFragment;
    private GuideFragmentPagerAdapter adapter;

    private Button btn_login;
    private TextView tv_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.guide);
        initView();
    }

    /**
     * 初始化控件，定义所有事件
     */
    @SuppressWarnings("deprecation")
    public void initView(){

        btn_login=(Button) findViewById(R.id.btn_login);
        tv_title = (TextView) findViewById(R.id.tv_title);
        viewPager=(ViewPager) findViewById(R.id.viewPager);

        //为了缓存viewpage中放的不被重绘，县缓存下来，此处填3的理解为，加载第一页的时候开始加载后面的三页，
        //此处加此代码还可以使得视频播放到中间被划过后返回还是在播放

        viewPager.setOffscreenPageLimit(3);

        //将所有的viewpage添加到list中
        if(guideOneFragment==null){
            guideOneFragment=new GuideOneFragment();
            list.add(guideOneFragment);
        }
        if(guideTwoFragment==null){
            guideTwoFragment=new GuideTwoFragment();
            list.add(guideTwoFragment);
        }
        if(guideThreeFragment==null){
            guideThreeFragment=new GuideThreeFragment();
            list.add(guideThreeFragment);
        }

        adapter=new GuideFragmentPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                if(arg0==0){
                    tv_title.setVisibility(View.VISIBLE);
                }else{
                    tv_title.setVisibility(View.GONE);
                }
                if(arg0==2){
                    btn_login.setVisibility(View.VISIBLE);
                }
                else{
                    btn_login.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,LoginAcvtivity.class));
                finish();
            }
        });
    }


    //自定义adapter
    private class GuideFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;
        public GuideFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list=list;
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
