package Layouts;

import Classes.*;
import Controllers.Main;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class DashboardController implements Initializable {
    @FXML
    private Button AddQuestion;

    @FXML
    private Button AddTrouble;

    @FXML
    private AnchorPane Adulteinformation;

    @FXML
    private AnchorPane Anamnese;

    @FXML
    private AnchorPane BOAnamnese;

    @FXML
    private AnchorPane BoAnamneseLayput;

    @FXML
    private TableColumn<Consultation, String> CBegin;

    @FXML
    private TableColumn<Consultation, String> Ccancel;

    @FXML
    private TableColumn<Consultation, LocalDate> ConsDate;

    @FXML
    private TableView<Consultation> Consultations;

    @FXML
    private AnchorPane Dashboardmain;

    @FXML
    private TableColumn<Consultation, String> EHour;

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
    private TextArea Qanswer;

    @FXML
    private ComboBox<String> Qcategorie;

    @FXML
    private TextArea Qtext;

    @FXML
    private TableColumn<Consultation, String> SHour;

    @FXML
    private Button SaveAnamnese;

    @FXML
    private Button SaveBOAnamnese;

    @FXML
    private Button SavePatient;

    @FXML
    private TextArea TherapyProject;

    @FXML
    private TextField Trouble;

    @FXML
    private ComboBox<String> TroubleType;

    @FXML
    private Label Winfo;

    @FXML
    private Label Winfo1;

    @FXML
    private Label alerteinfo;

    @FXML
    private Label alerteinfo1;

    @FXML
    private Label alerteinfo11;

    @FXML
    private AnchorPane homepage;

    @FXML
    private Text nbappointements;

    @FXML
    private Text nbatelier;

    @FXML
    private Text nbpatients;

    @FXML
    private Text nbprojet;

    @FXML
    private Label welcometext;


    private static final UserAccount Orthophoniste = AccountManager.getCurrentuser();

    private Consultation consultation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dashboardmain.setVisible(true);
        BoAnamneseLayput.setVisible(false);
        Anamnese.setVisible(false);
        BOAnamnese.setVisible(false);

        if (Orthophoniste != null) {
            welcometext.setText("Welcome Dr." + Orthophoniste.getFirstName());
            initializeConsultationsTable();
            Integer i = Orthophoniste.getRendezvous().size();
            nbappointements.setText(i.toString());
            i = Orthophoniste.getPatients().size();
            nbpatients.setText(i.toString());
            i = Orthophoniste.getConsultations().size();
            nbatelier.setText(i.toString());
            i = Orthophoniste.getMes_tests().size();
            nbprojet.setText(i.toString());
        } else {
            // Handle the case where no user is logged in
            welcometext.setText("Welcome, please log in.");
        }
    }

    Set<Consultation> consultationsSet = Orthophoniste.getConsultations();
    ObservableList<Consultation> consultationsList = FXCollections.observableArrayList(consultationsSet);

    private void initializeConsultationsTable() {
        Consultations.setItems(consultationsList);
        ConsDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        SHour.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeur_debut()));
        EHour.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeur_fin()));
        setButtonCellFactory(CBegin, "Begin", this::StartCons);
        setButtonCellFactory(Ccancel, "Cancel", this::deleteCons);
    }

    private Void deleteCons(Consultation consultation) {
        ObservableList<Consultation> testData = Consultations.getItems();
        testData.remove(consultation);
        Orthophoniste.DeleteConsultation(consultation);
        return null;
    }

    private Void StartCons(Consultation consultation) {
        Dashboardmain.setVisible(false);
        BoAnamneseLayput.setVisible(true);
        Anamnese.setVisible(false);
        BOAnamnese.setVisible(false);
        initializeBOAnamnese(consultation);
        return null;
    }

    private void setButtonCellFactory(TableColumn<Consultation, String> column, String buttonText, Callback<Consultation, Void> action) {
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

    public void initializeBOAnamnese(Consultation consultation) {
        if (consultation != null) {
            this.consultation = consultation;
            String ageString = consultation.getAge();

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
                // Display an error message or set a default behavior
                e.printStackTrace();
            }

        }
    }
    @FXML
    public void CreatePatient(ActionEvent event){
        if(event.getSource()==SavePatient) {
            String prenom = Pname.getText();
            String nom = Pfamilyname.getText();
            Date date = Date.from(Pbirthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String lieu = Pbirthplace.getText();
            String adresse = Paddress.getText();
            // Check for empty fields
            if (prenom.isEmpty() || nom.isEmpty() || lieu.isEmpty() || adresse.isEmpty()) {
                alerteinfo.setText("Please fill in all required fields.");
                return;
            }
            if (consultation.getAge().compareTo("18") == -1) {
                String numfather = Pfatherphone.getText();
                String nummother = Pmothernumber.getText();
                String grade = Pstudygrade.getText();
                Enfant E = new Enfant(nom, prenom, lieu, date, adresse, grade, nummother, numfather);
                Orthophoniste.ajouternouveaupatient(E);
                Orthophoniste.creerdossier(E);
                Dossier folder = Orthophoniste.getDossierByPatient(E);
                folder.ajouterRDV(consultation);
                consultationsList.remove(consultation);
                initializeConsultationsTable();
                alerteinfo.setText("Successfully created patient");
                initializeAnamnese();
            } else {
                String numtel = Pphone.getText();
                String studyf = Pfieldofstudy.getText();
                String profession = Pprofession.getText();
                Adulte A = new Adulte(nom, prenom, lieu, date, adresse, studyf, profession, numtel);
                Orthophoniste.ajouternouveaupatient(A);
                Orthophoniste.creerdossier(A);
                Dossier folder = Orthophoniste.getDossierByPatient(A);
                folder.ajouterRDV(consultation);
                consultationsList.remove(consultation);
                initializeConsultationsTable();
                alerteinfo.setText("Successfully created patient");
                initializeAnamnese();
            }

        }

    }


    @FXML
    public void initializeAnamnese(){
        Dashboardmain.setVisible(false);
        BoAnamneseLayput.setVisible(false);
        Anamnese.setVisible(true);
        BOAnamnese.setVisible(false);
        if(consultation.getAge().compareTo("18")==-1){
            for (CategorieQuestionEnfant category : CategorieQuestionEnfant.values()) {
                Qcategorie.getItems().add(category.name());
            }
        }else{
            for (CategorieQuestionAdult category : CategorieQuestionAdult.values()) {
                Qcategorie.getItems().add(category.name());
            }
        }
    }
    private Integer i = 1;
    private Set<QuestionAnamneseEnfant> QuestionsE = new HashSet<>();
    private Set<QuestionAnamneseAdult> QuestionsA = new HashSet<>();
    private Set<String> Answers = new HashSet<>();
    @FXML
    public void HandleAddQuestion(ActionEvent event){
        String question = Qtext.getText();
        String answer = Qanswer.getText();
        String category = Qcategorie.getValue();
        if(event.getSource()==AddQuestion) {
            if (consultation.getAge().compareTo("18") == -1) {
                QuestionAnamneseEnfant Q = new QuestionAnamneseEnfant(String.valueOf(QuestionsE.size() + 1), question, CategorieQuestionEnfant.valueOf(category));
                QuestionsE.add(Q);
                Answers.add(answer);
            } else {
                QuestionAnamneseAdult Q = new QuestionAnamneseAdult(String.valueOf(QuestionsA.size() + 1), question, CategorieQuestionAdult.valueOf(category));
                QuestionsA.add(Q);
                Answers.add(answer);
            }
            Qtext.clear();
            Qanswer.clear();
        }
    }

    Set<Anamnese> Anamneses = Orthophoniste.getAnamneses();
    @FXML
    public void CreateAnamnese(ActionEvent event){
        if (event.getSource()==SaveAnamnese){
            Anamnese A = new Anamnese(String.valueOf(Anamneses.size() + 1));
            if (consultation.getAge().compareTo("18") == -1) {
                A.setQuestionsEnfant(QuestionsE);
                Orthophoniste.ajouterAnamnese(A);
                CRAnamnese CRA = new CRAnamnese(A,consultation,Answers);
                System.out.println("ok");
                Winfo.setText("Succesfully created anamnesis");

            } else {
                A.setQuestionsAdult(QuestionsA);
                Orthophoniste.ajouterAnamnese(A);
                CRAnamnese CRA = new CRAnamnese(A,consultation,Answers);
                System.out.println("ok");
                Winfo.setText("Succesfully created anamnesis");
            }

        }
    }
    @FXML
    public void initializeBOAnamnese(){
        Dashboardmain.setVisible(false);
        BoAnamneseLayput.setVisible(false);
        Anamnese.setVisible(false);
        BOAnamnese.setVisible(true);
        for (CategorieQuestionEnfant category : CategorieQuestionEnfant.values()) {
            TroubleType.getItems().add(category.name());
        }
    }


    private Trouble[] troubles ;
    @FXML
    public void HandleADDTrouble(ActionEvent event){
        String type = TroubleType.getValue();
        String trouble = Trouble.getText();
        Trouble T = new Trouble(trouble,CategorieTrouble.valueOf(type));
        troubles[troubles.length - 1] = T;
    }

}
