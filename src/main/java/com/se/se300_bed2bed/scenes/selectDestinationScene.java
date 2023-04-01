package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
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

    public void goToGoogleMapScene() {
        Bed2BedApp.TryGoTo(GoogleMapScene.class);
    }

}
