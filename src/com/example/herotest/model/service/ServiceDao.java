package com.example.herotest.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpRequest;
import org.litepal.crud.DataSupport;

import com.example.herotest.model.MyHttpRequets;
import com.example.herotest.model.location.City;
import com.example.herotest.model.location.District;
import com.example.herotest.model.location.Province;
import com.example.herotest.model.weather.MainPageData;
import com.example.herotest.utils.LogMgr;

public class ServiceDao extends BaseDao {

    public ArrayList<Province> getProvince() {
        List<District> arrayList = getLocationFromNet();
        ArrayList<Province> provinceList = new ArrayList<Province>();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).save();
            if (!(arrayList.get(i).getProvinceName()).equals(arrayList.get(i + 1).getProvinceName())) {
                Province province = new Province();
                province.setProvinceName(arrayList.get(i + 1).getProvinceName());
                province.save();
                provinceList.add(province);
            }
        }
        LogMgr.d("provinceList::" + provinceList+","+"provinceList.size::" + provinceList.size());
        return provinceList;
    }

    public List<City> getCity(String provinceName) {
        List<District> arrayList = getLocationFromNet();
        List<District> districtList = (List<District>) DataSupport.where("provinceName=?", provinceName);
        List<City> cityList = new ArrayList<City>();
        for (int i = 0; i < districtList.size(); i++) {
            if (!(districtList.get(i).getCityName()).equals(districtList.get(i + 1).getCityName())) {
                City city = new City();
                city.setCityName(districtList.get(i + 1).getCityName());
                city.save();
                cityList.add(city);
            }
        }
        LogMgr.d("cityList::" + cityList+","+"cityList.size::" + cityList.size());
        return cityList;
    }

    public List<District> getDistrict(String cityName) {
        List<District> arrayList = getLocationFromNet();
        List<District> districtList = (List<District>) DataSupport.where("cityName=?", "cityName");
        LogMgr.d("districtList::" + districtList);
        return districtList;
    }

    public MainPageData getMainPageData(String districtName) {
        MyHttpRequets myHttpRequets = new MyHttpRequets();
        myHttpRequets.setUrl(BaseDao.URL_WEATHER_START + BaseDao.toURLEncoded(districtName) + BaseDao.URL_WEATHER_END);
        myHttpRequets.setList(false);
        myHttpRequets.setDataType(MainPageData.class);
        return (MainPageData) excute(myHttpRequets);
    }

    private List<District> getLocationFromNet() {
        MyHttpRequets mHttpRequest = new MyHttpRequets();
        mHttpRequest.setUrl(BaseDao.URL_PROVINCE);
        mHttpRequest.setList(true);
        mHttpRequest.setDataType(District.class);
        ArrayList<District> arrayList = (ArrayList<District>) excute(mHttpRequest);
        return arrayList;
    }
}
