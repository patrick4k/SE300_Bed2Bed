package com.se.se300_bed2bed.data;

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
        return "selectDestinationScene.fxml";
    }

    @FXML
    TextField originText;
    @FXML
    TextField destinationText;

    public void searchButton (ActionEvent event) {
        GoogleMapsCalculations("Daytona Beach FL", "Orlando FL");
    }

    public void GoogleMapsCalculations (String startLocation, String endLocation) {

        WebEngine engine = new WebEngine();

        startLocation = originText.getText();
        endLocation = destinationText.getText();


        URL url = this.getClass().getResource("GoogleMapsCalculations.html");
        assert url!= null;
        engine.load(url.toString());

        //System.out.println(document.getElementById("output").getContent());

        engine.executeScript("from" + startLocation + "to" + endLocation + "calcRoute();");
    }


}
