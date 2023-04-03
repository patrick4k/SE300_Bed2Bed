package com.se.se300_bed2bed.data;

import com.amadeus.Amadeus;
import com.amadeus.Params;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
public class FlightAPI {
    public static void main(String[] args) throws ResponseException {
        Amadeus amadeus = Amadeus
                .builder("1UxlAtMdXQTRKKdE02dyjfjoe79PhFF6", "vX4vggAsdGX2866e")
                .build();

        //Object Locations = null;
        Location[] locations = amadeus.referenceData.locations.get(Params
                .with("keyword", "MCO")
                .and("subType", Locations.ANY));
        locations[0].getResponse().getBody();


        System.out.println(locations);

        // The options of the flight (Make it into a user input)
        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", "MCO")
                        .and("destinationLocationCode", "JFK")
                        .and("departureDate", "2023-09-01")
                        .and("returnDate", "2023-09-08")
                        .and("currencyCode", "USD")
                        .and("adults", 2)
                        .and("max", 10));
        System.out.println(flightOffersSearches);
    }
}
