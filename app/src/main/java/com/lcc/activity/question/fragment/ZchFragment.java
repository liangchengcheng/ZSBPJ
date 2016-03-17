package com.lcc.activity.question.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lcc on 16/3/17.
 */
public class ZchFragment extends Fragment{

    public static Fragment newInstance(int id, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("id", type);
        Fragment fragment = new ZchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
