package view.lcc.tyzs.ui.car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import view.lcc.tyzs.R;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |07-31 17:04
 * Description:  |
 */
public class CarFragment  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_fragment,null);
        return view;
    }
}
