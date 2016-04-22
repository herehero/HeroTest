package com.example.herotest.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Handler;
import android.os.Looper;

import com.example.herotest.model.QueryCallbackInf;
import com.example.herotest.model.location.City;
import com.example.herotest.model.location.District;
import com.example.herotest.model.location.Province;
import com.example.herotest.utils.IDGenerator;


public class ServiceQuery {
    private static ServiceQuery instance;
    private static Object mLock=new Object();
    private HashMap<Integer, QueryCallbackInf> mCallbackMap;
    private Handler mHandler;
    private ServiceQuery(){
        initialize();
    }
    public static ServiceQuery getInstance(){
        if (instance==null) {
            synchronized (mLock) {
                if (instance==null) {
                    instance=new ServiceQuery();
                }
            }
        }
        return instance;
    }
    
    private void initialize(){
        mCallbackMap=new HashMap<Integer, QueryCallbackInf>();
    }
    
    public void queryProvince(QueryCallbackInf queryCallbackInf){
        final int requestId=IDGenerator.generateId();
        addCallback(requestId,queryCallbackInf);
        new Thread(){
            @Override
            public void run() {
                super.run();
                ArrayList<Province> ls_province=ServiceDatabaseDao.getProvince();
                if (ls_province==null) {
                     ls_province=new ServiceDao().getProvince();
                }
                if (ls_province==null) {
                    notifyUpLayer(requestId,QueryCallbackInf.CALLBACK_GET_PROVINCE_FAILED,ls_province);
                }else {
                    notifyUpLayer(requestId,QueryCallbackInf.CALLBACK_GET_PROVINCE_SUCESS,ls_province);
                }
            }
        }.start();
        
    }
    public void queryCity(QueryCallbackInf queryCallbackInf,final String provinceName){
        final int requestId=IDGenerator.generateId();
        addCallback(requestId,queryCallbackInf);
        new Thread(){
            @Override
            public void run() {
                super.run();
                ArrayList<City> ls_city=ServiceDatabaseDao.getCity(provinceName);
                if (ls_city==null) {
                    ls_city=(ArrayList<City>) new ServiceDao().getCity(provinceName);
                }
                if (ls_city==null) {
                    notifyUpLayer(requestId,QueryCallbackInf.CALLBACK_GET_PROVINCE_FAILED,ls_city);
                }else {
                    notifyUpLayer(requestId,QueryCallbackInf.CALLBACK_GET_PROVINCE_SUCESS,ls_city);
                }
            }
        }.start();
        
    }
    public void queryDistrict(QueryCallbackInf queryCallbackInf,final String cityName){
        final int requestId=IDGenerator.generateId();
        addCallback(requestId,queryCallbackInf);
        new Thread(){
            @Override
            public void run() {
                super.run();
                ArrayList<District> ls_District=ServiceDatabaseDao.getDistrict(cityName);
                if (ls_District==null) {
                    ls_District=(ArrayList<District>) new ServiceDao().getDistrict(cityName);
                }
                if (ls_District==null) {
                    notifyUpLayer(requestId,QueryCallbackInf.CALLBACK_GET_PROVINCE_FAILED,ls_District);
                }else {
                    notifyUpLayer(requestId,QueryCallbackInf.CALLBACK_GET_PROVINCE_SUCESS,ls_District);
                }
            }
        }.start();
        
    }

    private void notifyUpLayer(final int requestId,final int type,final Object obj){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                QueryCallbackInf queryCallbackInf=getCallback(requestId);
                queryCallbackInf.onEvent(type, obj);
                removeCallback(requestId);
            }
        });
        
    }
    
    private void addCallback(int requestId,QueryCallbackInf queryCallbackInf){
        mCallbackMap.put(requestId, queryCallbackInf);
    }
    
    private QueryCallbackInf getCallback(int requestId){
        return mCallbackMap.get(requestId);
    }
    private QueryCallbackInf removeCallback(int requestId){
        return mCallbackMap.remove(requestId);
    }
    
}
