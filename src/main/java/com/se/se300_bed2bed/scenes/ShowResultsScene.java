package com.se.se300_bed2bed.scenes;

import com.google.gson.Gson;
import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.routes.AirRoute;
import com.se.se300_bed2bed.routes.GroundRoute;
import com.se.se300_bed2bed.routes.Route;
import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowResultsScene extends FXMLController {
    @Override
    protected String fxmlName() {
        return "showResults.fxml";
    }

    @Override
    public void onGoTo() {
        // TODO: Results GUI, populate with results
        System.out.println("Displaying Results:");
        for (Route route: Bed2BedApp.manager.getRoutes()) {
            GroundRoute ground;
            AirRoute air;

            if (route instanceof GroundRoute) {
                ground = (GroundRoute) route;
                System.out.println("---------------------------------------------------------------------");
                System.out.println(ground.getTravelType());
                System.out.println(ground.getFrom() + " -> " + ground.getTo());
                System.out.println(ground.getDistance());
                System.out.println(ground.getDuration());
            }
            else if (route instanceof AirRoute) {
                air = (AirRoute) route;

            }

        }
    }

    @FXML
    protected void saveRouteToDB() {
        Map new_route = Bed2BedApp.manager.toMap();
        addUserToDatabase(new_route);
    }

    private void addUserToDatabase(Map new_route) {
        // Abort if guest
        if (Bed2BedApp.manager.getUser().isGuest) return;

        Gson gson = new Gson();

        ArrayList<Map> routes = new ArrayList<>();
        routes.add(new_route);

        if (Bed2BedApp.manager.getUser().saved_data != null) {
            String saved_data = Bed2BedApp.manager.getUser().saved_data;
            Map[] saved_routes = gson.fromJson(saved_data, Map[].class);
            routes.addAll(List.of(saved_routes));
        }

        String new_saved_data = gson.toJson(routes.toArray());

        UserAcct user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/amitdb";
        final String USERNAME = "root";
        final String PASSWORD = "Tomorrow227!";

        UserAcct currentUser = Bed2BedApp.manager.getUser();
        String username = currentUser.username;

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "UPDATE useraccount SET saved_data = '" + new_saved_data + "' " +
                    "WHERE username = '" + username + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                System.out.println("addedRows > 1!");
            }
            else System.out.println("addedRows < 1 :(");
        } catch (SQLException e) {
            // TODO: Error Handling
            e.printStackTrace();
        }
    }
}
