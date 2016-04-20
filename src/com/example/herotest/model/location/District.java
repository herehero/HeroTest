package com.example.herotest.model.location;

import org.litepal.crud.DataSupport;

public class District extends DataSupport{
private String districtName;
private String cityName;
private String provinceName;
private int mId;
public String getDistrictName() {
    return districtName;
}
public void setDistrictName(String districtName) {
    this.districtName = districtName;
}
public String getCityName() {
    return cityName;
}
public void setCityName(String cityName) {
    this.cityName = cityName;
}
public String getProvinceName() {
    return provinceName;
}
public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
}
public int getmId() {
    return mId;
}
public void setmId(int mId) {
    this.mId = mId;
}

}
