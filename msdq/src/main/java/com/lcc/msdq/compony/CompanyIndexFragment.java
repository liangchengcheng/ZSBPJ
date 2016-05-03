package com.lcc.msdq.compony;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lapism.searchview.adapter.SearchAdapter;
import com.lapism.searchview.adapter.SearchItem;
import com.lapism.searchview.history.SearchHistoryTable;
import com.lapism.searchview.view.SearchCodes;
import com.lapism.searchview.view.SearchView;
import com.lcc.adapter.CompanyAdapter;
import com.lcc.entity.CompanyEntity;
import com.lcc.msdq.R;

import java.util.ArrayList;
import java.util.List;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.view.recyclerview.S_RefreshAndLoadFragment;

public class CompanyIndexFragment extends S_RefreshAndLoadFragment implements
        SearchView.OnQueryTextListener, SearchView.SearchViewListener,
        SearchAdapter.OnItemClickListener, CompanyAdapter.OnItemClickListener {

    private SearchView mSearchView = null;
    private SearchHistoryTable mHistoryDatabase;
    private CompanyAdapter mAdapter;
    static final int ACTION_NONE = 0;

    public static Fragment newInstance() {
        Fragment fragment = new CompanyIndexFragment();
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        View view = getView();
        RecyclerView mRecyclerView = getRecyclerView();
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CompanyAdapter(getActivity());
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initSearchView(view);
        view.findViewById(R.id.iv_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.show(true);
            }
        });
        autoRefresh();
    }

    @Override
    protected void onFragmentLoadMore() {
        showMoreData(getData());
    }

    @Override
    protected int getLayoutView() {
        return R.layout.company_fragment;
    }

    @Override
    public void onRefreshData() {
        showRefreshData(getData());
    }

    private void initSearchView(View view) {
        mSearchView = (SearchView) view.findViewById(R.id.searchView);
        if (mSearchView == null) {
            return;
        }
        mSearchView.setVersion(SearchCodes.VERSION_MENU_ITEM);
        mSearchView.setStyle(SearchCodes.STYLE_MENU_ITEM_CLASSIC);
        mSearchView.setTheme(SearchCodes.THEME_LIGHT);
        mSearchView.setDivider(false);
        mSearchView.setHint("请输入你要查询的公司名称");
        mSearchView.setHintSize(getResources().getDimension(R.dimen.search_text_medium));
        mSearchView.setVoice(false);
        mSearchView.setAnimationDuration(300);
        mSearchView.setShadowColor(ContextCompat.getColor(getActivity(), R.color.search_shadow_layout));
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnSearchViewListener(this);

        mHistoryDatabase = new SearchHistoryTable(getActivity());
        mHistoryDatabase.addItem(new SearchItem("全部"));

        List<SearchItem> list = new ArrayList<>();
        SearchAdapter adapter = new SearchAdapter(getActivity(), list, mHistoryDatabase.getAllItems(),
                SearchCodes.THEME_LIGHT);
        adapter.setOnItemClickListener(this);
        mSearchView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
        String text = textView.getText().toString();
        loadDataFromServer(text);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        loadDataFromServer(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onSearchViewShown() {

    }

    @Override
    public void onSearchViewClosed() {

    }

    private void loadDataFromServer(String text) {
        mSearchView.hide(false);
        mHistoryDatabase.addItem(new SearchItem(text));
        if (TextUtils.isEmpty(text) || text.equals("全部")) {
            //加载全部的数据
        } else {
            //加载关键字的数据
            //loadListViewData(text);
        }
    }

    @Override
    public void OnItemClick(CompanyEntity entity) {
        startActivity(new Intent(getActivity(),CompanyContentActivity.class));
    }

    /**
     * 刷新数据
     */
    private void autoRefresh() {
        getSwipeRefreshWidget().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = STATE_REFRESH;
                getSwipeRefreshWidget().setRefreshing(true);
                showRefreshData(getData());
            }
        }, 500);
    }

    public void showError() {
        currentState = ACTION_NONE;
        if (getSwipeRefreshWidget().isRefreshing()) {
            getSwipeRefreshWidget().setRefreshing(false);
            FrameManager.getInstance().toastPrompt("刷新数据失败...");
        } else {
            FrameManager.getInstance().toastPrompt("加载数据失败...");
            mAdapter.setHasFooter(false);
        }
    }

    private List<CompanyEntity> getData() {
        List<CompanyEntity> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompanyEntity zxTest = new CompanyEntity();
            zxTest.setDate("03/23 14:26");
            zxTest.setName("百度科技网络有限公司");
            zxTest.setJj("这个是对这个公司的基本的简介这个是对这个公司的基本的简介这个是对这个公司的基本的简介这" +
                    "个是对这个公司的基本的简介这个是对这个公司的基本的简介");
            data.add(zxTest);
        }
        return data;
    }
}