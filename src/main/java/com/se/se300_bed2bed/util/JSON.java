package com.se.se300_bed2bed.util;

import com.google.gson.Gson;
import javafx.util.Pair;

import java.util.*;

public class JSON {
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        /* Make new position list */
        ArrayList<Pair<LLAPosition,LLAPosition>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(
                new Pair<>(
                    new LLAPosition(Math.random(),Math.random()),
                    new LLAPosition(Math.random(),Math.random())));
        }
        // write to json
        String json = deconstructRouteToJSON(list);
        System.out.println(json);

        /* Reconstruct from json to verify */
        List<Pair<LLAPosition,LLAPosition>> reconstructed = constructRoutesFromJSON(json);
        for (Pair<LLAPosition,LLAPosition> route : reconstructed) {
            System.out.println(route.getKey().getLongitude() + ", " + route.getKey().getLatitude());
            System.out.println(route.getValue().getLongitude() + ", " + route.getValue().getLatitude());
        }

    }

    /**
     * Takes in a list of pairs of LLAPositions and deconstructs it to a json string of the coordinates
     */
    public static String deconstructRouteToJSON(List<Pair<LLAPosition,LLAPosition>> startAndEnd) {
        Double[][] data = new Double[12][4];
        for (int i = 0; i < Math.min(startAndEnd.size(), data.length); i++) {
            LLAPosition start = startAndEnd.get(i).getKey();
            LLAPosition end = startAndEnd.get(i).getValue();
            Double[] route = data[i];
            route[0] = start.getLongitude();
            route[1] = start.getLatitude();
            route[2] = end.getLongitude();
            route[3] = end.getLatitude();
        }
        return gson.toJson(data);
    }

    /**
     * Constructs a list of LLAPosition pairs that represents routes saved by user
     * The key of each pair represents the start LLAPosition while the value represents the end LLAPosition
     */
    public static List<Pair<LLAPosition,LLAPosition>> constructRoutesFromJSON(String json) {
        Double[][] rawData = gson.fromJson(json, Double[][].class);
        List<Pair<LLAPosition,LLAPosition>> constructed = new ArrayList<>();
        for (Double[] route : rawData) {
            LLAPosition start = new LLAPosition(route[0], route[1]);
            LLAPosition end = new LLAPosition(route[2], route[3]);
            constructed.add(new Pair<>(start, end));
        }
        return constructed;
    }
}
