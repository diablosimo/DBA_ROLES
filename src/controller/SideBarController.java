package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Main;
import util.Connexion;

import java.io.IOException;

public class SideBarController {

    @FXML
    protected void gotoDashboard(ActionEvent event) throws IOException {
        Main.forward(event, "../view/dashBoard.fxml", this.getClass());
    }

    @FXML
    protected void gotoCreateRole(ActionEvent event) throws IOException {
        Main.forward(event, "../view/create.fxml", this.getClass());
    }

    @FXML
    protected void gotoAlterRole(ActionEvent event) throws IOException {
        Main.forward(event, "../view/alter.fxml", this.getClass());
    }

    @FXML
    protected void gotoGrantRole(ActionEvent event) throws IOException {
        Main.forward(event, "../view/grant.fxml", this.getClass());
    }


    @FXML
    protected void disconnect(ActionEvent event) throws IOException {
        Connexion.disconnect();
        Main.forward(event, "../view/login.fxml", this.getClass());
    }
}
