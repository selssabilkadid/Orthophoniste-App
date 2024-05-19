package Controllers;

import Classes.ObjectifEvalue;
import Classes.TypeObjectif;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class AddGoalController {
    @FXML
    private TextInputDialog goalNameDialog;

    @FXML
    private ComboBox<TypeObjectif> typeComboBox;

    @FXML
    private ComboBox<Integer> scoreComboBox;

    private ObservableList<ObjectifEvalue> goals;

    public void setGoals(ObservableList<ObjectifEvalue> goals) {
        this.goals = goals;
    }

    @FXML
    private void initialize() {
        typeComboBox.getItems().addAll(TypeObjectif.values());
        typeComboBox.setValue(TypeObjectif.COURT);

        scoreComboBox.getItems().addAll(1, 2, 3, 4, 5);
        scoreComboBox.setValue(1);
    }

    @FXML
    private void addGoal() {
        String goalName = goalNameDialog.showAndWait().orElse("");
        TypeObjectif selectedType = typeComboBox.getValue();
        int selectedScore = scoreComboBox.getValue();

        goals.add(new ObjectifEvalue(goalName, selectedType, selectedScore));

        // Close the dialog
        Stage stage = (Stage) typeComboBox.getScene().getWindow();
        stage.close();
    }
}
