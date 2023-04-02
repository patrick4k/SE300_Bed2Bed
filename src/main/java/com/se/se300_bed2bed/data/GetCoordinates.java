package com.se.se300_bed2bed.data;

import javafx.scene.web.WebView;

import java.net.URL;

public class GetCoordinates {
    String location = "location:";
    protected WebView webView;
    public void getCoords() {
        this.webView = new WebView();
        URL url = this.getClass().getResource("Coordinates.html");
        assert url != null;
        webView.getEngine().load(url.toString());

        //webView.getEngine().executeScript(location+"\"+ "geocode();");

    }
}
