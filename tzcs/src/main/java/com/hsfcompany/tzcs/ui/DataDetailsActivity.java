package com.hsfcompany.tzcs.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hsfcompany.tzcs.R;
import com.hsfcompany.tzcs.base.BaseActivity;
import com.hsfcompany.tzcs.bean.News;


/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-13 18:30
 * Description:  |
 */
public class DataDetailsActivity extends AppCompatActivity {
    private CollapsingToolbarLayoutState state = CollapsingToolbarLayoutState.INTERNEDIATE;

    private View tv_t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_deatils_layout);

        tv_t = findViewById(R.id.tv_t);
        tv_t.setVisibility(View.GONE);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, mToolbar);
        News news = (News) getIntent().getSerializableExtra("data");

        TextView tv_what = (TextView) findViewById(R.id.tv_what);
        TextView tv_what_content = (TextView) findViewById(R.id.tv_what_content);
        TextView tv_jili = (TextView) findViewById(R.id.tv_jili);
        TextView tv_jilicontent = (TextView) findViewById(R.id.tv_jilicontent);
        TextView tv_yingxiang = (TextView) findViewById(R.id.tv_yingxiang);
        TextView tv_yingxiangcontent = (TextView) findViewById(R.id.tv_yingxiangcontent);
        TextView tv_tiaoli = (TextView) findViewById(R.id.tv_tiaoli);
        TextView tv_tiaolicontent = (TextView) findViewById(R.id.tv_tiaolicontent);
        ImageView iv_img = (ImageView) findViewById(R.id.iv_img);
        if (news != null){
            tv_what.setText(news.getWhat());
            tv_what_content.setText(news.getWhat_content());
            tv_jili.setText(news.getJili());
            tv_jilicontent.setText(news.getJili_content());
            tv_yingxiang.setText(news.getYingxiang());
            tv_yingxiangcontent.setText(news.getYingxiang_content());
            tv_tiaoli.setText(news.getTiaoli());
            tv_tiaolicontent.setText(news.getTiaoli_content());
            Glide.with(iv_img.getContext())
                    .load(news.getRid())
                    .placeholder(R.color.bg_textmy)
                    .error(R.color.bg_textmy)
                    .into(iv_img);
        }
        AppBarLayout app_bar = (AppBarLayout) findViewById(R.id.appbar);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        //修改状态标记为展开
                        state = CollapsingToolbarLayoutState.EXPANDED;
                        //设置title为EXPANDED
                        //collapsingToolbarLayout.setTitle("EXPANDED");
                        tv_t.setVisibility(View.GONE);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        //设置title不显示
                        tv_t.setVisibility(View.VISIBLE);
                        //隐藏播放按钮
                        //修改状态标记为折叠
                        state = CollapsingToolbarLayoutState.COLLAPSED;
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            //由折叠变为中间状态时隐藏播放按钮

                        }
                        //设置title为INTERNEDIATE
                        //collapsingToolbarLayout.setTitle("INTERNEDIATE");
                        //修改状态标记为中间
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;
                    }
                }
            }
        });

    }

    public enum  CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

}
