package view.lcc.tyzs.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.HashMap;

import view.lcc.tyzs.frame.Frame;

/**
 * 头部信息参数
 */
public class HeaderMap extends HashMap<String, String> {

    public HeaderMap() {
        //版本号码
        put(AppConstants.ParamKey.APP_VER, getVersionCode());
        //设备唯一ID
        put(AppConstants.ParamKey.DEVICE_ID, getDeviceID());
        //token
        put(AppConstants.ParamKey.TOKEN_HEAD, "");
        //手机号
        put(AppConstants.ParamKey.PHONE,"");
        //平台
        put(AppConstants.ParamKey.PLATFROM, "android");
        //市场编号
        put(AppConstants.ParamKey.MC, "1101");
    }

    public void put(String key, int value) {
        super.put(key, value + "");
    }

    @Override
    public String put(String key, String value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value))
            return "";
        return super.put(key, value);
    }

    /**
     * 获取当前版本号
     */
    public static int getVersionCode() {
        try {
            PackageInfo info = Frame.getAppContext().getPackageManager()
                    .getPackageInfo("com.hdsx.aycx", 0);
            return info.versionCode;
        } catch (Exception e) {
            return -1;
        }
    }

    public String getDeviceID() {
        String mDeviceID = null;
        Context ctx = Frame.getAppContext();
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            mDeviceID = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mDeviceID == null && Build.VERSION.SDK_INT >= 9) {
            mDeviceID = Build.SERIAL;
        } else if (mDeviceID == null) {
            //获取到SIM卡唯一编号ID；
            //telephonyManager.getSimSerialNumber();
            //获取到客户ID，即IMSI；IMSI是15位的十进制数
            mDeviceID = telephonyManager.getSubscriberId();
        }

        return mDeviceID;
    }
}
