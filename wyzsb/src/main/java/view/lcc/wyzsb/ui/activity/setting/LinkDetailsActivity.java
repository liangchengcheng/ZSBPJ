package view.lcc.wyzsb.ui.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
public class LinkDetailsActivity extends BaseActivity   {
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
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        link = (Link) getIntent().getSerializableExtra("data");
        initWebView();
        webView.loadUrl(link.getL_u());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.webView);
        WebSettings setting = webView.getSettings();
        setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        setting.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                view.loadUrl(link.getL_u());
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress != 100) {
                   // progressBar.setVisibility(View.VISIBLE);
                   // progressBar.setProgress(newProgress);
                } else {
                   // progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
    }

}
