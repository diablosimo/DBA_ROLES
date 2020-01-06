package sample;

import bean.Role;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.RoleService;
import util.Connexion;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //Connection connection = Connexion.getConnection();
        RoleService roleService=new RoleService();
        Role role=new Role("blast",true,false,false,"Simo13089122","","");
        //int i=roleService.create(role);
        int i=roleService.alter(role);
        System.out.println(i+" res");
        launch(args);

    }
}
