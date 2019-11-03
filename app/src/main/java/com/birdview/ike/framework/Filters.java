package com.birdview.ike.framework;

import android.widget.Switch;

public class Filters {
    private String size;
    private String filters;
    public static int roomprogress;

    public Filters(String size, String filters, int roomprogress, Switch button) {
        this.size = size;
        this.filters = filters;
        this.roomprogress = roomprogress;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public int getRoomprogress() {
        return roomprogress;
    }

    public void setRoomprogress(int roomprogress) {
        this.roomprogress = roomprogress;
    }
}
