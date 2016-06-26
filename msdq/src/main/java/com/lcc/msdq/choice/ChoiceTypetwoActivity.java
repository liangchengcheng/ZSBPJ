package com.lcc.msdq.choice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcc.adapter.ChoiceType1Adapter;
import com.lcc.adapter.ChoiceType2Adapter;
import com.lcc.base.BaseActivity;
import com.lcc.entity.Type1;
import com.lcc.entity.Type2;
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.ChoiceTypePresenter;
import com.lcc.mvp.presenter.impl.ChoicePresenterImpl;
import com.lcc.mvp.view.ChoiceTypeView;
import com.lcc.view.loadview.LoadingLayout;

import java.util.List;

import zsbpj.lccpj.utils.GsonUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  ChoiceTypeoneActivity
 */
public class ChoiceTypetwoActivity extends BaseActivity implements ChoiceTypeView,
        ChoiceType2Adapter.OnItemClickListener{

    public static final String NID = "nid";
    private ChoiceTypePresenter choiceTypePresenter;
    private LoadingLayout loading_layout;
    private RecyclerView mRecyclerView;
    private ChoiceType2Adapter mAdapter;

    public static void startChoiceTypetwoActivity(Activity startingActivity, String nid) {
        Intent intent = new Intent(startingActivity, ChoiceTypetwoActivity.class);
        intent.putExtra(NID, nid);
        startingActivity.startActivity(intent);
    }
    @Override
    protected void initView() {
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        initRecycleView();
        choiceTypePresenter = new ChoicePresenterImpl(this);
        choiceTypePresenter.getType1();
    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ChoiceTypetwoActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ChoiceType2Adapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.layout_activity_type1;
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
    public void getDataSuccess(String msg) {
        try {
            List<Type2> data = GsonUtils.fromJsonArray(msg, Type2.class);
            mAdapter.bind(data);
            loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(Type2 data) {

    }
}
