package com.se.se300_bed2bed.routes;

public class AirRoute implements Route {

    protected GroundRoute fromAirport, toAirport;
    protected String travelType = "Flight";
    protected String to, from;
    protected String duration, cost;

    public GroundRoute getFromAirport() {
        return fromAirport;
    }

    public GroundRoute getToAirport() {
        return toAirport;
    }

    public String getTravelType() {
        return travelType;
    }

    public String getDuration() {
        return duration;
    }

    public String getCost() {
        return cost;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }
}
