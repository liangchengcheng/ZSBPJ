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
    private ChoiceAdapter adapter;
    @Override
    protected void initView() {
        GridView girdview = (GridView) this.findViewById(R.id.gv_gz);
        adapter=new ChoiceAdapter(ChoiceMainActivity.this, Arrays.asList(name));
        girdview.setAdapter(adapter);
        girdview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectItem(position);
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
