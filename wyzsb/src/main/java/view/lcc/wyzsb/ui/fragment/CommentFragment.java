package view.lcc.wyzsb.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.adapter.CommentAdapter;
import view.lcc.wyzsb.bean.Comments;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.frame.OnRecycleViewScrollListener;
import view.lcc.wyzsb.mvp.param.SendComments;
import view.lcc.wyzsb.mvp.presenter.CommentsPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.CommentsPresenterImpl;
import view.lcc.wyzsb.mvp.view.CommentsView;
import view.lcc.wyzsb.utils.KeyboardUtils;
import view.lcc.wyzsb.utils.TimeUtils;
import view.lcc.wyzsb.view.LoadingLayout;
import view.lcc.wyzsb.view.SendCommentButton;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class CommentFragment extends Fragment implements CommentsView, CommentAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener ,SendCommentButton.OnSendClickListener{
    private CommentsPresenter presenter;
    private CommentAdapter commentsAdapter;
    private RecyclerView mRecyclerView;
    private LoadingLayout loading_layout;

    private EditText etComment;

    private Video video;

    public CommentFragment(Video video) {
        this.video = video;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_fragment, null);
        presenter = new CommentsPresenterImpl(this);
        etComment = (EditText) view.findViewById(R.id.etComment);
        btnSendComment = (SendCommentButton) view .findViewById(R.id.btnSendComment);
        btnSendComment.setOnSendClickListener(this);
        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        initRefreshView(view);
        initRecycleView(view);
        presenter.getData(1, video.getId());
        return view;
    }

    protected static final int DEF_DELAY = 1000;
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;
    private SwipeRefreshLayout mSwipeRefreshWidget;

    private void initRefreshView(View view) {
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    private void initRecycleView(final View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        commentsAdapter = new CommentAdapter();
        commentsAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(commentsAdapter);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                if (currentState == STATE_NORMAL) {
                    currentState = STATE_LOAD;
                    currentTime = TimeUtils.getCurrentTime();
                    commentsAdapter.setHasFooter(true);
                    mRecyclerView.scrollToPosition(commentsAdapter.getItemCount() - 1);
                    currentPage++;
                    presenter.loadMore(currentPage, video.getId());
                }
            }
        });
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
    public void refreshDataSuccess(List<Comments> entities) {
        if (entities != null && entities.size() > 0) {
            commentsAdapter.bind(entities);
            loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        } else {
            loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
        }
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void loadMoreWeekDataSuccess(final List<Comments> entities) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;
                if (entities.isEmpty()) {
                    commentsAdapter.setHasMoreDataAndFooter(false, false);
                    Frame.getInstance().toastPrompt("没有更多数据...");
                } else {
                    commentsAdapter.appendToList(entities);
                    commentsAdapter.setHasMoreDataAndFooter(true, false);
                }
                commentsAdapter.notifyDataSetChanged();
            }
        }, delay);
    }

    @Override
    public void rePlaying() {

    }

    @Override
    public void replaySuccess() {
        onRefresh();
        etComment.setText("");
        KeyboardUtils.hide(getActivity());
        showSnackbar(etComment,"提交成功");
        btnSendComment.setCurrentState(SendCommentButton.STATE_DONE);
    }

    @Override
    public void replayFail() {
        KeyboardUtils.hide(getActivity());
        showSnackbar(etComment,"提交失败");
    }

    @Override
    public void onItemClick(Comments data) {

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage = 1;
                mSwipeRefreshWidget.setRefreshing(true);
                presenter.refresh(currentPage, video.getId());
            }
        }, 500);
    }

    private SendCommentButton btnSendComment;

    private boolean validateComment() {
        if (TextUtils.isEmpty(etComment.getText())) {
            btnSendComment.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.shake_error));
            return false;
        }

        return true;
    }

    private SendComments sendComments;

    @Override
    public void onSendClickListener(View v) {
        // String user_name = DataManager.getUserName();
        // TODO: 2017/4/25 此处做用户没登录的校验
        if (validateComment()) {
            sendComments = new SendComments();
            sendComments.setContent(etComment.getText().toString().trim());
            presenter.sendComments(sendComments);
        }
    }

    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
    }
}
