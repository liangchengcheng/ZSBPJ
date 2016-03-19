package com.lcc.activity.video;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
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
import com.lcc.constants.StateConstants;
import com.lcc.entity.CommentEntity;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.ResultEntity;
import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;
import com.lcc.utils.DeviceUtils;
import com.lcc.utils.ResourcesUtils;

import java.util.List;

import de.greenrobot.event.EventBus;
import io.vov.vitamio.LibsChecker;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.DensityUtil;
import zsbpj.lccpj.utils.LogUtils;
import zsbpj.lccpj.utils.PinyinUtils;
import zsbpj.lccpj.view.recyclerview.listener.OnRecycleViewScrollListener;
import zsbpj.lccpj.view.recyclerview.listener.TopScrollListener;

public class VideoPlayActivity extends AppCompatActivity
        implements PlayVideoView, SwipeRefreshLayout.OnRefreshListener,
        CommentsAdapter.OnCommentItemClickListener {

    public final static String MEDIAS_ID_KEY = "media_id";
    private RecyclerView mRecyclerView;
    private CommentsAdapter mAdapter;

    private VideoPlayHeader mVideoPlayHeader;
    private int medias_id;
    private int current_comment_page = 1;

    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra(MEDIAS_ID_KEY, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        if (!LibsChecker.checkVitamioLibs(this))
            return;
        //保持屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video_play);
        View view = findViewById(R.id.appbar);

        int width = DeviceUtils.getScreenWidth(this) + DensityUtil.dip2px(this, 110) + getActionBarSize();
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, width);
        view.setLayoutParams(params);

        medias_id = getIntent().getIntExtra(MEDIAS_ID_KEY, -1);
        RxService.getInstance().getMedias(getTaskId(), medias_id);
        initView();
        RxService.getInstance().getMedias(getTaskId(), medias_id);
        refresh(medias_id);
    }

    public void refresh(int id) {
        RxService.getInstance().getComments(getTaskId(), id, 1);
    }

    public void loadMore(int id, int page) {
        RxService.getInstance().getComments(getTaskId(), id, page);
    }

    public int getActionBarSize() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return 0;
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
        mVideoPlayHeader = new VideoPlayHeader(this, findViewById(R.id.video_header));
        initRecyclerView();
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
                loadMore(medias_id, current_comment_page);
                mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnCommentItemClickListener(this);
        mRecyclerView.addOnScrollListener(new TopScrollListener() {

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
        current_comment_page = 2;
        mAdapter.clear();
        mAdapter.appendToList(dataList);
        if (dataList.isEmpty()) {
            mAdapter.setHasMoreData(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreComments(List<CommentEntity> dataList) {
        if (dataList.isEmpty()) {
            mAdapter.setHasMoreData(false);
            FrameManager.getInstance().toastPrompt("没有更多的数据...");
        } else {
            current_comment_page++;
            mAdapter.appendToList(dataList);
            mAdapter.notifyDataSetChanged();
            mAdapter.setHasMoreData(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mVideoPlayHeader != null) {
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
    }

    @Override
    public void thumbDown(int id) {
    }

    @Override
    public void onRefresh() {
        refresh(medias_id);
    }

    public void onEventMainThread(ResultEntity response) {
        LogUtils.e("lcc", "进入impl onEventMainThread");
        if (response == null || response.getState() == StateConstants.FAIL) {
            showError(response);
        } else {
            //是视频
            if (response.getCode() == 1) {
                MediaEntity media_entity = (MediaEntity) response.getT();
                showMediaData(media_entity);
            } else {
                //是评论判断是第一次加载，还是加载更多。
                List<CommentEntity> commentEntityList  = (List<CommentEntity>) response.getT();
                if (response.getState() == StateConstants.REFRESH_SUCCESS) {
                    refreshComment(commentEntityList);
                } else {
                    showMoreComments(commentEntityList);
                }
            }
        }
    }

    /**
     * 对基本的错误进行处理
     */
    private void showError(ResultEntity response) {
        if (response!=null){
            if (response.getCode()==1){
                FrameManager.getInstance().toastPrompt("加载视频失败");
            }else {
                FrameManager.getInstance().toastPrompt("加载评论失败");
            }
        }else {
            FrameManager.getInstance().toastPrompt("服务器数据异常");
        }
    }
}
