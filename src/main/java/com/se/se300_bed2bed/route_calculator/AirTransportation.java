package com.se.se300_bed2bed.route_calculator;

import com.se.se300_bed2bed.util.RouteComputeEvent;
import javafx.scene.web.WebEngine;
import org.w3c.dom.events.EventTarget;

import java.net.URL;

public class AirTransportation {
/* Attributes ------------------------------------------------------------------------------------------------------- */
    private String startLocation, endLocation;
    private RouteComputeEvent onCompute = (routes) -> {};
    private String[] routes = new String[4];


/* Constructors ----------------------------------------------------------------------------------------------------- */

    public AirTransportation(String startLocation, String endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.calculateRoutes();
    }

/* Methods -----------------------------------------------------------------------------------------------------------*/

    public void calculateRoutes() {
        // TODO: Calculate air routes
        String[] routes = new String[0];
        this.onCompute.onRouteCalculated(routes);
    }


/* Setters & Getter ------------------------------------------------------------------------------------------------- */

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public RouteComputeEvent getOnCompute() {
        return onCompute;
    }

    public void setOnCompute(RouteComputeEvent onCompute) {
        this.onCompute = onCompute;
    }
}
