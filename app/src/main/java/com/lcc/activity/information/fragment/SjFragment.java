package com.lcc.activity.information.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SjFragment extends Fragment{

    public static Fragment newInstance(int id, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("id", type);
        Fragment fragment = new SjFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


}
