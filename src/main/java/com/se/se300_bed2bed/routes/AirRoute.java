package com.se.se300_bed2bed.routes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AirRoute implements Route {

    protected GroundRoute fromAirport, toAirport;
    protected String travelType = "Flight";
    protected String to, from;
    protected String duration, cost;

    public GroundRoute getFromAirport() {
        return fromAirport;
    }

    public GroundRoute getToAirport() {
        return toAirport;
    }

    public String getTravelType() {
        return travelType;
    }

    public String getDuration() {
        return duration;
    }

    public String getCost() {
        return cost;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public double getTotalPrice() {
        String cleanCost = this.getCost().replace("$","");
        double flightPrice =  Double.parseDouble(cleanCost);
        return flightPrice + this.toAirport.getTotalPrice() + this.fromAirport.getTotalPrice();
    }

    @Override
    public double getTotalDuration() {
        Pattern pattern = Pattern.compile(".*(\\d+) hr (\\d+) min.*");
        Matcher matcher = pattern.matcher(this.getDuration());
        if (matcher.matches()) {
            int hours = Integer.parseInt(matcher.group(1));
            int min = Integer.parseInt(matcher.group(2));
            return 60*hours + min + this.toAirport.getTotalDuration() + this.fromAirport.getTotalDuration();
        }
        return 0;
    }
}
