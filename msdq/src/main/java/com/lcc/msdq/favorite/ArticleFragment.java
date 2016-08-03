package com.lcc.msdq.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcc.adapter.FavAdapter;
import com.lcc.adapter.JSAdapter;
import com.lcc.entity.CompanyTest;
import com.lcc.entity.FavEntity;
import com.lcc.frame.Propertity;
import com.lcc.frame.fragment.base.BaseLazyLoadFragment;
import com.lcc.msdq.R;
import com.lcc.msdq.test.answer.AnswerIndexActivity;
import com.lcc.mvp.presenter.FavPresenter;
import com.lcc.mvp.presenter.JSPresenter;
import com.lcc.mvp.presenter.impl.FavPresenterImpl;
import com.lcc.mvp.presenter.impl.JSPresenterImpl;
import com.lcc.mvp.view.FavView;
import com.lcc.mvp.view.JSView;

import java.util.List;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.TimeUtils;
import zsbpj.lccpj.view.recyclerview.listener.OnRecycleViewScrollListener;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  ArticleFragment
 */
public class ArticleFragment extends BaseLazyLoadFragment implements
        SwipeRefreshLayout.OnRefreshListener, FavView, FavAdapter.OnItemClickListener {

    protected final static int STATE_REFRESH = 2;
    static final int ACTION_NONE = 0;
    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;

    private FavAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private FavPresenter mPresenter;
    private String type = "面试感想";

    public static ArticleFragment newInstance(String type) {
        ArticleFragment mFragment = new ArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public int initContentView() {
        return R.layout.fav_list_fragment;
    }

    @Override
    public void getBundle(Bundle bundle) {
        type = bundle.getString("type");
    }

    @Override
    public void initUI(View view) {
        mPresenter = new FavPresenterImpl(this);
        initRefreshView(view);
        initRecycleView(view);
    }

    private void initRefreshView(View view) {
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    private void initRecycleView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new FavAdapter();
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                if (currentState == STATE_NORMAL) {
                    currentState = STATE_LOAD;
                    currentTime = TimeUtils.getCurrentTime();
                    adapter.setHasFooter(true);
                    mRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
                    mPresenter.loadMore(currentPage, type);
                }
            }
        });
    }

    @Override
    public void initData() {
        currentPage = 1;
        mPresenter.getData(currentPage, type);
    }

    @Override
    public void getLoading() {
        showProgress(true);
    }

    @Override
    public void getDataEmpty() {
        showEmpty(true);
    }

    @Override
    public void getDataFail(String msg) {
        showError(true);
    }

    @Override
    public void refreshOrLoadFail(String msg) {
        currentState = ACTION_NONE;
        if (mSwipeRefreshWidget.isRefreshing()) {
            mSwipeRefreshWidget.setRefreshing(false);
            showError(true);
        } else {
            adapter.setHasFooter(false);
            FrameManager.getInstance().toastPrompt(msg);
        }
    }

    @Override
    public void refreshDataSuccess(final List<FavEntity> entities) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;
                mSwipeRefreshWidget.setRefreshing(false);
                currentPage = 2;
                if (entities != null && entities.size() > 0) {
                    adapter.bind(entities);
                }
            }
        }, delay);
        showContent(true);
    }

    @Override
    public void loadMoreWeekDataSuccess(final List<FavEntity> entities) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;
                if (entities.isEmpty()) {
                    adapter.setHasMoreDataAndFooter(false, false);
                    FrameManager.getInstance().toastPrompt("没有更多数据...");
                } else {
                    adapter.appendToList(entities);
                    currentPage++;
                    adapter.setHasMoreDataAndFooter(true, false);
                }
                adapter.notifyDataSetChanged();
            }
        }, delay);
    }

    @Override
    public void onItemClick(FavEntity data) {
        Intent intent = null;
        //我好像得重新写界面
        if (type.equals(Propertity.Article.NAME)){
            //去文章的界面
             intent=new Intent(getActivity(), AnswerIndexActivity.class);
        }else if (type.equals(Propertity.Test.QUESTION)){
            //去资料的问题
             intent=new Intent(getActivity(), AnswerIndexActivity.class);
        }else {
            //去公司的问题
             intent=new Intent(getActivity(), AnswerIndexActivity.class);
        }
        startActivity(intent);

    }

    @Override
    public void onRefresh() {
        if (currentState == STATE_NORMAL) {
            currentState = STATE_REFRESH;
            currentPage = 1;
            mSwipeRefreshWidget.setRefreshing(true);
            currentTime = TimeUtils.getCurrentTime();
            adapter.setHasFooter(true);
            mPresenter.refresh(currentPage, type);
        }
    }

    @Override
    public void onReloadClicked() {
        currentPage = 1;
        mPresenter.getData(currentPage, type);
    }

}
