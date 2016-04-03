package com.lcc.msdq.test;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcc.base.BaseFragment;
import com.lcc.msdq.R;

public class TestIndexFragment extends BaseFragment{

    public static Fragment newInstance() {
        Fragment fragment = new TestIndexFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.test_fragment,null);
        return view;
    }

}
