package Controllers;

import Layouts.MyPatientsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class HomePageController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button overviewbtn;

    @FXML
    private Button appointementbtn;

    @FXML
    private Button mypatientsbtn;

    @FXML
    private Button mytestsbtn;

    @FXML
    private Button calendarbtn;

    @FXML
    private Button myanamnsesbtn;

    @FXML
    public void initialize() {
        // Set the initial view to Dashboard
        loadCenterSection("/Layouts/Dashboard.fxml");
        mainPane.getProperties().put("controller", this);
    }

    @FXML
    private void switchFrom(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String fxmlFile = "/Layouts/Dashboard.fxml";

        if (clickedButton == overviewbtn) {
            fxmlFile = "/Layouts/Dashboard.fxml";
        } else if (clickedButton == appointementbtn) {
            fxmlFile = "/Layouts/MyAppointments.fxml";
        } else if (clickedButton == mypatientsbtn) {
            fxmlFile = "/Layouts/MyPatients.fxml";
        } else if (clickedButton == mytestsbtn) {
            fxmlFile = "/Layouts/MyTests.fxml";
        } else if (clickedButton == calendarbtn) {
            fxmlFile = "/Layouts/Calendar.fxml";
        } if (clickedButton == myanamnsesbtn) {
            fxmlFile = "/Layouts/Anamnese.fxml";
        }

        loadCenterSection(fxmlFile);
    }

    public void loadCenterSection(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node centerNode = loader.load();


            if (loader.getController() instanceof MyPatientsController) {
                MyPatientsController controller = loader.getController();
                controller.setHomePageController(this);
            }

            mainPane.setCenter(centerNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
