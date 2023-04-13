package com.se.se300_bed2bed.data;

import javafx.event.ActionEvent;
import javafx.scene.web.WebEngine;

import java.net.URL;

public class ReadFromHTML {

    public void searchButton (ActionEvent event) {
        GoogleMapsCalculations("Daytona Beach FL", "Orlando FL");
    }

    public void GoogleMapsCalculations (String startLocation, String endLocation) {

        startLocation = "";
        endLocation = "";

        WebEngine engine = new WebEngine();

        URL url = this.getClass().getResource("GoogleMapsCalculations.html");
        assert url!= null;
        engine.load(url.toString());

        //System.out.println(document.getElementById("output").getContent());

        engine.executeScript("from" + "to" + "calcRoute();");
    }
}
