package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        //primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void forward(ActionEvent actionEvent, String pageName, Class myClass) throws IOException {
        Parent parent = FXMLLoader.load(myClass.getResource(pageName));
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(scene);
        app_stage.show();

    }

    public static void main(String[] args) {
        //Connection connection = Connexion.getConnection();
//        int connection = Connexion.connect("SYS AS SYSDBA","");
//        RoleService roleService=new RoleService();

//        System.out.println("connect= "+connection);
//        System.out.println(Session.getConnection());
//       Role role=new Role("blastoooo",true,false,false,"Simo13089122","","");
//       int i=roleService.create(role);
//        int j=roleService.alter(role);
//       System.out.println(i+" res");
//       System.out.println(j+" res");

//        List<String> granted=new ArrayList<>();
//        List<String> granties=new ArrayList<>();
//        granted.add("SCOTT");
//        granted.add("JWARD");
//        granted.add("BLAST");
//        granties.add("BLASTOOOOOOOOO");
//        granties.add("BLASTOOO");

       // String grant = roleService.grant(granties, granted, true);
        launch(args);

    }
}
