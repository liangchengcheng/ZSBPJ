package view.lcc.wyzsb.ui.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.Book;
import view.lcc.wyzsb.mvp.presenter.BookDetailsPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.BookDetailsPresenterImpl;
import view.lcc.wyzsb.mvp.view.BookDetailsView;
import view.lcc.wyzsb.view.LoadingLayout;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class BookDetailsActivity extends BaseActivity implements BookDetailsView {
    private BookDetailsPresenter bookPresenter;
    private LoadingLayout loading_layout;
    private WebView webView;
    private Book book;

    public static void startBookDetailsActivity(Activity startingActivity, Book type) {
        Intent intent = new Intent(startingActivity, BookDetailsActivity.class);
        intent.putExtra("data", type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details_layout);
        book = (Book) getIntent().getSerializableExtra("data");
        webView = (WebView) findViewById(R.id.webView);
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

        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        bookPresenter = new BookDetailsPresenterImpl(this);
        bookPresenter.getContent(book.getId());
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
    public void getDataSuccess(Book msg) {
        try {
            webView.loadDataWithBaseURL("about:blank", msg.getB_b(), "text/html", "utf-8", null);
            loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
            // webView.loadData(URLEncoder.encode(result, "utf-8"), "text/html", "utf-8");
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
