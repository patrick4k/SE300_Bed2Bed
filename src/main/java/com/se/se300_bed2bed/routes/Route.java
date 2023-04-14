package com.se.se300_bed2bed.routes;

import java.util.Map;

public class Route {
    protected String travelType;
    protected String from, to;
    protected String distance, duration;

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

    public static Route fromGroundOutput(Map mapped_route) {

        Route route = new Route();
        route.distance = ((String) mapped_route.get("distance"));
        route.duration = ((String) mapped_route.get("duration"));
        route.to = ((String) mapped_route.get("to"));
        route.from = ((String) mapped_route.get("from"));

        String travelType = "";
        Double mode = (Double) mapped_route.get("mode");
        if (mode == 0.0) {
            travelType = "Driving";
        } else if (mode == 1) {
            travelType = "Walking";
        } else if (mode == 2) {
            travelType = "Biking";
        } else if (mode == 3) {
            travelType = "Transit";
        } else {
            System.out.println("mode outside of 0-3 received");
        }

        route.travelType = travelType;
        return route;
    }

}
