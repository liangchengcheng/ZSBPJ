package com.lcc.msdq.choice;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lcc.adapter.ChoiceAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;
import java.util.Arrays;


public class ChoiceMainActivity extends BaseActivity {

    private String[] name = {"编辑","会计","文员","产品经理","人事","客服","销售","平面设计","程序员"};

    @Override
    protected void initView() {
        GridView girdview = (GridView) this.findViewById(R.id.gv_gz);
        girdview.setAdapter(new ChoiceAdapter(ChoiceMainActivity.this, Arrays.asList(name)));
        girdview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_choice;
    }
}
