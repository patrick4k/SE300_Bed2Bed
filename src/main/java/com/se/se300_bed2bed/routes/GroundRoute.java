package com.se.se300_bed2bed.routes;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroundRoute implements Route {
    protected String travelType;
    protected String from, to;
    protected String distance, duration, cost;

    public String getTravelType() {
        return travelType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }

    public String getCost() {
        return cost;
    }

    @Override
    public double getTotalPrice() {
        String cleanCost = this.getCost().replace("$","");
        if (Objects.equals(this.getCost(), "N/A")) return -1;
        return Double.parseDouble(cleanCost);
    }

    @Override
    public double getTotalDuration() {
        int days = 0, hours = 0, min = 0;
        Pattern patternDays = Pattern.compile("\\D*(\\d+) day.*");
        Pattern patternHours = Pattern.compile("\\D*(\\d+) hours.*");
        Pattern patternMins = Pattern.compile("\\D*(\\d+) mins.*");
        Matcher matcher;

        matcher = patternDays.matcher(this.getDuration());
        if (matcher.matches()) {
            days = (int) Double.parseDouble(matcher.group(1));
        }

        matcher = patternHours.matcher(this.getDuration());
        if (matcher.matches()) {
            hours = (int) Double.parseDouble(matcher.group(1));
        }

        matcher = patternMins.matcher(this.getDuration());
        if (matcher.matches()) {
            min = (int) Double.parseDouble(matcher.group(1));
        }

        return 60*24*days + 60*hours + min;
    }
}
