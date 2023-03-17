package com.se.se300_bed2bed.scenes;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventTarget;

import java.net.URL;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartTrip extends FXMLController {
    @Override
    protected String fxmlName() {
        return "StartTrip.fxml";
    }

    protected WebView webView;

    @Override
    protected void onLoad() {
        AnchorPane pane = (AnchorPane) this.scene.getRoot().lookup("#pane");

        this.webView = new WebView();
        URL url = this.getClass().getResource("Distance.html");
        assert url != null;
        webView.getEngine().load(url.toString());


        webView.getEngine().documentProperty().addListener((v, o, document) -> {
            if (document != null) {
                Element btn = document.getElementById("goButton");
                ((EventTarget) btn).addEventListener("click", (ev) -> {
                    webView.getEngine().executeScript("calcRoute();");
                    onGo(document);
                },false);
            }
        });

        webView.setPrefWidth(pane.getPrefWidth());
        webView.setPrefHeight(pane.getPrefHeight());

        pane.getChildren().add(webView);
        super.onLoad();
    }

    private void onGo(Document document) {
        String outputContent = "";
        Element output = null;
        while (output == null) {
            output = document.getElementById("output");
        }

        outputContent = output.getTextContent();

        System.out.println(outputContent);

        Pattern outputParser = Pattern.compile("From:(.*)To:(.*)Driving Distance:(.*)Duration:(.*)");
        Matcher matcher = outputParser.matcher(outputContent);
        if (matcher.find())
            System.out.println("Match!");
        else System.out.println("No match");

        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));
        System.out.println(matcher.group(4));

//        System.out.println("Distance: " + distanceNode.getTextContent());
//        System.out.println("Duration: " + durationNode.getTextContent());
    }
}
