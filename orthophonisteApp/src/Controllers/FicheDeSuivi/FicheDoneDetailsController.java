package Controllers.FicheDeSuivi;

import Classes.FicheDeSuiviDone;
import Classes.ObjectifEvalue;
import Controllers.FicheDeSuivi.OtherSheetsController.FicheDeSuiviDoneCell;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import java.time.format.DateTimeFormatter;

public class FicheDoneDetailsController {
    @FXML
    private Label dateLabel;
    @FXML
    private ListView<ObjectifEvalue> goalsListView;

    public void setFiche(FicheDeSuiviDone fiche) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateLabel.setText(fiche.getSavedDate().format(formatter));
        //goalsListView.setItems(FXCollections.observableArrayList(fiche.getObjectifsAtteints()));

        goalsListView.setCellFactory(listView -> new FicheDeSuiviController.GoalListCell());
    }
}
