package com.lcc.msdq.news.xtnews;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcc.adapter.IndexMenuAdapter;
import com.lcc.adapter.XtNewsAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.entity.XtNewsEntity;
import com.lcc.msdq.R;
import com.lcc.view.loadview.LoadingLayout;

import zsbpj.lccpj.utils.TimeUtils;
import zsbpj.lccpj.view.recyclerview.listener.OnRecycleViewScrollListener;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  XtNewsActivity
 */
public class XtNewsActivity extends BaseActivity implements XtNewsAdapter.OnItemClickListener {

    private LoadingLayout loading_layout;
    private RecyclerView mRecyclerView;
    private XtNewsAdapter mAdapter;

    @Override
    protected void initView() {
        initRecycleView();
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.xt_news_layout;
    }

    @Override
    public void onItemClick(XtNewsEntity data) {

    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(XtNewsActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new XtNewsAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
