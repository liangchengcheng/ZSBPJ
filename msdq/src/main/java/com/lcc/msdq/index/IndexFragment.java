package com.lcc.msdq.index;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lcc.adapter.AnswerIndexAdapter;
import com.lcc.adapter.WeekDataAdapter;
import com.lcc.base.BaseFragment;
import com.lcc.entity.ActivityEntity;
import com.lcc.entity.Answer;
import com.lcc.entity.WeekData;
import com.lcc.frame.Advertisements;
import com.lcc.frame.update.UpdateApkTask;
import com.lcc.msdq.R;
import com.lcc.msdq.index.IndexMenuView.IndexMenuActivity;
import com.lcc.msdq.index.IndexWebView.IndexWebView;
import com.lcc.msdq.test.answer.AnswerContentActivity;
import com.lcc.mvp.presenter.IndexPresenter;
import com.lcc.mvp.presenter.LoginPresenter;
import com.lcc.mvp.presenter.impl.IndexPresenterImpl;
import com.lcc.mvp.presenter.impl.LoginPresenterImpl;
import com.lcc.mvp.view.IndexView;
import com.lcc.mvp.view.LoginView;
import com.lcc.view.FullyLinearLayoutManager;
import com.lcc.view.loadview.LoadingLayout;
import com.lcc.view.menu.GuillotineAnimation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.TimeUtils;
import zsbpj.lccpj.view.recyclerview.listener.OnRecycleViewScrollListener;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2016年04月21日07:17:52
 * Description: 第一页fragment
 */
public class IndexFragment extends BaseFragment implements IndexView,
        SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    private LinearLayout llAdvertiseBoard;
    private LayoutInflater inflaters;

    private static final long RIPPLE_DURATION = 250;
    private IndexPresenter mPresenter;
    private LoadingLayout loading_layout;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;

    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;
    private WeekDataAdapter mAdapter;

    public static Fragment newInstance() {
        Fragment fragment = new IndexFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.index_fragment, null);
        view.findViewById(R.id.mszb).setOnClickListener(this);
        view.findViewById(R.id.msjl).setOnClickListener(this);
        view.findViewById(R.id.mszz).setOnClickListener(this);
        view.findViewById(R.id.msjq).setOnClickListener(this);
        view.findViewById(R.id.msgx).setOnClickListener(this);
        view.findViewById(R.id.msjz).setOnClickListener(this);
        view.findViewById(R.id.msjt).setOnClickListener(this);
        view.findViewById(R.id.qt).setOnClickListener(this);
        initRefreshView(view);

        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ImageView iv_menu = (ImageView) view.findViewById(R.id.iv_menu);
        LinearLayout root = (LinearLayout) view.findViewById(R.id.root);
        View guillotineMenu
                = LayoutInflater.from(getActivity()).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);
        new GuillotineAnimation.GuillotineBuilder(guillotineMenu,
                guillotineMenu.findViewById(R.id.guillotine_hamburger), iv_menu)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

        inflaters = LayoutInflater.from(getActivity());
        llAdvertiseBoard = (LinearLayout) view.findViewById(R.id.llAdvertiseBoard);
        mPresenter = new IndexPresenterImpl(this);
        mPresenter.getActivity();
        initRecycleView(view);
        onRefresh();
        return view;
    }

    private void initRefreshView(View view) {
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    private void initRecycleView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new WeekDataAdapter();
        mAdapter.setOnItemClickListener(new WeekDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WeekData data) {

            }
        });

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
    public void getFail(String msg) {
        FrameManager.getInstance().toastPrompt(msg);
    }

    @Override
    public void getSuccess(final List<ActivityEntity> list) {
        try {
            if (list != null && list.size() > 0) {
                Advertisements advertisements = new Advertisements(getActivity(), true, inflaters, 3000);
                View view = advertisements.initView(list);
                advertisements.setOnPictureClickListener(new Advertisements.onPictrueClickListener() {
                    @Override
                    public void onClick(int position) {
                        //Intent intent=new Intent(getActivity(),IndexContentActivity.class);
                        //intent.putExtra(IndexContentActivity.KEY_URL,list.get(position).getMid());
                        //intent.putExtra(IndexContentActivity.IMAGE_URL,list.get(position).getActivity_pic());
                        //startActivity(intent);
                        Intent intent = new Intent(getActivity(), IndexWebView.class);
                        intent.putExtra(IndexWebView.KEY_URL, list.get(position).getMid());
                        intent.putExtra(IndexWebView.IMAGE_URL, list.get(position).getActivity_pic());
                        startActivity(intent);
                    }
                });
                llAdvertiseBoard.addView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getWeekDataLoading() {
        if (mSwipeRefreshWidget.isRefreshing()) {
            mSwipeRefreshWidget.setRefreshing(false);
            loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
        }
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void getWeekDataEmpty() {
        loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
    }

    @Override
    public void getWeekDataFail(String msg) {
        if (mSwipeRefreshWidget.isRefreshing()) {
            mSwipeRefreshWidget.setRefreshing(false);
        }else {
            FrameManager.getInstance().toastPrompt(msg);
        }
        loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
    }

    @Override
    public void refreshWeekDataSuccess(List<WeekData> entities) {
        if (entities != null && entities.size() > 0) {
            mAdapter.bind(entities);
        }
        mSwipeRefreshWidget.setRefreshing(false);
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }

    @Override
    public void loadMoreWeekDataSuccess(final List<WeekData> entities) {
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
                    FrameManager.getInstance().toastPrompt("没有更多数据...");
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mszb:
                IndexMenuActivity.startIndexMenuActivity(getActivity(),"面试准备");
                break;

            case R.id.msjl:
                IndexMenuActivity.startIndexMenuActivity(getActivity(),"面试简历");
                break;

            case R.id.msjq:
                IndexMenuActivity.startIndexMenuActivity(getActivity(),"面试技巧");
                break;

            case R.id.mszz:
                IndexMenuActivity.startIndexMenuActivity(getActivity(),"面试着装");
                break;

            case R.id.msgx:
                IndexMenuActivity.startIndexMenuActivity(getActivity(),"面试感想");
                break;

            case R.id.msjz:
                IndexMenuActivity.startIndexMenuActivity(getActivity(),"面试举止");
                break;

            case R.id.msjt:
                IndexMenuActivity.startIndexMenuActivity(getActivity(),"面试交通");
                break;

            case R.id.qt:
                IndexMenuActivity.startIndexMenuActivity(getActivity(),"其他");
                break;
        }
    }

}
