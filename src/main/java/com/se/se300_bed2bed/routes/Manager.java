package com.se.se300_bed2bed.routes;

import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.route_calculator.RouteAirGround;
import com.se.se300_bed2bed.route_calculator.RouteGroundOnly;
import com.se.se300_bed2bed.scenes.ShowResultsScene;
import com.se.se300_bed2bed.scenes.UserAcct;
import com.se.se300_bed2bed.util.Bed2BedEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private UserAcct user;
    private String to, from;
    private String targetDate;
    private final ArrayList<Route> routes = new ArrayList<>();

    RouteGroundOnly groundOnly;
    RouteAirGround airGround;
    Bed2BedEvent onFinishCompute;

    public void compute(Bed2BedEvent onFinish) {
        int[] num_finished = {0};
        this.onFinishCompute = () -> {
            num_finished[0]++;
            if (num_finished[0] >= 2)
                this.goToResults(onFinish);
        };
        this.groundOnly = new RouteGroundOnly(this.from, this.to);
        this.groundOnly.setOnFinishComputing(this.onFinishCompute);
        this.airGround = new RouteAirGround(this.from, this.to);
        this.airGround.setOnFinishComputing(this.onFinishCompute);
    }

    private void goToResults(Bed2BedEvent onFinish) {
        this.routes.addAll(List.of(airGround.getRoutes()));
        this.routes.addAll(List.of(groundOnly.getRoutes()));
        Bed2BedApp.TryGoTo(ShowResultsScene.class);
        onFinish.fire();
    }

    public void clear() {
        // Reset routes and targets
        this.onFinishCompute = () -> {};
        this.groundOnly = null;
        this.airGround = null;
        this.to = null;
        this.from = null;
        this.targetDate = null;
        this.routes.clear();
    }

    public void setFromTo(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setUser(UserAcct user) {
        System.out.println("User data: " + user.saved_data);
        this.user = user;
    }

    public UserAcct getUser() {
        return user;
    }

    public Map<String, String> toMap() {
        Map<String, String> route = new HashMap<>();
        route.put("to", this.to);
        route.put("from", this.from);
        return route;
    }
}
