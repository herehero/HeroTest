package com.example.herotest.model.service;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.example.herotest.model.location.City;
import com.example.herotest.model.location.District;
import com.example.herotest.model.location.Province;
import com.example.herotest.utils.LogMgr;

public class ServiceDatabaseDao {

    public static ArrayList<Province> getProvince() {
        List<Province> pList=DataSupport.findAll(Province.class);
        LogMgr.d("pList.size::" + pList.size());
        if (pList.size()<1) {
            return null;
        }
        return (ArrayList<Province>) pList;
    }
    public static ArrayList<City> getCity(String provinceName) {
        List<City> cList=(List<City>) DataSupport.where("provinceName=?", provinceName);
        LogMgr.d("pList.size::" + cList.size());
        if (cList.size()<1) {
            return null;
        }
        return (ArrayList<City>) cList;
    }
    public static ArrayList<District> getDistrict(String cityName) {
        List<District> dList=(List<District>) DataSupport.where("cityName=?", cityName);
        LogMgr.d("pList.size::" + dList.size());
        if (dList.size()<1) {
            return null;
        }
        return (ArrayList<District>) dList;
    }

}
