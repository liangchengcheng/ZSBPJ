package view.lcc.tyzs.frame;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by yishon on 15/9/11.
 */
public class Frame {
    private static final String LOG_TAG         = "Frame";
    private static final int    MSG_TOAST	    = 1;
    private static Frame self = null;
    private static Toast mToast;
    private static Context appContext;

    private boolean mIsInited;

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    /**
     * To get the application context which passed to Frame when initialize it.
     * @return the global application context instance.
     * @see Frame::init(Context context).
     */
    public static Context getAppContext() {
        return appContext;
    }

    /**
     * Application must set the context at the very beginning even if the method
     *  will be called soon.
     * @param context: the context to pass in.
     */
    public static void setAppContext(Context context) {
        if (context != null) {
            appContext = context.getApplicationContext();
        }
    }

    /**
     * @return true if it's inited already, else false.
     */
    public synchronized boolean isInited() {
        return mIsInited;
    }

    /**
     * It initialize the framework, including prepare/upgrade the database and so on.
     * Please call once after launch the application.
     * And must call it in a independent thread, to avoid it block the main thread,
     * and it's also suggested to display a progress bar to prompt the user to wait for
     * the finish the initialization.
     * @return True if initialization finished successfully, else return false.
     */
    public synchronized boolean init() {
        if (mIsInited)
            return true;

        if (appContext == null)
            return false;

//        File dbFile = new File(getDatabasePath());
//        if (!dbFile.exists()) {
//            if (!generateDBFile(false)) {
//                mIsInited = false;
//                return false;
//            }
//        } else {
//            Log.v(LOG_TAG, "update DB start !!!!");
//        }

        mIsInited = true;

        return true;
    }

    /**
     * It release all resources of the framework.
     * Please remember to call at least once before quit the application.
     */
    public synchronized static void exit() {
        if (self != null) {
            self.release();
        }
    }

    private void release() {
        if (mToast != null) {
            mToast.cancel();
        }

        if (mHandler != null && mHandler.getLooper() != null) {
            mHandler.getLooper().quit();
            mHandler = null;
        }
    }

    private Frame() {
        super();
    }

    /**
     * To get the singleton instance of Frame. Should call init() before use the instance returned.
     * @return the singleton instance of Frame.
     */
    public synchronized static Frame getInstance() {
        if (self == null) {
            self = new Frame();
        }

        return self;
    }

    private synchronized void initGlobalHandler() {
        if (mHandler != null) {
            return;
        }

        mHandlerThread = new HandlerThread("GlobalThread");
        mHandlerThread.start();
        mToast = null;
        mHandler = new ToastHandler(mHandlerThread.getLooper());
    }

    private static class ToastHandler extends Handler {

        public ToastHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TOAST:
                    toast((String)msg.obj);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private static void toast(final String prompt) {
        if (mToast == null) {
            mToast = Toast.makeText(appContext, prompt, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(prompt);
        }
        mToast.show();
    }

    public void toastPrompt(final String prompt) {
        if (mHandler == null) {
            initGlobalHandler();
        }

        Message msg = mHandler.obtainMessage(MSG_TOAST);
        msg.obj = prompt;
        mHandler.sendMessage(msg);
    }

}
