package com.lcc.msdq.index.article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcc.base.BaseActivity;
import com.lcc.entity.Article;
import com.lcc.entity.ArticleContent;
import com.lcc.msdq.R;
import com.lcc.msdq.comments.CommentsActivity;
import com.lcc.mvp.presenter.MenuContentPresenter;
import com.lcc.mvp.presenter.impl.MenuContentPresenterImpl;
import com.lcc.mvp.view.MenuContentView;
import com.lcc.view.loadview.LoadingLayout;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.frame.ImageManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  IndexMenuWebView
 */
public class IndexMenuWebView extends BaseActivity implements MenuContentView,
        View.OnClickListener {

    public static final String DATA = "data";
    public static final String TYPE = "type";

    private WebView webView;
    private ImageView ivZhihuStory;
    private LoadingLayout loading_layout;
    private TextView tv_question,
            tv_source;
    private ImageView iv_state;
    private LinearLayout ll_bottom_state;
    private TextView tv_comments;

    private MenuContentPresenter indexContentPresenter;
    private Article article;
    private ArticleContent articleContent;
    private String type = "面试感想";

    public static void startIndexMenuWebView(Activity startingActivity, Article article, String type) {
        Intent intent = new Intent(startingActivity, IndexMenuWebView.class);
        intent.putExtra(DATA, article);
        intent.putExtra(TYPE, type);
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
        articleContent = new ArticleContent();
        article = (Article) getIntent().getSerializableExtra(DATA);
        type = getIntent().getStringExtra(TYPE);
        indexContentPresenter = new MenuContentPresenterImpl(this);
        if (!article.getL_num().equals("0")) {
            tv_comments.setText(article.getL_num());
        }
    }

    @Override
    protected void initView() {
        findViewById(R.id.ll_issc).setOnClickListener(this);
        findViewById(R.id.ll_comments).setOnClickListener(this);
        findViewById(R.id.tv_to_comments).setOnClickListener(this);
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
        tv_comments = (TextView) findViewById(R.id.tv_comments);
        ll_bottom_state = (LinearLayout) findViewById(R.id.ll_bottom_state);
        iv_state = (ImageView) findViewById(R.id.iv_state);
        tv_question = (TextView) findViewById(R.id.tv_question);
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        ivZhihuStory = (ImageView) findViewById(R.id.user_head);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
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
    public void getSuccess(ArticleContent result) {
        try {
            articleContent = result;
            if (TextUtils.isEmpty(result.getContent())) {
                loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
            } else {
                //判断是否有介绍的图片
                String head_img = article.getImage_url();
                if (TextUtils.isEmpty(head_img)) {
                    ivZhihuStory.setVisibility(View.GONE);
                } else {
                    ivZhihuStory.setVisibility(View.VISIBLE);
                    Glide.with(IndexMenuWebView.this)
                            .load(head_img)
                            .placeholder(R.drawable.loading1)
                            .centerCrop()
                            .into(ivZhihuStory);
                }

                //判断是否被我收藏了
                if (result.getAuthor() == null) {
                    iv_state.setBackgroundResource(R.drawable.ic_heart_outline_grey);
                } else {
                    iv_state.setBackgroundResource(R.drawable.ic_heart_red);
                }

                webView.loadDataWithBaseURL("about:blank", result.getContent(),
                        "text/html", "utf-8", null);
                tv_question.setText(article.getTitle());
                loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
            }
            ll_bottom_state.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_comments:
                CommentsActivity.startUserProfileFromLocation(article.getMid(), type,
                        IndexMenuWebView.this);
                break;

            case R.id.tv_to_comments:
                CommentsActivity.startUserProfileFromLocation(article.getMid(), type,
                        IndexMenuWebView.this);
                break;

            case R.id.guillotine_hamburger:
                finish();
                break;

            case R.id.ll_issc:
                // TODO: 16/6/14 type需要传入进来
                if (articleContent.getAuthor() != null) {
                    indexContentPresenter.UnFav(article);
                }
                if (articleContent.getAuthor() == null) {
                    indexContentPresenter.Fav(article, "面试感想");
                }
                break;
        }
    }

    @Override
    public void FavSuccess() {
        articleContent.setAuthor("18813149871");
        ImageManager.getInstance().loadResImage(IndexMenuWebView.this,
                R.drawable.ic_heart_red, iv_state);
        FrameManager.getInstance().toastPrompt("收藏成功");
    }

    @Override
    public void FavFail(String msg) {
        FrameManager.getInstance().toastPrompt("收藏失败," + msg);
    }

    @Override
    public void UnFavSuccess() {
        articleContent.setAuthor(null);
        FrameManager.getInstance().toastPrompt("取消收藏成功");
        ImageManager.getInstance().loadResImage(IndexMenuWebView.this,
                R.drawable.ic_heart_outline_grey, iv_state);
    }

    @Override
    public void UnFavFail(String msg) {
        FrameManager.getInstance().toastPrompt("取消收藏失败," + msg);
    }

}
