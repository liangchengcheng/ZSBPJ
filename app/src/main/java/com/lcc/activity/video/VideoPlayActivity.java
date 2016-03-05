package com.lcc.activity.video;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lcc.activity.R;
import com.lcc.entity.CommentEntity;
import com.lcc.entity.MediaEntity;
import com.lcc.utils.ResourcesUtils;

import java.util.List;

public class VideoPlayActivity extends AppCompatActivity implements PlayVideoView{

    public final static String MEDIAS_ID_KEY = "media_id";

    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra(MEDIAS_ID_KEY, id);
        return intent;
    }

    @Override
    public void showMediaData(MediaEntity mediaEntity) {

    }

    @Override
    public void showLoadMediaError() {

    }

    @Override
    public void refreshComment(List<CommentEntity> dataList) {

    }

    @Override
    public void showMoreComments(List<CommentEntity> dataList) {

    }
}
