package Controllers.FicheDeSuivi;

import Classes.FicheDeSuiviDone;
import Controllers.FicheDeSuivi.SharedModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OtherSheetsController implements Initializable {
    @FXML
    public ListView<FicheDeSuiviDone> ficheDoneListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ficheDoneListView.setItems(SharedModel.getFicheDeSuiviDoneList());
    }

    public void receiveFichedeSuivi(FicheDeSuiviDone fichedeSuivi) {
        ficheDoneListView.getItems().add(fichedeSuivi);
    }

    public void showGoalList(MouseEvent mouseEvent) {
    }

    public void showEvaluatedGoals(MouseEvent mouseEvent) {
    }


}
