package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;

public class ChooseStartEndLocations extends FXMLController {

    @Override
    protected String fxmlName() {
        return "StartingLoc.fxml";
    }

    @FXML
    TextField originText;
    @FXML
    TextField destinationText;
    @FXML
    DatePicker calendar;
    @FXML
    Label errorLabel;

    WebEngine engine = new WebEngine();

    public void searchButton (ActionEvent event) {
        boolean hasError = false;
        errorLabel.setText("");

        if (originText.getText().isBlank() || destinationText.getText().isBlank()) {
            errorLabel.setText(errorLabel.getText() + "\nMust set both start and end locations");
            hasError = true;
        }
        if (calendar.getValue() == null || calendar.getValue().toString().isBlank()) {
            errorLabel.setText(errorLabel.getText() + "\nMust select date");
            hasError = true;
        }
        if (hasError) return;

        Bed2BedApp.manager.setToFrom(originText.getText(), destinationText.getText());
        Bed2BedApp.manager.setTargetDate(calendar.getValue().toString());
        Bed2BedApp.TryGoTo(CalculatingScene.class);
    }
}
