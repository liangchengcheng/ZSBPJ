package view.lcc.wyzsb.ui.activity.article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.frame.ImageManager;
import view.lcc.wyzsb.frame.UIManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月13日13:03:32
 * Description:  文章详情
 */
public class ArticleDetailsActivity extends BaseActivity implements View.OnClickListener{

    private WebView mWebView;
    private ImageView img_portrait;

    public static void startArticleDetails(Activity startingActivity, String type) {
        Intent intent = new Intent(startingActivity, ArticleDetailsActivity.class);
        intent.putExtra("", type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_details);
        findViewById(R.id.iv_back).setOnClickListener(this);
        img_portrait = (ImageView) findViewById(R.id.img_portrait);
        ImageManager.getInstance()
                .loadCircleResImage(ArticleDetailsActivity.this,R.drawable.setting_bg,img_portrait);;
        mWebView = (WebView) findViewById(R.id.layout_web_view);
        initView();
    }

    private void initView() {
        // init WebView
        String html = "<div><p> hello world </p></div>";
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        mWebView.loadDataWithBaseURL("", loadHTMLData(html), "text/html", "UTF-8", "");
    }

    /**
     * load html form remote
     */
    private String loadHTMLData(String body) {
        StringBuilder builder = new StringBuilder().append(UIManager.WEB_STYLE)
                .append(UIManager.WEB_LOAD_IMAGES);
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
}
