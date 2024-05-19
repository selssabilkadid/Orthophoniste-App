package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/Layouts/HomePage.fxml"));


        primaryStage.setTitle("SIGN UP");
        //primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setScene(new Scene(root,1100,600));
        primaryStage.show();

    }
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setScene(new Scene(pane, 1100, 600));
        stg.setTitle("Orthophoniste Cabinet Managment System");
    }
    public static void main(String[] args) {

        launch(args);
    }
}
