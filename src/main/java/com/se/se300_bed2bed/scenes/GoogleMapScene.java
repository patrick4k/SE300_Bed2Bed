package com.se.se300_bed2bed.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleMapScene extends FXMLController implements Initializable {
    @Override
    protected String fxmlName() {
        return "GoogleMapScene.fxml";
    }

    @FXML
    private WebView mapView;

    private WebEngine engine;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        engine = mapView.getEngine();
        loadPage();
    }

    public void loadPage() {
        engine.load("file:///C:/Users/Andrea/AppData/Local/Temp/Temp1_gtfiles%20(1).zip/gtfiles/MapDisplay.html");
    }
}
