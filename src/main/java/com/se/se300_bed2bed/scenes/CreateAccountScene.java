package com.se.se300_bed2bed.scenes;
import com.se.se300_bed2bed.Bed2BedApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.*;

public class CreateAccountScene extends FXMLController{

    @Override
    protected String fxmlName() {
        return ("CreateAccountScene.fxml");
    }

    @FXML
    private Button cancelButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField createUsernameTextField;
    @FXML
    private PasswordField createPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label createAcct;

    @FXML
    protected void goToLogin() {
        Bed2BedApp.TryGoTo(LoginScene.class);
    }
    public void cancelButtonAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void createAcctButton (ActionEvent e) {
        if(firstNameTextField.getText().isBlank() == false && lastNameTextField.getText().isBlank() == false &&
                createUsernameTextField.getText().isBlank() == false && createPasswordField.getText().isBlank() == false
                && confirmPasswordField.getText().isBlank() == false) {
            registerUser();
        } else {
            createAcct.setText("Please enter all Fields.");
        }
    }
    public void registerUser() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String username =createUsernameTextField.getText();
        String password = String.valueOf(createPasswordField.getText());
        String confirmPassword = String.valueOf(confirmPasswordField.getText());

        if(!password.equals(confirmPassword)) {
            createAcct.setText("Passwords do not Match.");
        }

       user = addUserToDatabase(firstName, lastName, username, password);
        if(user != null) {
            createAcct.setText("Success! Return to Login.");
        }else {
            createAcct.setText("Failed to Register New User. Please Try Again.");
        }
    }
    public UserAcct user;
    private UserAcct addUserToDatabase (String firstName, String lastName, String username, String password) {
        UserAcct user = null;
        final String DB_URL = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9603412";
        final String USERNAME = "sql9603412";
        final String PASSWORD = "a3Fhikr9v9";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO useraccount (firstName, lastName, username, password)" + "VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,username);
            preparedStatement.setString(4,password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new UserAcct();
                user.firstName = firstName;
                user.lastName = lastName;
                user.username = username;
                user.password = password;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
