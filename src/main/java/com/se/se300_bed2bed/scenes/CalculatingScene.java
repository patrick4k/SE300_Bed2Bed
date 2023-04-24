package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.Objects;

public class CalculatingScene extends FXMLController {
    @Override
    protected String fxmlName() {
        return "calculatingScene.fxml";
    }

    @FXML
    ProgressBar progressBar;

    @FXML
    Label loadingLabel, errorLabel;

    @FXML
    ImageView planeGif;

    @Override
    public void onGoTo(Scene scene) {
        // Get scene nodes
        progressBar = (ProgressBar) scene.getRoot().getChildrenUnmodifiable().get(0).lookup("#progressBar");
        errorLabel = (Label) scene.getRoot().getChildrenUnmodifiable().get(2).lookup("#errorLabel");
        planeGif = (ImageView) scene.getRoot().getChildrenUnmodifiable().get(3);

        // Clear error label
        errorLabel.setText("");

        // Clear progress bar
        progressBar.setProgress(0.1);

        // Set planeGif on image view
        try {
            planeGif.setImage(new Image(Objects.requireNonNull(this.getClass().getResource("plane.gif")).toURI().toString()));
        } catch (URISyntaxException ignore) { }

        // To change scenes before starting computation
        final boolean[] startedComputing = {false};

        // Timeout timeline to show warning once progress bar is full
        Timeline timeout = new Timeline();
        timeout.getKeyFrames().add(new KeyFrame(Duration.seconds(0.025), actionEvent -> {

            progressBar.setProgress(1.01*progressBar.getProgress());

            if (!startedComputing[0]) {
                startedComputing[0] = true;
                // Trigger to compute, stop timeout on complete
                Bed2BedApp.manager.compute(timeout::stop);
            }

            if (progressBar.getProgress() >= 2) {
                errorLabel.setText("This is taking longer than expected...\n\n" +
                        "Check your internet connection\n" +
                        "and if you inputted correct addresses");
                timeout.stop();
            }
        }));
        timeout.setCycleCount(Timeline.INDEFINITE);
        timeout.play();
    }

    @FXML
    protected void goBack(MouseEvent event) {
        Bed2BedApp.manager.clear();
        Bed2BedApp.TryGoTo(ChooseStartEndLocations.class);
    }
}
