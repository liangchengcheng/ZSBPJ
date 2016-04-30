package com.lcc.frame.update;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import com.lcc.msdq.R;
import com.lcc.view.UpdateDialog;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import zsbpj.lccpj.frame.FrameManager;

public class UpdateApkTask {
    public static final String UPDATE_APK_URL = "http://app.hdsxtech.com:8181/drivers/android/driver/getVersion/nmjkVedio";
    public static final String APK_NAME = "nmjkvedio.apk";
    public static final String APKPAKEG_NAME = "com.hdsx.nmjkvedio";
    private static final int DOWNLOAD = 1;
    private static final int DOWNLOAD_FINISH = 2;
    private String mXMLPath;
    private UpdateDialog mDialog;
    private Context mContext;
    private boolean mIsShowToast;
	public static final String HDSX_NMJK =  Environment.getExternalStorageDirectory() + "/com.hdsx.nmjkvedio/";

    public UpdateApkTask(Context mContext, boolean mIsShowToast) {
        this.mContext = mContext;
        this.mIsShowToast = mIsShowToast;
    }

    public void detectionVersionInfo() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                String sb = "";
                try {
                    URL url = new URL(UPDATE_APK_URL);
                    URLConnection conn = url.openConnection();
                    InputStreamReader in = new InputStreamReader(
                            conn.getInputStream(), "utf-8");
                    BufferedReader br = new BufferedReader(in);
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb += line;
                    }

                    in.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (TextUtils.isEmpty(sb)) {
                    if (mIsShowToast) {
                        FrameManager.getInstance().toastPrompt("当前已是最新版本");
                    }

                    return;
                }

                try {
                    JSONObject jb = new JSONObject(sb);
                    if (jb.getBoolean("success")) {
                        String info = jb.getString("data");
                        Map<String, String> map = parseJsonObject(info);
                        int versionCode = Integer.parseInt(map.get("code"));
                        if (versionCode > getVersionCode(mContext, APKPAKEG_NAME)) {
                            Message message = Message.obtain();
                            message.obj = map.get("url");
                            message.what = 3;
                            mHandler.sendMessage(message);
                        } else {
                            if (mIsShowToast) {
                                FrameManager.getInstance().toastPrompt("已经是最新版本");
                            }
                        }
                    } else {
                        if (mIsShowToast) {
                            FrameManager.getInstance().toastPrompt("获取版本信息失败");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static HashMap<String, String> parseJsonObject(String str)
            throws JSONException {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        JSONObject jb = new JSONObject(str);
        hashMap.put("code", jb.getInt("code") + "");
        hashMap.put("version", jb.getString("version"));
        hashMap.put("url", jb.getString("downloadUrl"));

        return hashMap;
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD:
                    mDialog.setTitle(msg.arg1);
                    break;
                case DOWNLOAD_FINISH:
                    installApk();
                    break;
                case 3:
                    String url = (String) msg.obj;
                    showNoticeDialog(url);
                    break;
            }
        }
    };

    /**
     * 显示软件更新对话框
     */
    public void showNoticeDialog(String path) {
        new AlertDialog.Builder(mContext)
                .setTitle("版本升级")
                .setMessage("发现了新的版本，是否进行程序升级？")
                .setPositiveButton("现在升级", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showDownloadDialog();
                    }
                })
                .setNegativeButton("以后再说",  new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();


        mXMLPath = path;
    }

    /**
     * 显示下载对话框
     */
    private void showDownloadDialog() {

        mDialog = new UpdateDialog(mContext);
        mDialog.show();
        // 下载文件
        downloadAPK();
    }

    /**
     * 下载apk文件
     */
    private void downloadAPK() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                // 获取sd卡存储的路径
                File tmpFile = new File(HDSX_NMJK);
                if (!tmpFile.exists()) {
                    tmpFile.mkdir();
                }
                URL url;
                try {
                    url = new URL(mXMLPath);
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File apkFile = new File(HDSX_NMJK, APK_NAME);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    int numread;
                    while ((numread = is.read(buf)) > 0) {
                        count += numread;
                        // 计算进度条位置
                        Message msg = Message.obtain();
                        msg.arg1 = (int) (((float) count / length) * 100);
                        msg.what = DOWNLOAD;
                        // 更新进度
                        mHandler.sendMessage(msg);
                        // 写入文件
                        fos.write(buf, 0, numread);
                    }

                    if (numread <= 0) {
                        // 下载完成
                        mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                    }

                    fos.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mDialog.dismiss();
            }
        }).start();
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        File apkfile = new File(HDSX_NMJK + APK_NAME);
        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }

    public static int getVersionCode(Context ctx, String packageName) {
        if (null == packageName || packageName.length() <= 0) {
            return -1;
        }

        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(packageName, 0);
            return info.versionCode;
        } catch (Exception e) {
            return -1;
        }
    }
}
