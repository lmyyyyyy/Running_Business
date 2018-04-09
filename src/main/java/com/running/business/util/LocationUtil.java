package com.running.business.util;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.vo.LocationVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liumingyu
 * @create 2017-11-28 下午8:19
 */
public class LocationUtil {
    private static final String BAIDU_APP_KEY = "42b8ececa9cd6fe72ae4cddd77c0da5d";


    private static String KEY = "aa4a48297242d22d2b3fd6eddfe62217";

    private static Pattern pattern = Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");

    public static double[] addressToGPS(String address) {
        try {

            String url = String .format("http://restapi.amap.com/v3/geocode/geo?&s=rsv3&address=%s&key=%s", address, KEY);
            URL myURL = null;
            URLConnection httpsConn = null;
            try {
                myURL = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStreamReader insr = null;
            BufferedReader br = null;
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = "";
                String line = null;
                while((line= br.readLine())!=null){
                    data+=line;
                }
                Matcher matcher = pattern.matcher(data);
                if (matcher.find() && matcher.groupCount() == 2) {
                    double[] gps = new double[2];
                    gps[0] = Double.valueOf(matcher.group(1));
                    gps[1] = Double.valueOf(matcher.group(2));
                    return gps;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度)
     */
    public static Map<String, String> getLatitude(String address) {
        try {
            // 将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
            // 如果有代理，要设置代理，没代理可注释
            // System.setProperty("http.proxyHost","192.168.172.23");
            // System.setProperty("http.proxyPort","3209");
            URL resjson = new URL("http://api.map.baidu.com/geocoder?address="
                    + address + "&output=json&key=" + BAIDU_APP_KEY);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    resjson.openStream()));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            in.close();
            String str = sb.toString();
            System.out.println("return json:" + str);
            if(str!=null&&!str.equals("")){
                Map<String, String> map = null;
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap<String, String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据经纬度查询地址基本信息
     *
     * @param log
     * @param lat
     * @return
     */
    public static String getAdd(String log, String lat ){
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        System.out.println(res);
        return res;
    }

    /**
     * 根据经纬度获取省市区
     *
     * @param lng
     * @param lat
     * @return
     */
    public static String[] getProvinceCityDistrictByLngAndLat(String lng, String lat) {
        String add = getAdd(lng, lat);
        JSONObject jsonObject = JSONObject.fromObject(add);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
        JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
        String allAdd = j_2.getString("admName");
        String arr[] = allAdd.split(",");
        return arr;
    }

    /**
     * 根据地址名称获取省市区
     *
     * @param address
     * @return
     */
    public static String[] getProvinceCityDistrictByAddr(String address) {
        Map<String, String> map = getLatitude(address);
        if (map == null || map.isEmpty()) {
            return null;
        }
        String add = getAdd(map.get("lng"), map.get("lat"));
        JSONObject jsonObject = JSONObject.fromObject(add);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
        JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
        String allAdd = j_2.getString("admName");
        String arr[] = allAdd.split(",");
        return arr;
    }

    /**
     * 根据地址获取地址VO
     *
     * @param address
     * @return
     * @throws AppException
     */
    public static LocationVO getLocationVO(String address) throws AppException {
        if (address == null || "".equals(address)) {
            throw new AppException(ResultEnum.QUERY_ADDRESS_INFO_ERROR);
        }
        LocationVO locationVO = new LocationVO();
        locationVO.setAddress(address);
        Map<String, String> map = LocationUtil.getLatitude(address);
        if (map == null || map.isEmpty()) {
            throw new AppException(ResultEnum.QUERY_ADDRESS_INFO_ERROR);
        }
        locationVO.setLongitude(map.get("lng"));
        locationVO.setLatitude(map.get("lat"));
        String[] str = getProvinceCityDistrictByLngAndLat(locationVO.getLongitude(), locationVO.getLatitude());
        if (str == null || str.length == 0) {
            return locationVO;
        }
        locationVO.setProvince(str[0]);
        locationVO.setCity(str[1]);
        locationVO.setDistrict(str[2]);

        return locationVO;
    }

    public static void main(String args[]) {

        double[] d = addressToGPS("北京美团点评总部");
        System.out.println(d[0] + "," + d[1]);

        Map<String, String> map = LocationUtil.getLatitude("北京美团点评总部");
        if (null != map) {
            System.out.println(map.get("lng"));
            System.out.println(map.get("lat"));
        }

        String add = getAdd(String.valueOf(d[0]), String.valueOf(d[1]));
        JSONObject jsonObject = JSONObject.fromObject(add);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
        JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
        String allAdd = j_2.getString("admName");
        String arr[] = allAdd.split(",");
        System.out.println("省:"+arr[0]+"\n市:"+arr[1]+"\n区:"+arr[2]);
    }
}