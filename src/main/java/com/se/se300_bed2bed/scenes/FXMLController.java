package com.se.se300_bed2bed.scenes;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.io.IOException;

public abstract class FXMLController {

    protected abstract String fxmlName();

    public Pair<Class<? extends FXMLController>, Scene> GET() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(this.fxmlName()));
        Scene scene = new Scene(loader.load());
        this.onload();
        return new Pair<>(
                this.getClass(), // Scene Name
                scene); // Scene Value
    }

    protected void onload() { }
    public void onGoTO() { }
}
