package com.lcc.activity.data;

import android.content.Context;
import android.os.AsyncTask;
import com.lcc.utils.CacheHelper;
import java.io.Serializable;
import java.lang.ref.WeakReference;

public class ReadCacheAsyncTask<T> extends AsyncTask<String, Void, T> {
    private WeakReference<Context> mContext;

    public interface OnReadCacheToDo<T> {
        void preExecute();

        void postExecute(T data);
    }

    private OnReadCacheToDo<T> onReadCacheToDo;

    public void setOnReadCacheToDo(OnReadCacheToDo<T> onReadCacheToDo) {
        this.onReadCacheToDo = onReadCacheToDo;
    }

    public ReadCacheAsyncTask(Context context) {
        mContext = new WeakReference<Context>(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onReadCacheToDo != null) {
            onReadCacheToDo.preExecute();
        }
    }

    @Override
    protected T doInBackground(String... params) {
        if (mContext.get() != null) {
            Serializable serializable = CacheHelper.readObject(mContext.get(), params[0]);
            if (serializable == null) {
                return null;
            } else {
                return (T) serializable;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(T data) {
        super.onPostExecute(data);
        if (onReadCacheToDo != null) {
            onReadCacheToDo.postExecute(data);
        }
    }
}
