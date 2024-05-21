package Layouts;

import Classes.*;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MyAppointmentsController {
    @FXML
    private VBox RDVLIST;

    @FXML
    private HBox RDVListeHEADER;

    @FXML
    private HBox RDVListeITEM;
    @FXML
    private Button addpatient;

    @FXML
    private JFXDatePicker datepick;

    @FXML
    private JFXTimePicker timepick1;

    @FXML
    private JFXTimePicker timepick2;

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



    private Set<Integer> patientsids = new HashSet<Integer>() ;
    private UserAccount Orthophoniste;

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
            try {
                int number = Integer.parseInt(patientid);
                patientsids.add(number);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + patientid + " is not a valid integer.");
            }
    }
    @FXML
    void make_appointement(){
        String choice = typeRDV.getValue();
        LocalDate selectedDate = dateRDV.getValue();
        String heured = heuredebutRDV.getText();
        String heuref = heurefinRDV.getText();
        String note = noteRDV.getText();

        if (choice.equals("Consultation")) {
            String name = firstname.getText();
            String surname = familyname.getText();
            String agep = age.getText();
            Consultation newconsult = new Consultation(selectedDate,heured,heuref,note,name,surname,agep);
            Orthophoniste.ajouter_Consultation(newconsult);
        } else if (choice.equals("Group Session")) {
            String thematique = theme.getText();
            addpatient.setOnAction(event -> handle_addpatientAction());
            AtelierGrp groupe = new AtelierGrp(selectedDate,heured,heuref,note,patientsids,thematique);
            for(Integer id : patientsids){
                Dossier folder = Orthophoniste.getDossierById(id);
                if(folder != null){
                Orthophoniste.ajouterRDV(folder,groupe);}
            }
        } else if (choice.equals("Personal Session")) {
            String patientid = ipatient.getText();
            try {
                int number = Integer.parseInt(patientid);
                Dossier folder = Orthophoniste.getDossierById(number);
                SeanceSuivi seance = new SeanceSuivi(selectedDate,heured,heuref,note,number);
                if(folder!= null){
                Orthophoniste.ajouterRDV(folder,seance);}
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + patientid + " is not a valid integer.");
            }

        }
    }


//    Set<RendezVous> rdvs = new HashSet<RendezVous>();
//    // Correct instantiation of LocalDate
//    // Declare Consultation objects
//    Consultation consultation1 = new Consultation(LocalDate.of(2024, 5, 21), "13:00", "15:00","hi", "Malak", "Kadid", "13");
//    Consultation consultation2 = new Consultation(LocalDate.of(2024, 5, 22), "10:00", "12:00","hello", "Alice", "Smith", "14");
//    Consultation consultation3 = new Consultation(LocalDate.of(2024, 5, 23), "09:00", "11:00","welcome", "John", "Doe", "15");
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Set<RendezVous> rdvs = new HashSet<>(); // Ensure this set is properly initialized
//        rdvs.add(consultation3);
//        rdvs.add(consultation2);
//        rdvs.add(consultation1);
//
//        for (RendezVous RDV : rdvs) {
//            FXMLLoader fxmlloader = new FXMLLoader();
//            fxmlloader.setLocation(getClass().getResource("/Layouts/RendezVousItem.fxml"));
//            try {
//                HBox hbox = fxmlloader.load();
//                RendezVousItem item = fxmlloader.getController(); // Get the controller from the FXMLLoader
//                item.setData(RDV); // Use the loaded controller instance to set data
//                RDVLIST.getChildren().add(hbox);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}

