package view.lcc.tyzs.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import org.json.JSONObject;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.AddressAddPresenter;
import view.lcc.tyzs.mvp.presenter.impl.AddressAddPresenterImpl;
import view.lcc.tyzs.mvp.view.AddressAddView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-03 08:33
 * Description:  |
 */
public class AddressAddActivity extends BaseActivity implements AddressAddView {

    private AddressAddPresenter addressAddPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addressAddPresenter = new AddressAddPresenterImpl(this);
    }

    @Override
    public void AddressAddLoading() {
        createDialog(R.string.address_add);
    }

    @Override
    public void AddressAddSuccess(String result) {
        Frame.getInstance().toastPrompt("添加收货地址成功");
        Intent intent = new Intent();
        intent.putExtra("ok", "ok");
        setResult(RESULT_OK, intent);
        finish();
        closeDialog();
    }

    @Override
    public void AddressAddFail(String msg) {
        Frame.getInstance().toastPrompt("添加收货地址失败");
        closeDialog();
    }

    @Override
    public void NetWorkErr(String msg) {
        Frame.getInstance().toastPrompt("网络不太好,去那个稍后再试");
        closeDialog();
    }
}
