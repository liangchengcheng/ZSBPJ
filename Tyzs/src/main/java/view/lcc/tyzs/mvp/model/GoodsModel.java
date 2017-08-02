package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  获取shangpin
 */
public class GoodsModel {

    public OkHttpRequest getGoods(String page,String pagesize,String type, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("page", page);
        paramsMap.put("pagesize", pagesize);
        paramsMap.put("type", type);
        return ApiClient.create(AppConstants.RequestPath.GOODS, paramsMap).post(callback);
    }

}
