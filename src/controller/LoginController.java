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

public class LoginController extends Controller implements Initializable {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> statutBox = new ComboBox<>();

    @FXML
    SideBarController sideBarController;

    public void seConnecter(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        String as = statutBox.getValue();

        if (login.isEmpty() || password.isEmpty() || as.isEmpty()) {
            AlertUtil.loginError();
        } else {
            login += " AS " + as;
            int result = Connexion.connect(login, password);
            if (result == 1) {
                try {
                    sideBarController = new SideBarController();
                    sideBarController.gotoDashboard(event);
                    //gotoDashboard(event);
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
