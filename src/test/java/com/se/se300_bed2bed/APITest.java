package com.se.se300_bed2bed;

import com.se.se300_bed2bed.data.FlightRadarAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
All test methods must be marked "@Test" and start with "test" in name

EX:
public void FlightRadarAPI() will NOT run
public void testFlightRadarAPI() will run
 */

public class APITest {

    @Test
    public void testFlightRadarAPI() {
        /* Confirms that a new instance of FlightRadarAPI can connect without error */
        FlightRadarAPI api = new FlightRadarAPI();
        boolean connectStatus = api.CONNECT();
        Assertions.assertTrue(connectStatus, "Flight API failed in connection phase");
    }

}
