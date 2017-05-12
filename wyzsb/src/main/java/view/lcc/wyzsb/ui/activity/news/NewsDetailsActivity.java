package view.lcc.wyzsb.ui.activity.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.News;
import view.lcc.wyzsb.frame.UIManager;
import view.lcc.wyzsb.mvp.presenter.NewsDetailsPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.NewsDetailsPresenterImpl;
import view.lcc.wyzsb.mvp.view.NewsDetailsView;
import view.lcc.wyzsb.view.LoadingLayout;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  新闻资讯的详情的页面
 */
public class NewsDetailsActivity extends BaseActivity implements NewsDetailsView,View.OnClickListener {

    private WebView mWebView;
    private LoadingLayout loading_layout;
    private NewsDetailsPresenter newsDetailsPresenter;
    private News news;

    public static void startNewsDetailsActivity(Activity startingActivity, News news) {
        Intent intent = new Intent(startingActivity, NewsDetailsActivity.class);
        intent.putExtra("news", news);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        this.news = (News) getIntent().getSerializableExtra("news");

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(news.getN_t());
        findViewById(R.id.lv_back).setOnClickListener(this);
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        newsDetailsPresenter = new NewsDetailsPresenterImpl(this);
        mWebView = (WebView) findViewById(R.id.layout_web_view);
        initView();
    }

    /**
     *  init WebView
     */
    private void initView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        newsDetailsPresenter.getContent(news.getId());
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
    public void getDataSuccess(News msg) {
        mWebView.loadDataWithBaseURL("", loadHTMLData(msg.getN_b()), "text/html", "UTF-8", "");
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }

    /**
     * load html form remote
     */
    private String loadHTMLData(String body) {
        StringBuilder builder = new StringBuilder();
        builder.append(UIManager.WEB_STYLE).append(UIManager.WEB_LOAD_IMAGES);
        // 下面是2个不同的注释。
        // builder.append("<body class='night'><div class='contentstyle' id='article_body'>");
        builder.append("<body><div class='contentstyle' id='article_body'>");
        return builder.append(setupContentImage(body)).append("</div></body>").toString();
    }

    /**
     * 对HTML里的图片做些处理
     */
    public static String setupContentImage(String body) {
        // 过滤掉 img标签的width,height属性
        body = body.replaceAll("(<img[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
        body = body.replaceAll("(<img[^>]*?)\\s+height\\s*=\\s*\\S+", "$1");
        // 添加点击图片放大支持
        body = body.replaceAll("(<img[^>]+src=\")(\\S+)\"", "$1$2\" onClick=\"showImagePreview('$2')\"");
        return body;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lv_back:
                finish();
                break;
        }
    }
}
