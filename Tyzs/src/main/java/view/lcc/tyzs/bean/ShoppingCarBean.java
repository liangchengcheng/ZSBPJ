package view.lcc.tyzs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:57
 * Description:  |
 */
public class ShoppingCarBean implements Serializable {

    //商品id
    private String GID;

    //零售价
    private String price;

    //成本
    private String cost;

    //利润
    private String profit;

    //商品名
    private String name;

    //商品描述
    private String description;

    //商品图片地址
    private String ImageUrl;

    //商品是否下架
    private String IsDelete;

    //商品数量
    private String number;
}
