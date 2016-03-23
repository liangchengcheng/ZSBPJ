package com.lcc.activity.information.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SjActivity extends Fragment{

    public static Fragment newInstance(int id, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("id", type);
        Fragment fragment = new SjActivity();
        fragment.setArguments(bundle);
        return fragment;
    }


}
