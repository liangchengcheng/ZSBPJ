package view.lcc.tyzs.mvp.view;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:00:59
 * Description:  |提交订单
 */
public interface OrderConfirmView {

    void OrderConfirmLoading();

    void OrderConfirmSuccess(String msg);

    void OrderConfirmFail(String msg);

    void NetWorkErr(String msg);
}
