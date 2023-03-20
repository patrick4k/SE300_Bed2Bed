package com.se.se300_bed2bed.scenes;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.io.IOException;

public abstract class FXMLController {

    protected abstract String fxmlName();

    protected Scene scene;

    public Pair<Class<? extends FXMLController>, Scene> GET() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(this.fxmlName()));
        this.scene = new Scene(loader.load());
        Pair<Class<? extends FXMLController>, Scene> returnSet = new Pair<>(
                this.getClass(), // Scene Name
                scene); // Scene Value
        this.onLoad();
        return returnSet;
    }

    protected void onLoad() {  /* Override for On Load Event */  }
    public void onGoTo() {  /* Override for On GoTo Event */  }
}
