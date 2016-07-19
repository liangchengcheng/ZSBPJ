package com.lcc.msdq.news.znews;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcc.adapter.UserGoodAdapter;
import com.lcc.adapter.XtNewsAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.entity.UserGood;
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.UserGoodPresenter;
import com.lcc.mvp.presenter.XtNewsPresenter;
import com.lcc.mvp.presenter.impl.UserGoodPresenterImpl;
import com.lcc.mvp.presenter.impl.XtNewsPresenterImpl;
import com.lcc.mvp.view.UserGoodView;
import com.lcc.view.loadview.LoadingLayout;

import java.util.List;

import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  ZNewsActivity
 */
public class ZNewsActivity extends BaseActivity implements UserGoodView,
        UserGoodAdapter.OnItemClickListener{

    private LoadingLayout loading_layout;
    private RecyclerView mRecyclerView;
    private UserGoodAdapter mAdapter;
    private UserGoodPresenter xtNewsPresenter;

    @Override
    protected void initView() {
        initRecycleView();
        xtNewsPresenter=new UserGoodPresenterImpl(this);
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        xtNewsPresenter.getData();
    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ZNewsActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new UserGoodAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_z_news;
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
        FrameManager.getInstance().toastPrompt("数据加载失败");
    }

    @Override
    public void getDataSuccess(List<UserGood> entities) {
        if (entities != null && entities.size() > 0) {
            mAdapter.bind(entities);
            loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        }else {
            getDataEmpty();
        }
    }

    @Override
    public void onItemClick(UserGood entities) {

    }
}
