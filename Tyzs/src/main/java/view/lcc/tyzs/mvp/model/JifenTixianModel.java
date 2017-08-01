package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
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
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("phone", SharePreferenceUtil.getName());
        paramsMap.put("value", value);
        paramsMap.put("name", name);
        paramsMap.put("CONTACT", CONTACT);
        paramsMap.put("BCID", BCID);
        paramsMap.put("bank", bank);

        return ApiClient.create(AppConstants.RequestPath.JFLB, paramsMap).get(callback);
    }

}
