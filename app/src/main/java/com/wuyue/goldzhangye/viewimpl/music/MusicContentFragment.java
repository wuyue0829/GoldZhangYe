package com.wuyue.goldzhangye.viewimpl.music;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuyue.goldzhangye.R;
import com.wuyue.goldzhangye.adapter.MusicAdapter;
import com.wuyue.goldzhangye.base.BaseFragment;
import com.wuyue.goldzhangye.utils.ThemeUtils;
import com.wuyue.goldzhangye.viewinterface.music.IGetMusicByTagView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.tag;

/**
 * Created by wuyue on 2016/12/1.
 */

public class MusicContentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,IGetMusicByTagView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout idSwiperefreshlayout;
    private int position;
    private int lastVisibleItem;
    private LinearLayoutManager mLayoutManager;
    private List<String> listTag;

    private MusicAdapter adapter;

    public static MusicContentFragment newInstance(int position, String title){
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putInt("position",position);
        MusicContentFragment fragment = new MusicContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_content,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            position = args.getInt("position");
        }
        scrollRecycleView();
        idSwiperefreshlayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        idSwiperefreshlayout.setOnRefreshListener(this);
        adapter=new MusicAdapter(getActivity());
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setAdapter(adapter);
    }

    /**
     * recyclerView Scroll listener , maybe in here is wrong ?
     */
    public void scrollRecycleView() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                    if (mLayoutManager.getItemCount() == 1) {
                        if(adapter!=null) {
                            adapter.updateLoadStatus(adapter.LOAD_NONE);
                        }
                        return;

                    }
                    if (lastVisibleItem + 1 == mLayoutManager.getItemCount()) {
                        if(adapter!=null) {
                            adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                            // isLoadMore = true;
                            adapter.updateLoadStatus(adapter.LOAD_MORE);
                        }
                        //new Handler().postDelayed(() -> getBeforeNews(time), 1000);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            }
                        },1000) ;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return null;
    }
}
