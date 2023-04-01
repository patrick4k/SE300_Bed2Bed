package com.se.se300_bed2bed.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleMapScene extends FXMLController implements Initializable {
    @Override
    protected String fxmlName() {
        return "GoogleMapScene.fxml";
    }

    @FXML
    private WebView mapView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void loadPage() {
       // engine.load("MapDisplay.html");
    }
}
