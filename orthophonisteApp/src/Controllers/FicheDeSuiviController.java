package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import java.net.URL;
import java.util.ResourceBundle;

import Classes.Objectif;
import Classes.TypeObjectif;

public class FicheDeSuiviController implements Initializable {
    @FXML
    private Button addGoalButton;
    @FXML
    private TableView<Objectif> table;
    @FXML
    private TableColumn<Objectif, String> goalNameClm;
    @FXML
    private TableColumn<Objectif, TypeObjectif> goalTypeClm;
    ObservableList<Objectif> listF;

    // Create sample data
    ObservableList<Objectif> data = FXCollections.observableArrayList(
            new Objectif("Goal 1", TypeObjectif.COURT),
            new Objectif("Goal 2", TypeObjectif.COURT));

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        goalNameClm.setCellValueFactory(new PropertyValueFactory<Objectif, String>("nom"));
        goalTypeClm.setCellValueFactory(new PropertyValueFactory<Objectif, TypeObjectif>("typeObjectif"));

        table.setEditable(true);
        table.setItems(data);

    }

    @FXML
    private void addGoal(ActionEvent event) {
    }

}
