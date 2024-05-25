package Controllers.FicheDeSuivi;

import Classes.FicheDeSuiviDone;
import Controllers.FicheDeSuivi.SharedModel;
import Controllers.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class OtherSheetsController implements Initializable {
    @FXML
    public ListView<FicheDeSuiviDone> ficheDoneListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ficheDoneListView.setItems(SharedModel.getFicheDeSuiviDoneList());
        ficheDoneListView.setCellFactory(listView -> new FicheDeSuiviDoneCell());
        ficheDoneListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                FicheDeSuiviDone selectedFiche = ficheDoneListView.getSelectionModel().getSelectedItem();
                if (selectedFiche != null) {
                    try {
                        showFicheDetails(selectedFiche);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void receiveFichedeSuivi(FicheDeSuiviDone fichedeSuivi) {
        ficheDoneListView.getItems().add(fichedeSuivi);
    }

    public void showGoalList(MouseEvent mouseEvent) {
    }

    public void showEvaluatedGoals(MouseEvent mouseEvent) {
    }


    public void goBack(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/Layouts/FicheDeSuivi/FicheDeSuivi.fxml");
    }
    public static class FicheDeSuiviDoneCell extends ListCell<FicheDeSuiviDone> {
        private HBox hBox = new HBox();
        private Label dateLabel;

        public FicheDeSuiviDoneCell() {
            super();
            dateLabel = new Label();
            dateLabel.setPrefWidth(190);
            Font customFont = Font.font("Arial", FontWeight.BOLD, 14);
            HBox.setHgrow(dateLabel, Priority.ALWAYS);
            hBox.getChildren().addAll(dateLabel);
           hBox.setPadding(new Insets(10, 10, 15, 7));
        }

        @Override
        protected void updateItem(FicheDeSuiviDone item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime savedDate = item.getSavedDate();
                if (savedDate != null) {
                    dateLabel.setText(savedDate.format(formatter));
                } else {
                    dateLabel.setText("No date");
                }
                setGraphic(hBox);
            } else {
                setGraphic(null);
            }
        }
    }
    private void showFicheDetails(FicheDeSuiviDone fiche) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Layouts/FicheDeSuivi/FicheDetails.fxml"));
        Parent root = loader.load();

        FicheDoneDetailsController controller = loader.getController();
        controller.setFiche(fiche);


        Scene scene = new Scene(root);
        Stage stage = (Stage) ficheDoneListView.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
