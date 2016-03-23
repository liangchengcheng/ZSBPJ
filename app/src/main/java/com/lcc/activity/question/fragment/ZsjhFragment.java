package com.lcc.activity.question.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcc.activity.R;

/**
 * Created by lcc on 16/3/17.
 */
public class ZsjhFragment extends Fragment{

    public static Fragment newInstance() {

        Fragment fragment = new ZsjhFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_zs,null);
        return view;
    }

}
