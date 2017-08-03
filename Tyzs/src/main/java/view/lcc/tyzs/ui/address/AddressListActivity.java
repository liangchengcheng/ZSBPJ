package view.lcc.tyzs.ui.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.AddressListAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.Address;
import view.lcc.tyzs.mvp.presenter.AddressGetPresenter;
import view.lcc.tyzs.mvp.view.AddressGetView;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-02 22:23
 * Description:  |
 */
// TODO: 2017/8/3 登录放在外侧
public class AddressListActivity extends BaseActivity implements AddressListAdapter.EditListener
        ,AdapterView.OnItemClickListener  ,View.OnClickListener,AddressGetView{
    private ListView addressList;
    private AddressListAdapter addressListAdapter;
    private AddressGetPresenter presenter;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list);
    }

    private void getAddress(){
        String phone = SharePreferenceUtil.getName();
        presenter.addressGet(phone);
    }

    @Override
    public void edit(Address address) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public void AddressGetLoading() {

    }

    @Override
    public void AddressGetSuccess(String msg) {

    }

    @Override
    public void AddressGetFail(String msg) {

    }

    @Override
    public void NetWorkErr(String msg) {

    }
}
