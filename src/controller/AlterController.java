package controller;

import bean.Role;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Controller;
import service.RoleService;
import util.AlertUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class AlterController extends Controller implements Initializable {

    @FXML
    SideBarController sideBarController;

    @FXML
    private ComboBox<String> nameBox = new ComboBox<>();

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField schemaField;

    @FXML
    private TextField packageField;

    @FXML
    private RadioButton identifiedRadio;

    @FXML
    private ToggleGroup identification;

    @FXML
    private RadioButton nonIdentifiedRadio;

    @FXML
    private RadioButton byPasswordRadio;

    @FXML
    private ToggleGroup identificationWay;

    @FXML
    private RadioButton usingAppRadio;

    @FXML
    private RadioButton externallyRadio;

    @FXML
    private RadioButton globallyRadio;

    @FXML
    private Button btnCreateMenu;

    @FXML
    public void cancel(ActionEvent event) {
        nameBox.setValue("");
        cancel();
    }

    private void cancel() {
        nameBox.setValue("");
        passwordField.setText("");
        schemaField.setText("");
        packageField.setText("");
    }

    @FXML
    public void alter(ActionEvent event) {
        String nom = (String) nameBox.getValue();
        Boolean isIdentified = false;
        String password = "";
        String schema = "";
        String pakage = "";
        Boolean isExternal = false;
        Boolean isGlobal = false;
        if (nom == null || nom.isEmpty()) {
            AlertUtil.nameNotSetAlert();
            return;
        }
        if (nonIdentifiedRadio.isSelected()) {
            isIdentified = false;
        } else {
            isIdentified = true;
            if (byPasswordRadio.isSelected()) {
                password = passwordField.getText();
                if (password.isEmpty()) {
                    AlertUtil.passwordCreationNotSetAlert();
                    return;
                }
            } else if (usingAppRadio.isSelected()) {
                pakage = packageField.getText();
                schema = schemaField.getText();
                if (pakage.isEmpty() || schema.isEmpty()) {
                    AlertUtil.appCreationNotSetAlert();
                    return;
                }
            } else if (externallyRadio.isSelected()) {
                isExternal = true;
            } else if (globallyRadio.isSelected()) {
                isGlobal = true;
            } else {
                AlertUtil.noIdentificationOptionSetAlert();
                return;
            }
        }

        Role role = new Role(nom, isIdentified, isExternal, isGlobal, password, schema, pakage);
        RoleService roleService = new RoleService();
        int res = roleService.alter(role);
        if (res == 0) {
            cancel();
            AlertUtil.showAlterAlert();
        } else if (res == -3) {
            AlertUtil.roleExistAlert();
        }
    }


    @FXML
    void byPassword(ActionEvent event) {
        passwordField.setDisable(false);
        packageField.setDisable(true);
        schemaField.setDisable(true);
        packageField.setText("");
        schemaField.setText("");

    }

    @FXML
    void globally(ActionEvent event) {
        passwordField.setDisable(true);
        packageField.setDisable(true);
        schemaField.setDisable(true);
    }

    @FXML
    void notIdentified(ActionEvent event) {
        byPasswordRadio.setDisable(true);
        usingAppRadio.setDisable(true);
        externallyRadio.setDisable(true);
        globallyRadio.setDisable(true);

        byPasswordRadio.setSelected(false);
        usingAppRadio.setSelected(false);
        externallyRadio.setSelected(false);
        globallyRadio.setSelected(false);

        passwordField.setDisable(true);
        packageField.setDisable(true);
        schemaField.setDisable(true);
    }

    @FXML
    void isIdentified(ActionEvent event) {
        byPasswordRadio.setDisable(false);
        usingAppRadio.setDisable(false);
        externallyRadio.setDisable(false);
        globallyRadio.setDisable(false);
    }

    @FXML
    void usingPackage(ActionEvent event) {
        passwordField.setDisable(true);
        packageField.setDisable(false);
        schemaField.setDisable(false);
        passwordField.setText("");
    }

    @FXML
    void xternally(ActionEvent event) {
        passwordField.setDisable(true);
        packageField.setDisable(true);
        schemaField.setDisable(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameBox.setItems(FXCollections.observableArrayList(RoleService.findAll()));
        byPasswordRadio.setDisable(true);
        usingAppRadio.setDisable(true);
        externallyRadio.setDisable(true);
        globallyRadio.setDisable(true);
        passwordField.setDisable(true);
        packageField.setDisable(true);
        schemaField.setDisable(true);

    }
}
