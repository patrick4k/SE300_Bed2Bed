package com.se.se300_bed2bed.route_calculator;

import com.se.se300_bed2bed.routes.GroundRoute;
import com.se.se300_bed2bed.routes.Route;
import com.se.se300_bed2bed.util.Bed2BedEvent;

public abstract class AbstractRouteCalc {
    protected final String startingLocation, endLocation;
    protected Route[] routes;
    protected boolean finishedComputing = false;
    protected Bed2BedEvent onFinishComputing = () -> {};

    public AbstractRouteCalc(String startingLocation, String endLocation) {
        this.startingLocation = startingLocation;
        this.endLocation = endLocation;
    }

    public void setOnFinishComputing(Bed2BedEvent event) {
        if (this.finishedComputing) event.fire();
        else this.onFinishComputing = event;
    }

    abstract protected void processRoutes(String[] routes);

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
}
