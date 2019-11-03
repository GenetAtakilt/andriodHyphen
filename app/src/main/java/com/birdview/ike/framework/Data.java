package com.birdview.ike.framework;

public class Data {
    private int icon;
    private int toicon;
    private String fromLoc;
    private String toLoc;
    private String calendertxt;
    private String size;
    private int calender;
    private String cartype;
    private int imgclr;
    private String carcode;
    private int imgcmr;
    private String txtclr;
    private int iconclr;
    private String carname;
    private int iconcar;
    private int progressBar;

    public Data(int icon, int toicon,int imgclr, int imgcmr,int iconclr, int iconcar,int calender, String fromLoc, String toLoc, String calendertxt,String size,
                String cartype, String carcode, String txtclr,String carname,int progressBar) {
        this.icon = icon;
        this.progressBar = progressBar;
        this.toicon = toicon;
        this.fromLoc = fromLoc;
        this.toLoc = toLoc;
        this.calendertxt = calendertxt;
        this.size = size;
        this.calender = calender;
        this.carcode = carcode;
        this.carname = carname;
        this.cartype = cartype;
        this.iconcar = iconcar;
        this.iconclr = iconclr;
        this.imgclr = imgclr;
        this.imgcmr = imgcmr;
        this.txtclr = txtclr;
    }

    public int getToicon() {
        return toicon;
    }

    public void setToicon(int toicon) {
        this.toicon = toicon;
    }

    public int getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(int progressBar) {
        this.progressBar = progressBar;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public int getImgclr() {
        return imgclr;
    }

    public void setImgclr(int imgclr) {
        this.imgclr = imgclr;
    }

    public String getCarcode() {
        return carcode;
    }

    public void setCarcode(String carcode) {
        this.carcode = carcode;
    }

    public int getImgcmr() {
        return imgcmr;
    }

    public void setImgcmr(int imgcmr) {
        this.imgcmr = imgcmr;
    }

    public String getTxtclr() {
        return txtclr;
    }

    public void setTxtclr(String txtclr) {
        this.txtclr = txtclr;
    }

    public int getIconclr() {
        return iconclr;
    }

    public void setIconclr(int iconclr) {
        this.iconclr = iconclr;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public int getIconcar() {
        return iconcar;
    }

    public void setIconcar(int iconcar) {
        this.iconcar = iconcar;
    }

    public int getCalender() {
        return calender;
    }

    public void setCalender(int calender) {
        this.calender = calender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getToIcon() {
        return toicon;    }

    public void setToIcon(int icons) {
        this.toicon = toicon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    public String getCalendertxt() {
        return calendertxt;
    }

    public void setCalendertxt(String calendertxt) {
        this.calendertxt = calendertxt;
    }
}
