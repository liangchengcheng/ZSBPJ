package com.lcc.activity.video;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lcc.activity.R;
import com.lcc.adapter.CommentsAdapter;
import com.lcc.entity.CommentEntity;
import com.lcc.entity.MediaEntity;
import com.lcc.utils.ResourcesUtils;

import java.util.List;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.PinyinUtils;
import zsbpj.lccpj.view.recyclerview.listener.OnRecycleViewScrollListener;
import zsbpj.lccpj.view.recyclerview.listener.TopScrollListener;

public class VideoPlayActivity extends AppCompatActivity implements PlayVideoView ,SwipeRefreshLayout.OnRefreshListener, CommentsAdapter.OnCommentItemClickListener{

    public final static String MEDIAS_ID_KEY = "media_id";
    private RecyclerView mRecyclerView;
    private CommentsAdapter mAdapter;
    private VideoPlayHeader mVideoPlayHeader;
    private int medias_id;
    private int current_comment_page = 1;

    //我在这继承了他的接口就要实现 接口impl了

    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra(MEDIAS_ID_KEY, id);
        return intent;
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommentsAdapter(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                mAdapter.setHasFooter(true);
                // TODO: 16/3/6 加载更多的数据 loadmore(medias_id,current_comment_page)
                mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnCommentItemClickListener(this);
        // TODO: 16/3/6 为什么是在滑动的时候判断视频开始和暂停
        mRecyclerView.addOnScrollListener(new  TopScrollListener(){

            @Override
            protected void start() {
                mVideoPlayHeader.getVideoControllerView().start();
            }

            @Override
            protected void pause() {
                mVideoPlayHeader.getVideoControllerView().pause();
            }
        });

    }

    private void setHeaderView(MediaEntity mediaEntity) {
        mVideoPlayHeader.bindData(mediaEntity);
    }



    @Override
    public void showMediaData(MediaEntity mediaEntity) {
        setHeaderView(mediaEntity);
    }

    @Override
    public void showLoadMediaError() {

    }

    @Override
    public void refreshComment(List<CommentEntity> dataList) {
        current_comment_page=2;
        mAdapter.clear();
        mAdapter.appendToList(dataList);
        if (dataList.isEmpty()){
            mAdapter.setHasMoreData(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreComments(List<CommentEntity> dataList) {
        if (dataList.isEmpty()){
            mAdapter.setHasMoreData(false);
            FrameManager.getInstance().toastPrompt("没有更多的数据...");
        }else {
            current_comment_page++;
            mAdapter.appendToList(dataList);
            mAdapter.notifyDataSetChanged();
            mAdapter.setHasMoreData(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoPlayHeader!=null){
            mVideoPlayHeader.getVideoControllerView().release();
        }
    }

    @Override
    public void onItemClick(CommentEntity commentEntity) {

    }

    @Override
    public void onClickAvatar(int uid) {

    }

    @Override
    public void onClickAtFriend(String screen_name) {

    }

    @Override
    public void thumbUp(int id) {
        //mPresenter.createLikeComment(id);
    }

    @Override
    public void thumbDown(int id) {
        //mPresenter.destoryLikeComment(id);
    }

    @Override
    public void onRefresh() {
        //刷新数据
        //presenter.refresh(mdeias_id);
    }
}
