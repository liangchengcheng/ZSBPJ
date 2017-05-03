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
public class FragmentCategory extends Fragment {
    private String clothes[] = {"全部题型", "专业知识", "人事知识", "其他"};
    private ListView listView;
    private FilterAdapter adapter;
    private TestIndexFragment mainActivity;


    @SuppressLint("ValidFragment")
    public FragmentCategory(TestIndexFragment mainActivity){
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
        adapter = new FilterAdapter(getActivity(), Arrays.asList(clothes));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.onFilter(1,clothes[position]);
                adapter.setCheckItem(position);
            }
        });
    }
}
