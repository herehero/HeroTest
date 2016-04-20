package com.example.herotest.model.location;

import java.util.ArrayList;

import org.litepal.crud.DataSupport;

public class Province extends DataSupport{
private String provinceName;
private ArrayList<City> cityList=new ArrayList<City>();
public String getProvinceName() {
    return provinceName;
}

public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
}

public ArrayList<City> getCityList() {
    return cityList;
}

public void setCityList(ArrayList<City> cityList) {
    this.cityList = cityList;
}
}
