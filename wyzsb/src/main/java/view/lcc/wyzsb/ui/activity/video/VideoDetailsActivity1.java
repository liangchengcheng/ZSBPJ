package view.lcc.wyzsb.ui.activity.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.frame.CollapsingToolbarLayoutState;
import view.lcc.wyzsb.frame.SystemBarHelper;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月11日13:46:31
 * Description:
 */
public class VideoDetailsActivity1 extends BaseActivity {
    private CollapsingToolbarLayoutState state =CollapsingToolbarLayoutState.INTERNEDIATE;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ButtonBarLayout playButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_details1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, toolbar);


        AppBarLayout app_bar = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        playButton = (ButtonBarLayout) findViewById(R.id.playButton);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        //修改状态标记为展开
                        state = CollapsingToolbarLayoutState.EXPANDED;
                        //设置title为EXPANDED
                        collapsingToolbarLayout.setTitle("EXPANDED");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        //设置title不显示
                        collapsingToolbarLayout.setTitle("");
                        //隐藏播放按钮
                        playButton.setVisibility(View.VISIBLE);
                        //修改状态标记为折叠
                        state = CollapsingToolbarLayoutState.COLLAPSED;
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                            //由折叠变为中间状态时隐藏播放按钮
                            playButton.setVisibility(View.GONE);
                        }
                        //设置title为INTERNEDIATE
                        collapsingToolbarLayout.setTitle("INTERNEDIATE");
                        //修改状态标记为中间
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;
                    }
                }
            }
        });
    }

}
