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
import com.lcc.activity.video.VideoPlayActivity;
import com.lcc.activity.video.VideoUtils;
import com.lcc.adapter.MediasAdapter;
import com.lcc.constants.Constant;
import com.lcc.constants.StateConstants;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.ResultEntity;
import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;

import java.util.Iterator;
import java.util.List;

import de.greenrobot.event.EventBus;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.LogUtils;
import zsbpj.lccpj.view.recyclerview.MGridLayoutManager;
import zsbpj.lccpj.view.recyclerview.RefreshAndLoadFragment;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  在线课程
 */
public class OnlineClassFragment extends RefreshAndLoadFragment implements MediasAdapter.OnItemClickListener {

    private static final String KEY_VIDEO_ID = "id";
    private static final String KEY_VIDEO_TYPE = "type";

    private MediasAdapter mAdapter;
    private static final int PAGER_SIZE = 20;
    private static final int GRID_COLUMN = 2;
    static final int ACTION_NONE = 0;

    private int id;
    private int type;

    public static Fragment newInstance(int id, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_VIDEO_ID, id);
        bundle.putInt(KEY_VIDEO_TYPE, type);
        Fragment fragment = new OnlineClassFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onRefreshData() {
        RxService.getInstance().getVideoList(getTaskId(), 1, PAGER_SIZE);
    }

    @Override
    protected void onFragmentLoadMore() {
        RxService.getInstance().getVideoList(getTaskId(), getCurrentPage(), PAGER_SIZE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        id = getArguments().getInt(KEY_VIDEO_ID);
        type = getArguments().getInt(KEY_VIDEO_TYPE);
        EventBus.getDefault().register(this);
        RecyclerView mRecyclerView = getRecyclerView();
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MediasAdapter(getActivity());
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new MGridLayoutManager(getActivity(), GRID_COLUMN, mAdapter));
        autoRefresh();
    }

    /**
     * 刷新数据
     */
    private void autoRefresh() {
        getSwipeRefreshWidget().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = STATE_REFRESH;
                getSwipeRefreshWidget().setRefreshing(true);
                RxService.getInstance().getVideoList(getTaskId(), 1, PAGER_SIZE);
            }
        }, 500);
    }
    
    public void onEventMainThread(ResultEntity response) {
        LogUtils.e("lcc", "进入onEventMainThread");
        if (response == null || response.getState()== StateConstants.FAIL) {
            showError();
        } else {
            List<MediaEntity> mediaEntities = VideoUtils.toMediaList((List<VideoItemEntity>) response.getT());
            if (response.getState()==StateConstants.REFRESH_SUCCESS){
                showRefreshData(mediaEntities);
            }else {
                showMoreData(mediaEntities);
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void showError() {
        currentState = ACTION_NONE;
        if (getSwipeRefreshWidget().isRefreshing()) {
            getSwipeRefreshWidget().setRefreshing(false);
            FrameManager.getInstance().toastPrompt("刷新数据失败");
        } else {
            FrameManager.getInstance().toastPrompt("加载数据失败");
            mAdapter.setHasFooter(false);
        }
    }

    @Override
    public void OnItemClick(MediaEntity entity) {
        startActivity(VideoPlayActivity.createIntent(getActivity(), entity.getId()));
    }

}
