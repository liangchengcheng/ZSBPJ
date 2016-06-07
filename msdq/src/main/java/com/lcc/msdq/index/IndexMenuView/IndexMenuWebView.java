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

import com.bumptech.glide.Glide;
import com.lcc.AppConstants;
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
public class IndexMenuWebView extends AppCompatActivity implements MenuContentView {

    public static final String DATA = "data";

    private WebView webView;

    private ImageView ivZhihuStory;

    private MenuContentPresenter indexContentPresenter;

    private Article article;
    private LoadingLayout loading_layout;

    public static void startIndexMenuWebView(Activity startingActivity, Article type) {
        Intent intent = new Intent(startingActivity, IndexMenuWebView.class);
        intent.putExtra(DATA, type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_menu_webview);
        initData();
        initView();
        getData();
    }

    private void initData() {
        article = (Article) getIntent().getSerializableExtra(DATA);
        indexContentPresenter = new MenuContentPresenterImpl(this);
    }

    private void initView() {
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

    private void getData() {
        Loading();
        indexContentPresenter.getArticleContent(article.getMid());
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
                shareIntent.putExtra(Intent.EXTRA_TEXT, "梁铖城" + " " + "wwww.baidu.com" + getString(R.string.share_tail));
                shareIntent.setType("text/plain");
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
        String head_img = article.getImage_url();
        if (TextUtils.isEmpty(head_img)){
            ivZhihuStory.setVisibility(View.GONE);
        }else {
            Glide.with(IndexMenuWebView.this)
                    .load(head_img)
                    .placeholder(R.drawable.loading1)
                    .centerCrop()
                    .into(ivZhihuStory);
        }

        try{
            if (TextUtils.isEmpty(result)){
                loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
            }else {
                loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
                webView.loadDataWithBaseURL("about:blank",result, "text/html", "utf-8", null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
