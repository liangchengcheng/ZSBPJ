package com.lcc.view.tab.fragment;

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
public class FragmentFloor extends Fragment {

    private ListView listView;
    private FilterAdapter adapter;
    private String floors[] = {"全部时间", "最近一周", "最近一月", "最近一年"};
    private TestIndexFragment mainActivity;

    public FragmentFloor(TestIndexFragment mainActivity){
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
        adapter = new FilterAdapter(getActivity(), Arrays.asList(floors));
        listView.setDividerHeight(0);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.onFilter(0,floors[position]);
                adapter.setCheckItem(position);
            }
        });
    }
}
