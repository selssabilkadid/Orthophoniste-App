package Controllers;

import Classes.CategorieTrouble;
import Classes.Trouble;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TroubleInputFieldController {

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<CategorieTrouble> typeChoiceBox;

    private VBox root;




    public VBox getRoot() {
        return root;
    }
    @FXML
    public void initialize() {
        initializeChoiceBox();
    }

    private void initializeChoiceBox() {
        List<CategorieTrouble> categories = Arrays.asList(CategorieTrouble.values());
        typeChoiceBox.getItems().addAll(categories);
    }

    public Trouble getTrouble() {
        String name = nameField.getText();
        CategorieTrouble type = typeChoiceBox.getValue();
        return new Trouble(name, type);
    }
}
