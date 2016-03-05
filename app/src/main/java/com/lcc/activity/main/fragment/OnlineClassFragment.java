package com.lcc.activity.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcc.activity.MainActivity;
import com.lcc.activity.R;
import com.lcc.adapter.MediasAdapter;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;

import java.util.List;

import zsbpj.lccpj.view.recyclerview.MGridLayoutManager;
import zsbpj.lccpj.view.recyclerview.RefreshAndLoadFragment;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  在线课程
 */
public class OnlineClassFragment extends RefreshAndLoadFragment implements MediasAdapter.OnItemClickListener  {

    private static final String KEY_VIDEO_ID = "id";
    private static final String KEY_VIDEO_TYPE = "type";

    private MediasAdapter mAdapter;
    private MainActivity mainActivity;
    private RecyclerView mRecyclerView;

    private static final int PAGER_SIZE = 20;
    private static final int GRID_COLUMN = 2;

    private int id;
    private int type;

    /**
     * 初始化这个fragment
     */
    public static Fragment newInstance(int id,int type){
        Bundle bundle=new Bundle();
        bundle.putInt(KEY_VIDEO_ID,id);
        bundle.putInt(KEY_VIDEO_TYPE,type);
        Fragment fragment=new OnlineClassFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity= (MainActivity) context;
    }

    @Override
    public void onRefreshData() {

    }

    @Override
    protected void onFragmentLoadMore() {
        super.onFragmentLoadMore();
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        RxService.getInstance().getBus().register(this);
        id=getArguments().getInt(KEY_VIDEO_ID);
        type=getArguments().getInt(KEY_VIDEO_TYPE);
        mRecyclerView=getRecyclerView();
        mRecyclerView.setHasFixedSize(true);
        mAdapter=new MediasAdapter(getActivity());
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new MGridLayoutManager(getActivity(), GRID_COLUMN, mAdapter));

    }

    private void autoRefresh(){
        getSwipeRefreshWidget().postDelayed(new Runnable() {
            @Override
            public void run() {
                //设置当前的状态是刷新
                currentPage=STATE_REFRESH;
                getSwipeRefreshWidget().setRefreshing(true);
                // TODO: 16/3/3 在此处加载数据 
                //mPresenter.refresh(id, type, PAGER_SIZE);
            }
        },500);
    }

    void showError(){

    }

    void refreshView(List<MediaEntity> entities){

    }

    void loadMoreView(List<MediaEntity> entities){

    }

    private void getNewData(){
        RxService.getInstance().getVideoList(getActivity().getTaskId(), "");
    }

    @Override
    public void OnItemClick(MediaEntity entity) {

    }

    public void onEventMainThread(List<VideoItemEntity> response) {
//        if(newsEvent!=null&&Constant.NEWSTYPE.BLOG.getNewsType().equals(newsEvent.getNewsType())) {
//            updateView(newsEvent);
//        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxService.getInstance().getBus().unregister(this);
    }

}
