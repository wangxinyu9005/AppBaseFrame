package com.xinbo.app.baseframe.modules.home.bean;

import android.graphics.Point;
import android.graphics.Rect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

public class Weather7Days implements Serializable {
    private static final long serialVersionUID = -9088709211046013491L;
    private String city ;// 廊坊 ,
    private String cityid ;// 143 ,
    private String citycode ;// 101090601 ,
    private String date ;// 2018-06-13 ,
    private String week ;// 星期三 ,
    private String weather ;// 多云 ,
    private String temp ;// 21 ,
    private String temphigh ;// 27 ,
    private String templow ;// 17 ,
    private String img ;// 1 ,
    private String humidity ;// 72 ,
    private String pressure ;// 1001 ,
    private String windspeed ;// 5.5 ,
    private String winddirect ;// 东北风 ,
    private String windpower ;// 3级 ,
    private String updatetime ;// 2018-06-13 13;//05;//00 ,

    private Aqi aqi;
    private List<TodayDescription> index;
    private List<Daily> daily;
    private List<Hourly> hourly;

    public List<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }

    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public String getCity() {
        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid == null ? "" : cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCitycode() {
        return citycode == null ? "" : citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week == null ? "" : week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeather() {
        return weather == null ? "" : weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp == null ? "" : temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemphigh() {
        return temphigh == null ? "" : temphigh;
    }

    public void setTemphigh(String temphigh) {
        this.temphigh = temphigh;
    }

    public String getTemplow() {
        return templow == null ? "" : templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getImg() {
        return img == null ? "" : img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHumidity() {
        return humidity == null ? "" : humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure == null ? "" : pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindspeed() {
        return windspeed == null ? "" : windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWinddirect() {
        return winddirect == null ? "" : winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getWindpower() {
        return windpower == null ? "" : windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getUpdatetime() {
        return updatetime == null ? "" : updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public List<TodayDescription> getIndex() {
        if (index == null) {
            return new ArrayList<>();
        }
        return index;
    }

    public void setIndex(List<TodayDescription> index) {
        this.index = index;
    }

    public List<Daily> getDaily() {
        if (daily == null) {
            return new ArrayList<>();
        }
        return daily;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }

    public  static  class TodayDescription implements Serializable {
        private static final long serialVersionUID = -5831139292421425105L;
        private String iname;//空调指数",
        private String ivalue;//部分时间开启",
        private String detail;//您将感到些燥热，

        public String getIname() {
            return iname == null ? "" : iname;
        }

        public void setIname(String iname) {
            this.iname = iname;
        }

        public String getIvalue() {
            return ivalue == null ? "" : ivalue;
        }

        public void setIvalue(String ivalue) {
            this.ivalue = ivalue;
        }

        public String getDetail() {
            return detail == null ? "" : detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    public static  class Daily implements Serializable {
        private static final long serialVersionUID = 2091522010537779064L;
        private String date;//2018-06-13",
        private String week;//星期三",
        private String sunrise;//04:45",
        private String sunset;//19:41",
        private Night night;
        private Day day;

        public String getDate() {
            return date == null ? "" : date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week == null ? "" : week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getSunrise() {
            return sunrise == null ? "" : sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset == null ? "" : sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public Night getNight() {
            return night;
        }

        public void setNight(Night night) {
            this.night = night;
        }

        public Day getDay() {
            return day;
        }

        public void setDay(Day day) {
            this.day = day;
        }
    }

    public static class Night implements Serializable {
        private static final long serialVersionUID = 3168441603877189838L;
        private String weather;//阴",
        private String templow;//17",
        private String img;//2",
        private String winddirect;//"东北风",
        private String windpower;//"微风"

        public String getWeather() {
            return weather == null ? "" : weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemplow() {
            return templow == null ? "" : templow;
        }

        public void setTemplow(String templow) {
            this.templow = templow;
        }

        public String getImg() {
            return img == null ? "" : img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getWinddirect() {
            return winddirect == null ? "" : winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower == null ? "" : windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }
    }
    public static class Day implements Serializable {

        private static final long serialVersionUID = 8552686339013065309L;
        private String weather;//"雷阵雨",
        private String temphigh;//"27",
        private String img;//"4",
        private String winddirect;//"东风",
        private String windpower;//"微风"

        public String getWeather() {
            return weather == null ? "" : weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemphigh() {
            return temphigh == null ? "" : temphigh;
        }

        public void setTemphigh(String temphigh) {
            this.temphigh = temphigh;
        }

        public String getImg() {
            return img == null ? "" : img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getWinddirect() {
            return winddirect == null ? "" : winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower == null ? "" : windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }
    }

    public static class Aqi implements Serializable {

        private static final long serialVersionUID = -8243464850142268184L;
        private String pm2_5;
        private String pm10;
        private String quality;
        private AqiInfo aqiinfo;

        public String getPm2_5() {
            return pm2_5;
        }

        public void setPm2_5(String pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public AqiInfo getAqiInfo() {
            return aqiinfo;
        }

        public void setAqiInfo(AqiInfo aqiInfo) {
            this.aqiinfo = aqiInfo;
        }
    }

    public static class Hourly implements Serializable {

        private static final long serialVersionUID = 6535888636538996648L;
        private String time;
        private String weather;
        private String temp;
        private String img;
        private Rect windyBoxRect; //表示风力的box
        private Point tempPoint; //温度的点坐标
        private int res = -1; //图片资源(有则绘制)

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public Point getTempPoint() {
            return tempPoint;
        }

        public void setTempPoint(Point tempPoint) {
            this.tempPoint = tempPoint;
        }

        public Rect getWindyBoxRect() {
            return windyBoxRect;
        }

        public void setWindyBoxRect(Rect windyBoxRect) {
            this.windyBoxRect = windyBoxRect;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class AqiInfo implements Serializable {

        private static final long serialVersionUID = -4136096244851238296L;
        private String updatetime;
        private ArrayList<MeasureListVo> measureList;

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public ArrayList<MeasureListVo> getMeasureList() {
            return measureList;
        }

        public void setMeasureList(ArrayList<MeasureListVo> measureList) {
            this.measureList = measureList;
        }
    }

    public static class MeasureListVo implements Serializable {

        private static final long serialVersionUID = 4222234681882241570L;
        private String zhishu;
        private String status;
        private String zhishuNeiRong;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getZhishu() {
            return zhishu;
        }

        public void setZhishu(String zhishu) {
            this.zhishu = zhishu;
        }

        public String getZhishuNeiRong() {
            return zhishuNeiRong;
        }

        public void setZhishuNeiRong(String zhishuNeiRong) {
            this.zhishuNeiRong = zhishuNeiRong;
        }
    }

}
