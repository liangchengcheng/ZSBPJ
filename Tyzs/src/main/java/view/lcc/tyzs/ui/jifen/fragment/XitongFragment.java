package view.lcc.tyzs.ui.jifen.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.JifenListAdapter;
import view.lcc.tyzs.adapter.WeifukuanAdapter;
import view.lcc.tyzs.bean.InterGrationBean;
import view.lcc.tyzs.bean.OrderInfoData;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.JifenListPresenter;
import view.lcc.tyzs.mvp.presenter.impl.JifenListPresenterImpl;
import view.lcc.tyzs.mvp.view.JifenListView;
import view.lcc.tyzs.utils.ErrorLogUtils;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.utils.TimeUtils;
import view.lcc.tyzs.view.LoadingLayout;
import view.lcc.tyzs.view.OnRecycleViewScrollListener;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 07:09
 * Description:  |系统积分
 */
public class XitongFragment extends Fragment implements JifenListView, SwipeRefreshLayout.OnRefreshListener
        , JifenListAdapter.OnItemClickListener {

    private LoadingLayout loading_layout;
    private JifenListAdapter mAdapter;
    private JifenListPresenter mPresenter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;

    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;

    private static final String TYPE = "系统积分";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weifukuan_fragment, null);
        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        mPresenter = new JifenListPresenterImpl(this);
        initRefreshView(view);
        initRecycleView(view);
        mPresenter.jifenList(currentPage + "", TYPE);
        return view;
    }

    private void initRefreshView(View view) {
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    private void initRecycleView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new JifenListAdapter();
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
                    mPresenter.loadMore(currentPage + "", TYPE);
                }
            }
        });
    }

    @Override
    public void JifenListLoading() {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void JifenListSuccess(String msg) {

    }

    @Override
    public void JifenListFail(String msg) {
        loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
        if (msg.equals("116")){
            loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
        }else {
            String message = ErrorLogUtils.SystemError(msg);
            loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
            if (!TextUtils.isEmpty(message)){
                Frame.getInstance().toastPrompt(message);
            }
        }
    }

    @Override
    public void NetWorkErr(String msg) {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
    }

    @Override
    public void refreshOrLoadFail(String msg) {
        if (mSwipeRefreshWidget.isRefreshing()) {
            mSwipeRefreshWidget.setRefreshing(false);
            loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
        } else {
            if (msg.equals("116")){
                mAdapter.setHasMoreDataAndFooter(false, false);
                Frame.getInstance().toastPrompt("没有更多数据...");
            }else {
                String s = ErrorLogUtils.SystemError(msg);
                if (TextUtils.isEmpty(s)){
                    Frame.getInstance().toastPrompt(s);
                }else {
                    Frame.getInstance().toastPrompt("加载失败，请稍后再试");
                }

            }
        }
    }

    @Override
    public void refreshDataSuccess(String result) {
        try {
            String data = result.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
            JSONObject jsonObject = new JSONObject(data);
            String resultJson = jsonObject.getString("resultjson");
            List<InterGrationBean> dataList = GsonUtils.fromJsonArray(resultJson, InterGrationBean.class);
            if (dataList != null && dataList.size() > 0) {
                mAdapter.bind(dataList);
                loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
            } else {
                loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
            }
            mSwipeRefreshWidget.setRefreshing(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadMoreWeekDataSuccess(String result) {
        try {
            String data = result.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
            JSONObject jsonObject = new JSONObject(data);
            String resultJson = jsonObject.getString("resultjson");
            final List<InterGrationBean> entities = GsonUtils.fromJsonArray(resultJson, InterGrationBean.class);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = 1;
                mSwipeRefreshWidget.setRefreshing(true);
                mPresenter.refresh(currentPage + "", TYPE);
            }
        }, 500);
    }

    @Override
    public void onItemClick(InterGrationBean data) {

    }
}
