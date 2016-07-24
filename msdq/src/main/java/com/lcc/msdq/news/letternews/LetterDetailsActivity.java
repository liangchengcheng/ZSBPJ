package com.lcc.msdq.news.letternews;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcc.adapter.LatterEntityAdapter;
import com.lcc.adapter.LetterAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.db.test.UserInfo;
import com.lcc.entity.LatterEntity;
import com.lcc.entity.Letter;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.LatterEntityPresenter;
import com.lcc.mvp.presenter.LetterPresenter;
import com.lcc.mvp.presenter.impl.LatterEntityPresenterImpl;
import com.lcc.mvp.presenter.impl.LetterPresenterImpl;
import com.lcc.mvp.view.LatterEntityView;
import com.lcc.view.loadview.LoadingLayout;

import java.util.List;

import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  LetterActivity(个人的私信的界面)
 */
public class LetterDetailsActivity extends BaseActivity implements LatterEntityView {

    public static final String LETTER = "letter";
    private Letter letter;

    private LoadingLayout loading_layout;
    private RecyclerView mRecyclerView;
    private LatterEntityAdapter mAdapter;
    private LatterEntityPresenter Presenter;

    public static void startLetterDetailsActivity(Letter letter,
                                                  Activity startingActivity) {
        Intent intent = new Intent(startingActivity, LetterDetailsActivity.class);
        intent.putExtra(LETTER, letter);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void initView() {
        letter = (Letter) getIntent().getSerializableExtra(LETTER);

        initRecycleView();
        Presenter = new LatterEntityPresenterImpl(this);
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        Presenter.getData("1",letter.getFrom_w());
    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(LetterDetailsActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        UserInfo userInfo = DataManager.getUserInfo();
        mAdapter = new LatterEntityAdapter(userInfo, letter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.latter_edtails;
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
    public void getDataSuccess(List<LatterEntity> entities) {
        if (entities != null && entities.size() > 0) {
            mAdapter.bind(entities);
            loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        } else {
            getDataEmpty();
        }
    }
}
