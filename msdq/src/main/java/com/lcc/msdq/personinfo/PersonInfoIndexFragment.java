package com.lcc.msdq.personinfo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PersonInfoIndexFragment extends Fragment{

    public static Fragment newInstance() {
        Fragment fragment = new PersonInfoIndexFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view=inflater.inflate(R.layout.fragment_zx,null);
        //return view;
        return null;
    }

}
