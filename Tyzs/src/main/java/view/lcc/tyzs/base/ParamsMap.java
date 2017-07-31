package view.lcc.tyzs.base;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class ParamsMap extends HashMap<String, String> {

    public ParamsMap() {
        //设备的id
        put("", "");
    }

    public void put(String key, int value) {
        super.put(key, value + "");
    }

    @Override
    public String put(String key, String value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value))
            return  "";
        return super.put(key, value);
    }
}
