package com.lcc.activity.information.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lcc on 16/3/17.
 */
public class ZgFragment extends Fragment{

    public static Fragment newInstance(int id, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("id", type);
        Fragment fragment = new ZgFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
