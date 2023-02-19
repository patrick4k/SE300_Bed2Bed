package com.se.se300_bed2bed.util;

public class LLAPosition {

    /* Constructor */
    public LLAPosition(double longitude, double latitude) {
        this.setLL(longitude, latitude);
    }


    /* Attributes */
    private double longitude;
    private double latitude;


    /* Getters */
    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }


    /* Setters */
    public void setLL(double longitude, double latitude) {
        this.setLong(longitude);
        this.setLat(latitude);
    }

    public void setLong(double longitude) {
        this.longitude = longitude;
    }

    public void setLat(double latitude) {
        this.latitude = latitude;
    }
}
