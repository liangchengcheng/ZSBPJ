package com.lcc.msdq.test.answer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.IndexContentPresenter;
import com.lcc.mvp.presenter.impl.IndexContentPresenterImpl;
import com.lcc.mvp.view.IndexContentView;

import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  先暂时先弄个页面
 */
public class AnswerContentActivity extends AppCompatActivity implements IndexContentView {

    public static final String KEY_URL = "url";

    public static final String IMAGE_URL = "image";

    private Toolbar toolbar;

    private WebView webView;

    private ImageView ivZhihuStory;

    private CollapsingToolbarLayout ctl;

    private NestedScrollView nest;

    private FloatingActionButton fabButton;

    private String id;
    private String image_url;

    private IndexContentPresenter indexContentPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_webview);
        initData();
        initView();
        getData();
    }

    private void initData() {
        id = getIntent().getStringExtra(KEY_URL);
        image_url = getIntent().getStringExtra(IMAGE_URL);
        indexContentPresenter = new IndexContentPresenterImpl(this);
    }

    private void initView() {

        ivZhihuStory= (ImageView) findViewById(R.id.ivZhihuStory);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("第五种基本能力是真你的被发现了？");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        nest= (NestedScrollView) findViewById(R.id.nest);
        fabButton = (FloatingActionButton) findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nest.smoothScrollTo(0, 0);
            }
        });
        webView= (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        //settings.setUseWideViewPort(true);造成文字太小
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebChromeClient(new WebChromeClient());
        ctl= (CollapsingToolbarLayout) findViewById(R.id.ctl);
    }

    private void getData() {
        indexContentPresenter.getActivityContent(id);
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

    @Override
    public void getLoginFail(String msg) {
        FrameManager.getInstance().toastPrompt("加载数据失败");
    }

    @Override
    public void getSuccess(String result) {
        String head_img = AppConstants.RequestPath.BASE_URL+image_url;
        Glide.with(AnswerContentActivity.this)
                .load(head_img)
                .placeholder(R.drawable.loading1)
                .centerCrop()
                .into(ivZhihuStory);
        try{
            webView.loadDataWithBaseURL("about:blank",result, "text/html", "utf-8", null);
           // webView.loadData(URLEncoder.encode(result, "utf-8"), "text/html", "utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
