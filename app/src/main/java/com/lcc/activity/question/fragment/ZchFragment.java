package com.lcc.activity.question.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcc.activity.video.VideoUtils;
import com.lcc.adapter.question.ZXAdapter;
import com.lcc.constants.StateConstants;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.ResultEntity;
import com.lcc.entity.VideoItemEntity;
import com.lcc.entity.ZXTest;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.LogUtils;
import zsbpj.lccpj.view.recyclerview.RefreshAndLoadFragment;

public class ZchFragment extends RefreshAndLoadFragment implements ZXAdapter.OnItemClickListener{

    public static Fragment newInstance() {
        Fragment fragment = new ZchFragment();
        return fragment;
    }

    private ZXAdapter mAdapter;
    static final int ACTION_NONE = 0;

    @Override
    public void onRefreshData() {
        showRefreshData(getData());
    }

    @Override
    protected void onFragmentLoadMore() {
        showMoreData(getData());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();

        RecyclerView mRecyclerView = getRecyclerView();
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ZXAdapter(getActivity());
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                showRefreshData(getData());
            }
        }, 500);
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
    public void OnItemClick(ZXTest entity) {

    }

    private List<ZXTest> getData(){
        List<ZXTest> data=new ArrayList<>();
        for (int i=0;i<10;i++){
            ZXTest zxTest=new ZXTest();
            zxTest.setDate("2016年03月23日14:26:29"+i);
            zxTest.setName("美国挑衅南海，中国外交部强烈谴责");
            zxTest.setJj("浏览器根据请求的URL交给DNS域名解析，找到真实IP，向服务器发起请求服务器交给后台处理完成后返回数据，浏览器接收文件（HTML、JS、CSS、图象等）浏览器对加载到的资源（HTML、JS、CSS等）进行语法解析，建立相应的内部数据结构（如HTML的DOM）");
            data.add(zxTest);
        }
        return data;
    }


    public void onEventMainThread(ResultEntity response) {
        LogUtils.e("lcc", "进入onEventMainThread");
        if (response == null || response.getState()== StateConstants.FAIL) {
            showError();
        } else {
            if (response.getClass_tag()==StateConstants.VIDEO_CLASS_TAG){
                List<MediaEntity> mediaEntities = VideoUtils.toMediaList((List<VideoItemEntity>) response.getT());
                if (response.getState()==StateConstants.REFRESH_SUCCESS){
                    showRefreshData(mediaEntities);
                }else {
                    showMoreData(mediaEntities);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
