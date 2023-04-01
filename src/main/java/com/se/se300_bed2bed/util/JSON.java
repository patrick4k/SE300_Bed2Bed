package com.se.se300_bed2bed.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;

import java.util.ArrayList;

public class JSON {
    private static final TypeToken<Pair<LLAPosition,LLAPosition>> saved_location_type = new TypeToken<>() {};
    private static final TypeToken<Pair<LLAPosition,LLAPosition>[]> saved_locations_type = new TypeToken<>() {};

    public static void main(String[] args) {
        // TEST

        Route[] list = new Route[3];
        for (int i = 0; i < 3; i++) {
            LLAPosition start = new LLAPosition(12*i, 34*i);
            LLAPosition end = new LLAPosition(56*i, 78*i);
            list[i] = new Route(start,end);
        }
        System.out.println(arrToJson(list));
    }

    public static String arrToJson(Route[] savedLocations) {
        Gson gson = new Gson();
        return gson.toJson(savedLocations);
    }

    public static Route[] jsonToArr(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Route[].class);
    }
}
