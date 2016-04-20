package com.example.herotest.model.location;

import java.util.ArrayList;

import org.litepal.crud.DataSupport;

public class City extends DataSupport{
private String cityName;
private ArrayList<District> districtList=new ArrayList<District>();
public String getCityName() {
    return cityName;
}

public void setCityName(String cityName) {
    this.cityName = cityName;
}

public ArrayList<District> getDistrictList() {
    return districtList;
}

public void setDistrictList(ArrayList<District> districtList) {
    this.districtList = districtList;
}

}
