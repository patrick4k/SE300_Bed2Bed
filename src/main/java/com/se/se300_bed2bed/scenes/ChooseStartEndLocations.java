package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.route_calculator.GroundTransportation;
import com.se.se300_bed2bed.routes.Manager;
import com.se.se300_bed2bed.routes.RouteGroundOnly;
import com.se.se300_bed2bed.util.RouteComputeEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import org.w3c.dom.events.EventTarget;

import java.net.URL;

public class ChooseStartEndLocations extends FXMLController {

    @Override
    protected String fxmlName() {
        return "StartingLoc.fxml";
    }

    @FXML
    TextField originText;
    @FXML
    TextField destinationText;
    @FXML
    DatePicker calendar;
    @FXML
    Label errorLabel;

    WebEngine engine = new WebEngine();

    public void searchButton (ActionEvent event) {
        errorLabel.setText("");

        if (originText.getText().isBlank() || destinationText.getText().isBlank()) {
            errorLabel.setText("Must set both start and end locations");
            return;
        }
        Bed2BedApp.manager = new Manager(originText.getText(), destinationText.getText());
        Bed2BedApp.TryGoTo(CalculatingScene.class);
    }

    public void GoogleMapsCalculations(RouteComputeEvent routeComputeEvent) {
        String[] routes = new String[4];
        final int[] routes_index = {0};

        String startLocation = originText.getText();
        String endLocation = destinationText.getText();

        URL url = this.getClass().getResource("GoogleMapCalculations.html");
        assert url!= null;
        engine.load(url.toString());

        engine.documentProperty().addListener((v, o, document) -> {
            if (document == null) return;
            EventTarget click = (EventTarget) document.getElementById("eventHolder");
            click.addEventListener("click", (ev) -> {
                routes[routes_index[0]] = document.getElementById("output").getTextContent();
                routes_index[0]++;
                if (routes_index[0] > 3) {
                    routeComputeEvent.onRouteCalculated(routes);
                }
            }, false);

            engine.executeScript("to = \"" + startLocation + "\";"
                    +   "from = \"" + endLocation + "\";"
                    +   "calcRoute(0);" // Drive
                    +   "calcRoute(1);" // Walk
                    +   "calcRoute(2);" // Bike
                    +   "calcRoute(3);"); // Transit
        });

    }
}
