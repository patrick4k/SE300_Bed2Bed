package com.se.se300_bed2bed.scenes;

import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import org.w3c.dom.Element;
import org.w3c.dom.events.EventTarget;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartTrip extends FXMLController {
    @Override
    protected String fxmlName() {
        return "StartTrip.fxml";
    }

    protected WebView webView;

    @Override
    public void onGoTo() {
//        this.loadGetCarTrnsportHTML();
        this.loadDistanceHMTL();
    }

    private void loadGetCarTrnsportHTML() {
        String to = "St. Petersburg FL";
        String from = "Orlando FL";

        AnchorPane pane = (AnchorPane) this.scene.getRoot().lookup("#pane");

        this.webView = new WebView();
        URL url = this.getClass().getResource("GetCarTransport.html");
        assert url != null;
        webView.getEngine().load(url.toString());

        webView.getEngine().documentProperty().addListener((v, o, document) -> {
            if (document != null) {
                EventTarget click = (EventTarget) document.getElementById("eventHolder");
                click.addEventListener("click", (ev) -> {
                    System.out.println(document.getElementById("output").getTextContent());
                }, false);

                webView.getEngine().executeScript("to = \""   + to   + "\";"
                        +   "from = \"" + from + "\";"
                        +   "calcRoute();");
            }
        });

        webView.setPrefWidth(pane.getPrefWidth());
        webView.setPrefHeight(pane.getPrefHeight());

        pane.getChildren().add(webView);
    }

    private void loadDistanceHMTL() {
        AnchorPane pane = (AnchorPane) this.scene.getRoot().lookup("#pane");

        this.webView = new WebView();
        URL url = this.getClass().getResource("Distance.html");
        assert url != null;
        webView.getEngine().load(url.toString());

        webView.getEngine().documentProperty().addListener((v, o, document) -> {
            if (document == null) return;
            Element btn = document.getElementById("goButton");
            ((EventTarget) btn).addEventListener("click", (ev) -> {
                Object outputObj = webView.getEngine().executeScript("calcRoute();");
                System.out.println(outputObj);
            },false);
        });

        webView.setPrefWidth(pane.getPrefWidth());
        webView.setPrefHeight(pane.getPrefHeight());

        pane.getChildren().add(webView);
    }

}
