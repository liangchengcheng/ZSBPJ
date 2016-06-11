package com.lcc.msdq.index.IndexMenuView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcc.AppConstants;
import com.lcc.base.BaseActivity;
import com.lcc.entity.Article;
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.IndexContentPresenter;
import com.lcc.mvp.presenter.MenuContentPresenter;
import com.lcc.mvp.presenter.impl.IndexContentPresenterImpl;
import com.lcc.mvp.presenter.impl.MenuContentPresenterImpl;
import com.lcc.mvp.view.IndexContentView;
import com.lcc.mvp.view.MenuContentView;
import com.lcc.view.loadview.LoadingLayout;

import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  IndexMenuWebView
 */
public class IndexMenuWebView extends BaseActivity implements MenuContentView,
        View.OnClickListener{

    public static final String DATA = "data";

    private WebView webView;

    private ImageView ivZhihuStory;

    private MenuContentPresenter indexContentPresenter;

    private Article article;
    private LoadingLayout loading_layout;

    private TextView tv_question,tv_source;

    public static void startIndexMenuWebView(Activity startingActivity, Article type) {
        Intent intent = new Intent(startingActivity, IndexMenuWebView.class);
        intent.putExtra(DATA, type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        getData();
    }

    private void initData() {
        article = (Article) getIntent().getSerializableExtra(DATA);
        indexContentPresenter = new MenuContentPresenterImpl(this);
    }

    @Override
    protected void initView() {
        tv_question= (TextView) findViewById(R.id.tv_question);
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        ivZhihuStory= (ImageView) findViewById(R.id.user_head);
        webView= (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.index_menu_webview;
    }

    private void getData() {
        Loading();
        indexContentPresenter.getArticleContent(article.getMid());
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

    @Override
    public void Loading() {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void getFail(String msg) {
        loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
    }

    @Override
    public void getSuccess(String result) {
        //String head_img = AppConstants.RequestPath.BASE_URL+image_url;
        try{
            if (TextUtils.isEmpty(result)){
                loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
            }else {
                String head_img = article.getImage_url();
                if (TextUtils.isEmpty(head_img)){
                    ivZhihuStory.setVisibility(View.GONE);
                }else {
                    ivZhihuStory.setVisibility(View.VISIBLE);
                    Glide.with(IndexMenuWebView.this)
                            .load(head_img)
                            .placeholder(R.drawable.loading1)
                            .centerCrop()
                            .into(ivZhihuStory);
                }

                webView.loadDataWithBaseURL("about:blank",result, "text/html", "utf-8", null);
                tv_question.setText(article.getTitle());
                loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.guillotine_hamburger:
                finish();
                break;
        }
    }
}
