package com.se.se300_bed2bed.routes;

import com.google.gson.Gson;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Route {

    double getTotalPrice();
    double getTotalDuration();

    static GroundRoute fromGroundOutput(Map mapped_route) {

        GroundRoute route = new GroundRoute();
        route.distance = ((String) mapped_route.get("distance"));
        route.duration = ((String) mapped_route.get("duration"));
        route.to = ((String) mapped_route.get("to"));
        route.from = ((String) mapped_route.get("from"));

        String travelType = ((String) mapped_route.get("travelType"));
        if (travelType == null) {
            Double mode = (Double) mapped_route.get("mode");
            if (mode == -1) {
                travelType = "Ride Share";
            } else if (mode == 0.0) {
                travelType = "Driving";
            } else if (mode == 1) {
                travelType = "Walking";
            } else if (mode == 2) {
                travelType = "Biking";
            } else if (mode == 3) {
                travelType = "Transit";
            } else {
                System.out.println("mode outside of -1-3 received");
            }

            route.cost = "$0"; // TODO Get cost of route

        }

        route.travelType = travelType;
        return route;
    }

    static AirRoute fromAirTransport(Map mapped_route) {
        AirRoute route = new AirRoute();
        Map flight = (Map) mapped_route.get("flight");
        route.duration = convertDuration((String) flight.get("duration"));
        route.to = ((String) flight.get("to"));
        route.from = ((String) flight.get("from"));
        route.cost = "$" + flight.get("cost");

        route.fromAirport = Route.fromGroundOutput((Map) mapped_route.get("fromAirport"));
        route.toAirport = Route.fromGroundOutput((Map) mapped_route.get("toAirport"));

        return route;
    }

    private static String convertDuration(String isoDuration) {
        Pattern pattern = Pattern.compile(".*PT(\\d+)H(\\d+)M.*");
        Matcher matcher = pattern.matcher(isoDuration);

        if (matcher.matches()) {
            int hours = Integer.parseInt(matcher.group(1));
            int minutes = Integer.parseInt(matcher.group(2));
            return String.format("%d hr %d min", hours, minutes);
        }

        return "";
    }

}
