package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class CalculatingScene extends FXMLController {
    @Override
    protected String fxmlName() {
        return "calculatingScene.fxml";
    }

    @FXML
    ProgressBar progressBar; // TODO: Dont know how to use this, could just remove

    @Override
    public void onGoTO() {
        try {
            Bed2BedApp.manager.compute();
        }
        catch (Exception e) {
            // TODO: Put error handling here
            e.printStackTrace();
        }
    }
}
