package view.lcc.tyzs.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.view.scrollnumber.MultiScrollNumber;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-11 14:04
 * Description:  |
 */
public class PersonNumActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_num_activity);

        MultiScrollNumber scrollNumber = (MultiScrollNumber) findViewById(R.id.scroll_number);

        scrollNumber.setTextColors(new int[]{R.color.red01, R.color.orange01,
                R.color.blue01, R.color.green01, R.color.purple01});
//        scrollNumber.setTextSize(64);

//        scrollNumber.setNumber(64, 2048);
//        scrollNumber.setInterpolator(new DecelerateInterpolator());


        scrollNumber.setScrollVelocity(100);
        scrollNumber.setNumber(20.48);

    }
}
