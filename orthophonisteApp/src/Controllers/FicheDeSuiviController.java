package Controllers;

import Classes.ObjectifEvalue;
import Classes.TypeObjectif;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.util.Optional;

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
                return new GoalListCell();
            }
        });

        // Bind the observable list to the ListView
        ficheListView.setItems(goals);

        // Add some sample goals (you can replace this with actual data)
        goals.add(new ObjectifEvalue("Read Book", TypeObjectif.COURT, 3));
        goals.add(new ObjectifEvalue("Exercise", TypeObjectif.LONG, 4));
        goals.add(new ObjectifEvalue("Learn JavaFX", TypeObjectif.COURT, 5));
    }

    /*@FXML
    private void addGoal() {
        // Prompt user for goal name
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Goal");
        dialog.setHeaderText("Enter the name of the goal:");
        dialog.setContentText("Goal Name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String goalName = result.get();

            // Prompt user for goal type
            ComboBox<TypeObjectif> typeComboBox = new ComboBox<>();
            typeComboBox.getItems().addAll(TypeObjectif.values());
            typeComboBox.setValue(TypeObjectif.COURT);

            dialog.getDialogPane().setContent(typeComboBox);
            dialog.getDialogPane().setHeaderText("Select the type of the goal:");

            result = dialog.showAndWait();
            if (result.isPresent()) {
                TypeObjectif selectedType = typeComboBox.getValue();

                // Prompt user for score selection
                ComboBox<Integer> scoreComboBox = new ComboBox<>();
                scoreComboBox.getItems().addAll(1, 2, 3, 4, 5);
                scoreComboBox.setValue(1);

                dialog.getDialogPane().setContent(scoreComboBox);
                dialog.getDialogPane().setHeaderText("Select the score of the goal:");

                result = dialog.showAndWait();
                if (result.isPresent()) {
                    int selectedScore = scoreComboBox.getValue();
                    // Create a new ObjectifEvalue instance with selected score
                    goals.add(new ObjectifEvalue(goalName, selectedType, selectedScore));
                }
            }
        }
    }

*/
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


    static class GoalListCell extends ListCell<ObjectifEvalue> {
        private HBox hBox = new HBox();
        private Label goalNameLabel = new Label();
        private Label goalTypeLabel = new Label();
        private Label goalScoreLabel = new Label();

        public GoalListCell() {
            super();
            hBox.setSpacing(10);

            goalNameLabel.setPrefWidth(190);
            goalTypeLabel.setPrefWidth(190);
            goalScoreLabel.setPrefWidth(45);
            Font customFont = Font.font("Arial", FontWeight.BOLD, 14);
            goalNameLabel.setFont(customFont);
            goalTypeLabel.setFont(customFont);
            goalScoreLabel.setFont(customFont);
            HBox.setHgrow(goalNameLabel, Priority.ALWAYS);
            HBox.setHgrow(goalTypeLabel, Priority.ALWAYS);
            HBox.setHgrow(goalScoreLabel, Priority.ALWAYS);

            hBox.getChildren().addAll(goalNameLabel, goalTypeLabel, goalScoreLabel);
            hBox.setPadding(new Insets(10, 10, 15, 7));
        }

        @Override
        protected void updateItem(ObjectifEvalue objectif, boolean empty) {
            super.updateItem(objectif, empty);
            if (empty || objectif == null) {
                setText(null);
                setGraphic(null);
            } else {
                goalNameLabel.setText(objectif.getNom());
                goalTypeLabel.setText(objectif.getTypeObjectif().toString());
                goalScoreLabel.setText(String.valueOf(objectif.getNote()));
                setGraphic(hBox);
            }
        }
    }
}
