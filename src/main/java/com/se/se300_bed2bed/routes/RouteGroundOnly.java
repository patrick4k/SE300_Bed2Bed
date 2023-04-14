package com.se.se300_bed2bed.routes;

import com.google.gson.Gson;
import com.se.se300_bed2bed.route_calculator.GroundTransportation;
import com.se.se300_bed2bed.util.Bed2BedEvent;
import javafx.event.ActionEvent;

import java.util.Map;

public class RouteGroundOnly {

/* Attributes ------------------------------------------------------------------------------------------------------- */
    private final String startingLocation, endLocation;
    private Route[] routes;
    private boolean finishedComputing = false;
    private Bed2BedEvent onFinishComputing = () -> {};

/* Constructor ------------------------------------------------------------------------------------------------------ */

    public RouteGroundOnly(String startingLocation, String endLocation) {
        // Set Attributes
        this.startingLocation = startingLocation;
        this.endLocation = endLocation;

        // Calculate routes
        GroundTransportation ground = new GroundTransportation(startingLocation, endLocation);
        ground.setOnCompute(this::processRoutes);
        ground.calculateRoutes();
    }

/* Methods ---------------------------------------------------------------------------------------------------------- */

    private void processRoutes(String[] routes) {
        Gson gson = new Gson();
        this.routes = new Route[routes.length];
        for (int i = 0; i < routes.length; i++) {
            Map route = gson.fromJson(routes[i], Map.class);
            this.routes[i] = Route.fromGroundOutput(route);
        }
        this.finishedComputing = true;
        this.onFinishComputing.fire();
    }

/* Getters ---------------------------------------------------------------------------------------------------------- */

    public String getStartingLocation() {
        return startingLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public Route[] getRoutes() {
        return routes;
    }

    public boolean isFinishedComputing() {
        return finishedComputing;
    }

    public void setOnFinishComputing(Bed2BedEvent event) {
        if (this.finishedComputing) event.fire();
        else this.onFinishComputing = event;
    }
}
