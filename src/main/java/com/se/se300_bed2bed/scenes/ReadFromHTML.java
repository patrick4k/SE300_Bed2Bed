package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.scenes.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import org.w3c.dom.Text;
import org.w3c.dom.events.EventTarget;

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

    public void GoogleMapsCalculations() {

        // CHANGE TO BURTE SCENE TEXTIDs ***
        String startLocation = originText.getText();
        String endLocation = destinationText.getText();

        URL url = this.getClass().getResource("GoogleMapCalculations.html");
        assert url!= null;
        engine.load(url.toString());

        engine.documentProperty().addListener((v, o, document) -> {
            if (document == null) return;
            EventTarget click = (EventTarget) document.getElementById("eventHolder");
            click.addEventListener("click", (ev) -> {
                System.out.println(document.getElementById("output").getTextContent());
            }, false);

            engine.executeScript("to = \"" + startLocation + "\";"
                    +   "from = \"" + endLocation + "\";"
                    +   "calcRoute(0);"
                    +   "calcRoute(1);"
                    +   "calcRoute(2);"
                    +   "calcRoute(3);");
        });

    }
}
