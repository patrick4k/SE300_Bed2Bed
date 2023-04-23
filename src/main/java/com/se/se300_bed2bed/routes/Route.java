package com.se.se300_bed2bed.routes;

import com.google.gson.Gson;

import java.util.Map;

public interface Route {

    static GroundRoute fromGroundOutput(Map mapped_route) {

        GroundRoute route = new GroundRoute();
        route.distance = ((String) mapped_route.get("distance"));
        route.duration = ((String) mapped_route.get("duration"));
        route.to = ((String) mapped_route.get("to"));
        route.from = ((String) mapped_route.get("from"));
        route.cost = "$0"; // TODO Get cost of route

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

    static AirRoute fromAirTransport(Map mapped_route) {
        Gson gson = new Gson();
        AirRoute route = new AirRoute();
        route.duration = ((String) mapped_route.get("duration"));
        route.to = ((String) mapped_route.get("to"));
        route.from = ((String) mapped_route.get("from"));
        route.cost = ((String) mapped_route.get("cost"));

        route.fromAirport = Route.fromGroundOutput(
                gson.fromJson(
                        (String) mapped_route.get("fromAirport"), Map.class
                )
        );

        route.toAirport = Route.fromGroundOutput(
                gson.fromJson(
                        (String) mapped_route.get("toAirport"), Map.class
                )
        );

        return route;
    }

}
