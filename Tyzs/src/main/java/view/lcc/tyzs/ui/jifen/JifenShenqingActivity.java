package view.lcc.tyzs.ui.jifen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.JifenshenqingAdapter;
import view.lcc.tyzs.base.BaseActivity;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 22:04
 * Description:  |
 */
public class JifenShenqingActivity extends BaseActivity implements View.OnClickListener{

    private JifenshenqingAdapter jifenshenqingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jifen_shenqing_activity);
    }

    @Override
    public void onClick(View v) {

    }
}
