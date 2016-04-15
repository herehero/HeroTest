package com.example.herotest.model.location;

import org.litepal.crud.DataSupport;

public class District extends DataSupport{
private String districtName;
private City city;
private Province province;
private int mId;
public String getDistrictName() {
    return districtName;
}
public void setDistrictName(String districtName) {
    this.districtName = districtName;
}
public City getCity() {
    return city;
}
public void setCity(City city) {
    this.city = city;
}
public Province getProvince() {
    return province;
}
public void setProvince(Province province) {
    this.province = province;
}
public int getId() {
    return mId;
}
public void setId(int id) {
    this.mId = id;
}
}
