package com.wuyue.goldzhangye.ui.guide;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuyue.goldzhangye.R;
import com.wuyue.goldzhangye.utils.MCVideoView;


/**
 * Created by wuyue on 2016/10/28.
 */

public class GuideThreeFragment extends Fragment {

    private MCVideoView videoView;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_guidethree, null, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(videoView!=null){
            videoView.playVideo(getActivity(), Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.guide_3));
        }
    }

    private void initView(View view) {
        videoView=(MCVideoView) view.findViewById(R.id.videoView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(videoView!=null){
            videoView.stopPlayback();
        }
    }
}
