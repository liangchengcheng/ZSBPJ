package com.lcc.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcc.activity.login.LoginActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainNavigationHeader implements View.OnClickListener {
    private CircleImageView mImageViewAvatar;
    private TextView mTextViewNickName;
    private View mRelativeLayout1;
    private View mRelativeLayout2;

    private TextView mTextViewVideosCount;
    private TextView mTextViewRepostsCount;
    private TextView mTextViewFriendsCount;
    private Activity mActivity;

    public MainNavigationHeader(Activity activity, NavigationView navigationView) {
        this.mActivity = activity;
        View headView = navigationView.getHeaderView(0);
        headView.findViewById(R.id.textView_login).setOnClickListener(this);
        headView.findViewById(R.id.headview).setOnClickListener(this);
        headView.findViewById(R.id.textView_signup).setOnClickListener(this);
        mRelativeLayout1 = headView.findViewById(R.id.relative_layout1);
        mRelativeLayout2 = headView.findViewById(R.id.relative_layout2);
        mTextViewVideosCount = (TextView) headView.findViewById(R.id.textView_videos_count);
        mTextViewRepostsCount = (TextView) headView.findViewById(R.id.textView_reposts_count);
        mTextViewFriendsCount = (TextView) headView.findViewById(R.id.textView_friends_count);
        mImageViewAvatar = (CircleImageView) headView.findViewById(R.id.headview);
        mTextViewNickName = (TextView) headView.findViewById(R.id.textView_nickName);
    }

    public void bindData() {
        String oauthUserEntity = null;
        if (oauthUserEntity == null) {
            mImageViewAvatar.setImageResource(R.mipmap.head);
            mImageViewAvatar.setOnClickListener(this);
            mRelativeLayout1.setVisibility(View.GONE);
            mRelativeLayout2.setVisibility(View.VISIBLE);
        } else {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            mImageViewAvatar.setLayoutParams(lp);
            mTextViewNickName.setVisibility(View.GONE);
            mImageViewAvatar.setOnClickListener(null);
            mRelativeLayout1.setVisibility(View.VISIBLE);
            mRelativeLayout2.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_login:
                mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                break;

            case R.id.headview:
                mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                break;

            case R.id.textView_signup:
                break;
        }
    }

}
