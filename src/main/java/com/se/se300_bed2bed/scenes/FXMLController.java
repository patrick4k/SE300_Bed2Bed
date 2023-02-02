package com.se.se300_bed2bed.scenes;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.io.IOException;

public abstract class FXMLController {

    protected abstract String sceneName();
    protected abstract String fxmlName();

    public Pair<String, Scene> GET() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(this.fxmlName()));
        return new Pair<>(
                this.sceneName(), // Scene Name
                new Scene(loader.load())); // Scene Value
    }
}
