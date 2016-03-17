package com.lcc.activity.data.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lcc.rx.RxService;

/**
 * Created by lcc on 16/3/17.
 */
public class EnglishFragment extends Fragment{

    public static Fragment newInstance(int id, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("id", type);
        Fragment fragment = new EnglishFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
