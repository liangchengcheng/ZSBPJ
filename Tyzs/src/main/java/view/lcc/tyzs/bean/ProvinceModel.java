package view.lcc.tyzs.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:50
 * Description:  |
 */
public class ProvinceModel implements Serializable {

    public String province;

    List<CityModel> city_list;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<CityModel> getCity_list() {
        return city_list;
    }

    public void setCity_list(List<CityModel> city_list) {
        this.city_list = city_list;
    }

    @Override
    public String toString() {
        return "ProvinceModel [province=" + province + ", city_list="
                + city_list + "]";
    }
}
