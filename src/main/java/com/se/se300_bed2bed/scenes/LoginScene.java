package com.se.se300_bed2bed.scenes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.HashMap;

public class LoginScene extends FXMLController {
    @Override
    protected String fxmlName() { return "LoginScene.fxml";}

    private Button continueAsGuestButton;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Button loginButton;
    private Hyperlink creatAccountHyperlink;
    private Button getContinueAsGuestButton;
    private TextField createUsernameTextField;
    private TextField createPasswordTextField;

    HashMap<TextField,TextField> logininfo = new HashMap<TextField,TextField>();
        // TODO not compiling
//    LoginScene(HashMap<String,String> loginInfoOriginal){
//        logininfo = loginInfoOriginal;
//    }

    public void continueAsGuestAction(ActionEvent event) {
        Stage stage = (Stage)  continueAsGuestButton.getScene().getWindow();
        if(event.getSource() == continueAsGuestButton) {
            MapScene map = new MapScene();
        }
        stage.close();
    }
    public void loginAction(ActionEvent event) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        if(event.getSource() == loginButton) {
            String userID = usernameTextField.getText();
            String password = passwordTextField.getText();

            if(logininfo.containsKey(userID)) {
                if(logininfo.get(userID).equals(password)) {
                    MapScene map = new MapScene();
                }
            }
        }
        stage.close();
    }

    public void createAccountAction (ActionEvent event) {
        Stage stage = (Stage) creatAccountHyperlink.getScene().getWindow();
//        TODO not compiling
//        if(event.getSource() == creatAccountButton) {
//
//            String newUserID = createUsernameTextField.getText();
//            String newPassword = createPasswordTextField.getText();
//
//            logininfo.put(createPasswordTextField, createPasswordTextField);
//        }
    }
}
