package controller;

import bean.Role;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.controlsfx.control.CheckComboBox;
import sample.Controller;
import service.RoleService;
import util.AlertUtil;

import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.List;

public class GrantController implements Initializable {

    @FXML
    SideBarController sideBarController;

    @FXML
    private CheckComboBox<String> nameBox = new CheckComboBox<>();
    @FXML
    private CheckComboBox<String> privBox = new CheckComboBox<>();
    @FXML
    private CheckComboBox<String> usersBox = new CheckComboBox<>();
    @FXML
    private CheckComboBox<String> rolesBox = new CheckComboBox<>();
    @FXML
    private CheckBox publicCheckBox = new CheckBox();
    @FXML
    private CheckBox adminCheckBox = new CheckBox();


    @FXML
    public void cancel(ActionEvent event) {
        cancel();
    }

    private void cancel() {
        nameBox.getCheckModel().clearChecks();
        privBox.getCheckModel().clearChecks();
        usersBox.getCheckModel().clearChecks();
        rolesBox.getCheckModel().clearChecks();
        adminCheckBox.setSelected(false);
        publicCheckBox.setSelected(false);
    }

    @FXML
    public void grant(ActionEvent event) {
        List<String> granties = new ArrayList<>();
        List<String> granted = new ArrayList<>();

        boolean isPulic;
        boolean withAdminOption = false;
        if (nameBox.getCheckModel().getCheckedItems().size() <= 0 || privBox.getCheckModel().getCheckedItems().size() <= 0) {
            AlertUtil.noRoleSelectedForGrantAlert();
            return;
        }
        if (usersBox.getCheckModel().getCheckedItems().size() <= 0 && rolesBox.getCheckModel().getCheckedItems().size() <= 0) {
            AlertUtil.noGrantedSelectedAlert();
            return;
        }
        if (publicCheckBox.isSelected()) {
            isPulic = true;
        } else {
            isPulic = false;
        }
        if (publicCheckBox.isSelected()) {
            withAdminOption = true;
        } else {
            withAdminOption = false;
        }

        for (String g : nameBox.getCheckModel().getCheckedItems())
            granties.add(g);
        for (String g : privBox.getCheckModel().getCheckedItems())
            granties.add(g);

        for (String g : usersBox.getCheckModel().getCheckedItems())
            granted.add(g);
        for (String g : rolesBox.getCheckModel().getCheckedItems())
            granted.add(g);
        RoleService roleService = new RoleService();
        String res = roleService.grant(granties, granted, isPulic, withAdminOption);
        if (res.equals("Success")) {
            cancel();
            AlertUtil.showGrantAlert();
        } else {
            AlertUtil.showGrantError(res);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameBox.getItems().addAll(RoleService.findAll());
        usersBox.getItems().addAll(RoleService.findAllUsers());
        rolesBox.getItems().addAll(RoleService.findAll());
        privBox.getItems().addAll(RoleService.findAllSysPrivs());


    }
}
