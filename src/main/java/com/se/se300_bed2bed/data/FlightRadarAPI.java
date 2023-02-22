package com.se.se300_bed2bed.data;

import java.util.HashMap;

public class FlightRadarAPI extends APIDatabase {

    @Override
    protected boolean CONNECT() {
        return false;
    }

    @Override
    protected HashMap<String, String> GET() {
        return null;
    }
}
