package com.running.business.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liumingyu
 * @create 2018-02-27 下午5:20
 */
public class MapDistance {
    private static double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lat1Str 用户纬度
     * @param lng1Str 用户经度
     * @param lat2Str 商家纬度
     * @param lng2Str 商家经度
     * @return
     */
    public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr.
                substring(0, distanceStr.indexOf("."));

        return distanceStr;
    }

    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 minLat
     * 最大经度 maxLng
     * 最大纬度 maxLat
     */
    public static Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();

        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度

        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);

        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLat = longitude - radiusLng;
        // 获取最大经度
        Double maxLat = longitude + radiusLng;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLng = latitude - radiusLat;
        // 获取最大纬度
        Double maxLng = latitude + radiusLat;

        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");

        return map;
    }

    /**
     * 得到两点间的距离 米
     *
     * @param lat1 第一点纬度
     * @param lng1 第一点经度
     * @param lat2 第二点纬度
     * @param lng2 第二点经度
     * @return
     */
    public static double getDistanceOfMeter(double lat1, double lng1,
                                            double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 根据经纬度和距离返回一个矩形范围
     *
     * @param lng
     *            经度
     * @param lat
     *            纬度
     * @param distance
     *            距离(单位为米)
     * @return [lng1,lat1, lng2,lat2] 矩形的左下角(lng1,lat1)和右上角(lng2,lat2)
     */
    public static double[] getRectangle(double lng, double lat, long distance) {
        float delta = 111000;
        if (lng != 0 && lat != 0) {
            double lng1 = lng - distance
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta);
            double lng2 = lng + distance
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta);
            double lat1 = lat - (distance / delta);
            double lat2 = lat + (distance / delta);
            return new double[] { lng1, lat1, lng2, lat2 };
        } else {
            // TODO ZHCH 等于0时的计算公式
            double lng1 = lng - distance / delta;
            double lng2 = lng + distance / delta;
            double lat1 = lat - (distance / delta);
            double lat2 = lat + (distance / delta);
            return new double[] { lng1, lat1, lng2, lat2 };
        }
    }

    public static void main(String[] args) {
        //测试经纬度：117.11811  36.68484
        //测试经纬度2：117.00999000000002  36.66123
        System.out.println(getDistance("39.929986","116.395645","39.90403","116.407526"));

        System.out.println(getAround("45.717828", "126.641554", "2000"));
        //117.01028712333508(Double), 117.22593287666493(Double),
        //36.44829619896034(Double), 36.92138380103966(Double)
        System.out.println(getDistanceOfMeter(40.008600, 116.487560, 40.000985, 116.486266));
        double[] a = getRectangle(126.641554, 45.717828, 2000);
        for (int i=0; i<a.length; i ++) {
            System.out.println(a[i]);
        }
    }

}
