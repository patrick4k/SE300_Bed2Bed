package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import javafx.fxml.FXML;

public class MainScene extends FXMLController {

    @Override
    protected String fxmlName() {
        return "MainScene.fxml";
    }

    @FXML
    protected void goToMap() {
        Bed2BedApp.TryGoTo(MapScene.class);
    }

}
