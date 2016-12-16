package com.wuyue.goldzhangye;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jaeger.library.StatusBarUtil;
import com.wuyue.goldzhangye.base.BaseActivity;
import com.wuyue.goldzhangye.utils.ThemeUtils;
import com.wuyue.goldzhangye.viewimpl.film.FilmFragment;
import com.wuyue.goldzhangye.viewimpl.music.MusicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 程序主界面
 *
 * by：YY
 * author:yy107829@sina.com
 * data：2016-11-21
 *
 * 新的开始，新的坚持
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerlayout_home)
    DrawerLayout drawerlayoutHome;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.radiogroup)
    RadioGroup radioGroup;

    @BindView(R.id.id_navigationview)
    NavigationView idNavigationview;


    private FilmFragment filmFragment;
    private MusicFragment musicFragment;
    private List<Fragment> listFragment;

    private int currentFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册绑定事件
        ButterKnife.bind(this);
        //适配Android版本对主题的支持
        applyKitKatTranslucency();
        //设置状态栏颜色，ToolBar是什么颜色，状态栏就是什么颜色，勉强称为一个沉浸式状态栏
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this,drawerlayoutHome, ThemeUtils.getThemeColor());
        //初始化控件
        initView();
        //初始化Fragment
        initViewpagerAndFragment();
        initListener();

    }

    private void initView(){

        //设置toolbar颜色和渲染
        toolbar.setBackgroundColor(ThemeUtils.getToolBarColor());
        // 设置Drawerlayout开关指示器，即Toolbar最左边的那个icon
        ActionBarDrawerToggle mActionBarDrawerToggle =
                new ActionBarDrawerToggle(this,drawerlayoutHome,toolbar,R.string.open,R.string.close);
        mActionBarDrawerToggle.syncState();
        drawerlayoutHome.setDrawerListener(mActionBarDrawerToggle);

        //设置侧滑UI，给NavigationView填充顶部区域，也可在xml中使用app:headerLayout="@layout/header_nav"来设置
        idNavigationview.inflateHeaderView(R.layout.header_nav);
        View headerView = idNavigationview.getHeaderView(0);
        CircleImageView sdvHeader = (CircleImageView) headerView.findViewById(R.id.sdv_avatar);
        sdvHeader.setImageResource(R.drawable.ic_avtar);
        idNavigationview.inflateMenu(R.menu.menu_nav);
        idNavigationview.setItemIconTintList(ThemeUtils.getNaviItemIconTinkList());
        // 自己写的方法，设置NavigationView中menu的item被选中后要执行的操作
        onNavgationViewMenuItemSelected(idNavigationview);
    }


    private void initListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_home:
                        currentFragment = 0;
                        break;
                    case R.id.rb_dynamic:
                        currentFragment = 1;
                        break;
                    case R.id.rb_message:
                        currentFragment=2;
                        break;
                }
                viewpager.setCurrentItem(currentFragment, false);

            }
        });

        viewpager.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return listFragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return listFragment.get(arg0);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                super.destroyItem(container, position, object);
            }

        });
    }


    private void initViewpagerAndFragment(){
        musicFragment= MusicFragment.newInstance();
        listFragment=new ArrayList<>();
        listFragment.add(musicFragment);
        viewpager.setOffscreenPageLimit(1);
        viewpager.setOnPageChangeListener(onPageChangeListener);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    radioGroup.check(R.id.rb_home);
                    break;
                case 1:
                    radioGroup.check(R.id.rb_dynamic);
                    break;
                case 2:
                    radioGroup.check(R.id.rb_message);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



    /**
     * 设置NavigationView中menu的item被选中后要执行的操作
     *
     * @param mNav
     */
    private void onNavgationViewMenuItemSelected(NavigationView mNav) {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_menu_home:
                        break;
                    case R.id.nav_menu_categories:
                        break;
                    case R.id.nav_menu_recommend:
                        break;
                    case R.id.nav_menu_feedback:
                        break;
                    case R.id.nav_menu_setting:
                        break;
                    case R.id.nav_menu_theme:
                        break;
                }

                // Menu item点击后选中，并关闭Drawerlayout
                menuItem.setChecked(true);
                drawerlayoutHome.closeDrawers();
                // Toast.makeText(MainActivity.this,msgString,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}