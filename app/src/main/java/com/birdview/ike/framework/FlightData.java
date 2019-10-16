package com.birdview.ike.framework;

public class FlightData {
    private int id;
    private String price;
    private String place;
    private String duration;
    private String stops;
    private String range;
    private String time;
    private String type;
    private int image;






    public FlightData(int id, String price, String place, String duration, String stops, String range, String time, String type,int image) {
        this.id = id;
        this.price = price;
        this.place = place;
        this.duration = duration;
        this.stops = stops;
        this.range = range;
        this.time = time;
        this.type = type;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getPlace() {
        return place;
    }

    public String getDuration() {
        return duration;
    }

    public String getStops() {
        return stops;
    }

    public String getRange() {
        return range;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public int getImage() {
        return image;
    }
}
