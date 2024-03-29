package com.example.t_weather.util;

import android.text.TextUtils;

import com.example.t_weather.db.City;
import com.example.t_weather.db.County;
import com.example.t_weather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if (TextUtils.isEmpty(response)){
            try {
                JSONArray allProvince =new JSONArray(response);
                for (int i=0;i<allProvince.length();i++) {
                    JSONObject provinceObject= allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceID){
        if (TextUtils.isEmpty(response)){
            try {
                JSONArray allCity =new JSONArray(response);
                for (int i=0;i<allCity.length();i++) {
                    JSONObject cityObject= allCity.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProviceId(provinceID);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if (TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties =new JSONArray(response);
                for (int i=0;i<allCounties.length();i++) {
                    JSONObject cityObject= allCounties.getJSONObject(i);
                    County county =new County();
                    county.setCountyName(cityObject.getString("name"));
                    county.setWeatherId(cityObject.getString("weather_id"));
                    county.setCityID(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
