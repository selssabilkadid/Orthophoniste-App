import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("FicheDeSuivi.fxml"));

        primaryStage.setTitle("SIGN UP");
        primaryStage.setScene(new Scene(root, 891.33333333334, 566));
        primaryStage.show();

    }
   
    public static void main(String[] args) {
        launch(args);
    }
}
