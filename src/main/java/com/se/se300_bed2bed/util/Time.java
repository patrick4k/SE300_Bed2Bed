package com.se.se300_bed2bed.util;

import java.util.regex.Pattern;

// TODO: Create time class to handle different units and conversions
public class Time {

    private double duration;
    private String units;

    public static Time from(String duration) {

        Pattern parser = Pattern.compile("(\\d+[.\\d+]?)\\s*(\\w+)");

        return new Time();

    }
}
