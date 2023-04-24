package com.se.se300_bed2bed.scenes;

import com.google.gson.Gson;
import com.se.se300_bed2bed.Bed2BedApp;
import com.se.se300_bed2bed.routes.AirRoute;
import com.se.se300_bed2bed.routes.GroundRoute;
import com.se.se300_bed2bed.routes.Route;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import static com.google.gson.JsonParser.parseString;
import static java.lang.Double.parseDouble;
import java.util.*;

public class ShowResultsScene extends FXMLController implements Initializable{
    @Override
    protected String fxmlName() {
        return "showResults.fxml";
    }
    @FXML
    private TreeView treeView;
    //@FXML
    //Label originLabel;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    @FXML
    public Label originLabel;

    @FXML
    Label destinationLabel;

    // THIS OUTPUTS NOTHING!!!
    public static Label destinationStaticLabel = new Label();

    //public static Label originLabel;

    //@FXML
    //private Label destinationLabel;
    //public static Label destinationStaticLabel;
 //    */

    private List<Route> routes;

    public void run(){
       destinationStaticLabel = destinationLabel;
       onGoTo();
    }

    @Override
    public void onGoTo(Scene scene) {
        treeView = (TreeView) scene.getRoot().getChildrenUnmodifiable().get(3);
        originLabel = (Label) scene.getRoot().getChildrenUnmodifiable().get(4);
        destinationLabel = (Label) scene.getRoot().getChildrenUnmodifiable().get(5);

        originLabel.setText(Bed2BedApp.manager.getFrom());
        destinationLabel.setText(Bed2BedApp.manager.getTo());

        this.routes = Bed2BedApp.manager.getRoutes();
        putResults(Bed2BedApp.manager.getRoutes());
    }

    private void putResults(List<Route> routes) {
        for (Object item: this.treeView.getRoot().getChildren())
            ((TreeItem) item).getChildren().clear();

        for (Route route: routes) {
            GroundRoute ground;
            AirRoute air;
            if (route instanceof GroundRoute) {
                ground = (GroundRoute) route;
                ((TreeItem) treeView.getRoot().getChildren().get(1)).getChildren().add(getTreeItem(ground, false));
            } else if (route instanceof AirRoute) {
                air = (AirRoute) route;
                ((TreeItem) treeView.getRoot().getChildren().get(0)).getChildren().add(getTreeItem(air));
            }
        }
    }

    private TreeItem<String> getTreeItem(GroundRoute route, boolean includeFromTo) {
        final DecimalFormat decfor = new DecimalFormat("0.00");
        TreeItem<String> treeItem = new TreeItem<>(route.getTravelType()
                + ": " + (!Objects.equals(route.getCost(),"N/A")?  "$" + decfor.format(route.getTotalPrice()) : "N/A") + ", " + route.getTotalDuration() + " min");
        if (includeFromTo) {
            treeItem.getChildren().add(new TreeItem<>("Origin: " + route.getFrom()));
            treeItem.getChildren().add(new TreeItem<>("Destination: " + route.getTo()));
        }
        treeItem.getChildren().add(new TreeItem<>("Distance: " + route.getDistance()));
        treeItem.getChildren().add(new TreeItem<>("Duration: " + route.getDuration()));

//        double driveCost = Double.parseDouble(route.getDistance().replace("mi", "").replace(",", ""));

//        if (route.getTravelType().equals("Driving")) {
//            treeItem.getChildren().add(new TreeItem<>("Cost: $" + decfor.format((driveCost / 25.4) * 3.66)));
//            if (driveCost > 100) {
//                treeItem.getChildren().add(new TreeItem<>("RideShare Cost: N/A"));
//            } else {
//                treeItem.getChildren().add(new TreeItem<>("RideShare Cost: $" + decfor.format((driveCost * 1.5))));
//            }
//        } else if (route.getTravelType().equals("Transit")) {
//            treeItem.getChildren().add(new TreeItem<>("Cost: $" + decfor.format(driveCost * 0.228)));
//        } else {
//            treeItem.getChildren().add(new TreeItem<>("Cost: " + route.getCost()));
//        }
        return treeItem;
    }

    private TreeItem<String> getTreeItem(AirRoute route) {
        final DecimalFormat decfor = new DecimalFormat("0.00");
        TreeItem<String> treeItem = new TreeItem<>(route.getTravelType()
                + ": $" + decfor.format(route.getTotalPrice()) + ", " + route.getTotalDuration() + " min");

        TreeItem<String> toAirport = getTreeItem(route.getToAirport(), true);
        toAirport.setValue("To Airport: " + toAirport.getValue());
        treeItem.getChildren().add(toAirport);

        treeItem.getChildren().add(new TreeItem<>("Origin: " + route.getFrom()));
        treeItem.getChildren().add(new TreeItem<>("Destination: " + route.getTo()));
        treeItem.getChildren().add(new TreeItem<>("Duration: " + route.getDuration()));
        treeItem.getChildren().add(new TreeItem<>("Cost: " + route.getCost()));

        TreeItem<String> fromAirport = getTreeItem(route.getFromAirport(), true);
        fromAirport.setValue("From Airport: " + fromAirport.getValue());
        treeItem.getChildren().add(fromAirport);

        return treeItem;
    }

    @FXML
    protected void sortByPriceAscending() {
        List<Route> routes = Bed2BedApp.manager.getRoutes();
        routes.sort(Comparator.comparingDouble(Route::getTotalPrice));
        putResults(routes);
    }
    @FXML
    protected void sortByPriceDescending() {
        List<Route> routes = Bed2BedApp.manager.getRoutes();
        routes.sort((route1, route2) -> -Double.compare(route1.getTotalPrice(), route2.getTotalPrice()));
        putResults(routes);
    }
    @FXML
    protected void sortByDurationAscending() {
        List<Route> routes = Bed2BedApp.manager.getRoutes();
        routes.sort(Comparator.comparingDouble(Route::getTotalDuration));
        putResults(routes);
    }
    @FXML
    protected void sortByDurationDescending() {
        List<Route> routes = Bed2BedApp.manager.getRoutes();
        routes.sort((route1, route2) -> -Double.compare(route1.getTotalDuration(), route2.getTotalDuration()));
        putResults(routes);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TreeItem<String> transportMethods = new TreeItem<>("Available Routes");
        treeView.setRoot(transportMethods);

        TreeItem<String> airTransport = new TreeItem<>("Air Transportation");
        TreeItem<String> groundTransport = new TreeItem<>("Ground Transportation");

        transportMethods.getChildren().addAll(airTransport, groundTransport);

        treeView.setRoot(transportMethods);

        transportMethods.setExpanded(true);
        airTransport.setExpanded(true);
        groundTransport.setExpanded(true);

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

    public void chooseType() {

    }
}
