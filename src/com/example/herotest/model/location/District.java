package com.example.herotest.model.location;

import org.litepal.crud.DataSupport;

public class District extends DataSupport{
private int id;
private String district;
private String city;
private String province;
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getDistrict() {
    return district;
}
public void setDistrict(String district) {
    this.district = district;
}
public String getCity() {
    return city;
}
public void setCity(String city) {
    this.city = city;
}
public String getProvince() {
    return province;
}
public void setProvince(String province) {
    this.province = province;
}

}
