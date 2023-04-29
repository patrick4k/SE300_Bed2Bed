package com.se.se300_bed2bed.route_calculator;

import com.se.se300_bed2bed.util.RouteComputeEvent;
import javafx.scene.web.WebEngine;
import org.w3c.dom.events.EventTarget;

import java.net.URL;

public class GroundTransportation {

/* Attributes ------------------------------------------------------------------------------------------------------- */
    private String startLocation, endLocation;
    private RouteComputeEvent onCompute = (routes) -> {};
    private String[] routes = new String[4];


/* Constructors ----------------------------------------------------------------------------------------------------- */

    public GroundTransportation(String startLocation, String endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public GroundTransportation(String startLocation, String endLocation, RouteComputeEvent onCompute) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.onCompute = onCompute;
    }


/* Methods -----------------------------------------------------------------------------------------------------------*/

    public void calculateRoutes() {
        this.routes = new String[4];
        WebEngine engine = new WebEngine();
        final int[] routes_index = {0};

        URL url = this.getClass().getResource("GoogleMapCalculations.html");
        engine.load(url.toString());

        // Waits for document to load then executes
        engine.documentProperty().addListener((v, o, document) -> {
            if (document == null) throw new RuntimeException("Null Document on HTML Load");

            // <button id="eventHolder"> is a DOM element used to trigger a JavaFX reaction to a JavaScript action
            EventTarget click = (EventTarget) document.getElementById("eventHolder");
            click.addEventListener("click", (ev) -> {

                // Get current route data from <p id="output"> DOM element
                // Append output to routes array
                this.routes[routes_index[0]] = document.getElementById("output").getTextContent();

                // increase count
                routes_index[0]++;

                // if routes count is 4, trigger onRouteCalculated event
                if (routes_index[0] > 3) {

                    this.onCompute.onRouteCalculated(this.routes);
                }
            }, false);

            // Set from and to values in engine, execute calcRoute for all 4 modes
            engine.executeScript("to = \"" + this.endLocation + "\";"
                    +   "from = \"" + this.startLocation + "\";"
                    +   "calcRoute(0);" // Drive
                    +   "calcRoute(1);" // Walk
                    +   "calcRoute(2);" // Bike
                    +   "calcRoute(3);" // Transit
            );
        });

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
