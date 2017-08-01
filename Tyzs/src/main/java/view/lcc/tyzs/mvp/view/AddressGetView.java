package view.lcc.tyzs.mvp.view;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:00:59
 * Description:
 */
public interface AddressGetView {

    void AddressGetLoading();

    void AddressGetSuccess(String msg);

    void AddressGetFail(String msg);

    void NetWorkErr(String msg);
}
