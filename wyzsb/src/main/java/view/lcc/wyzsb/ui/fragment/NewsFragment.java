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
import view.lcc.wyzsb.adapter.NewsAdapter;
import view.lcc.wyzsb.base.BaseFragment;
import view.lcc.wyzsb.bean.News;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.frame.OnRecycleViewScrollListener;
import view.lcc.wyzsb.mvp.presenter.ArticlePresenter;
import view.lcc.wyzsb.mvp.presenter.NewsPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.ArticlePresenterImpl;
import view.lcc.wyzsb.mvp.presenter.impl.NewsPresenterImpl;
import view.lcc.wyzsb.mvp.view.ArticleView;
import view.lcc.wyzsb.mvp.view.NewsView;
import view.lcc.wyzsb.ui.activity.article.ArticleActivity;
import view.lcc.wyzsb.utils.TimeUtils;
import view.lcc.wyzsb.view.LoadingLayout;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class NewsFragment extends Fragment implements NewsView,SwipeRefreshLayout.OnRefreshListener,
        NewsAdapter.OnFavClickListener, NewsAdapter.OnItemClickListener,View.OnClickListener {

    private LoadingLayout loading_layout;
    private NewsAdapter mAdapter;
    private NewsPresenter mPresenter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;

    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment,null);
        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        mPresenter = new NewsPresenterImpl(this);
        initRefreshView(view);
        initRecycleView(view);
        mPresenter.getData(1,"");
        return  view;
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
        mAdapter = new NewsAdapter();
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
                    mPresenter.loadMore(currentPage,"");
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
                mPresenter.refresh(currentPage,"");
            }
        }, 500);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onOnFavClick(News data) {

    }

    @Override
    public void onItemClick(News data) {

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
    public void refreshView(List<News> entities) {
        if (entities != null && entities.size() > 0) {
            mAdapter.bind(entities);
        }
        mSwipeRefreshWidget.setRefreshing(false);
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }

    @Override
    public void loadMoreView(final List<News> entities) {
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
}
