package com.example.herotest.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;

import com.example.herotest.model.MyHttpRequets;
import com.example.herotest.model.location.District;
import com.example.herotest.utils.LogMgr;
import com.google.gson.Gson;

import android.telephony.gsm.GsmCellLocation;

public class BaseDao {
    public final static String APP_KEY = "eac8c9768344df68724fe44f5089de10";
    public final static String URL_PROVINCE = "http://v.juhe.cn/weather/citys?key=" + APP_KEY;
    public Object excute(MyHttpRequets mHttpRequets){
        HttpsURLConnection cn=null;
        StringBuilder sb =null;
        try {
            URL url=new URL(mHttpRequets.getUrl());
            cn=(HttpsURLConnection) url.openConnection();
            InputStream is=cn.getInputStream();
            InputStreamReader ir=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(ir);
            String line=null;
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            LogMgr.d("results::"+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            cn.disconnect();
        }
        return parseResultToObject(sb.toString(),mHttpRequets.getDataType());
        
    }
    private Object parseResultToObject(String result,Class dataType) {
        ArrayList
        try {
            JSONObject jsonObject=new JSONObject(result.toString());
            String[] districts=(String[]) jsonObject.get("result");
            for (String district:districts) {
             Js
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
}
