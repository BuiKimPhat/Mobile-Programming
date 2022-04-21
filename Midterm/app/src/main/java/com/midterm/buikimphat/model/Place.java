package com.midterm.buikimphat.model;

import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String desc;

    @SerializedName("timeStamp")
    private String timeStamp;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("addr")
    private String addr;

    @SerializedName("e")
    private String e;

    @SerializedName("zip")
    private String zip;

    public Place(String title, String desc, String timeStamp, double lat, double lng, String addr, String e, String zip) {
        this.title = title;
        this.desc = desc;
        this.timeStamp = timeStamp;
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.e = e;
        this.zip = zip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
