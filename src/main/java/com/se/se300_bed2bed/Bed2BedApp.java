package com.se.se300_bed2bed;

import com.se.se300_bed2bed.scenes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bed2BedApp extends Application {
    private static Stage stage;
    private static final Map<Class<? extends FXMLController>, Scene> SceneMap = new HashMap<>();
    private static final Map<Class<? extends FXMLController>, FXMLController> SceneControllerMap = new HashMap<>();

    public static void TryGoTo(Class<? extends FXMLController> sceneClass) {
        if (SceneMap.containsKey(sceneClass)) {
            Scene scene = SceneMap.get(sceneClass);
            stage.setScene(scene);
            SceneControllerMap.get(sceneClass).onGoTo();
        }
        else {
            throw new RuntimeException(sceneClass.getName() + " DOES NOT EXIST\n" +
                    "\tMaybe it isn't added to Bed2BedApp.initScenes?");
        }
    }

    @Override
    public void start(Stage stage) {
        initScenes(stage);
        Bed2BedApp.TryGoTo(StartTrip.class);
        stage.setTitle("Bed2Bed");
        stage.show();
    }

    private static void initScenes(Stage stage) {
        Bed2BedApp.stage = stage;
        List<Class<? extends FXMLController>> fxmlClasses = List.of(
                // ADD SCENE CLASSES HERE
                MapScene.class,
                LoginScene.class,
                CreateAccountScene.class,
                StartTrip.class
        );

        for (Class<? extends FXMLController> fxmlClass: fxmlClasses) {
            Bed2BedApp.pushScene(fxmlClass);
        }
    }

    private static void pushScene(Class<? extends FXMLController> className) {
        try {
            FXMLController controller = className.getConstructor().newInstance();
            Pair<Class<? extends FXMLController>, Scene> scenePair = controller.GET();
            SceneMap.put(scenePair.getKey(), scenePair.getValue());
            SceneControllerMap.put(scenePair.getKey(), controller);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
