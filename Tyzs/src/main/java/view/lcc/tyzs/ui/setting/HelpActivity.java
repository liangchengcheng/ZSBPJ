package view.lcc.tyzs.ui.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-07 20:51
 * Description:  |
 */
public class HelpActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_activity);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
