package com.birdview.ike.framework;

public class FlightSearch  {
    private int id;

    private String data;
    private String data1;
    private int Icon;
    private int Icon1;


    public FlightSearch(int id, String data, String data1, int icon, int icon1) {
        this.id = id;
        this.data = data;
        this.data1 = data1;
        Icon = icon;
        Icon1 = icon1;
    }


    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getData1() {
        return data1;
    }

    public int getIcon() {
        return Icon;
    }

    public int getIcon1() {
        return Icon1;
    }
}
