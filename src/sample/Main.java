package sample;

import bean.Role;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.RoleService;
import util.Connexion;
import util.Session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
        int connection = Connexion.connect("SYS AS SYSDBA","Simo13089122");
        RoleService roleService=new RoleService();

//        System.out.println("connect= "+connection);
//        System.out.println(Session.getConnection());
//       Role role=new Role("blastoooo",true,false,false,"Simo13089122","","");
//       int i=roleService.create(role);
//        int j=roleService.alter(role);
//       System.out.println(i+" res");
//       System.out.println(j+" res");

        List<String> granted=new ArrayList<>();
        List<String> granties=new ArrayList<>();
        granted.add("SCOTT");
        granted.add("JWARD");
        granted.add("BLAST");
        granties.add("BLASTOOOOOOOOO");
        granties.add("BLASTOOO");

        String grant = roleService.grant(granties, granted, true);
        launch(args);

    }
}
