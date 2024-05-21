package Controllers.FicheDeSuivi;


import Classes.*;
import Controllers.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class GoalListController {
    @FXML
    private ListView<Objectif> noScoreGoals;

    private final ObservableList<Objectif> goals = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Set custom cell factory
        noScoreGoals.setCellFactory(new Callback<ListView<Objectif>, ListCell<Objectif>>() {
            @Override
            public ListCell<Objectif> call(ListView<Objectif> listView) {
                return new  GoalListCell();
            }
        });

        // Bind the observable list to the ListView
        noScoreGoals.setItems(goals);

        goals.add(new Objectif("Read Book", TypeObjectif.COURT ));
        goals.add(new Objectif("Exercise", TypeObjectif.LONG));
        goals.add(new Objectif("Learn JavaFX", TypeObjectif.COURT));
    }


    @FXML
    private void showEvaluatedGoals(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/Layouts/FicheDeSuivi/EvaluatedGoals.fxml");
    }

    @FXML
    private void goToSheet() {
        // Your code to show the goal list
    }
    static class GoalListCell extends ListCell<Objectif> {
        private HBox hBox = new HBox();
        private Label goalNameLabel = new Label();
        private Label goalTypeLabel = new Label();

        public GoalListCell() {
            super();
            hBox.setSpacing(10);

            goalNameLabel.setPrefWidth(190);
            goalTypeLabel.setPrefWidth(190);
            Font customFont = Font.font("Arial", FontWeight.BOLD, 14);
            goalNameLabel.setFont(customFont);
            goalTypeLabel.setFont(customFont);
            HBox.setHgrow(goalNameLabel, Priority.ALWAYS);
            HBox.setHgrow(goalTypeLabel, Priority.ALWAYS);


            hBox.getChildren().addAll(goalNameLabel, goalTypeLabel);
            hBox.setPadding(new Insets(10, 10, 15, 7));
        }
        @Override
        protected void updateItem(Objectif objectif, boolean empty) {
            super.updateItem(objectif, empty);
            if (empty || objectif == null) {
                setText(null);
                setGraphic(null);
            } else {
                goalNameLabel.setText(objectif.getNom());
                goalTypeLabel.setText(objectif.getTypeObjectif().toString());

                setGraphic(hBox);
            }
        }
}
    @FXML
    private void addGoaltoList() {
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


                goals.add(new Objectif(goalName, selectedType));

            }
        }
    }
}
