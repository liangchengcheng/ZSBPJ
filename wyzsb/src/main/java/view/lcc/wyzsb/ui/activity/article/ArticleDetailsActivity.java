package view.lcc.wyzsb.ui.activity.article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.frame.ImageManager;
import view.lcc.wyzsb.frame.UIManager;
import view.lcc.wyzsb.mvp.presenter.ArticleDetailsPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.ArticleDetailsPresenterImpl;
import view.lcc.wyzsb.mvp.view.ArticleDetailsView;
import view.lcc.wyzsb.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年04月13日13:03:32
 * Description:  |文章详情页面
 */
public class ArticleDetailsActivity extends BaseActivity implements View.OnClickListener,ArticleDetailsView{
    private WebView mWebView;
    private ImageView img_portrait;
    private LoadingLayout loading_layout;
    private ArticleDetailsPresenter presenter;
    private Article data;

    public static void startArticleDetails(Activity startingActivity, Article type) {
        Intent intent = new Intent(startingActivity, ArticleDetailsActivity.class);
        intent.putExtra("data", type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_details);

        data = (Article) getIntent().getSerializableExtra("data");
        presenter = new ArticleDetailsPresenterImpl(this);

        findViewById(R.id.iv_back).setOnClickListener(this);
        img_portrait = (ImageView) findViewById(R.id.img_portrait);
        mWebView = (WebView) findViewById(R.id.layout_web_view);
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(data.getA_t());
        TextView tv_person_name = (TextView) findViewById(R.id.tv_person_name);
        tv_person_name.setText(getDefaultAuthor(data.getA_a()));
        TextView tv_publish_time = (TextView) findViewById(R.id.tv_publish_time);
        tv_publish_time.setText(data.getA_ct());

        String url = data.getA_img();
        if (TextUtils.isEmpty(url)){
            ImageManager.getInstance().loadCircleResImage(ArticleDetailsActivity.this
                    ,R.mipmap.ic_launcher,img_portrait);
        }else {
            ImageManager.getInstance().loadCircleImage(ArticleDetailsActivity.this,url,img_portrait);
        }
        initView();
    }

    private String getDefaultAuthor(String a){
        if (TextUtils.isEmpty(a)){
            a = "管理员";
        }
        return a;
    }

    private void initView() {
        // init WebView
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        presenter.getContent(data.getId());
    }

    /**
     * load html form remote
     */
    private String loadHTMLData(String body) {
        StringBuilder builder = new StringBuilder().append(UIManager.WEB_STYLE).append(UIManager.WEB_LOAD_IMAGES);
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
            case R.id.iv_back:
                finish();
                break;
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
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
    }

    @Override
    public void getDataSuccess(Article msg) {
        //其他的属性则直接用上个界面传递过来的
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        mWebView.loadDataWithBaseURL("", loadHTMLData(msg.getA_b()), "text/html", "UTF-8", "");
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
    }
}
