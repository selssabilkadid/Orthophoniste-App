package Controllers;

import Classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class DossierPatientController {

    @FXML
    private Button btnFiches;

    @FXML
    private Button btnBilans;

    @FXML
    private Button btnRendezvous;

    @FXML
    private StackPane contentPane;
    @FXML
    private Label patientFullname;
    @FXML
    private Label patientPlace;
    @FXML
    private Label patientAdress;
    @FXML
    private Label patientDate;
    @FXML
    private Label patientAge;
    @FXML
    private Label troubles;
    @FXML
    private Label contactNumber;
    @FXML
    private Label patientGrp;

    @FXML
    public void initialize() {
        btnFiches.setOnAction(event -> showFiches());
        btnBilans.setOnAction(event -> showBilans());
        btnRendezvous.setOnAction(event -> showRendezvous());
        System.out.println("DossierPatientController initialized");
    }

    public void setPatientData(Patient patient) {
        System.out.println("Setting patient data: " + patient.getFullname());

        patientFullname.setText(patient.getFullname());
        Date birthDateUtil = patient.getDatenaissance();
        LocalDate birthDate;
        if (birthDateUtil != null) {
            birthDate = birthDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate, currentDate).getYears();
            patientAge.setText(String.valueOf(age));
            System.out.println(patientAge);
        } else {
            patientAge.setText("N/A");
        }

        patientDate.setText(patient.getDateNaissanceString());
        patientPlace.setText(patient.getLieuNaissance());
        patientAdress.setText(patient.getAdresse());

        if (patient.getTroubles() == null || patient.getTroubles().isEmpty()) {
            ArrayList<Trouble> dummyTroubles = new ArrayList<>();
            dummyTroubles.add(new Trouble("Trouble 1", CategorieTrouble.deglutition));
            dummyTroubles.add(new Trouble("Trouble 2", CategorieTrouble.cognitifs));
            dummyTroubles.add(new Trouble("Trouble 3", CategorieTrouble.neuro_developpementaux));
            patient.setTroubles(dummyTroubles);
        }
        troubles.setText(patient.getTroubles().toString());
        if (patient instanceof Adulte adulte) {
            contactNumber.setText(adulte.getNumTel());
            patientGrp.setText("Adulte");
        } else if (patient instanceof Enfant enfant) {
            contactNumber.setText("Père: " + enfant.getTelpere() + "\n" + "Mère: " + enfant.getTelmere());
            patientGrp.setText("Enfant");
        }
        System.out.println("Patient data set successfully");
    }

    private void showFiches() {
        VBox fichesView = new VBox();
        fichesView.getChildren().add(new Label("Displaying Fiches"));
        contentPane.getChildren().setAll(fichesView);
    }

    private void showBilans() {
        VBox bilansView = new VBox();  // Replace with actual loading logic
        bilansView.getChildren().add(new Label("Displaying Bilans"));
        contentPane.getChildren().setAll(bilansView);
    }

    private void showRendezvous() {
        VBox rendezvousView = new VBox();  // Replace with actual loading logic
        rendezvousView.getChildren().add(new Label("Displaying Rendezvous"));
        contentPane.getChildren().setAll(rendezvousView);
    }
}
