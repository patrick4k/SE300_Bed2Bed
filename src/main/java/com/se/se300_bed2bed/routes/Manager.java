package com.se.se300_bed2bed.routes;

import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.scenes.ShowResultsScene;

public class Manager {
    private String to, from;

    public Manager(String to, String from) {
        this.to = to;
        this.from = from;
    }

    public void compute() throws Exception {
        RouteGroundOnly groundOnly = new RouteGroundOnly(to, from);
        groundOnly.setOnFinishComputing(() -> {
            Bed2BedApp.TryGoTo(ShowResultsScene.class);
        });
    }

}
