package Layouts;

import Classes.AccountManager;
import Classes.Consultation;
import Classes.RendezVous;
import Classes.UserAccount;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

public class DashboardController implements Initializable {
    @FXML
    private TableColumn<Consultation, String> CBegin;

    @FXML
    private TableColumn<Consultation, String> Ccancel;

    @FXML
    private TableColumn<Consultation, LocalDate> ConsDate;

    @FXML
    private TableView<Consultation> Consultations;

    @FXML
    private TableColumn<Consultation, String> EHour;

    @FXML
    private TableColumn<Consultation, String> SHour;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Orthophoniste != null) {
            welcometext.setText("Welcome Dr." + Orthophoniste.getFirstName());
            initializeConsultationsTable();
            Integer i = Orthophoniste.getRendezvous().size();
            nbappointements.setText(i.toString());
            i =Orthophoniste.getPatients().size();
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
}
