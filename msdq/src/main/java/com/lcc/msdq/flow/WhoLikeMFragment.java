package com.lcc.msdq.flow;

import android.os.Bundle;
import android.view.View;

import com.lcc.frame.fragment.base.BaseLazyLoadFragment;
import com.lcc.msdq.R;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description: WhoLikeMFragment(自己看的话就不用没后面的关注，但是别人看的话能关注)
 */
public class WhoLikeMFragment extends BaseLazyLoadFragment {

    public static WhoLikeMFragment newInstance(String fid) {
        WhoLikeMFragment mFragment = new WhoLikeMFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fid", fid);
        mFragment.setArguments(bundle);
        return mFragment;
    }


    @Override
    public int initContentView() {
        return R.layout.fragment_wholikem;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {

    }

    @Override
    public void initData() {

    }
}
