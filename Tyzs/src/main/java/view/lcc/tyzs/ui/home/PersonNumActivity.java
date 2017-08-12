package view.lcc.tyzs.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.PersonNumGetPresenter;
import view.lcc.tyzs.mvp.presenter.impl.PersonNumGetPresenterImpl;
import view.lcc.tyzs.mvp.view.PersonNumGetView;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.view.scrollnumber.MultiScrollNumber;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-11 14:04
 * Description:  |
 */
public class PersonNumActivity extends BaseActivity implements PersonNumGetView {

    private PersonNumGetPresenter personNumGetPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_num_activity);

        personNumGetPresenter = new PersonNumGetPresenterImpl(this);

        MultiScrollNumber scrollNumber = (MultiScrollNumber) findViewById(R.id.scroll_number);
        scrollNumber.setTextColors(new int[]{R.color.white});
//        scrollNumber.setTextSize(64);

//        scrollNumber.setNumber(64, 2048);
//        scrollNumber.setInterpolator(new DecelerateInterpolator());


        scrollNumber.setScrollVelocity(1000);
        scrollNumber.setNumber(111);
        personNumGetPresenter.PersonNumGet(SharePreferenceUtil.getName());

    }

    @Override
    public void PersonNumGetLoading() {
        createDialog(R.string.loading);
    }

    @Override
    public void PersonNumGetSuccess(String msg) {
        closeDialog();
    }

    @Override
    public void PersonNumGetFail(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("网络不稳定请稍后再试");
    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("网络不稳定请稍后再试");
    }
}
