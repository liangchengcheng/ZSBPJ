package view.lcc.wyzsb.base.state;


import view.lcc.wyzsb.R;

public class ProgressState extends AbstractShowState {

    @Override
    public void show(boolean animate) {
        showViewById(R.id.epf_progress, animate);
    }

    @Override
    public void dismiss(boolean animate) {
        dismissViewById(R.id.epf_progress, animate);
    }
}
