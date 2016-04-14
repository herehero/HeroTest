package com.example.herotest.model;

import java.util.ArrayList;

public class QueryService {
    public static void queryProvince(QueryCallbackInf queryCallbackInf){
        new Thread(){
            @Override
            public void run() {
                super.run();
                ArrayList<String> ls_province=DaoService.getProvince();
            }
        }.start();
        
    }

}
