package com.se.se300_bed2bed.route_calculator;

import com.google.gson.Gson;
import com.se.se300_bed2bed.routes.GroundRoute;
import com.se.se300_bed2bed.routes.Route;

import java.util.ArrayList;
import java.util.List;
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
        List<Route> routesList = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            Map route = gson.fromJson(routes[i], Map.class);
            routesList.add(Route.fromGroundOutput(route));
            if (((Double) route.get("mode")) == 0) {
                route.put("mode",-1.0);
                routesList.add(Route.fromGroundOutput(route));
            }
        }
        this.routes = new Route[routesList.size()];
        this.routes = routesList.toArray(this.routes);
        this.finishedComputing = true;
        this.onFinishComputing.fire();
    }

}
