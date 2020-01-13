package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Controller;
import sample.Main;
import service.RoleService;
import util.AlertUtil;
import util.Connexion;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> statutBox = new ComboBox<>();

    @FXML
    private TextField instanceField;

    @FXML
    private TextField portField;

    @FXML
    private TextField hostNameField;

    @FXML
    SideBarController sideBarController;

    public void seConnecter(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        String as = statutBox.getValue();
        String hostName = hostNameField.getText();
        String port = portField.getText();
        String instance = instanceField.getText();


        if (login.isEmpty() || password.isEmpty() || as.isEmpty()) {
            AlertUtil.loginError();
        } else if(hostName.isEmpty() || port.isEmpty()||instance.isEmpty()){
            AlertUtil.urlStringError();
        } else {
            login += " AS " + as;
            String url=hostName+":"+port+":"+instance;
            int result = Connexion.connect(login, password,url);
            if (result == 1) {
                try {
                    sideBarController = new SideBarController();
                    sideBarController.gotoDashboard(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                AlertUtil.falseloginError();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statutBox.setItems(FXCollections.observableArrayList(new String[]{"SYSDBA"}));
    }
}
