package zsbpj.lccpj.frame;

public class ApiException {

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     */
    // TODO: 16/5/9 此处没处理完成 
    public static String getApiExceptionMessage(String msg) {
        String message = "";
        if (msg.contains("fail")) {
            message="连接服务器失败";
        } else if (msg.contains("time out")) {
            message="连接服务器超时";
        } else if (msg.contains("net work")) {
            message="服务器不可用";
        }  else {
            message="程序发生错误";
        }
        return message;
    }
}
