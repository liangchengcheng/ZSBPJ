package view.lcc.tyzs.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;
import org.json.JSONObject;
import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.OrderDetails;
import view.lcc.tyzs.bean.PersonNum;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.PersonNumGetPresenter;
import view.lcc.tyzs.mvp.presenter.impl.PersonNumGetPresenterImpl;
import view.lcc.tyzs.mvp.view.PersonNumGetView;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-11 14:04
 * Description:  |
 */
public class PersonNumActivity extends BaseActivity implements PersonNumGetView {

    private PersonNumGetPresenter personNumGetPresenter;
    private TextView tv_num;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_num_activity);

        personNumGetPresenter = new PersonNumGetPresenterImpl(this);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_num.setText("0");
        personNumGetPresenter.PersonNumGet(SharePreferenceUtil.getName());

    }

    @Override
    public void PersonNumGetLoading() {
       createDialog(R.string.loading);
    }

    @Override
    public void PersonNumGetSuccess(String result) {
        closeDialog();
        try {
            JSONObject jsonObjecte = new JSONObject(result);
            String code = jsonObjecte.getString("resultno");
            if (code.equals("000")) {
                String data = result.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
                JSONObject jsonObject = new JSONObject(data);
                String resultJson = jsonObject.getString("resultjson");
                PersonNum personNum = GsonUtils.changeGsonToBean(resultJson, PersonNum.class);
                if (personNum != null) {
                    tv_num.setText(personNum.getNum());
                } else {
                    tv_num.setText("0");
                }
            }
        } catch (Exception e) {
            tv_num.setText("0");
            e.printStackTrace();
        }
    }

    @Override
    public void PersonNumGetFail(String msg) {
        closeDialog();
        if (!TextUtils.isEmpty(msg)){
            Frame.getInstance().toastPrompt(msg);
        }else {
            Frame.getInstance().toastPrompt("数据加载失败，请稍后再试");
        }
    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
    }
}
