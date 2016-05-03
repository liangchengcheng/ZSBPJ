package com.lcc.activity.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lcc.activity.R;
import com.lcc.adapter.TravelingAdapter;
import com.lcc.entity.ChannelEntity;
import com.lcc.entity.FilterData;
import com.lcc.entity.FilterEntity;
import com.lcc.entity.FilterTwoEntity;
import com.lcc.entity.OperationEntity;
import com.lcc.entity.TravelingEntity;
import com.lcc.utils.ColorUtil;
import com.lcc.utils.ModelUtil;
import com.lcc.view.SmoothListView.SmoothListView;
import com.lcc.view.index.FilterView;
import com.lcc.view.index.HeaderAdViewView;
import com.lcc.view.index.HeaderChannelViewView;
import com.lcc.view.index.HeaderDividerViewView;
import com.lcc.view.index.HeaderFilterViewView;
import com.lcc.view.index.HeaderOperationViewView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import zsbpj.lccpj.utils.DensityUtil;

// TODO: 16/5/3 此处忽略摧毁事件
public class IndexFragment  extends Fragment implements SmoothListView.ISmoothListViewListener{

    @Bind(R.id.listView)
    SmoothListView smoothListView;
    @Bind(R.id.fv_top_filter)
    FilterView fvTopFilter;
    @Bind(R.id.rl_bar)
    RelativeLayout rlBar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.view_title_bg)
    View viewTitleBg;
    @Bind(R.id.view_action_more_bg)
    View viewActionMoreBg;
    @Bind(R.id.fl_action_more)
    FrameLayout flActionMore;

    private Context mContext;
    private Activity mActivity;
    //屏幕高度
    private int mScreenHeight;
    // 广告数据
    private List<String> adList = new ArrayList<>();
    // 频道数据
    private List<ChannelEntity> channelList = new ArrayList<>();
    // 运营数据
    private List<OperationEntity> operationList = new ArrayList<>();
    // ListView数据
    private List<TravelingEntity> travelingList = new ArrayList<>();
    // 广告视图
    private HeaderAdViewView listViewAdHeaderView;
    // 频道视图
    private HeaderChannelViewView headerChannelView;
    // 运营视图
    private HeaderOperationViewView headerOperationViewView;
    // 分割线占位图
    private HeaderDividerViewView headerDividerViewView;
    // 分类筛选视图
    private HeaderFilterViewView headerFilterViewView;
    // 筛选数据
    private FilterData filterData;
    private TravelingAdapter mAdapter;
    // 是否吸附在顶部
    private boolean isStickyTop = false;
    // 没有吸附的前提下，是否在滑动
    private boolean isSmooth = false;
    // 标题栏的高度
    private int titleViewHeight = 50;
    private int filterPosition = -1;
    // 广告视图的高度
    private int adViewHeight = 180;
    // 广告视图距离顶部的距离
    private int adViewTopSpace;
    // 筛选视图的位置
    private int filterViewPosition = 4;
    // 筛选视图距离顶部的距离
    private int filterViewTopSpace;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.index_fragment,null);
        ButterKnife.bind(this,view);
        initData();
        initView();
        initListener();
        return view;
    }

    private void initView() {
        fvTopFilter.setVisibility(View.INVISIBLE);

        // 设置筛选数据
        fvTopFilter.setFilterData(mActivity, filterData);

        // 设置广告数据
        listViewAdHeaderView = new HeaderAdViewView(getActivity());
        listViewAdHeaderView.fillView(adList, smoothListView);

        // 设置频道数据
        headerChannelView = new HeaderChannelViewView(getActivity());
        headerChannelView.fillView(channelList, smoothListView);

        // 设置运营数据
        headerOperationViewView = new HeaderOperationViewView(getActivity());
        headerOperationViewView.fillView(operationList, smoothListView);

        // 设置分割线
        headerDividerViewView = new HeaderDividerViewView(getActivity());
        headerDividerViewView.fillView("", smoothListView);

        // 设置筛选数据
        headerFilterViewView = new HeaderFilterViewView(getActivity());
        headerFilterViewView.fillView(new Object(), smoothListView);

        // 设置ListView数据
        mAdapter = new TravelingAdapter(getActivity(), travelingList);
        smoothListView.setAdapter(mAdapter);

        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;
    }

    private void initData() {
        mContext = getActivity();
        mActivity = getActivity();
        mScreenHeight = DensityUtil.getWindowHeight(getActivity());

        // 筛选数据
        filterData = new FilterData();
        filterData.setCategory(ModelUtil.getCategoryData());
        filterData.setSorts(ModelUtil.getSortData());
        filterData.setFilters(ModelUtil.getFilterData());

        // 广告数据
        adList = ModelUtil.getAdData();

        // 频道数据
        channelList = ModelUtil.getChannelData();

        // 运营数据
        operationList = ModelUtil.getOperationData();

        // ListView数据
        travelingList = ModelUtil.getTravelingData();
    }

    private void initListener() {
        smoothListView.setRefreshEnable(true);
        smoothListView.setLoadMoreEnable(true);
        smoothListView.setSmoothListViewListener(this);
        smoothListView.setOnScrollListener(new SmoothListView.OnSmoothScrollListener() {
            @Override
            public void onSmoothScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View adView = smoothListView.getChildAt(1 - firstVisibleItem);
                if (adView != null) {
                    adViewTopSpace = DensityUtil.px2dip(mContext, adView.getTop());
                    adViewHeight = DensityUtil.px2dip(mContext, adView.getHeight());
                }

                View filterView = smoothListView.getChildAt(filterViewPosition - firstVisibleItem);
                if (filterView != null) {
                    filterViewTopSpace = DensityUtil.px2dip(mContext, filterView.getTop());
                }

                // 处理筛选是否吸附在顶部
                if (filterViewTopSpace > titleViewHeight) {
                    // 没有吸附在顶部
                    isStickyTop = false;
                    fvTopFilter.setVisibility(View.INVISIBLE);
                } else {
                    // 吸附在顶部
                    isStickyTop = true;
                    fvTopFilter.setVisibility(View.VISIBLE);
                }

                if (isSmooth && isStickyTop) {
                    isSmooth = false;
                    fvTopFilter.showFilterLayout(filterPosition);
                }

                fvTopFilter.setStickyTop(isStickyTop);

                // 处理标题栏颜色渐变
                handleTitleBarColorEvaluate();
            }
        });

        // (真正的)筛选视图点击
        headerFilterViewView.setOnFilterClickListener(new HeaderFilterViewView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                if (!isStickyTop) {
                    filterPosition = position;
                    isSmooth = true;
                    smoothListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, titleViewHeight));
                }
            }
        });

        // (ListView头部展示的)筛选视图点击
        fvTopFilter.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                if (isStickyTop) {
                    filterPosition = position;
                    fvTopFilter.showFilterLayout(position);
                }
            }
        });

        // 关于
        flActionMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(mActivity, AboutActivity.class));
            }
        });

        // 分类Item点击
        fvTopFilter.setOnItemCategoryClickListener(new FilterView.OnItemCategoryClickListener() {
            @Override
            public void onItemCategoryClick(FilterTwoEntity entity) {
                List<TravelingEntity> list = ModelUtil.getCategoryTravelingData(entity);
                if (list.size() < TravelingAdapter.ONE_SCREEN_COUNT) {
                    smoothListView.setLoadMoreEnable(false);
                }
                if (list.size() == 0) {
                    int height = mScreenHeight - DensityUtil.dip2px(mContext, 95);
                    mAdapter.setData(ModelUtil.getNoDataEntity(height));
                } else {
                    mAdapter.setData(list);
                }
            }
        });

        // 排序Item点击
        fvTopFilter.setOnItemSortClickListener(new FilterView.OnItemSortClickListener() {
            @Override
            public void onItemSortClick(FilterEntity entity) {
                mAdapter.setData(ModelUtil.getSortTravelingData(entity));
            }
        });

        // 筛选Item点击
        fvTopFilter.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick(FilterEntity entity) {
                List<TravelingEntity> list = ModelUtil.getFilterTravelingData(entity);
                if (list.size() < TravelingAdapter.ONE_SCREEN_COUNT) {
                    smoothListView.setLoadMoreEnable(false);
                }
                if (list.size() == 0) {
                    int height = mScreenHeight - DensityUtil.dip2px(mContext, 95);
                    mAdapter.setData(ModelUtil.getNoDataEntity(height));
                } else {
                    mAdapter.setData(list);
                }
            }
        });
    }

    /**
     * 处理标题栏颜色渐变
     */
    private void handleTitleBarColorEvaluate() {
        float fraction;
        if (adViewTopSpace > 0) {
            fraction = adViewTopSpace * 1f / 60;
            rlBar.setAlpha(1.0f - fraction);
            return ;
        }
        rlBar.setAlpha(1.0f);
        float space = Math.abs(adViewTopSpace) * 1f;
        fraction = space / (adViewHeight - titleViewHeight);
        if (fraction > 1.0f) {
            fraction = 1.0f;
        }
        viewTitleBg.setAlpha(1.0f - fraction);
        viewActionMoreBg.setAlpha(1.0f - fraction);
        rlBar.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(mContext, fraction, R.color.transparent, R.color.orange));
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopRefresh();
                smoothListView.setRefreshTime("刚刚");
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopLoadMore();
            }
        }, 2000);
    }
}
