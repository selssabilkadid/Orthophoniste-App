package Controllers;

import Classes.ObjectifEvalue;
import Classes.TypeObjectif;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class FicheDeSuiviController {

    @FXML
    private ListView<ObjectifEvalue> ficheListView;

    private final ObservableList<ObjectifEvalue> goals = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set custom cell factory
        ficheListView.setCellFactory(new Callback<ListView<ObjectifEvalue>, ListCell<ObjectifEvalue>>() {
            @Override
            public ListCell<ObjectifEvalue> call(ListView<ObjectifEvalue> listView) {
                return new GoalCell();
            }
        });

        // Bind the observable list to the ListView
        ficheListView.setItems(goals);

        // Add some sample goals (you can replace this with actual data)
        goals.add(new ObjectifEvalue("Read Book", TypeObjectif.COURT, 3));
        goals.add(new ObjectifEvalue("Exercise", TypeObjectif.LONG, 4));
        goals.add(new ObjectifEvalue("Learn JavaFX", TypeObjectif.COURT, 5));
    }

    @FXML
    private void addGoal() {
        // Your code to add a goal
    }
    @FXML
    private void createFiche() {
        // Your code to add a goal
    }
    @FXML
    private void saveFiche() {
        // Your code to add a goal
    }
    @FXML
    private void showGoalList() {
        // Your code to show the goal list
    }
    @FXML
    private void showEvaluatedGoals() {
        // Your code to show the goal list
    }
    @FXML
    private void goToSheet() {
        // Your code to show the goal list
    }
    // Custom ListCell implementation for displaying Objectif objects
    static class GoalCell extends ListCell<ObjectifEvalue> {
        @Override
        protected void updateItem(ObjectifEvalue objectif, boolean empty) {
            super.updateItem(objectif, empty);
            if (empty || objectif == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText("Name: " + objectif.getNom() + ", Type: " + objectif.getTypeObjectif() + ", Score: " + objectif.getNote());
            }
        }
    }
}
