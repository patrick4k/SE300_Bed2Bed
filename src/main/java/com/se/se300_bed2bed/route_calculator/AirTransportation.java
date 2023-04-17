package com.se.se300_bed2bed.route_calculator;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.util.RouteComputeEvent;

public class AirTransportation {
/* Attributes ------------------------------------------------------------------------------------------------------- */
    private String startLocation, endLocation;
    private RouteComputeEvent onCompute = (routes) -> {};
    private String[] routes = new String[4];


/* Constructors ----------------------------------------------------------------------------------------------------- */

    public AirTransportation(String startLocation, String endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.calculateRoutes();
    }

/* Methods -----------------------------------------------------------------------------------------------------------*/

    public void calculateRoutes() {

        Amadeus amadeus = Amadeus
                .builder("1UxlAtMdXQTRKKdE02dyjfjoe79PhFF6", "vX4vggAsdGX2866e")
                .build();

        //Object Locations = null;
        Location[] locations = new Location[0];
        try {
            locations = amadeus.referenceData.locations.get(Params
                    .with("keyword", "MCO")
                    .and("subType", Locations.ANY));
            locations[0].getResponse().getBody();
        } catch (Exception ignore) {}


        System.out.println(locations);

        // The options of the flight (Make it into a user input)
        FlightOfferSearch[] flightOffersSearches = new FlightOfferSearch[0];
        try {
            flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", "MCO") // TODO: Get closest airport
                            .and("destinationLocationCode", "JFK")
                            .and("departureDate", Bed2BedApp.manager.getTargetDate())
//                            .and("returnDate", "2023-09-08")
                            .and("currencyCode", "USD")
                            .and("adults", 1)
                            .and("max", 10));
        } catch (ResponseException e) {
            System.out.println("No flight routes computed");
            String[] routes = new String[0];
            this.onCompute.onRouteCalculated(routes);
        }
        System.out.println(flightOffersSearches);

        // TODO: Calculate air routes
        String[] routes = new String[0];
        this.onCompute.onRouteCalculated(routes);
    }


/* Setters & Getter ------------------------------------------------------------------------------------------------- */

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public RouteComputeEvent getOnCompute() {
        return onCompute;
    }

    public void setOnCompute(RouteComputeEvent onCompute) {
        this.onCompute = onCompute;
    }
}
