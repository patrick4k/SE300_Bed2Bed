package com.se.se300_bed2bed.scenes;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class GoogleMapScene extends FXMLController{
    @Override
    protected String fxmlName() {
        return "GoogleMapScene.fxml";
    }

    @FXML
    private WebView mapView;

}
