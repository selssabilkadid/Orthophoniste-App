package Layouts;

import Classes.Adulte;
import Classes.Enfant;
import Classes.Patient;
import Controllers.DossierPatientController;
import Controllers.HomePageController;
import Controllers.Main;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    private HomePageController homePageController;

    public void setHomePageController(HomePageController homePageController) {
        this.homePageController = homePageController;
    }

    @FXML
    public void initialize() {
        numdossier.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        groupe.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Adulte) {
                return new SimpleStringProperty(((Adulte) cellData.getValue()).getAgeGroup());
            } else if (cellData.getValue() instanceof Enfant) {
                return new SimpleStringProperty(((Enfant) cellData.getValue()).getAgeGroup());
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
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));


        patientliste.setItems(getPatients());
        patientliste.setRowFactory(tableView -> new CustomPatientRow());
    }


    private ObservableList<Patient> getPatients() {
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


            Date adultDateOfBirth = sdf.parse("01/01/1980");
            Date childDateOfBirth = sdf.parse("15/07/2010");


            patients.add(new Adulte("Kheddia", "Assia", "Chlef", adultDateOfBirth, "Algeria", "Masters", "Engineer", "123456789"));
            patients.add(new Enfant("Kadid", "Selssabil", "Medea", childDateOfBirth, "Algeria", "5th Grade", "987654321", "123456789"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return patients;

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
                        dossierPatientController.setPatientData(patient);
                        Main.changerScene(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

}

    }
