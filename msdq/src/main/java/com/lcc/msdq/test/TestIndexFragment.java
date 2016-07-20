package com.lcc.msdq.test;

import android.content.Intent;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lcc.adapter.CompanyAdapter;
import com.lcc.adapter.MyAdapter;
import com.lcc.adapter.SubAdapter;
import com.lcc.adapter.TestAdapter;
import com.lcc.base.BaseFragment;
import com.lcc.entity.CompanyEntity;
import com.lcc.entity.TestEntity;
import com.lcc.msdq.R;
import com.lcc.msdq.description.other.OtherUserProfileActivity;
import com.lcc.msdq.test.answer.AnswerIndexActivity;
import com.lcc.mvp.presenter.TestPresenter;
import com.lcc.mvp.presenter.impl.TestPresenterImpl;
import com.lcc.mvp.view.TestView;
import com.lcc.utils.SharePreferenceUtil;
import com.lcc.view.loadview.LoadingLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.LogUtils;
import zsbpj.lccpj.utils.TimeUtils;
import zsbpj.lccpj.view.recyclerview.S_RefreshAndLoadFragment;

public class TestIndexFragment extends S_RefreshAndLoadFragment implements
        PopupWindow.OnDismissListener, TestAdapter.OnImageClickListener,
        TestAdapter.OnItemClickListener, TestView, View.OnClickListener {

    private LinearLayout lv1_layout;
    private ListView lv1, lv2;
    private TextView quyu, huxing, jiage;
    private ImageView icon1, icon2, icon3;
    private LoadingLayout loading_layout;
    private View ll_layout;

    private int screenWidth;
    private int screenHeight;
    private int idx;
    private String start_time = "全部时间";
    private String end_time = "全部时间";
    private String options = "全部题型";

    private MyAdapter madapter;
    private TestAdapter mAdapter;
    private TestPresenter mPresenter;

    public static Fragment newInstance() {
        return new TestIndexFragment();
    }

    @Override
    protected void onFragmentCreate() {
        currentPage = 1;
        super.onFragmentCreate();
        mPresenter = new TestPresenterImpl(this);
        RecyclerView mRecyclerView = getRecyclerView();
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new TestAdapter(getActivity());
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnImageClickListener(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter.getData(currentPage, options, start_time, end_time);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_fragment, null);
        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        ll_layout = view.findViewById(R.id.ll_layout);

        view.findViewById(R.id.ll_quyu).setOnClickListener(this);
        view.findViewById(R.id.ll_jiage).setOnClickListener(this);
        view.findViewById(R.id.ll_huxing).setOnClickListener(this);

        quyu = (TextView) view.findViewById(R.id.quyu);
        huxing = (TextView) view.findViewById(R.id.huxing);
        jiage = (TextView) view.findViewById(R.id.jiage);
        icon1 = (ImageView) view.findViewById(R.id.icon1);
        icon2 = (ImageView) view.findViewById(R.id.icon2);
        icon3 = (ImageView) view.findViewById(R.id.icon3);
        initScreenWidth();
        return view;
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.loadMore(getCurrentPage(), options, start_time, end_time);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.test_fragment;
    }

    @Override
    public void onRefreshData() {
        currentPage = 1;
        mPresenter.refresh(currentPage, options, start_time, end_time);
    }

    public void showPopupWindow(View anchor, int flag) {
        final PopupWindow popupWindow = new PopupWindow(getActivity());
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.windows_popupwindow, null);
        lv1 = (ListView) contentView.findViewById(R.id.lv1);
        lv2 = (ListView) contentView.findViewById(R.id.lv2);
        lv1_layout = (LinearLayout) contentView.findViewById(R.id.lv_layout);
        switch (flag) {
            case 1:
                madapter = new MyAdapter(getActivity(),
                        initArrayData(R.array.tiaojian1));
                break;
            case 2:
                madapter = new MyAdapter(getActivity(),
                        initArrayData(R.array.tiaojian2));
                break;
            case 3:
                madapter = new MyAdapter(getActivity(),
                        initArrayData(R.array.tiaojian3));
                break;
        }
        lv1.setAdapter(madapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (parent.getAdapter() instanceof MyAdapter) {
                    madapter.setSelectItem(position);
                    madapter.notifyDataSetChanged();
                    lv2.setVisibility(View.INVISIBLE);
                    if (lv2.getVisibility() == View.INVISIBLE) {
                        lv2.setVisibility(View.VISIBLE);
                        lv1_layout.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                        String name = (String) parent.getAdapter().getItem(
                                position);
                        setHeadText(idx, name);
                        popupWindow.dismiss();
                    }
                }
            }
        });
        popupWindow.setOnDismissListener(this);
        popupWindow.setWidth(screenWidth);
        popupWindow.setHeight(screenHeight);
        popupWindow.setContentView(contentView);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.showAsDropDown(anchor);
    }

    private void setHeadText(int idx, String text) {
        switch (idx) {
            case 1:
                quyu.setText(text);
                start_time = TimeUtils.getStartTime(text);
                end_time = TimeUtils.getEndTime();
                currentPage = 1;
                mPresenter.getData(currentPage, options, start_time, end_time);
                break;

            case 2:
                jiage.setText(text);
                options = text;
                currentPage = 1;
                mPresenter.getData(currentPage, options, start_time, end_time);
                break;

            case 3:
                huxing.setText(text);
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;
    }

    private List<String> initArrayData(int id) {
        List<String> list = new ArrayList<String>();
        String[] array = this.getResources().getStringArray(id);
        List<String> results = Arrays.asList(array);
        for (String result : results) {
            list.add(result);
        }
        return list;
    }

    @Override
    public void onDismiss() {
        icon1.setImageResource(R.drawable.icon_435);
        icon2.setImageResource(R.drawable.icon_435);
        icon3.setImageResource(R.drawable.icon_435);
    }

    @Override
    public void OnItemClick(TestEntity entity) {
        AnswerIndexActivity.startAnswerIndexActivity(entity, getActivity());
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
        if (getSwipeRefreshWidget().isRefreshing()) {
            getSwipeRefreshWidget().setRefreshing(false);
            loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
        } else {
            FrameManager.getInstance().toastPrompt(msg);
        }
    }

    @Override
    public void refreshView(List<TestEntity> entities) {
        showRefreshData(entities);
        if (entities == null || entities.size() == 0) {
            getDataEmpty();
        } else {
            loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        }
    }

    @Override
    public void loadMoreView(List<TestEntity> entities) {
        showMoreData(entities);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_quyu:
                idx = 1;
                icon1.setImageResource(R.drawable.icon_43343434);
                showPopupWindow(ll_layout, 1);
                break;

            case R.id.ll_jiage:
                idx = 2;
                icon2.setImageResource(R.drawable.icon_43343434);
                showPopupWindow(ll_layout, 2);
                break;

            case R.id.ll_huxing:
                idx = 3;
                icon3.setImageResource(R.drawable.icon_43343434);
                showPopupWindow(ll_layout, 3);
                break;
        }
    }

    @Override
    public void onImageClick(String user_phone) {
        OtherUserProfileActivity.starOtherUserProfileActivity(user_phone,getActivity());
    }
}
