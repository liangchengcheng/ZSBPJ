package com.lcc.msdq.personinfo;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.lcc.db.test.UserInfo;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.login.LoginActivity;
import com.lcc.msdq.R;
import com.lcc.msdq.setting.SettingActivity;
import com.ufreedom.uikit.FloatingText;

import zsbpj.lccpj.frame.ImageManager;

public class PersonInfoIndexFragment extends Fragment implements View.OnClickListener {

    //头像
    private ImageView iv_more;
    //用户名
    private TextView tv_username;
    //签名
    private TextView tv_qm;
    //签到
    private RelativeLayout rl_qd;
    private TextView tv_qd;

    public static Fragment newInstance() {
        Fragment fragment = new PersonInfoIndexFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_info_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) view.findViewById(R.id.scroll_view);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_content_view, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);

        view.findViewById(R.id.iv_more).setOnClickListener(this);
        view.findViewById(R.id.iv_sys_image).setOnClickListener(this);
        iv_more= (ImageView) view.findViewById(R.id.iv_more);
        tv_username= (TextView) view.findViewById(R.id.tv_username);
        tv_qm= (TextView) view.findViewById(R.id.tv_qm);
        rl_qd= (RelativeLayout) view.findViewById(R.id.rl_qd);
        tv_qd= (TextView) view.findViewById(R.id.tv_qd);
        rl_qd.setOnClickListener(this);
        setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_more:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class),100);
                break;
            case R.id.rl_qd:
                FloatingText floatingText = new FloatingText.FloatingTextBuilder(getActivity())
                        .textColor(Color.RED)
                        .textSize(50)
                        .textContent("签到成功!")
                        .build();
                floatingText.attach2Window();
                floatingText.startFloating(tv_qd);
                break;
            case R.id.iv_sys_image:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==100&&resultCode==100){
            setData();
        }
    }

    private void setData(){
        UserInfo userInfo= DataManager.getUserInfo();
        if (userInfo!=null){
            ImageManager.getInstance().loadCircleImage(getActivity(),userInfo.getUser_image(),iv_more);
            tv_username.setText(userInfo.getNickname());
            if (TextUtils.isEmpty(userInfo.getQm())){
                tv_qm.setText("这个家伙很懒，什么也没留下");
            }else {
                tv_qm.setText(userInfo.getQm());
            }
        }
    }

}
