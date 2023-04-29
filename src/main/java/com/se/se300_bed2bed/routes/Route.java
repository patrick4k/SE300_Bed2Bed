package com.se.se300_bed2bed.routes;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;
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
        route.cost = "$0";

        final DecimalFormat decfor = new DecimalFormat("0.00");
        double driveCost = 10*Double.parseDouble(route.distance.replace("mi", "").replace(",", ""));

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
        }

        route.travelType = travelType;

        if (Objects.equals(route.getTravelType(), "Ride Share")) {
            if (driveCost > 300)
                route.cost = "N/A";
            else
                route.cost = "$" + decfor.format((driveCost * 0.3 * 1.5));
        }
        else if (Objects.equals(route.getTravelType(), "Driving")) {
            route.cost = "$" + decfor.format((driveCost / 25.4) * 3.66);
        }
        else if (Objects.equals(route.getTravelType(), "Transit")) {
            route.cost = "$" + decfor.format(driveCost * 0.228);
        }

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
