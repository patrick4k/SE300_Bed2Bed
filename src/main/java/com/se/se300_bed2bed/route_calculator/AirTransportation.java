package com.se.se300_bed2bed.route_calculator;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.google.gson.Gson;
import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.routes.GroundRoute;
import com.se.se300_bed2bed.util.RouteComputeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AirTransportation {
/* Attributes ------------------------------------------------------------------------------------------------------- */
    private Amadeus amadeus;
    private String startLocation, endLocation;
    private RouteComputeEvent onCompute = (routes) -> {};
    private String[] routes;
    private final int[] routesComputed = {0};
    private Map<String,String>[] toAirportMaps, flightMaps, fromAirportMaps;
    private boolean isFirst = true;


/* Constructors ----------------------------------------------------------------------------------------------------- */

    public AirTransportation(String startLocation, String endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.calculateRoutes();
    }

/* Methods -----------------------------------------------------------------------------------------------------------*/

    public void calculateRoutes() {
        routesComputed[0] = 0;

        amadeus = Amadeus
                .builder("1UxlAtMdXQTRKKdE02dyjfjoe79PhFF6", "vX4vggAsdGX2866e")
                .build();


        /* Get closest airport -------------------------------------------------------------------------------------- */
        String originCode, originAddress, destinationCode, destinationAddress;
        try {
            String from = Bed2BedApp.manager.getFromCityCountry().isBlank()?
                    Bed2BedApp.manager.getFrom() : Bed2BedApp.manager.getFromCityCountry();

            System.out.println("From: " + from);

            Location[] locations = amadeus.referenceData.locations.get(Params
                    .with("keyword", from)
                    .and("subType", Locations.AIRPORT));

            originCode = locations[0].getIataCode();
            originAddress = originCode + " AIRPORT " + from;


            String to = Bed2BedApp.manager.getToCityCountry().isBlank()?
                    Bed2BedApp.manager.getTo() : Bed2BedApp.manager.getToCityCountry();

            System.out.println("To: " + to);

            locations = amadeus.referenceData.locations.get(Params
                    .with("keyword", to)
                    .and("subType", Locations.AIRPORT));

            destinationCode = locations[0].getIataCode();
            destinationAddress = destinationCode + " AIRPORT " + to;

        } catch (Exception e) {
            this.onFailResponse();
            return;
        }

        if (originCode.isBlank() || destinationCode.isBlank()) {
            this.onFailResponse();
            return;
        }

        System.out.println("origin: " + originCode + "\n\t" + originAddress);
        System.out.println("destination: " + destinationCode + "\n\t" + destinationAddress);

        /* Find from -> fromAirport --------------------------------------------------------------------------------- */
        RouteGroundOnly groundToAirport = new RouteGroundOnly(
                Bed2BedApp.manager.getFrom(),
                originAddress
        );
        groundToAirport.setOnFinishComputing(() -> {
            this.toAirportMaps = new HashMap[groundToAirport.getRoutes().length];
            if (groundToAirport.getRoutes().length == 0) {
                this.onFailResponse();
                return;
            }
            for (int i = 0; i < groundToAirport.getRoutes().length; i++) {
                GroundRoute groundRoute = (GroundRoute) groundToAirport.getRoutes()[i];
                Map<String,String> route = new HashMap<>();
                route.put("to",groundRoute.getTo());
                route.put("from",groundRoute.getFrom());
                route.put("distance",groundRoute.getDistance());
                route.put("duration",groundRoute.getDuration());
                route.put("travelType",groundRoute.getTravelType());
                route.put("cost",groundRoute.getCost());
                this.toAirportMaps[i] = route;
            }
            System.out.println("Found to airport information");
            incrementSuccess();
        });


        /* Find toAirport -> to ------------------------------------------------------------------------------------- */
        RouteGroundOnly groundFromAirport = new RouteGroundOnly(
                destinationAddress,
                Bed2BedApp.manager.getTo()
        );
        groundFromAirport.setOnFinishComputing(() -> {
            this.fromAirportMaps = new HashMap[groundFromAirport.getRoutes().length];
            if (groundFromAirport.getRoutes().length == 0) {
                this.onFailResponse();
                return;
            }
            for (int i = 0; i < groundFromAirport.getRoutes().length; i++) {
                GroundRoute groundRoute = (GroundRoute) groundFromAirport.getRoutes()[i];
                Map<String,String> route = new HashMap<>();
                route.put("to",groundRoute.getTo());
                route.put("from",groundRoute.getFrom());
                route.put("distance",groundRoute.getDistance());
                route.put("duration",groundRoute.getDuration());
                route.put("travelType",groundRoute.getTravelType());
                route.put("cost",groundRoute.getCost());
                this.fromAirportMaps[i] = route;
            }
            System.out.println("Found from airport information");
            incrementSuccess();
        });


        /* Find Flight Options -------------------------------------------------------------------------------------- */
        FlightOfferSearch[] flightOffersSearches;
        try {
            flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", originCode)
                            .and("destinationLocationCode", destinationCode)
                            .and("departureDate", Bed2BedApp.manager.getTargetDate())
                            .and("currencyCode", "USD")
                            .and("adults", 1)
                            .and("max", 5));
        } catch (ResponseException e) {
            this.onFailResponse();
            return;
        }

        // Add flight routes to flightMaps
        this.flightMaps = new HashMap[flightOffersSearches.length];
        for (int i = 0; i < flightOffersSearches.length; i++) {
            Map<String, String> route = new HashMap<>();
            FlightOfferSearch flight = flightOffersSearches[i];
            route.put("cost", flight.getPrice().getGrandTotal());
            StringBuilder duration = new StringBuilder();
            for (FlightOfferSearch.Itinerary itinerary: flight.getItineraries()) {
                duration.append(" + ").append(itinerary.getDuration());
            }
            route.put("duration",duration.toString());
            route.put("from", originAddress);
            route.put("to", destinationAddress);

            this.flightMaps[i] = route;
        }

        System.out.println("Found flight information");
        incrementSuccess();
    }

    private void incrementSuccess() {
        Gson gson = new Gson();
        routesComputed[0]++;
        if (routesComputed[0] < 3) return;

        int route_count = 0;
        String[] routes = new String[flightMaps.length*toAirportMaps.length];

        for (Map<String, String> flightMap : flightMaps) {

            for (int i = 0; i < toAirportMaps.length; i++) {

                Map<String, Map> airGroundRoutes = new HashMap<>();

                airGroundRoutes.put("toAirport", toAirportMaps[i]);
                airGroundRoutes.put("flight", flightMap);
                airGroundRoutes.put("fromAirport", fromAirportMaps[i]);

                String airGroundRoutesString = gson.toJson(airGroundRoutes);

                routes[route_count] = airGroundRoutesString;
                route_count++;
            }

        }

        this.onCompute.onRouteCalculated(routes);

    }

    private void onFailResponse() {
        System.out.println("No flight routes computed");
        this.onCompute.onRouteCalculated(new String[0]);
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
