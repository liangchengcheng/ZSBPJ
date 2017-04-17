package view.lcc.wyzsb.ui.activity.article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.adapter.ArticleAdapter;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.frame.OnRecycleViewScrollListener;
import view.lcc.wyzsb.mvp.presenter.ArticlePresenter;
import view.lcc.wyzsb.mvp.presenter.impl.ArticlePresenterImpl;
import view.lcc.wyzsb.mvp.view.ArticleView;
import view.lcc.wyzsb.utils.TimeUtils;
import view.lcc.wyzsb.view.LoadingLayout;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class ArticleActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        ArticleAdapter.OnFavClickListener, ArticleAdapter.OnItemClickListener,View.OnClickListener,ArticleView {

    public static final String TYPE = "type";
    private LoadingLayout loading_layout;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private ArticlePresenter mPresenter;

    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;
    private String type=" ";

    public static void startArticleActivity(Activity startingActivity, String type) {
        Intent intent = new Intent(startingActivity, ArticleActivity.class);
        intent.putExtra(TYPE, type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_layout);

        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
        mPresenter = new ArticlePresenterImpl(this);
        initRefreshView();
        initRecycleView();
        mPresenter.getData(1,type);
    }

    private void initRefreshView() {
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ArticleActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ArticleAdapter();
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                if (currentState == STATE_NORMAL) {
                    currentState = STATE_LOAD;
                    currentTime = TimeUtils.getCurrentTime();
                    mAdapter.setHasFooter(true);
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                    currentPage++;
                    mPresenter.loadMore(currentPage,type);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = 1;
                mSwipeRefreshWidget.setRefreshing(true);
                mPresenter.refresh(currentPage,type);
            }
        }, 500);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.guillotine_hamburger:
                finish();
                break;
        }
    }

    @Override
    public void onOnFavClick(Article data) {

    }

    @Override
    public void onItemClick(Article data) {
        ArticleDetailsActivity.startArticleDetails(ArticleActivity.this,"");
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
            Frame.getInstance().toastPrompt(msg);
        }
    }

    @Override
    public void refreshDataSuccess(List<Article> entities) {
        if (entities != null && entities.size() > 0) {
            mAdapter.bind(entities);
        }
        mSwipeRefreshWidget.setRefreshing(false);
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }

    @Override
    public void loadMoreWeekDataSuccess(final List<Article> entities) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;
                if (entities.isEmpty()) {
                    mAdapter.setHasMoreDataAndFooter(false, false);
                    Frame.getInstance().toastPrompt("没有更多数据...");
                } else {
                    mAdapter.appendToList(entities);
                    mAdapter.setHasMoreDataAndFooter(true, false);
                }
                mAdapter.notifyDataSetChanged();
            }
        }, delay);
    }
}
