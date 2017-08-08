package view.lcc.tyzs.base;

import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

import view.lcc.tyzs.utils.DialogUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-08 20:54
 * Description:  |
 */
public class BaseFragment extends Fragment {
    public MaterialDialog progressDialog;

    public void createDialog(int text){
        if (progressDialog == null)
            progressDialog = DialogUtils.createProgress(getActivity(), text);
        DialogUtils.show(progressDialog);
    }

    public void closeDialog(){
        DialogUtils.dismiss(progressDialog);
    }

}
