package view.lcc.wyzsb.ui.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.Book;
import view.lcc.wyzsb.bean.Link;
import view.lcc.wyzsb.mvp.presenter.LinkDetailsPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.BookDetailsPresenterImpl;
import view.lcc.wyzsb.mvp.presenter.impl.LinkDetailsPresenterImpl;
import view.lcc.wyzsb.mvp.view.LinkDetailsView;
import view.lcc.wyzsb.mvp.view.LinkView;
import view.lcc.wyzsb.view.LoadingLayout;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月23日22:57:03
 * Description:  LinkDetailsActivity
 */
public class LinkDetailsActivity extends BaseActivity implements LinkDetailsView {
    private LinkDetailsPresenter linkDetailsPresenter;
    private LoadingLayout loading_layout;
    private WebView webView;
    private Link link;

    public static void startLinkDetailsActivity(Activity startingActivity, Link type) {
        Intent intent = new Intent(startingActivity, LinkDetailsActivity.class);
        intent.putExtra("data", type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_details_layout);

        link = (Link) getIntent().getSerializableExtra("data");
        webView = (WebView) findViewById(R.id.webView);
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

        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        linkDetailsPresenter = new LinkDetailsPresenterImpl(this);
        linkDetailsPresenter.getContent(link.getId());
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
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
    }

    @Override
    public void getDataSuccess(Link msg) {
        try {
            webView.loadDataWithBaseURL("about:blank", msg.getUrl(), "text/html", "utf-8", null);
            loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
