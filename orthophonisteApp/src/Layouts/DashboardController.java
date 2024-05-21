package Layouts;

import Classes.AccountManager;
import Classes.UserAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private AnchorPane homepage;

    @FXML
    private Text nbappointements;

    @FXML
    private Text nbatelier;

    @FXML
    private Text nbpatients;

    @FXML
    private Text nbprojet;

    @FXML
    private Label welcometext;

    private static final UserAccount Orthophoniste = AccountManager.getCurrentuser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Orthophoniste != null) {
            welcometext.setText("Welcome Dr." + Orthophoniste.getFirstName());
        } else {
            // Handle the case where no user is logged in
            welcometext.setText("Welcome, please log in.");
        }
    }

}
