package Layouts;

import Classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreateBOAnamnese{
    @FXML
    private AnchorPane Adulteinformation;

    @FXML
    private AnchorPane PEnfantinformation;

    @FXML
    private TextField Paddress;

    @FXML
    private DatePicker Pbirthday;

    @FXML
    private TextField Pbirthplace;

    @FXML
    private TextField Pfamilyname;

    @FXML
    private TextField Pfatherphone;

    @FXML
    private TextField Pfieldofstudy;

    @FXML
    private TextField Pmothernumber;

    @FXML
    private TextField Pname;

    @FXML
    private TextField Pphone;

    @FXML
    private TextField Pprofession;

    @FXML
    private TextField Pstudygrade;

    @FXML
    private AnchorPane mainLayout;

    @FXML
    private Label alerteinfo;

    private static final  UserAccount Orthophoniste = AccountManager.getCurrentuser();
    public Consultation consultation ;

    public void initializeBOAnamnese(){

        if (consultation != null) {
            String ageString = consultation.getAge();
            // Try to parse the age string into an integer
            try {
                int age = Integer.parseInt(ageString);
                if (age < 18) {
                    PEnfantinformation.setVisible(true);
                    Adulteinformation.setVisible(false);
                } else {
                    PEnfantinformation.setVisible(false);
                    Adulteinformation.setVisible(true);
                }
            } catch (NumberFormatException e) {
                // Handle the case where the age string cannot be parsed as an integer
                // Maybe display an error message or set a default behavior
                e.printStackTrace();
            }
        }

    }
    public void setConsultation(Consultation consultation){
        this.consultation= consultation;
        System.out.println(consultation.getNom());
    }

    public void CreatePatient(){
        String prenom = Pname.getText();
        String nom = Pfamilyname.getText();
        Date date = Date.from(Pbirthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String lieu = Pbirthplace.getText();
        String adresse = Paddress.getText();
        if (consultation.getAge().compareTo("18")==-1){
            String numfather = Pfatherphone.getText();
            String nummother = Pmothernumber.getText();
            String grade = Pstudygrade.getText();
            Enfant E = new Enfant(nom,prenom,lieu,date,adresse,grade,nummother,numfather);
            Orthophoniste.ajouternouveaupatient(E);
            Orthophoniste.creerdossier(E);
        } else {
            String numtel = Pphone.getText();
            String studyf = Pfieldofstudy.getText();
            String profession = Pprofession.getText();
            Adulte A = new Adulte(nom,prenom,lieu,date,adresse,studyf,profession,numtel);
            Orthophoniste.ajouternouveaupatient(A);
            Orthophoniste.creerdossier(A);
        }
    }
}