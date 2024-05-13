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

public class FicheDeSuiviController implements Initializable {
    @FXML
    private Button addGoalButton;
    @FXML
    private TableView<Objectif> table;
    @FXML
    private TableColumn<Objectif, String> goalNameClm;
    @FXML
    private TableColumn<Objectif, TypeObjectif> goalTypeClm;
    @FXML
    private TableColumn<Objectif, Integer> goalScoreClm;
    ObservableList<Objectif> listF;

    // Create sample data
    ObservableList<Objectif> data = FXCollections.observableArrayList(
            new Objectif("Goal 1", TypeObjectif.COURT, 1),
            new Objectif("Goal 2", TypeObjectif.COURT, 2),
            new Objectif("Goal 3", TypeObjectif.COURT, 3),
            new Objectif("Goal 4", TypeObjectif.COURT, 4),
            new Objectif("Goal 5", TypeObjectif.COURT, 5));

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        goalNameClm.setCellValueFactory(new PropertyValueFactory<Objectif, String>("nom"));
        goalTypeClm.setCellValueFactory(new PropertyValueFactory<Objectif, TypeObjectif>("typeObjectif"));
        goalScoreClm.setCellValueFactory(new PropertyValueFactory<Objectif, Integer>("note"));
        goalScoreClm.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        goalScoreClm.setOnEditCommit(event -> {
            try {
                // Get the edited value
                Integer newValue = event.getNewValue();

                Objectif objectif = event.getRowValue();

                if (newValue < 1 || newValue > 5) {
                    // Display an alert or handle the invalid input
                    throw new IllegalArgumentException("Score must be between 1 and 5");
                }

                // Update the score of the Objectif
                objectif.setNote(newValue);
            } catch (Exception e) {
                // Handle the exception (e.g., display an error message)
                System.out.println("Invalid input: " + e.getMessage());

                table.refresh();
            }
        });
        // Enable table editing
        table.setEditable(true);

        table.setItems(data);

    }

    @FXML
    private void addGoal(ActionEvent event) {
        // Implementation goes here
    }

}
