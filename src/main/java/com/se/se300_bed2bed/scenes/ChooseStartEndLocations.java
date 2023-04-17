package com.se.se300_bed2bed.scenes;

import com.google.gson.Gson;
import com.se.se300_bed2bed.Bed2BedApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    ChoiceBox<String> savedTrips;

    WebEngine engine = new WebEngine();

    @Override
    public void onGoTo(Scene scene) {
        savedTrips = (ChoiceBox<String>) scene.getRoot().getChildrenUnmodifiable().get(7);
        Gson gson = new Gson();

        Map[] saved_data = gson.fromJson(Bed2BedApp.manager.getUser().saved_data, Map[].class);

        for (Map route: saved_data) {
            savedTrips.getItems().add(route.get("from") + " -> " + route.get("to"));
        }

    }

    @FXML
    protected void chooseSavedTrip() {
        Pattern pattern = Pattern.compile("(.+) -> (.+)");
        Matcher matcher = pattern.matcher(savedTrips.getValue());
        if (matcher.matches()) {
            String from = matcher.group(1);
            String to = matcher.group(2);
            this.originText.setText(from);
            this.destinationText.setText(to);
        }
    }

    public void searchButton(ActionEvent event) {
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
        if (calendar.getValue().isBefore(LocalDate.now())) {
            errorLabel.setText(errorLabel.getText() + "\nCannot select pasted dates");
            hasError = true;
        }
        if (hasError) return;

        Bed2BedApp.manager.setFromTo(originText.getText(), destinationText.getText());
        Bed2BedApp.manager.setTargetDate(calendar.getValue().toString());
        Bed2BedApp.TryGoTo(CalculatingScene.class);
    }
}
