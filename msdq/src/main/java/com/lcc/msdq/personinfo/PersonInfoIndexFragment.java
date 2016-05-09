package com.lcc.msdq.personinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcc.msdq.Login.LoginActivity;
import com.lcc.msdq.R;

import zsbpj.lccpj.frame.FrameManager;

public class PersonInfoIndexFragment extends Fragment implements View.OnClickListener {

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
            FrameManager.getInstance().toastPrompt("111");
        }
    }
}
