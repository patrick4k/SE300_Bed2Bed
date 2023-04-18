package com.se.se300_bed2bed.scenes;

import com.se.se300_bed2bed.Bed2BedApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginScene extends FXMLController implements Initializable {
    @Override
    protected String fxmlName() {
        return "LoginScene.fxml";
    }

    @FXML
    private Label loginMessage;
    @FXML
    private ImageView plane;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image planeImage = null;
        try {
            planeImage = new Image(this.getClass().getResource("plane.jpg").toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        plane.setImage(planeImage);
    }

    @FXML
    protected void goToCreateAcct() {
        Bed2BedApp.TryGoTo(CreateAccountScene.class);
    }

    //IN PLACE FOR START DESTINATION SCENE
    @FXML
    protected void goToMapScene() {
        Bed2BedApp.TryGoTo(ChooseStartEndLocations.class);
    }

    @FXML
    protected void continueAsGuestAction() {
        UserAcct guestUser = new UserAcct();
        guestUser.isGuest = true;
        guestUser.username = "Guest";
        guestUser.firstName = "Guest";
        guestUser.lastName = "";
        guestUser.password = "GuestPassword";
        Bed2BedApp.manager.setUser(guestUser);
        goToMapScene();
    }

    public void image() {

    }

    public void loginButton(ActionEvent event) {

        // Abort on blank text
        if(username.getText().isBlank() == true || password.getText().isBlank() == true){
            loginMessage.setText("Enter a Username and Password.");
            return;
        }

        loginMessage.setText("");
        String userName = username.getText();
        String passWord = password.getText();

        user = validateLogin(userName, passWord);

        if (user != null) {
            Bed2BedApp.manager.setUser(user);
            goToMapScene();
        }
        else if (loginMessage.getText().isBlank()) {
            loginMessage.setText("Invalid Login. Please Try Again.");
        }

    }

    public UserAcct user;

    private UserAcct validateLogin(String userName, String passWord) {
        UserAcct user = null;

        final String DB_URL = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9603412";
        final String USERNAME = "sql9603412";
        final String PASSWORD = "a3Fhikr9v9";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM useraccount WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,passWord);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new UserAcct();
                user.firstName = resultSet.getString("firstName");
                user.lastName = resultSet.getString("lastName");
                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");
                user.saved_data = resultSet.getString("saved_data");
            }
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            loginMessage.setText("Validation error, please check internet connection.");
        }
        return user;
    }
}
