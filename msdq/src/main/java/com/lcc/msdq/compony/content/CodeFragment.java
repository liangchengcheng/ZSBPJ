package com.lcc.msdq.compony.content;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcc.adapter.JSAdapter;
import com.lcc.entity.CompanyTest;
import com.lcc.frame.fragment.base.BaseLazyLoadFragment;
import com.lcc.msdq.R;
import com.lcc.msdq.compony.answer.CompanyAnswerIndexActivity;
import com.lcc.mvp.presenter.JSPresenter;
import com.lcc.mvp.presenter.impl.JSPresenterImpl;
import com.lcc.mvp.view.JSView;

import java.util.List;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.TimeUtils;
import zsbpj.lccpj.view.recyclerview.listener.OnRecycleViewScrollListener;

public class CodeFragment extends BaseLazyLoadFragment implements SwipeRefreshLayout.OnRefreshListener,
        JSView, JSAdapter.OnItemClickListener {
    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;
    private JSAdapter adapter;
    private String fid = "";
    private String type = "";

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private JSPresenter mPresenter;

    public static CodeFragment newInstance(String fid, String type) {
        CodeFragment mFragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fid", fid);
        bundle.putString("type", type);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public int initContentView() {
        return R.layout.code_fragment;
    }

    @Override
    public void getBundle(Bundle bundle) {
        fid = bundle.getString("fid");
        type = bundle.getString("type");
    }

    @Override
    public void initUI(View view) {
        mPresenter = new JSPresenterImpl(this);
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
        adapter = new JSAdapter();
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
                    currentPage++;
                    mPresenter.loadMore(currentPage, fid, type);
                }
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getData(1, fid, type);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = 1;
                mSwipeRefreshWidget.setRefreshing(true);
                mPresenter.refresh(currentPage, fid, type);
            }
        }, 500);
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
        if (mSwipeRefreshWidget.isRefreshing()) {
            mSwipeRefreshWidget.setRefreshing(false);
            showError(true);
        } else {
            FrameManager.getInstance().toastPrompt(msg);
        }
    }

    @Override
    public void refreshDataSuccess(List<CompanyTest> entities) {
        if (entities != null && entities.size() > 0) {
            adapter.bind(entities);
        }
        mSwipeRefreshWidget.setRefreshing(false);
        showContent(true);
    }

    @Override
    public void loadMoreWeekDataSuccess(final List<CompanyTest> entities) {
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
                    adapter.setHasMoreDataAndFooter(true, false);
                }
                adapter.notifyDataSetChanged();
            }
        }, delay);
    }

    @Override
    public void onReloadClicked() {
        super.onReloadClicked();
        onRefresh();
    }

    @Override
    public void onItemClick(CompanyTest data) {
        Intent intent = new Intent(getActivity(), CompanyAnswerIndexActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}
