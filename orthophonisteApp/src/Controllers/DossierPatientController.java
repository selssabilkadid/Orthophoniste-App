package Controllers;

import Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Label profession;
    @FXML
    private Label profType;
    @FXML
    private VBox troublesVBox;
    @FXML
    private Button createBo;
    private Dossier dossier;
    private Patient patient;
    @FXML
    public void initialize() {
        btnFiches.setOnAction(event -> showFiches());
        btnBilans.setOnAction(event -> showBilans());
        btnRendezvous.setOnAction(event -> showRendezvous());
        createBo.setOnAction(event -> createBo());
        System.out.println("DossierPatientController initialized");
    }
    public void setPatientAndDossier(Patient patient, Dossier dossier) {
        this.patient = patient;
        this.dossier = dossier;
        setPatientData(patient);
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

        troublesVBox.getChildren().clear(); // Clear any existing troubles
        if (patient.getTroubles() == null || patient.getTroubles().isEmpty()) {
            ArrayList<Trouble> dummyTroubles = new ArrayList<>();
            dummyTroubles.add(new Trouble("Trouble 1", CategorieTrouble.deglutition));
            dummyTroubles.add(new Trouble("Trouble 2", CategorieTrouble.cognitifs));
            dummyTroubles.add(new Trouble("Trouble 3", CategorieTrouble.neuro_developpementaux));
            patient.setTroubles(dummyTroubles);
        }

        for (Trouble trouble : patient.getTroubles()) {
            Text troubleText = new Text(trouble.getNom());
            troubleText.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14;");
            troublesVBox.getChildren().add(troubleText);
        }
        if (patient instanceof Adulte adulte) {
            contactNumber.setText(adulte.getNumTel());
            patientGrp.setText("Adulte");
            profType.setText("Profession");
            profession.setText(adulte.getProfession());
        } else if (patient instanceof Enfant enfant) {
            contactNumber.setText("Père: " + enfant.getTelpere() + "\n" + "Mère: " + enfant.getTelmere());
            patientGrp.setText("Enfant");
            profType.setText("Grade");
            profession.setText(enfant.getClasseEtude());
        }
        System.out.println("Patient data set successfully");
    }

    private void showFiches() {
        VBox fichesView = new VBox();
        fichesView.getChildren().add(new Label("Displaying Fiches"));
        contentPane.getChildren().setAll(fichesView);
    }

    private void showBilans() {
        VBox bilansView = new VBox();

        for (BilanO bilan : dossier.getBilans()) {

            Label bilanLabel = new Label(" Projet: " + bilan.getProjet().getProjet());
            bilansView.getChildren().add(bilanLabel);
        }

        contentPane.getChildren().setAll(bilansView);
    }

    private void showRendezvous() {
        VBox rendezvousView = new VBox();
        rendezvousView.getChildren().add(new Label("Displaying Rendezvous"));
        contentPane.getChildren().setAll(rendezvousView);
    }

    private void createBo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layouts/CreateBO.fxml")); // Ensure this path is correct
            ScrollPane createBoPane = loader.load();

            CreateBoController createBoController = loader.getController();
            createBoController.setDossier(dossier);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(createBoPane));
            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();
        m.changeScene("../layouts/HomePage.fxml");
    }
}


