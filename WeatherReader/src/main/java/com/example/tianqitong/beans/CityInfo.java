package com.example.tianqitong.beans;

/**
 * Created by Administrator on 2017/5/28 0028.
 */

public class CityInfo {
    private String id;
    private String province;
    private String city;
    private String district;

    public void setId(String id) {
        this.id = id;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {

        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getId() {

        return id;
    }
}
