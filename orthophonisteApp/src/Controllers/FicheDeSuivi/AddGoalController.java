package Controllers.FicheDeSuivi;

import Classes.TypeObjectif;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddGoalController {
    @FXML
    private TextField goalNameField;

    @FXML
    private ComboBox<TypeObjectif> typeComboBox;



    @FXML
    private void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList(TypeObjectif.values()));
        typeComboBox.setPromptText("Select goal type");
    }

    public String getGoalName() {
        return goalNameField.getText();
    }

    public TypeObjectif getSelectedGoalType() {
        return typeComboBox.getValue();
    }
}
