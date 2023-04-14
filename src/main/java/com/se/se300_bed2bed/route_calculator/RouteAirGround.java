package com.se.se300_bed2bed.route_calculator;

import com.se.se300_bed2bed.routes.Route;
import com.se.se300_bed2bed.util.Bed2BedEvent;

public class RouteAirGround extends AbstractRouteCalc {

    public RouteAirGround(String startingLocation, String endLocation) {
        super(startingLocation, endLocation);
        AirTransportation airTransportation = new AirTransportation(startingLocation, endLocation);
        airTransportation.setOnCompute(this::processRoutes);
        airTransportation.calculateRoutes();
    }

    private void processRoutes(String[] routes) {
        // TODO: Get route from AirTransportation and set to this.routes
        this.routes = new Route[0];
        this.finishedComputing = true;
        this.onFinishComputing.fire();
    }

}
