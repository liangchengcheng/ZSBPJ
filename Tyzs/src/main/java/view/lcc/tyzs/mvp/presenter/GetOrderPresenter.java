package view.lcc.tyzs.mvp.presenter;




/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  登录
 */
public interface GetOrderPresenter {

    void getOrder(String page,String pagesize,String phone,String state);

    void refresh(String page,String pagesize,String phone,String state);

    void loadMore(String page,String pagesize,String phone,String state);

}
