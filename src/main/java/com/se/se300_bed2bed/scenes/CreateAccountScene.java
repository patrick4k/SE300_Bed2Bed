package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import javafx.fxml.FXML;

public class CreateAccountScene extends FXMLController{
    @Override
    protected String fxmlName() {
        return ("CreateAccountScene.fxml");
    }
    @FXML
    protected void goToLogin() {
        Bed2BedApp.TryGoTo(LoginScene.class);
    }
}
