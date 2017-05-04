package com.lcc.view.tab.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lcc.msdq.test.TestIndexFragment;
import com.lcc.view.tab.adapter.FilterAdapter;

import java.util.Arrays;


/**
 * Created by sunger on 16/4/16.
 */
@SuppressLint("ValidFragment")
public class FragmentSort extends Fragment {
    private String sorts[] = {"综合排序", "最多收藏", "最新发布"};
    ListView listView;
    FilterAdapter adapter;

    private TestIndexFragment mainActivity;

    public FragmentSort(){

    }

    @SuppressLint("ValidFragment")
    public FragmentSort(TestIndexFragment mainActivity){
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = new ListView(getActivity());
        return listView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setDividerHeight(0);
        adapter = new FilterAdapter(getActivity(), Arrays.asList(sorts));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (mainActivity != null){
                   mainActivity.onFilter(2,sorts[position]);
                   adapter.setCheckItem(position);
               }
            }
        });
    }
}
