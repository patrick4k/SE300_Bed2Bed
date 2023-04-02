package com.se.se300_bed2bed.scenes;

import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class GetCoordinatesScene extends FXMLController{


    @Override
    protected String fxmlName() {
        return "LatLng.fxml";
    }

    protected WebView webView;

    private void getCoordinates() {
        String to = "Daytona Beach FL";
        String from = "Orlando FL";

        //AnchorPane pane = (AnchorPane) this.scene.getRoot().lookup("#pane");
        this.webView = new WebView();
    }
}
