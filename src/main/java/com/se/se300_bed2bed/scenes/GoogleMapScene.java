package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.util.LLAPosition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleMapScene extends FXMLController implements Initializable {
    @Override
    protected String fxmlName() {
        return "GoogleMapScene.fxml";
    }

    LLAPosition costCalc (double longitude, double latitude) {

        return null;
    }

    @FXML
    private WebView mapView;

    private WebEngine engine;

    @FXML
    private ChoiceBox<String> methodOfTravel;

    @FXML
    private Label MOTlabel;


    // TO-DO: Cost association
    private String[] travelMethods = {"DRIVING", "WALKING: $0", "BICYCLING: $0", "TRANSIT", "RIDE SHARE"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        methodOfTravel.getItems().addAll(travelMethods);
        methodOfTravel.setOnAction(this::changeMOT);
        changeMap();
    }

    public void changeMOT(ActionEvent event) {
        String changeMethod = methodOfTravel.getValue();
        MOTlabel.setText("");
    }

    public void changeMap() {
        if(travelMethods.equals("DRIVING")) {
            //TO-DO: change map to reflect route with driving path
        } else if (travelMethods.equals("WALKING")) {
            //TO-DO: change map to reflect route with walking path
        } else if (travelMethods.equals("BICYCLING")) {
            //TO-DO: change map to reflect route with Bicycling path
        }else if (travelMethods.equals("TRANSIT")){
            //TO-DO: change map to reflect route with transit path
        }else {
            //TO-DO: change map to reflect route with driving path
        }
    }
}
