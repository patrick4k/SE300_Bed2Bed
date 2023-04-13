package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.scenes.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import org.w3c.dom.Text;

import java.net.URL;

public class ReadFromHTML extends FXMLController {

    @Override
    protected String fxmlName() {
        // CHANGE TO BURTE SCENE NAME
        return "selectDestinationScene.fxml";
    }

    // CHAGNE TO BURTE SCENE ID NAMEs ***
    @FXML
    TextField originText;
    @FXML
    TextField destinationText;

    WebEngine engine = new WebEngine();

    public void searchButton (ActionEvent event) {
        GoogleMapsCalculations( );
    }

    @Override
    protected void onload() {
        URL url = this.getClass().getResource("GoogleMapCalculations.html");
        assert url!= null;
        engine.load(url.toString());
    }

    public void GoogleMapsCalculations ( ) {

        // CHANGE TO BURTE SCENE TEXTIDs ***
        String startLocation = originText.getText();
        String endLocation = destinationText.getText();

        //System.out.println(document.getElementById("output").getContent());

        System.out.println("from=\"" + startLocation + "\";to=\"" + endLocation + "\";calcRoute(0);");

        engine.executeScript("from=\"" + startLocation + "\";");
        engine.executeScript("to=\"" + endLocation + "\";");
        engine.executeScript("calcRoute(0);");
//        engine.executeScript("from='" + startLocation + "';to='" + endLocation + "';calcRoute(1);");
//        engine.executeScript("from='" + startLocation + "';to='" + endLocation + "';calcRoute(2);");
//        engine.executeScript("from='" + startLocation + "';to='" + endLocation + "';calcRoute(3);");
    }
}
