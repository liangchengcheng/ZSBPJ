package com.hsfcompany.tzcs;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsfcompany.tzcs.adapter.DataAdapter;
import com.hsfcompany.tzcs.base.BaseFragment;
import com.hsfcompany.tzcs.bean.News;
import com.hsfcompany.tzcs.utils.BeanUtil;

import java.util.List;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-13 12:27
 * Description:  |
 */
public class DataFragment extends BaseFragment implements DataAdapter.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment,null);
        initRecycleView(view);
        return view;
    }

    private void initRecycleView(View view) {
        RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DataAdapter adapter = new DataAdapter();
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        List<News> data = BeanUtil.getNewsData();
        adapter.bind(data);
    }

    @Override
    public void onItemClick(News data) {

    }
}
