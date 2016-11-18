package com.wuyue.goldzhangye.ui.guide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;

import com.wuyue.goldzhangye.MainActivity;
import com.wuyue.goldzhangye.R;
import com.wuyue.goldzhangye.base.BaseActivity;

import java.util.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by wuyue on 2016/11/18.
 */

public class EntryActivity extends BaseActivity{

    @BindView(R.id.iv_entry)
    ImageView mSplashImage;

    private static final int ANIMATION_TIME = 2000;

    private static final float SCALE_END = 1.13f;

    private static final int[] IMAGES = {
        R.drawable.ic_screen_default,
            R.drawable.splash0,
            R.drawable.splash1,
            R.drawable.splash2,
            R.drawable.splash3,
            R.drawable.splash4,
            R.drawable.splash5,
            R.drawable.splash6,
            R.drawable.splash7,
            R.drawable.splash8,
            R.drawable.splash9,
            R.drawable.splash10,
            R.drawable.splash11,
            R.drawable.splash12,
            R.drawable.splash13,
            R.drawable.splash14,
            R.drawable.splash15,
            R.drawable.splash16
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        ButterKnife.bind(this);
        setTranslucentStatus(true);

        Random random = new Random(SystemClock.elapsedRealtime());
        mSplashImage.setImageResource(IMAGES[random.nextInt(IMAGES.length)]);

        rx.Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>()
                {

                    @Override
                    public void call(Long aLong)
                    {
                        startAnim();
                    }
                });

    }


    private void startAnim()
    {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mSplashImage, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mSplashImage, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                startActivity(new Intent(EntryActivity.this, MainActivity.class));
                EntryActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
