package view.lcc.tyzs.base;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class BaseActivity extends FragmentActivity {

    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
    }
}
