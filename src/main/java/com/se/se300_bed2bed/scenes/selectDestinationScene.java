package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.util.LLAPosition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;

public class selectDestinationScene extends FXMLController{
    @Override
    protected String fxmlName() {
        return "selectDestinationScene.fxml";
    }
    @FXML
    private TextField originText;
    @FXML
    private TextField destinationText;

    protected WebView webView;


    LLAPosition getCoords(double latitude, double longitude) {


        return null;
    }

    public void goToGoogleMapScene() {
        Bed2BedApp.TryGoTo(GoogleMapScene.class);
    }

    /*
    public void autoComplete(ActionEvent event) {
        String from = originText.getText();
        String to = destinationText.getText();

        this.webView = new WebView();
        URL url = this.getClass().getResource("GetCoordinates.html");
        assert url != null;
        webView.getEngine().load(url.toString());

        webView.getEngine().executeScript(from + to + "autoComplete();");

    }
*/
}
