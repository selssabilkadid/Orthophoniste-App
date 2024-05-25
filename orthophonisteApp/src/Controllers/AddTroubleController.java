package Controllers;

import Classes.CategorieTrouble;
import Classes.TypeObjectif;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddTroubleController {
    @FXML
    private TextField troubleNameField;

    @FXML
    private ComboBox<CategorieTrouble> typeComboBox;



    @FXML
    private void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList(CategorieTrouble.values()));
        typeComboBox.setPromptText("Select goal type");
    }

    public String getTroubleName() {
        return troubleNameField.getText();
    }

    public CategorieTrouble getSelectedTroubleType() {
        return typeComboBox.getValue();
    }
}
