package Controllers;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static Layouts.MyTestsController.convertSetToObservableList;

public class CreateBoController {

    @FXML
    private ListView<Trouble> troublesListView;
    private final ObservableList<Trouble> troubles = FXCollections.observableArrayList();

    @FXML
    private ListView<Test> testsListView;


    private UserAccount orthophoniste = AccountManager.getCurrentuser();

    private Set<Test> mestests = orthophoniste.getMes_tests();
    ObservableList<Test> testObservableList = convertSetToObservableList(mestests);
    @FXML
    private void initialize() {

        troublesListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Trouble> call(ListView<Trouble> troubleListView) {
                return new TroubleListCell();
            }
        });
        troublesListView.setItems(troubles);
        troublesListView.setPlaceholder(new Label("No current troubles, please create a new one"));


        testsListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Test> call(ListView<Test> testListView) {
                return new TestListCell();
            }
        });

        testsListView.setItems(testObservableList);
        testsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



    }



    @FXML
    private void addTrouble() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layouts/AddTrouble.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        AddTroubleController controller = loader.getController();
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String troubleName = controller.getTroubleName();
                CategorieTrouble selectedType = controller.getSelectedTroubleType();
                if (troubleName != null && !troubleName.isEmpty() && selectedType != null) {
                    troubles.add(new Trouble(troubleName, selectedType));
                }
            }
            return null;
        });
        dialog.showAndWait();
    }

    static class TestListCell extends ListCell<Test> {
        @Override
        protected void updateItem(Test test, boolean empty) {
            super.updateItem(test, empty);
            if (empty || test == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(test.getNom());
            }
        }
    }

    static class TroubleListCell extends ListCell<Trouble> {
        private HBox hBox = new HBox();
        private Label troubleNameLabel = new Label();
        private Label troubleTypeLabel = new Label();

        public TroubleListCell() {
            super();
            hBox.setSpacing(10);
            troubleNameLabel.setPrefWidth(190);
            troubleTypeLabel.setPrefWidth(190);

            Font customFont = Font.font("Arial", FontWeight.BOLD, 14);
            troubleNameLabel.setFont(customFont);
            troubleTypeLabel.setFont(customFont);
            HBox.setHgrow(troubleNameLabel, Priority.ALWAYS);
            HBox.setHgrow(troubleTypeLabel, Priority.ALWAYS);

            hBox.getChildren().addAll(troubleNameLabel, troubleTypeLabel);
            hBox.setPadding(new Insets(10, 10, 15, 7));
        }

        @Override
        protected void updateItem(Trouble trouble, boolean empty) {
            super.updateItem(trouble, empty);
            if (empty || trouble == null) {
                setText(null);
                setGraphic(null);
            } else {
                troubleNameLabel.setText(trouble.getNom());
                troubleTypeLabel.setText(trouble.getCategorie().toString());
                setGraphic(hBox);
            }
        }
    }

    public BilanO createBilanO() {
        BilanO bilanO = new BilanO();
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setTroubles(getTroubles().toArray(new Trouble[0])); // Convert list to array
        bilanO.setDiagnostique(diagnostic);
        return bilanO;
    }

    public List<Trouble> getTroubles() {
        return troubles;
    }
}
