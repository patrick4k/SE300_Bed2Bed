package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.util.LLAPosition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.util.Pair;
import org.w3c.dom.events.EventTarget;

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

    @FXML
    private Button getCoordinates;

    protected WebView webView;


    LLAPosition getCoords(double latitude, double longitude) {


        return null;
    }

    public void goToGoogleMapScene() {
        Bed2BedApp.TryGoTo(GoogleMapScene.class);
    }

    public void getCoordinates(ActionEvent event) {

        String start = originText.getText();
        String end = destinationText.getText();

        this.webView = new WebView();
        URL url = this.getClass().getResource("Coordinates.html");
        assert url != null;
        webView.getEngine().load(url.toString());

        webView.getEngine().documentProperty().addListener((v, o, document) -> {
            if (document == null) return;
            EventTarget click = (EventTarget) document.getElementById("eventHolder");
            click.addEventListener("click", (ev) -> {
                System.out.println(document.getElementById("lat").getTextContent());
            }, false);

            webView.getEngine().executeScript("lat = \"" + "lng = \"" );

        });
    }


}
