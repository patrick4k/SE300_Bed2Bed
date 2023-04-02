package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.util.LLAPosition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class selectDestinationScene extends FXMLController{
    @Override
    protected String fxmlName() {
        return "selectDestinationScene.fxml";
    }
    @FXML
    private TextField originText;
    @FXML
    private TextField destinationText;

    LLAPosition getCoords(double latitude, double longitude) {

        return null;
    }

    public void goToGoogleMapScene() {
        Bed2BedApp.TryGoTo(GoogleMapScene.class);
    }

}
