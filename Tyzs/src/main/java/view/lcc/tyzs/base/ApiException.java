package view.lcc.tyzs.base;

import android.text.TextUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  服务端异常转换类
 */
public class ApiException {

    public static String getApiExceptionMessage(String msg) {
        String message = "";
        if (TextUtils.isEmpty(msg)){
            return "网络不稳定，请稍后再试";
        }

        try{
            int code = Integer.parseInt(msg);

            if (code == 400){
                message = "服务未开启，请稍后再试";
            }else if (code == 405) {
                message = "服务未响应，请稍后再试";
            }else if (code >= 500) {
                message = "服务器错误，请稍后再试";
            }else {
                message = "网络不稳定，请稍后再试";
            }
        }catch (Exception e){
            message = "不能连接服务器，请稍后再试";
            e.printStackTrace();
        }

        return message;
    }
}
