package com.example.herotest.model.service;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.example.herotest.model.location.Province;
import com.example.herotest.utils.LogMgr;

public class ServiceDatabaseDao {

    public static ArrayList<Province> getProvince() {
        List<Province> pList=DataSupport.findAll(Province.class);
        LogMgr.d("pList::" + pList+","+"pList.size::" + pList.size());
        if (pList.size()<1) {
            return null;
        }
        return (ArrayList<Province>) pList;
    }

}
