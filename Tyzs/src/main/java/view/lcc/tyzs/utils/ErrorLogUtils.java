package view.lcc.tyzs.utils;

/**
 * 错误处理类
 */
public class ErrorLogUtils {
    public static String SystemError(String code) {
        String error = "错误";
        try {
            switch (code) {
                case "100":
                    error = "服务器正在维护";
                    break;
                case "101":
                    error = "操作失败";
                    break;
                case "102":
                    error = "参数存在非法数据或特殊字符";
                    break;
                //签名失败
                case "103":
                    error = "请联系客服更改密码";
                    break;
                //回传日期失败
                case "104":
                    error = "请更新你手机的时间";
                    break;

                //回传参数存在空值
                case "105":
                    error = "请联系客服代码105";
                    break;
                case "106":
                    error = "用户不存在";
                    break;
                case "107":
                    error = "无法解析回传信息";
                    break;
                case "108":
                    error = "手机号已经注册";
                    break;
                case "109":
                    error = "注册失败";
                    break;
                case "110":
                    error = "推荐人不存在";
                    break;
                case "111":
                    error = "推荐人等级错误";
                    break;
                case "112":
                    error = "该级别推荐人已占用";
                    break;
                case "113":
                    error = "该用户未通过审核";
                    break;
                case "114":
                    error = "用户名或密码错误";
                    break;
                case "115":
                    error = "页数或页面大小错误";
                    break;
                case "116":
                    error = "当前页没有数据";
                    break;
                //禁止非法调用
                case "117":
                    error = "请先更新你的时间！如不行请联系客服";
                    break;
                case "118":
                    error = "密码修改失败";
                    break;
                case "119":
                    error = "推荐人不允许是本人";
                    break;
                case "120":
                    error = "收货信息新增失败";
                    break;
                case "121.1":
                    error = "Action无法解析(空)";
                    break;
                case "121.2":
                    error = "Action无法解析(错误)";
                    break;
                case "122":
                    error = "是否默认错误";
                    break;
                case "123":
                    error = "收货信息修改失败";
                    break;
                case "124":
                    error = "推荐人新增失败";
                    break;
                case "125":
                    error = "收货信息删除失败";
                    break;
                case "126":
                    error = "收货信息与用户不匹配";
                    break;
                case "127":
                    error = "该用户没有设置收货信息";
                    break;
                case "128":
                    error = "购物车信息不正确";
                    break;
                case "129":
                    error = "购物车信息插入失败";
                    break;
                case "130":
                    error = "购物车信息删除失败";
                    break;
                case "131":
                    error = "验证码不通过";
                    break;
                case "132":
                    error = "订单信息不合格";
                    break;
                case "133":
                    error = "下单时间非法";
                    break;
                case "134":
                    error = "收货信息不存在";
                    break;
                case "135":
                    error = "积分余额不足";
                    break;
                case "136":
                    error = "订单中存在非法商品";
                    break;
                case "137":
                    error = "获取用户折扣信息失败";
                    break;
                case "138":
                    error = "获取用户角色信息失败";
                    break;
                case "139":
                    error = "订单不存在";
                    break;
                case "140":
                    error = "订单删除失败";
                    break;
                case "141":
                    error = "该用户没有积分记录";
                    break;
                case "142":
                    error = "该用户没有购物车信息";
                    break;
                case "143":
                    error = "订单创建失败";
                    break;
                case "144":
                    error = "订单状态错误";
                    break;
                case "145":
                    error = "当前页没有此状态的订单";
                    break;
                case "146":
                    error = "用户与订单不匹配";
                    break;
                case "147":
                    error = "该订单没有订单详情";
                    break;
                case "148":
                    error = "留言超过长度限制";
                    break;
                case "149":
                    error = "订单未支付";
                    break;
                case "150.1":
                    error = "订单状态错误(未支付)";
                    break;
                case "150.2":
                    error = "订单状态错误(已支付)";
                    break;
                case "150.3":
                    error = "订单状态错误(已完成)";
                    break;
                case "150.4":
                    error = "订单状态错误(申请退货)";
                    break;
                case "150.5":
                    error = "订单状态错误(已退货)";
                    break;
                case "150.6":
                    error = "订单状态错误(待收货)";
                    break;
                case "151":
                    error = "确认收货失败";
                    break;
                case "152":
                    error = "支付时间非法";
                    break;
                case "153":
                    error = "订单支付失败";
                    break;
                case "154":
                    error = "退货原因超出字数限制";
                    break;
                case "155":
                    error = "申请退单失败";
                    break;
                case "156":
                    error = "存在非法字符";
                    break;
                case "157":
                    error = "此订单已经提交申请";
                    break;
                case "165.3":
                    error = "未达到本月最低消费积分";
                    break;
                case "165.2":
                    error = "本月未消费";
                    break;
                case "168":
                    error = "可用积分余额不足,请先充值";
                    break;
                case "166":
                    error = "推荐人数不满足使用积分要求";
                    break;
                case "167":
                    error = "积分使用比例未设置";
                    //error = "积分不可使用";
                case "163":
                    error = "VIP会员无法进行转换";
                    break;
                case "169":
                    error = "今日已签到";
                    break;
                default:
                    error = "";
                    break;
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
        return error;
    }

}
