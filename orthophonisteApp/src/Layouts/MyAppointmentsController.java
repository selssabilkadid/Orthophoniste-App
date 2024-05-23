package Layouts;

import Classes.*;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MyAppointmentsController {

    @FXML
    private Button addpatient;

    @FXML
    private TextField age;

    @FXML
    private AnchorPane appointments;

    @FXML
    private AnchorPane consultdetails;

    @FXML
    private TableColumn<RendezVous, LocalDate> date;

    @FXML
    private DatePicker dateRDV;

    @FXML
    private TextField familyname;

    @FXML
    private TextField firstname;

    @FXML
    private AnchorPane groupdetails;

    @FXML
    private TableColumn<RendezVous, String> heur_fin;

    @FXML
    private TableColumn<RendezVous, String> heure_debut;

    @FXML
    private TextField heuredebutRDV;

    @FXML
    private TextField heurefinRDV;

    @FXML
    private TextField idpatient;

    @FXML
    private TextField ids;

    @FXML
    private AnchorPane main_appointments;

    @FXML
    private AnchorPane makeRDV;

    @FXML
    private Button newRDV;

    @FXML
    private TextArea noteRDV;

    @FXML
    private TableColumn<RendezVous,String> observation;

    @FXML
    private TableView<RendezVous> RDVListe;

    @FXML
    private TextField patients_liste;

    @FXML
    private AnchorPane personaldetails;

    @FXML
    private Button save_appointment;

    @FXML
    private TextField theme;
    @FXML
    private TextField ipatient;

    @FXML
    private ComboBox<String> typeRDV;

    @FXML
    private TableColumn<RendezVous,String> type_RDV;
    @FXML
    private TableView<RendezVous> rdvliste;

    @FXML
    private TableColumn<RendezVous, String> deleteRDV;

    @FXML
    private Label alerteinfo;

    private Set<Integer> patientsids = new HashSet<Integer>() ;
    private  UserAccount Orthophoniste = AccountManager.getCurrentuser();
    private Set<RendezVous> mesRDVs = Orthophoniste.getRDVs();
    ObservableList<RendezVous> RObservableList = convertSetToObservableList(mesRDVs);

    @FXML
    public void initialize() {
        makeRDV.setVisible(false);

        // Add an action listener to the createtest button
        newRDV.setOnAction(event -> showMakeRDVPage());
        //Intializing all comboboxes

        //test_type_combobox
        Set<String> items = Set.of("Consultation", "Group Session" , "Personal Session");
        typeRDV.getItems().addAll(items);
        typeRDV.setValue("Consultation");


        // Intialize views to add details abt each RDV type

        // Set action event handler for the ComboBox
        typeRDV.setOnAction(event -> handleComboBoxAction());

        // intialize date to local date
        dateRDV.setValue(LocalDate.now());

        //initialize rdv liste view of all rdvs
        intializeRDVlist();

    }

    public void showMakeRDVPage() {
        // Hide the testsview and show the buildtest view
        main_appointments.setVisible(false);
        makeRDV.setVisible(true);

    }

    private void handleComboBoxAction() {
        String choice = typeRDV.getValue();
        if (choice != null) {
            if (choice.equals("Consultation")) {
                consultdetails.setVisible(true);
                personaldetails.setVisible(false);
                groupdetails.setVisible(false);
            } else if (choice.equals("Group Session")) {
                consultdetails.setVisible(false);
                personaldetails.setVisible(false);
                groupdetails.setVisible(true);
            } else if (choice.equals("Personal Session")) {
                consultdetails.setVisible(false);
                personaldetails.setVisible(true);
                groupdetails.setVisible(false);
            }
        }
    }
    @FXML
    void handle_addpatientAction(){
        String patientid = ids.getText();
        if (patientid.isEmpty()) {
            alerteinfo.setText("Please enter a patient ID.");
            return;
        }

        try {
            int number = Integer.parseInt(patientid);
            Dossier folder = Orthophoniste.getDossierById(number);
            if (folder != null) {
                patientsids.add(number);
                alerteinfo.setText("Patient added successfully.");
            } else {
                alerteinfo.setText("Patient with ID " + patientid + " does not exist.");
            }
        } catch (NumberFormatException e) {
            alerteinfo.setText("Invalid input: " + patientid + " is not a valid integer.");
        }
    }

    @FXML
    void make_appointement(){

        String choice = typeRDV.getValue();
        LocalDate selectedDate = dateRDV.getValue();
        String heured = heuredebutRDV.getText();
        String heuref = heurefinRDV.getText();
        String note = noteRDV.getText();

        if (selectedDate == null || heured.isEmpty() || heuref.isEmpty() || note.isEmpty() || choice == null) {
            alerteinfo.setText("Please fill all required fields.");
            return;
        }

        if (choice.equals("Consultation")) {
            String name = firstname.getText();
            String surname = familyname.getText();
            String agep = age.getText();

            if (name.isEmpty() || surname.isEmpty() || agep.isEmpty()) {
                alerteinfo.setText("Please fill all required fields for Consultation.");
                return;
            }

            Consultation newconsult = new Consultation(selectedDate, heured, heuref, note, name, surname, agep);
            Orthophoniste.ajouter_Consultation(newconsult);
            alerteinfo.setText("Consultation added successfully.");
        } else if (choice.equals("Group Session")) {
            String thematique = theme.getText();

            if (thematique.isEmpty() || patientsids.isEmpty()) {
                alerteinfo.setText("Please fill all required fields for Group Session and add at least one patient.");
                return;
            }

            AtelierGrp groupe = new AtelierGrp(selectedDate, heured, heuref, note, patientsids, thematique);
            boolean allPatientsExist = true;
            for (Integer id : patientsids) {
                Dossier folder = Orthophoniste.getDossierById(id);
                if (folder != null) {
                    Orthophoniste.ajouterRDV(folder, groupe);
                } else {
                    allPatientsExist = false;
                    alerteinfo.setText("Patient with ID " + id + " does not exist.");
                    break;
                }
            }
            if (allPatientsExist) {
                alerteinfo.setText("Group Session added successfully.");
            }
        } else if (choice.equals("Personal Session")) {
            String patientid = ipatient.getText();
            if (patientid.isEmpty()) {
                alerteinfo.setText("Please fill all required fields for Personal Session.");
                return;
            }

            try {
                int number = Integer.parseInt(patientid);
                Dossier folder = Orthophoniste.getDossierById(number);
                if (folder == null) {
                    alerteinfo.setText("Patient with ID " + patientid + " does not exist.");
                    return;
                }
                SeanceSuivi seance = new SeanceSuivi(selectedDate, heured, heuref, note, number);
                Orthophoniste.ajouterRDV(folder, seance);
                alerteinfo.setText("Personal Session added successfully.");
            } catch (NumberFormatException e) {
                alerteinfo.setText("Invalid input: " + patientid + " is not a valid integer.");
            }
        }
    }

    public static ObservableList<RendezVous> convertSetToObservableList(Set<RendezVous> RSet) {
        return FXCollections.observableArrayList(RSet);
    }

    @FXML
    void intializeRDVlist() {
        date.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        heure_debut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeur_debut()));
        heur_fin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeur_fin()));
        observation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObservation()));
        type_RDV.setCellValueFactory(cellData -> new SimpleStringProperty(getRDVTypeName(cellData.getValue())));
        setButtonCellFactory(deleteRDV, "Delete", this::deleteRDV);
        rdvliste.setItems(RObservableList);
    }
    private String getRDVTypeName(RendezVous rdv) {
        if (rdv instanceof Consultation) {
            return "Consultation";
        } else if (rdv instanceof AtelierGrp) {
            return "Group session";
        } else if(rdv instanceof SeanceSuivi){
            return "Follow up"; // default case if other types of tests are added in the future
        }else{
            return "Unknown";
        }
    }


    private void setButtonCellFactory(TableColumn<RendezVous, String> column, String buttonText, Callback<RendezVous, Void> action) {
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
    private Void deleteRDV(RendezVous R) {
        System.out.println("Deleting test: " + R.getDate());
        ObservableList<RendezVous> testData = rdvliste.getItems();
        testData.remove(R);
        Orthophoniste.SupprimerRDV(R);
        return null;
    }



}

