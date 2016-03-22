package com.lcc.activity.data;

import android.content.Context;
import android.os.AsyncTask;

import com.lcc.utils.CacheHelper;

import java.io.Serializable;
import java.lang.ref.WeakReference;


public class SaveCacheAsyncTask extends AsyncTask<Void, Void, Void> {
    private WeakReference<Context> mContext;
    private Serializable serializable;
    private String cacheKey;

    public SaveCacheAsyncTask(Context context, Serializable serializable, String cacheKey) {
        mContext = new WeakReference<Context>(context);
        this.serializable = serializable;
        this.cacheKey = cacheKey;
    }

    @Override
    protected Void doInBackground(Void... params) {
        CacheHelper.saveObject(mContext.get(), serializable, cacheKey);
        return null;
    }


}
