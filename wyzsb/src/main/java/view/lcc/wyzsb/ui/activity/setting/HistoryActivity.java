package view.lcc.wyzsb.ui.activity.setting;

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
import view.lcc.wyzsb.adapter.BookAdapter;
import view.lcc.wyzsb.adapter.HistoryAdapter;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.Book;
import view.lcc.wyzsb.bean.History;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.frame.OnRecycleViewScrollListener;
import view.lcc.wyzsb.mvp.presenter.BookPresenter;
import view.lcc.wyzsb.mvp.presenter.HistoryPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.BookPresenterImpl;
import view.lcc.wyzsb.mvp.presenter.impl.HistoryPresenterImpl;
import view.lcc.wyzsb.mvp.view.BookView;
import view.lcc.wyzsb.mvp.view.HistoryView;
import view.lcc.wyzsb.ui.activity.video.VideoDetailsActivity1;
import view.lcc.wyzsb.utils.TimeUtils;
import view.lcc.wyzsb.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2015年11月21日15:28:25
 * Description:  |历史记录的界面
 */
public class HistoryActivity extends BaseActivity implements HistoryView, SwipeRefreshLayout.OnRefreshListener
        ,HistoryAdapter.OnItemClickListener, View.OnClickListener {

    public static final String TYPE = "type";
    private LoadingLayout loading_layout;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private HistoryAdapter mAdapter;
    private HistoryPresenter mPresenter;

    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;
    private String type = "";

    public static void startBookActivity(Activity startingActivity, String type) {
        Intent intent = new Intent(startingActivity, BookActivity.class);
        intent.putExtra(TYPE, type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        findViewById(R.id.iv_back).setOnClickListener(this);
        mPresenter = new HistoryPresenterImpl(this);
        initRefreshView();
        initRecycleView();
        mPresenter.getData(currentPage);
    }

    private void initRefreshView() {
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(HistoryActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new HistoryAdapter();
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
                    mPresenter.loadMore(currentPage);
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
            Frame.getInstance().toastPrompt(msg);
        }
    }

    @Override
    public void refreshView(List<History> entities) {
        if (entities != null && entities.size() > 0) {
            mAdapter.bind(entities);
        }
        mSwipeRefreshWidget.setRefreshing(false);
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }

    @Override
    public void loadMoreView(final List<History> entities) {
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

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = 1;
                mSwipeRefreshWidget.setRefreshing(true);
                mPresenter.refresh(currentPage);
            }
        }, 500);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(History data) {
        Video video = new Video();
        video.setV_l(data.getV_l());
        video.setV_type(data.getV_type());
        video.setV_t(data.getV_t());
        video.setBq(data.getBq());
        video.setId(data.getVid());
        video.setV_a(data.getV_a());
        video.setV_img(data.getV_img());
        video.setV_time(data.getV_time());
        video.setV_url(data.getV_url());
        video.setV_js(data.getV_js());

        VideoDetailsActivity1.startVideoDetailsActivity(HistoryActivity.this,video);
    }
}

