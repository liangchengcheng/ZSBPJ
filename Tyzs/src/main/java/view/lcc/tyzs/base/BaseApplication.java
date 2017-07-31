package view.lcc.tyzs.base;

import android.app.Activity;
import android.app.Application;
import com.squareup.okhttp.OkHttpClient;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.frame.okhttp.OkHttpClientManager;

//MultiDexApplication(应该使用这个)
public class BaseApplication extends Application {

    private static final int CONNECT_TIMEOUT_MILLIS = 30 * 1000;
    private static final int READ_TIMEOUT_MILLIS = 30 * 1000;

    private static List<Activity> activityList = new LinkedList();

    private OkHttpClient okHttpClient;

    /**
     * 添加Activity到容器中
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 遍历所有Activity并finish
     */
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        //System.exit(0);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Frame.setAppContext(this);
        Frame.getInstance().init();
        initOkHttp();

    }

    private void initOkHttp() {
        okHttpClient = OkHttpClientManager.getInstance().getOkHttpClient();
        okHttpClient.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    }

}
