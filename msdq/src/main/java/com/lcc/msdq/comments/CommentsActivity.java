package com.lcc.msdq.comments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lcc.adapter.CommentAdapter;
import com.lcc.adapter.CommentsAdapter;
import com.lcc.adapter.IndexMenuAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.entity.Article;
import com.lcc.entity.Comments;
import com.lcc.msdq.R;
import com.lcc.msdq.index.IndexMenuView.IndexMenuWebView;
import com.lcc.mvp.presenter.CommentsPresenter;
import com.lcc.mvp.presenter.IndexMenuPresenter;
import com.lcc.mvp.presenter.impl.CommentsPresenterImpl;
import com.lcc.mvp.view.CommentsView;
import com.lcc.utils.ScreenUtils;
import com.lcc.view.FullyLinearLayoutManager;
import com.lcc.view.SendCommentButton;
import com.lcc.view.loadview.LoadingLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.TimeUtils;
import zsbpj.lccpj.view.recyclerview.listener.OnRecycleViewScrollListener;

public class CommentsActivity extends BaseActivity implements SendCommentButton.OnSendClickListener ,
        CommentsView,SwipeRefreshLayout.OnRefreshListener{

    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";
    public static final String ID= "id";

    @Bind(R.id.contentRoot)
    LinearLayout contentRoot;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.llAddComment)
    LinearLayout llAddComment;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.btnSendComment)
    SendCommentButton btnSendComment;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private CommentAdapter commentsAdapter;
    private int drawingStartLocation;
    private String content_id;

    public static final String TYPE = "type";
    private LoadingLayout loading_layout;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;

    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;

    private CommentsPresenter mPresenter;


    public static void startUserProfileFromLocation(String id, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, CommentsActivity.class);
        intent.putExtra(ID, id);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter=new CommentsPresenterImpl(this);
        //content_id=getIntent().getStringExtra(ID);
        content_id="46f337bddcb925c166bfac9acf96dea6";
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        initRefreshView();
        initRecycleView();
        setupSendCommentButton();
        mPresenter.getData(1,content_id);

        drawingStartLocation = getIntent().getIntExtra(ARG_DRAWING_START_LOCATION, 0);
        if (savedInstanceState == null) {
            contentRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    contentRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                    startIntroAnimation();
                    return true;
                }
            });
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_comments;
    }

    private void setupSendCommentButton() {
        btnSendComment= (SendCommentButton) findViewById(R.id.btnSendComment);
        btnSendComment.setOnSendClickListener(this);
    }

    private void startIntroAnimation() {
        ViewCompat.setElevation(toolbar, 0);
        contentRoot.setScaleY(0.1f);
        contentRoot.setPivotY(drawingStartLocation);
        llAddComment.setTranslationY(200);

        contentRoot.animate()
                .scaleY(1)
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewCompat.setElevation(toolbar, ScreenUtils.dpToPx(8));

                    }
                })
                .start();
    }

    @Override
    public void onBackPressed() {
        ViewCompat.setElevation(toolbar, 0);
        contentRoot.animate()
                .translationY(ScreenUtils.getScreenHeight(this))
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        CommentsActivity.super.onBackPressed();
                        overridePendingTransition(0, 0);
                    }
                })
                .start();
    }

    @Override
    public void onSendClickListener(View v) {
        if (validateComment()) {
            recyclerView.smoothScrollBy(0, recyclerView.getChildAt(0).getHeight() *
                    commentsAdapter.getItemCount());
            etComment.setText(null);
            btnSendComment.setCurrentState(SendCommentButton.STATE_DONE);
        }
    }

    private boolean validateComment() {
        if (TextUtils.isEmpty(etComment.getText())) {
            btnSendComment.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_error));
            return false;
        }

        return true;
    }

    private void initRefreshView() {
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(CommentsActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        commentsAdapter = new CommentAdapter();
        commentsAdapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Comments data) {
                //IndexMenuWebView.startIndexMenuWebView(CommentsActivity.this,data);
            }
        });

        mRecyclerView.setAdapter(commentsAdapter);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                if (currentState == STATE_NORMAL) {
                    currentState = STATE_LOAD;
                    currentTime = TimeUtils.getCurrentTime();
                    commentsAdapter.setHasFooter(true);
                    mRecyclerView.scrollToPosition(commentsAdapter.getItemCount() - 1);
                    currentPage++;
                    mPresenter.loadMore(currentPage,content_id);
                }
            }
        });
    }

    @Override
    public void getLoading() {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void getDataEmpty() {
        loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
    }

    @Override
    public void getDataFail(String msg) {
        loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
    }

    @Override
    public void refreshOrLoadFail(String msg) {
        if (mSwipeRefreshWidget.isRefreshing()) {
            mSwipeRefreshWidget.setRefreshing(false);
            loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
        } else {
            FrameManager.getInstance().toastPrompt(msg);
        }
    }

    @Override
    public void refreshDataSuccess(List<Comments> entities) {
        if (entities != null && entities.size() > 0) {
            commentsAdapter.bind(entities);
        }
        mSwipeRefreshWidget.setRefreshing(false);
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }

    @Override
    public void loadMoreWeekDataSuccess(final List<Comments> entities) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;
                if (entities.isEmpty()) {
                    commentsAdapter.setHasMoreDataAndFooter(false, false);
                    FrameManager.getInstance().toastPrompt("没有更多数据...");
                } else {
                    commentsAdapter.appendToList(entities);
                    commentsAdapter.setHasMoreDataAndFooter(true, false);
                }
                commentsAdapter.notifyDataSetChanged();
            }
        }, delay);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = 1;
                mSwipeRefreshWidget.setRefreshing(true);
                mPresenter.refresh(currentPage,content_id);
            }
        }, 500);
    }
}
