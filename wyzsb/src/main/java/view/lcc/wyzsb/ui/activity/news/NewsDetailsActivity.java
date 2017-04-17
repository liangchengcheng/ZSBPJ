package view.lcc.wyzsb.ui.activity.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.News;
import view.lcc.wyzsb.frame.UIManager;
import view.lcc.wyzsb.ui.activity.article.ArticleDetailsActivity;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  新闻资讯的详情的页面
 */
public class NewsDetailsActivity extends BaseActivity {

    public static void startNewsDetailsActivity(Activity startingActivity, News news) {
        Intent intent = new Intent(startingActivity, NewsDetailsActivity.class);
        intent.putExtra("news", news);
        startingActivity.startActivity(intent);
    }

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        mWebView = (WebView) findViewById(R.id.layout_web_view);
        initView();
    }


    private void initView() {
        // init WebView
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

    private String html = "<div class=\"table-responsive\">\n" +
            "        <!--table-striped-->\n" +
            "        <table class=\"table  table-bordered table-hover\">\n" +
            "            <thead>\n" +
            "            <tr class=\"active\">\n" +
            "                <th>表格标题</th>\n" +
            "                <th>表格标题</th>\n" +
            "                <th>表格标题</th>\n" +
            "            </tr>\n" +
            "            </thead>\n" +
            "            <tbody>\n" +
            "                <tr class=\"success\">\n" +
            "                    <td >\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                    <td>\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                    <td>\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr class=\"info\">\n" +
            "                    <td>\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                    <td>\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                    <td>\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr class=\"danger\">\n" +
            "                    <td>\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                    <td>\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                    <td>\n" +
            "                        表格的内容\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "    </div>";
}
