package com.lcc.msdq.test.answer;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.lcc.adapter.AnswerIndexAdapter;
import com.lcc.adapter.BaseRecyclerAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.entity.Answer;
import com.lcc.msdq.R;
import com.lcc.msdq.comments.CommentsActivity;
import com.lcc.mvp.presenter.TestAnswerPresenter;
import com.lcc.mvp.presenter.TestPresenter;
import com.lcc.mvp.presenter.impl.TestAnswerPresenterImpl;
import com.lcc.mvp.view.TestAnswerView;
import com.lcc.view.FullyLinearLayoutManager;
import com.lcc.view.LoadMoreRecyclerView;
import com.lcc.view.ObservableScrollView;
import com.lcc.view.StretchyTextView;
import java.util.ArrayList;
import java.util.List;

import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  AnswerIndexActivity
 */
public class AnswerIndexActivity extends BaseActivity implements TestAnswerView,
        SwipeRefreshLayout.OnRefreshListener, ObservableScrollView.ScrollViewListener
,View.OnClickListener{

    private RecyclerView mRecyclerView;
    private FullyLinearLayoutManager mLayoutManager;
    private AnswerIndexAdapter mAdapter;
    private StretchyTextView spreadTextView;
    private TestAnswerPresenter mPresenter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private int currentPage=0;
    private String fid="5e7f684866bee25219269994f4784573";
    private ObservableScrollView scroll_view;
    private LinearLayout ll_loading;
    private TextView tv_sc;

    @Override
    protected void initView() {

        findViewById(R.id.tv_sc).setOnClickListener(this);
        mPresenter=new TestAnswerPresenterImpl(this);
        ll_loading= (LinearLayout) findViewById(R.id.ll_loading);
        scroll_view= (ObservableScrollView) findViewById(R.id.scroll_view);
        scroll_view.setScrollViewListener(this);

        mSwipeRefreshWidget = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        spreadTextView = (StretchyTextView) findViewById(R.id.spread_textview);
        spreadTextView.setMaxLineCount(3);
        spreadTextView.setContent("近些年来，越来越多的行业开始和互联网结合，诞生了越来越多的互联网创业公司。" +
                "互联网创业公司需要面对许多的不确定因素。如果你和你的小伙伴们够幸运，你们的公司可能会在几个星期之内让用户数、商品数" +
                "、订单量增长几十倍上百倍。一次促销可能会带来平时几十倍的访问流量，" +
                "一次秒杀活动可能会吸引平时数百倍的访问用户。这对公司自然是极大的好事，说明产品得到认可，公司未来前景美妙。");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new FullyLinearLayoutManager(this, FullyLinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AnswerIndexAdapter();

        mRecyclerView.setAdapter(mAdapter);
        onRefresh();
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_answer_index;
    }

    @Override
    public void showError() {
        if (mSwipeRefreshWidget.isRefreshing()) {
            mSwipeRefreshWidget.setRefreshing(false);
            FrameManager.getInstance().toastPrompt("刷新数据失败");
        } else {
            FrameManager.getInstance().toastPrompt("加载数据失败");
        }
    }

    @Override
    public void refreshView(List<Answer> entities) {
        mAdapter.bind(entities);
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void loadMoreView(List<Answer> entities) {
        mAdapter.appendToList(entities);
        mAdapter.notifyDataSetChanged();
        ll_loading.setVisibility(View.GONE);

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage=1;
                mSwipeRefreshWidget.setRefreshing(true);
                mPresenter.refresh(currentPage,fid);
            }
        }, 500);
    }

    @Override
    public void onScrollChangedToBottom() {
        ll_loading.setVisibility(View.VISIBLE);
        currentPage++;
        mPresenter.loadMore(currentPage,fid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_answer, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "梁铖城" + " " + "wwww.baidu.com" + getString(R.string.share_tail));
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
                break;

            case R.id.action_use_browser:
                startActivity(new Intent(AnswerIndexActivity.this, CommentsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sc:
                startActivity(new Intent(AnswerIndexActivity.this, CommentsActivity.class));
                break;
        }
    }
}
