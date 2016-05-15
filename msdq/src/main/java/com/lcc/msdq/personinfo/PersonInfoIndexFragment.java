package com.lcc.msdq.personinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcc.db.test.UserInfo;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.Login.LoginActivity;
import com.lcc.msdq.R;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.frame.ImageManager;

public class PersonInfoIndexFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_more;
    private TextView tv_username;
    private TextView tv_qm;

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
        view.findViewById(R.id.iv_more).setOnClickListener(this);
        iv_more= (ImageView) view.findViewById(R.id.iv_more);
        tv_username= (TextView) view.findViewById(R.id.tv_username);
        tv_qm= (TextView) view.findViewById(R.id.tv_qm);
        setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_more:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class),100);
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
