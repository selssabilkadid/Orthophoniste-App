package Controllers.FicheDeSuivi;

import Classes.ObjectifEvalue;
import Classes.TypeObjectif;
import Controllers.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.io.IOException;

public class EvaluatedGoalsController {
    @FXML
    private ListView<ObjectifEvalue> yesScoreGoals;
    private final ObservableList<ObjectifEvalue> goals = FXCollections.observableArrayList();
    public void initialize() {
        // Set custom cell factory
        yesScoreGoals.setCellFactory(new Callback<ListView<ObjectifEvalue>, ListCell<ObjectifEvalue>>() {
            @Override
            public ListCell<ObjectifEvalue> call(ListView<ObjectifEvalue> listView) {
                return new GoalListCell();
            }
        });

        // Bind the observable list to the ListView
        yesScoreGoals.setItems(goals);

        // Add some sample goals (you can replace this with actual data)
        goals.add(new ObjectifEvalue("Read Book", TypeObjectif.COURT, 3));
        goals.add(new ObjectifEvalue("Exercise", TypeObjectif.LONG, 4));
        goals.add(new ObjectifEvalue("Learn JavaFX", TypeObjectif.COURT, 5));
    }


    @FXML
    private void goToSheet() {
        // Your code to show the goal list
    }

    public void showGoalList(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/Layouts/FicheDeSuivi/GoalList.fxml");
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
