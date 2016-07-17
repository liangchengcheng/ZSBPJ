package com.lcc.msdq.test.answer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
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
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.bumptech.glide.Glide;
import com.lcc.AppConstants;
import com.lcc.base.BaseActivity;
import com.lcc.entity.Answer;
import com.lcc.entity.TestEntity;
import com.lcc.msdq.R;
import com.lcc.msdq.comments.CommentsActivity;
import com.lcc.mvp.presenter.IndexContentPresenter;
import com.lcc.mvp.presenter.TestAnswerContentPresenter;
import com.lcc.mvp.presenter.impl.IndexContentPresenterImpl;
import com.lcc.mvp.presenter.impl.TestAnswerContentPresenterImpl;
import com.lcc.mvp.view.IndexContentView;
import com.lcc.mvp.view.TestAnswerContentView;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.frame.ImageManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  答案的详情界面
 */
public class AnswerContentActivity extends BaseActivity implements View.OnClickListener,
        TestAnswerContentView{

    private WebView webView;
    private ImageView user_head;
    private FloatingActionButton floatingCollect;
    private TextView tv_who;

    private Answer answer;
    private TestEntity testEntity;
    private TestAnswerContentPresenter testAnswerContentPresenter;

    public static final String DATA = "data";
    public static final String ANSWER = "answer";
    private boolean isFav;

    public static void startAnswerContentActivity(TestEntity data,Answer answer, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, AnswerContentActivity.class);
        intent.putExtra(ANSWER, answer);
        intent.putExtra(DATA, data);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setData();
    }

    private void initData() {
        answer = (Answer) getIntent().getSerializableExtra(ANSWER);
        testEntity = (TestEntity) getIntent().getSerializableExtra(DATA);
        testAnswerContentPresenter=new TestAnswerContentPresenterImpl(this);
    }

    @Override
    protected void initView() {
        initData();
        floatingCollect= (FloatingActionButton) findViewById(R.id.floatingCollect);
        floatingCollect.setOnClickListener(this);
        findViewById(R.id.floatingComment).setOnClickListener(this);
        tv_who= (TextView) findViewById(R.id.tv_who);
        tv_who.setText(answer.getNickname()+"的回答");
        user_head = (ImageView) findViewById(R.id.user_head);
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
        testAnswerContentPresenter.isFav(answer.getMid());
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_answer_content;
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
                shareIntent.putExtra(Intent.EXTRA_TEXT, "梁铖城" + " " +
                        "wwww.baidu.com" + getString(R.string.share_tail));
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

    public void setData() {
        if (answer == null) {
            return;
        }
        ImageManager.getInstance().loadCircleImage(AnswerContentActivity.this,
                answer.getUser_image(), user_head);
        try {
            webView.loadDataWithBaseURL("about:blank", answer.getAnswer(),
                    "text/html", "utf-8", null);
            // webView.loadData(URLEncoder.encode(result, "utf-8"), "text/html", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingComment:
                CommentsActivity.startUserProfileFromLocation(answer.getMid(),"资料答案",
                        AnswerContentActivity.this);
                break;

            case R.id.floatingReport:
                FrameManager.getInstance().toastPrompt("举报成功");
                break;

            case R.id.floatingCollect:
                if (isFav){
                    testAnswerContentPresenter.UnFav(answer);
                }else {
                    testAnswerContentPresenter.Fav(answer,"资料答案",testEntity.getTitle());
                }
                break;

            case R.id.floatingShare:
                FrameManager.getInstance().toastPrompt("分享成功");
                break;
        }
    }

    @Override
    public void getLoading() {

    }

    @Override
    public void getDataEmpty() {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void isHaveFav(boolean isfavEntity) {
        this.isFav=isfavEntity;
        if (isfavEntity){
            floatingCollect.setLabelText("取消收藏");
        }else {
            floatingCollect.setLabelText("收藏");
        }
    }

    @Override
    public void FavSuccess() {
        isHaveFav(true);
        FrameManager.getInstance().toastPrompt("收藏成功");
    }

    @Override
    public void FavFail(String msg) {
        FrameManager.getInstance().toastPrompt("收藏失败");
    }

    @Override
    public void UnFavSuccess() {
        isHaveFav(false);
        FrameManager.getInstance().toastPrompt("取消收藏成功");
    }

    @Override
    public void UnFavFail(String msg) {
        FrameManager.getInstance().toastPrompt("取消收藏失败");
    }
}
