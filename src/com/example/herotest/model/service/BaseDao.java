package com.example.herotest.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.herotest.model.MyHttpRequets;
import com.example.herotest.model.location.District;
import com.example.herotest.model.weather.MainPageData;
import com.example.herotest.utils.LogMgr;
import com.google.gson.Gson;

import android.telephony.gsm.GsmCellLocation;

public class BaseDao {
    public final static String APP_KEY = "eac8c9768344df68724fe44f5089de10";
    public final static String URL_PROVINCE = "http://v.juhe.cn/weather/citys?key=" + APP_KEY;
    public final static String URL_WEATHER_START = "http://v.juhe.cn/weather/index?format=2&cityname=";
    public final static String URL_WEATHER_END = "&key="+APP_KEY;
    
    public Object excute(MyHttpRequets mHttpRequets){
        HttpURLConnection cn=null;
        StringBuilder sb =new StringBuilder();
        try {
            URL url=new URL(mHttpRequets.getUrl());
            cn=(HttpURLConnection) url.openConnection();
            InputStream is=cn.getInputStream();
            InputStreamReader ir=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(ir);
            String line=null;
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            cn.disconnect();
        }
        String resultcode = null;
        String reason=null;
        String result=null;
        String error_code=null;
        try {
            JSONObject jsonObject=new JSONObject(sb.toString());
            resultcode = jsonObject.getString("resultcode");
            reason=jsonObject.getString("reason");
            result=jsonObject.getString("result");
            error_code=jsonObject.getString("error_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (resultcode.equals("200")) {
            LogMgr.d("result::"+result);
            return parseResultToObject(result,mHttpRequets.getDataType(),mHttpRequets.isList());
        }else {
            LogMgr.e("error_code::"+error_code);
            LogMgr.e("reason::"+reason);
            LogMgr.e("resultcode::"+resultcode);
            return null;
        }
        
        
    }
    private Object parseResultToObject(String result,Class dataType,boolean isList) {
        if (isList) {
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayList list=new ArrayList();
            for(int i=0;i<jsonArray.length();i++){
                list.add(new Gson().fromJson(result, dataType));
            }
            LogMgr.d("list.size::"+list.size());
            return list;
        }
        MainPageData mainPageData=new MainPageData();
        try {
            JSONObject jsonObject=new JSONObject(result);
            mainPageData.setRefreshTime(new JSONObject(jsonObject.getString("sk")).getString("time"));
            mainPageData.setDate(new JSONObject(jsonObject.getString("today")).getString("date_y"));
            mainPageData.setTemperature(new JSONObject(jsonObject.getString("today")).getString("temperature"));
            mainPageData.setWeather(new JSONObject(jsonObject.getString("today")).getString("weather"));
            mainPageData.setCity(new JSONObject(jsonObject.getString("today")).getString("city"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogMgr.d("mainPageData::"+mainPageData.toString());
        return mainPageData;
    }
    
    public static String toURLEncoded(String paramString) {  
        if (paramString == null || paramString.equals("")) {  
            return "";  
        }  
          
        try  
        {  
            String str = new String(paramString.getBytes(), "UTF-8");  
            str = URLEncoder.encode(str, "UTF-8");  
            return str;  
        }  
        catch (Exception localException)  
        {  
           LogMgr.e("toURLEncoded error:"+paramString);  
        }  
          
        return "";  
    }  
    
}
