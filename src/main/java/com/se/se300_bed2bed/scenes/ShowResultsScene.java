package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.routes.Route;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ShowResultsScene extends FXMLController {
    @Override
    protected String fxmlName() {
        return "showResults.fxml";
    }

    @Override
    public void onGoTo() {
        System.out.println("Displaying Results:");
        for (Route route: Bed2BedApp.manager.getRoutes()) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println(route.getTravelType());
            System.out.println(route.getFrom() + " -> " + route.getTo());
            System.out.println(route.getDistance());
            System.out.println(route.getDuration());
        }
    }

    @FXML
    protected void saveRouteToDB() {
        String route_json = Bed2BedApp.manager.toJSON();
        System.out.println(route_json);
        addUserToDatabase(route_json);
    }

    private void addUserToDatabase (String append_routes) {
        // TODO: Have not tested yet!
        UserAcct user = null;
        final String DB_URL = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9603412";
        final String USERNAME = "sql9603412";
        final String PASSWORD = "a3Fhikr9v9";

        UserAcct currentUser = Bed2BedApp.manager.getUser();
        String  firstName = currentUser.firstName,
                lastName  = currentUser.lastName,
                username  = currentUser.username,
                password  = currentUser.password;
        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "UPDATE useraccount SET saved_data = '" + append_routes + "' " +
                    "WHERE username = '" + username + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                System.out.println("addedRows > 1!");
            }
            else System.out.println("addedRows < 1 :(");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
