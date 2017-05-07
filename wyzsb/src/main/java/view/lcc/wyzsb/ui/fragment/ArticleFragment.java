package view.lcc.wyzsb.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import view.lcc.wyzsb.R;
import view.lcc.wyzsb.adapter.ArticleAdapter;
import view.lcc.wyzsb.adapter.VideoAdapter;
import view.lcc.wyzsb.base.BaseFragment;
import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.bean.model.FilterData;
import view.lcc.wyzsb.bean.model.FilterEntity;
import view.lcc.wyzsb.bean.model.FilterTwoEntity;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.frame.OnRecycleViewScrollListener;
import view.lcc.wyzsb.mvp.param.ArticleParams;
import view.lcc.wyzsb.mvp.param.HomeParams;
import view.lcc.wyzsb.mvp.param.VideoParams;
import view.lcc.wyzsb.mvp.presenter.ArticlePresenter;
import view.lcc.wyzsb.mvp.presenter.VideoPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.ArticlePresenterImpl;
import view.lcc.wyzsb.mvp.presenter.impl.VideoPresenterImpl;
import view.lcc.wyzsb.mvp.view.ArticleView;
import view.lcc.wyzsb.mvp.view.VideoView;
import view.lcc.wyzsb.ui.activity.article.ArticleDetailsActivity;
import view.lcc.wyzsb.utils.ModelUtil;
import view.lcc.wyzsb.utils.TimeUtils;
import view.lcc.wyzsb.view.LoadingLayout;
import view.lcc.wyzsb.view.home.FilterView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年04月23日11:02:32
 * Description:  |文字资料
 */
public class ArticleFragment extends Fragment implements ArticleView,SwipeRefreshLayout.OnRefreshListener,
        ArticleAdapter.OnFavClickListener,ArticleAdapter.OnItemClickListener,View.OnClickListener{

    private LoadingLayout loading_layout;
    private ArticleAdapter mAdapter;
    private ArticlePresenter mPresenter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;

    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;
    private FilterView realFilterView;

    private String a_l = "";
    private String a_c = "";
    private String a_type = "";

    private ArticleParams params;

    // 筛选数据
    private FilterData filterData;
    // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)
    private int filterPosition = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment ,null);
        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        mPresenter = new ArticlePresenterImpl(this);
        initRefreshView(view);
        initRecycleView(view);
        setFilerView(view);

        params = new ArticleParams();
        params.setPage(currentPage);
        params.setA_c(a_c);
        params.setA_l(a_l);
        params.setA_type(a_type);
        mPresenter.getData(params);
        return view;
    }

    private void initRefreshView(View view) {
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    private void initRecycleView(View view) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ArticleAdapter();
        mAdapter.setOnItemClickListener(this);

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

                    params = new ArticleParams();
                    params.setPage(currentPage);
                    params.setA_c(a_c);
                    params.setA_l(a_l);
                    params.setA_type(a_type);
                    mPresenter.loadMore(params);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = 1;
                mSwipeRefreshWidget.setRefreshing(true);

                params = new ArticleParams();
                params.setPage(currentPage);
                params.setA_c(a_c);
                params.setA_l(a_l);
                params.setA_type(a_type);
                mPresenter.refresh(params);
            }
        }, 500);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onOnFavClick(Article data) {

    }

    @Override
    public void onItemClick(Article data) {
        ArticleDetailsActivity.startArticleDetails(getActivity(),data);
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
        if (mSwipeRefreshWidget.isRefreshing()) {
            mSwipeRefreshWidget.setRefreshing(false);
            loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
        } else {
            Frame.getInstance().toastPrompt(msg);
        }
    }

    @Override
    public void refreshDataSuccess(List<Article> entities) {
        if (entities != null && entities.size() > 0) {
            mAdapter.bind(entities);
        }
        mSwipeRefreshWidget.setRefreshing(false);
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }

    @Override
    public void loadMoreWeekDataSuccess(final List<Article> entities) {
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
                    Frame.getInstance().toastPrompt("没有更多数据...");
                } else {
                    mAdapter.appendToList(entities);
                    mAdapter.setHasMoreDataAndFooter(true, false);
                }
                mAdapter.notifyDataSetChanged();
            }
        }, delay);
    }

    private void setFilerView(View view){
        // 筛选数据
        realFilterView = (FilterView) view.findViewById(R.id.real_filterView);
        filterData = new FilterData();
        // 第1行筛选数据
        filterData.setCategory(ModelUtil.getCategoryData());
        // 第2行筛选数据
        filterData.setSorts(ModelUtil.getSortData());
        // 第3行筛选数据
        filterData.setFilters(ModelUtil.getFilterData());
        // 设置真FilterView数据
        realFilterView.setFilterData(getActivity(), filterData);
        realFilterView.setVisibility(View.VISIBLE);
        // (真正的)筛选视图点击
        realFilterView.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                realFilterView.show(position);
            }
        });
        // 分类Item点击
        realFilterView.setOnItemCategoryClickListener(new FilterView.OnItemCategoryClickListener() {
            @Override
            public void onItemCategoryClick(FilterTwoEntity leftEntity, FilterEntity rightEntity) {
                currentPage = 1;
                if (rightEntity.getKey().equals("全部")){
                    a_type = "";
                }else {
                    a_type = rightEntity.getKey();
                }
                params = new ArticleParams();
                params.setPage(currentPage);
                params.setA_c(a_c);
                params.setA_l(a_l);
                params.setA_type(a_type);
                mPresenter.getData(params);
            }
        });
        // 排序Item点击
        realFilterView.setOnItemSortClickListener(new FilterView.OnItemSortClickListener() {
            @Override
            public void onItemSortClick(FilterEntity entity) {
                currentPage = 1;
                if (entity.getKey().equals("全部")){
                    a_c = "";
                }else {
                    a_c = entity.getKey();
                }
                params = new ArticleParams();
                params.setPage(currentPage);
                params.setA_c(a_c);
                params.setA_l(a_l);
                params.setA_type(a_type);
                mPresenter.getData(params);
            }
        });
        // 筛选Item点击
        realFilterView.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick(FilterEntity entity) {
                currentPage = 1;
                if (entity.getKey().equals("全部")){
                    a_l = "";
                }else {
                    a_l = entity.getKey();
                }
                params = new ArticleParams();
                params.setPage(currentPage);
                params.setA_c(a_c);
                params.setA_l(a_l);
                params.setA_type(a_type);
                mPresenter.getData(params);
            }
        });
    }
}
