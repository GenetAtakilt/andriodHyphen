package com.birdview.ike.framework;

public class DataFurniture {
    private String fromLoc;
    private String toLoc;
    private String size;
    private String price;
    private String calaendertxt;
    private int calender;
    private int icon;
    private int toIcon;
    private int progress;
    private int progressp;

    public DataFurniture(String fromLoc, String toLoc,String calaendertxt, String size, String price, int calender, int icon, int toIcon, int progress, int progressp) {
        this.fromLoc = fromLoc;
        this.calaendertxt = calaendertxt;
        this.toLoc = toLoc;
        this.size = size;
        this.price = price;
        this.icon = icon;
        this.toIcon = toIcon;
        this.progress = progress;
        this.progressp = progressp;
        this.calender = calender;
    }

    public int getCalender() {
        return calender;
    }

    public void setCalender(int calender) {
        this.calender = calender;
    }

    public String getCalaendertxt() {
        return calaendertxt;
    }

    public void setCalaendertxt(String calaendertxt) {
        this.calaendertxt = calaendertxt;
    }

    public String getFromLoc() {
        return fromLoc;
    }

    public void setFromLoc(String fromLoc) {
        this.fromLoc = fromLoc;
    }

    public String getToLoc() {
        return toLoc;
    }

    public void setToLoc(String toLoc) {
        this.toLoc = toLoc;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getToIcon() {
        return toIcon;
    }

    public void setToIcon(int toIcon) {
        this.toIcon = toIcon;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgressp() {
        return progressp;
    }

    public void setProgressp(int progressp) {
        this.progressp = progressp;
    }
}
