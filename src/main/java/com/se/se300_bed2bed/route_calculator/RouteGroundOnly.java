package com.se.se300_bed2bed.route_calculator;

import com.google.gson.Gson;
import com.se.se300_bed2bed.routes.GroundRoute;
import com.se.se300_bed2bed.routes.Route;

import java.util.Map;

public class RouteGroundOnly extends AbstractRouteCalc {


/* Constructor ------------------------------------------------------------------------------------------------------ */

    public RouteGroundOnly(String startingLocation, String endLocation) {
        super(startingLocation, endLocation);
        System.out.println(startingLocation + " -> " + endLocation);
        // Calculate routes
        GroundTransportation ground = new GroundTransportation(startingLocation, endLocation);
        ground.setOnCompute(this::processRoutes);
        ground.calculateRoutes();
    }

/* Methods ---------------------------------------------------------------------------------------------------------- */

    @Override
    protected void processRoutes(String[] routes) {
        Gson gson = new Gson();
        this.routes = new GroundRoute[routes.length];
        for (int i = 0; i < routes.length; i++) {
            Map route = gson.fromJson(routes[i], Map.class);
            this.routes[i] = Route.fromGroundOutput(route);
        }
        this.finishedComputing = true;
        this.onFinishComputing.fire();
    }

}
