package view.lcc.tyzs.base;

import android.text.TextUtils;

import java.util.HashMap;

import view.lcc.tyzs.utils.Base64;
import view.lcc.tyzs.utils.Md5Utils;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.utils.SingleTrueUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class ParamsMap extends HashMap<String, String> {

    public ParamsMap() {
        StringBuffer sb = new StringBuffer();
        long time = System.currentTimeMillis();
        sb.append(SingleTrueUtils.singltime(time));
        sb.append(SharePreferenceUtil.getName());
        sb.append(Md5Utils.encode(SharePreferenceUtil.getPwd()));
        String md5str = Md5Utils.encode(sb.toString().toUpperCase()).toUpperCase();
        String base = Base64.encode(md5str.getBytes());
        put("CallSiganture", base);
        put("CallUser", SharePreferenceUtil.getName());
        String timeChou = time + "";
        timeChou = timeChou.substring(0, timeChou.length() - 3);
        put("Calldate", timeChou);
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
