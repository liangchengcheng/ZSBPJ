package com.hsfcompany.tzcs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsfcompany.tzcs.base.BaseFragment;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-13 12:27
 * Description:  |
 */
public class DataFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment,null);
        return view;
    }
}
