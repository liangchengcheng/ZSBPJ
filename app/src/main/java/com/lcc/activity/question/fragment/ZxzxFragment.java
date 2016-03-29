package com.lcc.activity.question.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcc.activity.R;

/**
 * Created by lcc on 16/3/17.
 */
public class ZxzxFragment extends Fragment{

    public static Fragment newInstance(int id, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("id", type);
        Fragment fragment = new ZxzxFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_zx,null);
        return view;
    }

}
