package view.lcc.wyzsb.ui.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.Book;
import view.lcc.wyzsb.frame.ImageManager;
import view.lcc.wyzsb.frame.SystemBarHelper;
import view.lcc.wyzsb.mvp.presenter.BookDetailsPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.BookDetailsPresenterImpl;
import view.lcc.wyzsb.mvp.view.BookDetailsView;
import view.lcc.wyzsb.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年05月06日11:33:32
 * Description:  |书籍详情。
 */
public class BookDetailsActivity extends BaseActivity implements View.OnClickListener{

    private Book book;
    private ImageView ivZhihuStory;
    private TextView tv_title;
    private TextView tv_jianjie;
    private TextView tv_lb;
    private TextView tv_fenshu;
    private TextView tv_url;

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

        findViewById(R.id.iv_back).setOnClickListener(this);

        tv_url = (TextView) findViewById(R.id.tv_url);
        tv_fenshu = (TextView) findViewById(R.id.tv_fenshu);
        tv_lb = (TextView) findViewById(R.id.tv_lb);
        tv_jianjie = (TextView) findViewById(R.id.tv_jianjie);
        tv_title = (TextView) findViewById(R.id.tv_title);

        tv_title.setText(book.getB_t());
        tv_jianjie.setText(book.getB_js());
        tv_lb.setText("类别："+book.getB_type());
        tv_fenshu.setText("分数："+book.getB_g());
        tv_url.setText(book.getB_u());

        ivZhihuStory = (ImageView) findViewById(R.id.ivZhihuStory);
        ImageManager.getInstance().loadUrlImage(BookDetailsActivity.this,book.getB_i(),ivZhihuStory);
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
