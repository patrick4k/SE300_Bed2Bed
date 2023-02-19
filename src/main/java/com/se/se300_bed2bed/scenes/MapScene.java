package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import javafx.fxml.FXML;

public class MapScene extends FXMLController {

    @Override
    protected String fxmlName() {
        return "MapScene.fxml";
    }

    @FXML
    protected void backToMain() {
        Bed2BedApp.TryGoTo(MainScene.class);
    }
}
