package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.app.utils.HelperMethods;
import org.example.app.utils.PasswordUtils;
import org.w3c.dom.Node;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public JFXTextField admin_username;
    public JFXPasswordField admin_password;
    public JFXButton admin_btnlogin;
    public JFXComboBox<String> admin_select;


    public void handleLoginButtonAction(ActionEvent event) throws SQLException, IOException {
        String username = admin_username.getText();
        String providedPassword =admin_password.getText();


        if ((username == null || username.isEmpty()) || (providedPassword == null || providedPassword.isEmpty())) {
            HelperMethods.alertBox("Please enter the Username and Password", null, "Login Failed!");
        } else if (!HelperMethods.validate(username)) {
            HelperMethods.alertBox("Please enter a valid Username!", null, "Login Failed!");
        } else {

            User user = model.Datasource.getInstance().getUserByUsername(username);
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                HelperMethods.alertBox("There is no user registered with that username!", null, "Login Failed!");
            } else {
                boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, user.getPassword(), user.getSalt());

                if (passwordMatch) {
                    UserSessionController.setUserId(user.getId());
                    UserSessionController.setUserFullName(user.getFullname());
                    UserSessionController.setUserName(user.getUsername());
                    UserSessionController.setUserEmail(user.getEmail());
                    UserSessionController.setUserAdmin(user.getAdmin());
                    UserSessionController.setUserStatus(user.getStatus());

                    Node node = (Node) event.getSource();
                    Stage = (Stage) node.getScene().getWindow();
                    Stage.close();
                    if (user.getAdmin() == 0) {
                        scene = new Scene(FXMLLoader.load(getClass().getResource("admin")));
                    } else if (user.getAdmin() == 1) {
                        scene = new Scene(FXMLLoader.load(getClass().getResource("employee")));
                    }
                    Stage.setScene(scene);
                    Stage.show();
                } else {
                    HelperMethods.alertBox("Please enter correct Email and Password", null, "Login Failed!");
                }
            }
        }
    }


    public void handleRegisterButtonAction(ActionEvent actionEvent) throws IOException {
        Stage dialogStage;
        Node node = (Node) actionEvent.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

