package com.example.herotest.model.service;

import java.util.ArrayList;

import org.apache.http.HttpRequest;

import com.example.herotest.model.MyHttpRequets;

public class ServiceDao extends BaseDao{

    public static ArrayList<String> getProvince() {
        MyHttpRequets mHttpRequest=new MyHttpRequets();
        return excute(mHttpRequest);
    }

}
