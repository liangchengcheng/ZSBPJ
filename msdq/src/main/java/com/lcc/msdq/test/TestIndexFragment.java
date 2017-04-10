package com.lcc.msdq.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcc.adapter.TestAdapter;
import com.lcc.entity.TabEntity;
import com.lcc.entity.TestEntity;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.R;
import com.lcc.msdq.area.LoginDialogFragment;
import com.lcc.msdq.description.other.OtherUserProfileActivity;
import com.lcc.msdq.test.answer.AnswerIndexActivity;
import com.lcc.msdq.test.choice.ChoiceA_Activity;
import com.lcc.mvp.presenter.TestPresenter;
import com.lcc.mvp.presenter.impl.TestPresenterImpl;
import com.lcc.mvp.view.TestView;
import com.lcc.utils.SharePreferenceUtil;
import com.lcc.view.loadview.LoadingLayout;
import com.lcc.view.tab.CommonTabLayout;
import com.lcc.view.tab.adapter.ShopAdapter;
import com.lcc.view.tab.fragment.FragmentCategory;
import com.lcc.view.tab.fragment.FragmentFloor;
import com.lcc.view.tab.fragment.FragmentSort;
import com.lcc.view.tab.listener.CustomTabEntity;
import com.lcc.view.tab.listener.OnTabSelectListener;

import org.net.sunger.widget.DropDownLayout;
import org.net.sunger.widget.MenuLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.TimeUtils;
import zsbpj.lccpj.view.recyclerview.S_RefreshAndLoadFragment;

public class TestIndexFragment extends S_RefreshAndLoadFragment implements
        TestAdapter.OnImageClickListener, TestAdapter.OnItemClickListener, TestView, View.OnClickListener {
    private LoadingLayout loading_layout;
    private String start_time = "全部时间";
    private String end_time = "全部时间";
    private String options = "全部题型";
    private String orders = "";
    private TestAdapter mAdapter;
    private TestPresenter mPresenter;

    //添加了新的筛选的控件
    private CommonTabLayout tabs;
    private String[] mTitles = {"选择时间", "选择类型", "智能排序"};
    private int[] mIconUnselectIds = {
            R.drawable.tab_floor_unselected, R.drawable.tab_category_unseleted,
            R.drawable.tab_sort_unseleted};
    private int[] mIconSelectIds = {
            R.drawable.tab_floor_selected, R.drawable.tab_category_seleted,
            R.drawable.tab_sort_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private DropDownLayout dropDownLayout;
    private MenuLayout menuLayout;

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
        mPresenter.getData(currentPage, options, start_time, end_time, orders);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, null);
        menuLayout = (MenuLayout) view.findViewById(R.id.menuLayout);
        dropDownLayout = (DropDownLayout) view.findViewById(R.id.dropdown);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentFloor(TestIndexFragment.this));
        fragments.add(new FragmentCategory(TestIndexFragment.this));
        fragments.add(new FragmentSort(TestIndexFragment.this));
        menuLayout.setFragmentManager(getActivity().getSupportFragmentManager());
        menuLayout.bindFragments(fragments);
        tabs = (CommonTabLayout) view.findViewById(R.id.tabs);
        updateTabData();

        tabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                dropDownLayout.showMenuAt(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (menuLayout.isShow()) {
                    dropDownLayout.closeMenu();
                } else {
                    dropDownLayout.showMenuAt(position);
                }

            }
        });

        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        return view;
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.loadMore(getCurrentPage(), options, start_time, end_time, orders);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.test_fragment;
    }

    @Override
    public void onRefreshData() {
        currentPage = 1;
        mPresenter.refresh(currentPage, options, start_time, end_time, orders);
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
            case R.id.iv_add:
                String user_name = DataManager.getUserName();
                if (TextUtils.isEmpty(user_name)) {
                    LoginDialogFragment dialog = new LoginDialogFragment();
                    dialog.show(getActivity().getFragmentManager(), "loginDialog");
                    return;
                }

                final String zy = DataManager.getZY();
                if (TextUtils.isEmpty(zy)) {
                    FrameManager.getInstance().toastPrompt("请选选择一个要添加的职业类型");
                    startActivity(new Intent(getActivity(), ChoiceA_Activity.class));
                } else {
                    final String q = zy.substring(zy.length() - 32, zy.length());
                    final String z = zy.substring(0, zy.length() - 33);
                    new AlertDialog.Builder(getActivity())
                            .setTitle("添加新问题")
                            .setMessage("你是" + z + "是否重新选择添加别的职业的问题？")
                            .setPositiveButton("直接添加", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TestAddActivity.startTestAddActivity(getActivity(), q);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("其他职业", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getActivity(), ChoiceA_Activity.class));
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }

                break;
        }
    }

    @Override
    public void onImageClick(String user_phone) {
        OtherUserProfileActivity.starOtherUserProfileActivity(user_phone, getActivity());
    }

    private String getOrders(String text) {
        if (text.equals("综合排序")) {
            return "";
        } else if (text.equals("最多收藏")) {
            return "fav";
        } else if (text.equals("最新发布")) {
            return "time";
        } else {
            return "";
        }
    }

    @Override
    public void checkToken() {
        DataManager.deleteAllUser();
        SharePreferenceUtil.setUserTk("");
        FrameManager.getInstance().toastPrompt("身份失效请重现登录");
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getActivity().getFragmentManager(), "loginDialog");
    }

    private void updateTabData() {
        mTabEntities.clear();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabs.setTabData(mTabEntities);
    }

    public void onFilter(int type, String tag) {
        dropDownLayout.closeMenu();
        switch (type) {
            case 0:
                mTitles[0] = tag;
                start_time = TimeUtils.getStartTime(tag);
                end_time = TimeUtils.getEndTime();
                currentPage = 1;
                mPresenter.getData(currentPage, options, start_time, end_time, orders);
                break;
            case 1:
                mTitles[1] = tag;
                options = tag;
                currentPage = 1;
                mPresenter.getData(currentPage, options, start_time, end_time, orders);
                break;
            case 2:
                mTitles[2] = tag;
                orders = getOrders(tag);
                currentPage = 1;
                mPresenter.getData(currentPage, options, start_time, end_time, orders);
                break;
        }
        updateTabData();
    }
}
