package com.se.se300_bed2bed.routes;

import java.util.Map;

public class GroundRoute implements Route {
    protected String travelType;
    protected String from, to;
    protected String distance, duration, cost;

    public String getTravelType() {
        return travelType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }

    public String getCost() {
        return cost;
    }

}
