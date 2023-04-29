package com.se.se300_bed2bed.util;

public class LLAPosition {

    /* Constructor */
    public LLAPosition(double latitude, double longitude) {
        this.setLL(latitude, longitude);
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
    public void setLL(double latitude, double longitude) {
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
