package Layouts;

import Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class RendezVousItem implements Initializable {
    @FXML
    private Button RDVDELETE;

    @FXML
    private Label RDVEND;

    @FXML
    private Label RDVNOTE;

    @FXML
    private Label RDVSTART;

    @FXML
    private ComboBox<String> RDVTYPE;

    @FXML
    private Label RDZDATE;

    // Corrected method name to follow Java naming conventions
    public void initializeComboBox() {
        Set<String> items = Set.of("Consultation", "AtelierGrp", "SeanceSuivi");
        RDVTYPE.getItems().addAll(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBox();
    }

    public void setData(RendezVous rendezVous) {
        RDVNOTE.setText(rendezVous.getObservation());
        RDVTYPE.setValue(rendezVous.getClass().getSimpleName());
        RDVSTART.setText(rendezVous.getHeur_debut());
        RDVEND.setText(rendezVous.getHeur_fin());
    }
}
