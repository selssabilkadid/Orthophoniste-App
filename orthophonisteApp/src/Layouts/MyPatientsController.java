package Layouts;

import Classes.*;
import Controllers.DossierPatientController;
import Controllers.HomePageController;
import Controllers.Main;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPatientsController {

    @FXML
    private TableView<Patient> patientliste;

    @FXML
    private TableColumn<Patient, String> numdossier;

    @FXML
    private TableColumn<Patient, String> fullname;

    @FXML
    private TableColumn<Patient, String> groupe;

    @FXML
    private TableColumn<Patient, String> datenaissance;

    @FXML
    private TableColumn<Patient, String> lieunaissance;

    @FXML
    private TableColumn<Patient, String> numtel;

    @FXML
    private TableColumn<Patient, String> adresse;

    private UserAccount currentUser = AccountManager.getCurrentuser();
    private HomePageController homePageController;

    public void setHomePageController(HomePageController homePageController) {
        this.homePageController = homePageController;
    }

    @FXML
    public void initialize() {
        numdossier.setCellValueFactory(cellData -> new SimpleObjectProperty<>(currentUser.getDossierByPatient(cellData.getValue()).getId()));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        groupe.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Adulte) {
                return new SimpleStringProperty("Adulte");
            } else if (cellData.getValue() instanceof Enfant) {
                return new SimpleStringProperty("Child");
            } else {
                return new SimpleStringProperty("");
            }
        });
        datenaissance.setCellValueFactory(new PropertyValueFactory<>("DateNaissanceString"));
        lieunaissance.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        numtel.setCellValueFactory(cellData -> {
            Patient patient = cellData.getValue();
            if (patient instanceof Enfant) {
                return new SimpleStringProperty(((Enfant) patient).getTelpere());
            } else if (patient instanceof Adulte) {
                return new SimpleStringProperty(((Adulte) patient).getNumTel());
            } else {
                return new SimpleStringProperty("");
            }
        });

        setButtonCellFactory(adresse, "Delete", this::DeletePatient);

        patientliste.setItems(getPatients());
        patientliste.setRowFactory(tableView -> new CustomPatientRow());
    }

    private Void DeletePatient(Patient patient) {
        currentUser.supprimerPatient(patient);
        initialize();
        return null;
    }

    private void setButtonCellFactory(TableColumn<Patient, String> column, String buttonText, Callback<Patient, Void> action) {
        column.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button(buttonText);

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    button.setStyle("-fx-background-color: #FF5363; -fx-background-radius: 7; -fx-text-color: #FFFFFF;");
                    button.setOnAction(event -> action.call(getTableView().getItems().get(getIndex())));
                    setGraphic(button);
                }
                setText(null);
            }
        });
    }

    private ObservableList<Patient> getPatients() {
        return FXCollections.observableArrayList(currentUser.getPatients());
    }

    public class CustomPatientRow extends TableRow<Patient> {

        public CustomPatientRow() {
            super();
            setOnMouseClicked(this::handleMouseClicked);
        }

        @Override
        protected void updateItem(Patient item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setStyle("");
            } else {
                setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-padding: 10px;");
            }
        }

        private void handleMouseClicked(MouseEvent event) {
            if (!isEmpty() && event.getClickCount() == 1) {
                Patient patient = getItem();

                if (patient != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Layouts/DossierPatient.fxml"));
                        Parent root = loader.load();
                        DossierPatientController dossierPatientController = loader.getController();

                        Dossier dossier = currentUser.getDossierByPatient(patient);

                        System.out.println(dossier);
                        dossierPatientController.setPatientAndDossier(patient, dossier);

                        Main.changerScene(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
