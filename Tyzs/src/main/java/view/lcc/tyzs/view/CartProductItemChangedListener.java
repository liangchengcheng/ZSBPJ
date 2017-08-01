package view.lcc.tyzs.view;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 14:09
 * Description:  |
 */
public interface CartProductItemChangedListener {
    /**
     * 商品数量改变
     */
    void itemNumChanged(int position, int num);

    /**
     * 商品选中状态
     */
    void itemCheckChanged(int position, boolean isCheck);
}
