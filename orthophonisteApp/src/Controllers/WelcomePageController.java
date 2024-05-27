package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;


import java.io.IOException;

public class WelcomePageController {
    @FXML
    private Button getstartedbtn;

    @FXML
    private void getStarted(ActionEvent event) throws IOException {
            Main m =  new Main();
            m.changeScene("../Layouts/SignUp.fxml");

    }
}
