package com.lcc.msdq.compony.answer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.lcc.base.BaseActivity;
import com.lcc.entity.Answer;
import com.lcc.entity.AnswerContent;
import com.lcc.entity.Article;
import com.lcc.entity.CompanyAnswer;
import com.lcc.entity.CompanyEntity;
import com.lcc.entity.CompanyTest;
import com.lcc.frame.Propertity;
import com.lcc.msdq.R;
import com.lcc.msdq.comments.CommentsActivity;
import com.lcc.mvp.presenter.ComAnswerContentPresenter;
import com.lcc.mvp.presenter.MenuContentPresenter;
import com.lcc.mvp.presenter.TestAnswerContentPresenter;
import com.lcc.mvp.presenter.impl.ComAnswerContentPresenterImpl;
import com.lcc.mvp.presenter.impl.MenuContentPresenterImpl;
import com.lcc.mvp.view.ComAnswerContentView;
import com.lcc.mvp.view.MenuContentView;
import com.lcc.view.MyWebView;
import com.lcc.view.loadview.LoadingLayout;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.frame.ImageManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  【这个地方的答案需要单独的提炼到一个表里面去】
 */
public class CompanyAnswerWebView extends BaseActivity implements View.OnClickListener,
        MyWebView.OnScrollChangedCallback, ComAnswerContentView {
    private MyWebView webView;
    private ImageView user_head;
    private FloatingActionMenu floatingMenu;
    private LinearLayout ll_top;
    private FloatingActionButton floatingCollect;
    private LoadingLayout loading_layout;
    private TextView tv_who;

    public static final String ANSWER = "answer";
    public static final String QUESTION = "question";
    private CompanyAnswer answer;
    private boolean isFav;
    private ComAnswerContentPresenter comAnswerContentPresenter;
    private CompanyTest entity;

    public static void startCompanyAnswerWebView(Activity startingActivity, CompanyAnswer type,
                                                 CompanyTest test) {
        Intent intent = new Intent(startingActivity, CompanyAnswerWebView.class);
        intent.putExtra(QUESTION, test);
        intent.putExtra(ANSWER, type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setData();
    }

    private void initData() {
        comAnswerContentPresenter = new ComAnswerContentPresenterImpl(this);
        answer = (CompanyAnswer) getIntent().getSerializableExtra(ANSWER);
        entity = (CompanyTest) getIntent().getSerializableExtra(QUESTION);
    }

    @Override
    protected void initView() {
        initData();
        tv_who= (TextView) findViewById(R.id.tv_who);
        tv_who.setText(answer.getNickname()+"的回答");
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        floatingCollect = (FloatingActionButton) findViewById(R.id.floatingCollect);
        floatingCollect.setOnClickListener(this);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);
        floatingMenu = (FloatingActionMenu) findViewById(R.id.floatingMenu);
        user_head = (ImageView) findViewById(R.id.user_head);
        webView = (MyWebView) findViewById(R.id.webView);
        webView.setOnScrollChangedCallback(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        //settings.setUseWideViewPort(true);造成文字太小
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebChromeClient(new WebChromeClient());
        //comAnswerContentPresenter.isFav(answer.getMid());
        comAnswerContentPresenter.getContent(answer.getMid());

    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_answer_content;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        menu.findItem(R.id.action_share).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.removeItem(R.id.action_use_browser);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "梁铖城" + " "
                        + "wwww.baidu.com" + getString(R.string.share_tail));
                shareIntent.setType("text/plain");
                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            webView.getClass().getMethod("onResume").invoke(webView, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            webView.getClass().getMethod("onPause").invoke(webView, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    public void setData() {
        if (answer == null) {
            return;
        }
        ImageManager.getInstance().loadCircleImage(CompanyAnswerWebView.this,
                answer.getUser_image(), user_head);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingComment:
                CommentsActivity.startUserProfileFromLocation(answer.getMid(), Propertity.Test.ANSWER,
                        CompanyAnswerWebView.this);
                break;

            case R.id.floatingCollect:
                if (isFav) {
                    comAnswerContentPresenter.UnFav(answer);
                } else {
                    comAnswerContentPresenter.Fav(answer, Propertity.COM.ANSWER, entity.getTitle());
                }
                break;

        }
    }

    @Override
    public void onScroll(int dx, int dy) {
        if (Math.abs(dy) > 4) {
            if (dy < 0) {
                floatingMenu.showMenu(true);
            } else {
                floatingMenu.hideMenu(true);
            }
        }
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
    public void getDataSuccess(AnswerContent msg) {
        webView.loadDataWithBaseURL("about:blank", msg.getA_content(),
                "text/html", "utf-8", null);
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }

    @Override
    public void isHaveFav(boolean isfavEntity) {
        this.isFav = isfavEntity;
        if (isfavEntity) {
            floatingCollect.setLabelText("取消收藏");
        } else {
            floatingCollect.setLabelText("收藏");
        }
    }

    @Override
    public void FavSuccess() {
        isHaveFav(true);
        FrameManager.getInstance().toastPrompt("收藏成功");
    }

    @Override
    public void FavFail(String msg) {
        FrameManager.getInstance().toastPrompt("收藏失败");
    }

    @Override
    public void UnFavSuccess() {
        isHaveFav(false);
        FrameManager.getInstance().toastPrompt("取消收藏成功");
    }

    @Override
    public void UnFavFail(String msg) {
        FrameManager.getInstance().toastPrompt("取消收藏失败");
    }
}
