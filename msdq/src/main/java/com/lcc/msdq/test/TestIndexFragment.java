package com.lcc.msdq.test;


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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.view.recyclerview.S_RefreshAndLoadFragment;

public class TestIndexFragment extends S_RefreshAndLoadFragment implements
        PopupWindow.OnDismissListener,
        TestAdapter.OnItemClickListener {

    private LinearLayout ll_quyu, ll_jiage, ll_huxing, lv1_layout;
    private ListView lv1, lv2;
    private TextView quyu, huxing, jiage;
    private ImageView icon1, icon2, icon3;
    private int screenWidth;
    private int screenHeight;
    private int idx;
    private MyAdapter madapter;
    private View ll_layout;
    static final int ACTION_NONE = 0;
    private TestAdapter mAdapter;
    private String tj1,tj2,tj3;

    public static Fragment newInstance() {
        Fragment fragment = new TestIndexFragment();
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        View view = getView();
        RecyclerView mRecyclerView = getRecyclerView();
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new TestAdapter(getActivity());
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        autoRefresh();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, null);

        ll_layout = view.findViewById(R.id.ll_layout);
        ll_quyu = (LinearLayout) view.findViewById(R.id.ll_quyu);
        ll_quyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idx = 1;
                icon1.setImageResource(R.drawable.icon_43343434);
                showPopupWindow(ll_layout, 1);
            }
        });

        ll_jiage = (LinearLayout) view.findViewById(R.id.ll_jiage);
        ll_jiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idx = 2;
                icon2.setImageResource(R.drawable.icon_43343434);
                showPopupWindow(ll_layout, 2);
            }
        });
        ll_huxing = (LinearLayout) view.findViewById(R.id.ll_huxing);
        ll_huxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idx = 3;
                icon3.setImageResource(R.drawable.icon_43343434);
                showPopupWindow(ll_layout, 3);
            }
        });
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
        showMoreData(getData());
    }

    @Override
    protected int getLayoutView() {
        return R.layout.test_fragment;
    }

    @Override
    public void onRefreshData() {
        showRefreshData(getData());
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
                        switch (idx) {
                            case 1:
                                lv1_layout.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                                break;
                            case 2:
                                lv1_layout.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;

                                break;
                            case 3:
                                lv1_layout.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                                break;
                        }
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
                tj1=text;
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                jiage.setText(text);
                tj2=text;
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                huxing.setText(text);
                tj3=text;
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
            FrameManager.getInstance().toastPrompt("刷新数据失败");
        } else {
            FrameManager.getInstance().toastPrompt("加载数据失败");
            mAdapter.setHasFooter(false);
        }
    }

    private List<TestEntity> getData() {
        List<TestEntity> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestEntity zxTest = new TestEntity();
            zxTest.setDate("2016年03月23日14:26:29" + i);
            zxTest.setName("SD学院公布了最新的录取分数线");
            zxTest.setJj("浏览器根据请求的URL交给DNS域名解析，找到真实IP，向服务器发起请求服务器交给后台处理完成后返回数据，浏览器接收文件（HTML、JS、CSS、图象等）浏览器对加载到的资源（HTML、JS、CSS等）进行语法解析，建立相应的内部数据结构（如HTML的DOM）");
            data.add(zxTest);
        }
        return data;
    }
}