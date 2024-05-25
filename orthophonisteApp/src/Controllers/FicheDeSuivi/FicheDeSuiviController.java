package Controllers.FicheDeSuivi;

import Classes.FicheDeSuiviDone;
import Classes.FichedeSuivi;
import Controllers.FicheDeSuivi.AddGoalController;
import Classes.ObjectifEvalue;
import Classes.TypeObjectif;
import Controllers.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ComboBox;

public class FicheDeSuiviController {

    @FXML
    private ListView<ObjectifEvalue> ficheListView;

    private final ObservableList<ObjectifEvalue> goals = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Add some sample goals (you can replace this with actual data)
        goals.add(new ObjectifEvalue("Read Book", TypeObjectif.COURT));
        goals.add(new ObjectifEvalue("Exercise", TypeObjectif.LONG));
        goals.add(new ObjectifEvalue("Learn JavaFX", TypeObjectif.COURT));

        // Set custom cell factory
        ficheListView.setCellFactory(new Callback<ListView<ObjectifEvalue>, ListCell<ObjectifEvalue>>() {
            @Override
            public ListCell<ObjectifEvalue> call(ListView<ObjectifEvalue> listView) {
                return new GoalListCell();
            }
        });

        // Bind the observable list to the ListView
        ficheListView.setItems(goals);
        ficheListView.setPlaceholder(new Label("No current sheets, please create a new one"));
    }



@FXML
private void addGoal() {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Add Goal");
//    dialog.setHeaderText("Enter the details of the goal:");

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Layouts/FicheDeSuivi/AddGoal.fxml"));
    try {
        dialog.getDialogPane().setContent(loader.load());
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Set the button types
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    // Get the controller instance
    AddGoalController controller = loader.getController();

    // Convert the result to a Goal object when the OK button is clicked
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            String goalName = controller.getGoalName();
            TypeObjectif selectedType = controller.getSelectedGoalType();
            if (goalName != null && !goalName.isEmpty() && selectedType != null) {
                goals.add(new ObjectifEvalue(goalName, selectedType));
            }
        }
        return null;
    });

    dialog.showAndWait();
}

    @FXML
    private void createFiche() {

        Dialog<List<ObjectifEvalue>> dialog = new Dialog<>();
        dialog.setTitle("Create Fiche");
        dialog.setHeaderText("Enter the goals for the new fiche:");


        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField goalNameField = new TextField();
        goalNameField.setPromptText("Goal Name");
        ComboBox<TypeObjectif> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll(TypeObjectif.values());
        typeComboBox.setPromptText("Select goal type");

        grid.add(new Label("Goal Name:"), 0, 0);
        grid.add(goalNameField, 1, 0);
        grid.add(new Label("Goal Type:"), 0, 1);
        grid.add(typeComboBox, 1, 1);

        dialog.getDialogPane().setContent(grid);


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                List<ObjectifEvalue> newGoals = new ArrayList<>();
                String goalName = goalNameField.getText();
                TypeObjectif goalType = typeComboBox.getValue();
                if (goalName != null && !goalName.isEmpty() && goalType != null) {
                    newGoals.add(new ObjectifEvalue(goalName, goalType));
                }
                return newGoals;
            }
            return null;
        });

        Optional<List<ObjectifEvalue>> result = dialog.showAndWait();
        result.ifPresent(newGoals -> goals.addAll(newGoals));
    }





    @FXML
    private void saveFiche() {
        FicheDeSuiviDone ficheDeSuiviDone = new FicheDeSuiviDone();
        ficheDeSuiviDone.setObjectifsAtteints(new ArrayList<>(goals));
        LocalDateTime savedDateTime = LocalDateTime.now();
        ficheDeSuiviDone.setSavedDate(savedDateTime);

        SharedModel.getFicheDeSuiviDoneList().add(ficheDeSuiviDone);


        goals.clear();


        if (goals.isEmpty()) {
            ficheListView.setPlaceholder(new Label("No current sheets, please create a new one"));
        }
    }




    @FXML
    private void showEvaluatedGoals(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/Layouts/FicheDeSuivi/EvaluatedGoals.fxml");
    }

    @FXML
    private void goToSheet(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();


        m.changeScene("/Layouts/FicheDeSuivi/OtherSheets.fxml");
    }

    public void showGoalList(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/Layouts/FicheDeSuivi/GoalList.fxml");
    }
    @FXML
    private void gradeGoal(ObjectifEvalue goal) {
        // Prompt user for the grade
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Grade Goal");
        dialog.setHeaderText("Enter the grade for the goal:");
        dialog.setContentText("Grade:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            int grade = Integer.parseInt(result.get());
            goal.addScore(grade);
        }
    }

    static class GoalListCell extends ListCell<ObjectifEvalue> {
        private HBox hBox = new HBox();
        private Label goalNameLabel = new Label();
        private Label goalTypeLabel = new Label();
        private ComboBox<Integer> gradeComboBox = new ComboBox<>(); // ComboBox for grading

        public GoalListCell() {
            super();
            hBox.setSpacing(10);

            goalNameLabel.setPrefWidth(190);
            goalTypeLabel.setPrefWidth(190);
            gradeComboBox.getItems().addAll(0, 1, 2, 3, 4, 5); // Add grade options
            gradeComboBox.setPromptText("Grade"); // Set prompt text
            Font customFont = Font.font("Arial", FontWeight.BOLD, 14);
            goalNameLabel.setFont(customFont);
            goalTypeLabel.setFont(customFont);
            gradeComboBox.setPrefWidth(50);
            HBox.setHgrow(goalNameLabel, Priority.ALWAYS);
            HBox.setHgrow(goalTypeLabel, Priority.ALWAYS);
            HBox.setHgrow(gradeComboBox, Priority.ALWAYS);

            hBox.getChildren().addAll(goalNameLabel, goalTypeLabel, gradeComboBox);
            hBox.setPadding(new Insets(10, 10, 15, 7));


            gradeComboBox.setOnAction(event -> {
                ObjectifEvalue goal = getItem();
                if (goal != null) {
                    goal.addScore(gradeComboBox.getValue());
                }
            });
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


                if (!objectif.getScores().isEmpty()) {
                    gradeComboBox.setValue(objectif.getScores().get(objectif.getScores().size() - 1));
                } else {
                    gradeComboBox.setValue(0);
                }

                setGraphic(hBox);
            }
        }
    }

}
