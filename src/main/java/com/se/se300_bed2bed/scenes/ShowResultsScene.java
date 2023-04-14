package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.routes.Route;

public class ShowResultsScene extends FXMLController {
    @Override
    protected String fxmlName() {
        return "showResults.fxml";
    }

    @Override
    public void onGoTo() {
        System.out.println("Displaying Results:");
        for (Route route: Bed2BedApp.manager.getRoutes()) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println(route.getTravelType());
            System.out.println(route.getFrom() + " -> " + route.getTo());
            System.out.println(route.getDistance());
            System.out.println(route.getDuration());
        }
    }
}
