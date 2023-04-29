package com.se.se300_bed2bed.route_calculator;

import com.google.gson.Gson;
import com.se.se300_bed2bed.routes.AirRoute;
import com.se.se300_bed2bed.routes.Route;

import java.util.Map;

public class RouteAirGround extends AbstractRouteCalc {

    public RouteAirGround(String startingLocation, String endLocation) {
        super(startingLocation, endLocation);
        AirTransportation airTransportation = new AirTransportation(startingLocation, endLocation);
        airTransportation.setOnCompute(this::processRoutes);
    }

    @Override
    protected void processRoutes(String[] routes) {
        Gson gson = new Gson();
        this.routes = new AirRoute[routes.length];

        for (int i = 0; i < routes.length; i++) {
            Map route = gson.fromJson(routes[i], Map.class);
            this.routes[i] = Route.fromAirTransport(route);
        }

        this.finishedComputing = true;
        this.onFinishComputing.fire();
    }

}
