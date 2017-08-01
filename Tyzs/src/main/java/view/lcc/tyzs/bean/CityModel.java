package view.lcc.tyzs.bean;

import java.util.List;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:47
 * Description:  |
 */
public class CityModel {

    public String city;

    private List<CountryModel> county_list;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CountryModel> getCounty_list() {
        return county_list;
    }

    public void setCounty_list(List<CountryModel> county_list) {
        this.county_list = county_list;
    }

    @Override
    public String toString() {
        return "HSFCityModel [city=" + city + ", county_list=" + county_list + "]";
    }
}
