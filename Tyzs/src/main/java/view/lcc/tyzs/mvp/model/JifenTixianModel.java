package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.JifenTixianRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:
 */
public class JifenTixianModel {

    public OkHttpRequest jifenTixian(String value,
                                     String name, String CONTACT,
                                     String BCID,
                                     String bank, ResultCallback<String> callback) {
        JifenTixianRequest jifenTixianRequest = new JifenTixianRequest();
        jifenTixianRequest.setPhone(SharePreferenceUtil.getName());
        jifenTixianRequest.setValue(value);
        jifenTixianRequest.setName(name);
        jifenTixianRequest.setCONTACT(CONTACT);
        jifenTixianRequest.setBCID(BCID);
        jifenTixianRequest.setBank(bank);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(jifenTixianRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.JFTX;
        if (url.endsWith("ashx")) {
            url = url + "?Calldate=" + timeValue;
        } else {
            url = url + "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
