package Controllers;

import Classes.Orthophoniste;
import Classes.Question;
import Classes.QuestionQCM;
import Classes.QuestionQCU;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class HomePageController {

    @FXML
    private Button addnewpatient;

    @FXML
    private TableColumn<?, ?> adresse;

    @FXML
    private Button appointementbtn;

    @FXML
    private TableColumn<?, ?> appointementlist;

    @FXML
    private Button calendarbtn;

    @FXML
    private TableColumn<?, ?> capacite;

    @FXML
    private Button confirmexo;

    @FXML
    private Button confirmquest;

    @FXML
    private Button createexo;

    @FXML
    private Button createquest;

    @FXML
    private Button createtest;

    @FXML
    private AnchorPane creationexo;

    @FXML
    private AnchorPane creationquest;

    @FXML
    private TableColumn<?, ?> datenaissance;

    @FXML
    private TextArea descriptionfield;

    @FXML
    private TextArea enoncefield;

    @FXML
    private TableColumn<?, ?> enoncequest;

    @FXML
    private TableColumn<?, ?> fullname;

    @FXML
    private TableColumn<?, ?> groupe;

    @FXML
    private ImageView helpicon;

    @FXML
    private AnchorPane homepage;

    @FXML
    private TableColumn<?, ?> idexo;

    @FXML
    private TextField idfield;

    @FXML
    private TableColumn<?, ?> idquest;

    @FXML
    private TextField idquestfield;

    @FXML
    private TableColumn<?, ?> lieunaissance;

    @FXML
    private AnchorPane logobar;

    @FXML
    private TextField mat1;

    @FXML
    private TextField mat2;

    @FXML
    private TextField mat3;

    @FXML
    private TextField mat4;

    @FXML
    private TextField mat5;

    @FXML
    private Button mypatientsbtn;

    @FXML
    private AnchorPane mytests;

    @FXML
    private Button mytestsbtn;

    @FXML
    private Text nbappointements;

    @FXML
    private Text nbatelier;

    @FXML
    private Text nbpatients;

    @FXML
    private Text nbprojet;

    @FXML
    private TableColumn<?, ?> nbrelements;

    @FXML
    private TableColumn<?, ?> nbrelements1;

    @FXML
    private TableColumn<?, ?> numdossier;

    @FXML
    private TableColumn<?, ?> numtel;

    @FXML
    private Button overviewbtn;

    @FXML
    private AnchorPane patientliste;

    @FXML
    private TextField prop1;

    @FXML
    private TextField prop2;

    @FXML
    private TextField prop3;

    @FXML
    private TextField prop4;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private TableColumn<?, ?> textexo;

    @FXML
    private TableColumn<?, ?> titre;

    @FXML
    private TableColumn<?, ?> titre1;

    @FXML
    private SplitMenuButton typequest;

    @FXML
    private TableColumn<?, ?> typetest;

    @FXML
    private Text welcometext;
    @FXML
    private AnchorPane buildtest;
    @FXML
    private Label wronginfo;

    public void switchFrom(ActionEvent event){
        if(event.getSource()== overviewbtn){
            homepage.setVisible(true);
            patientliste.setVisible(false);
            mytests.setVisible(false);
            buildtest.setVisible(false);
        } else if(event.getSource()== mypatientsbtn){
            homepage.setVisible(false);
            patientliste.setVisible(true);
            mytests.setVisible(false);
            buildtest.setVisible(false);
        }else if(event.getSource()== mytestsbtn){
            homepage.setVisible(false);
            patientliste.setVisible(false);
            mytests.setVisible(true);
            buildtest.setVisible(false);
        }else if(event.getSource()== createtest){
            homepage.setVisible(false);
            patientliste.setVisible(false);
            mytests.setVisible(false);
            buildtest.setVisible(true);
        }
    }
    public void Tests_switchform(ActionEvent event){
        if(event.getSource()== createquest){
            creationquest.setVisible(true);
            creationexo.setVisible(false);
        } else if(event.getSource()== createexo){
            creationquest.setVisible(false);
            creationexo.setVisible(true);
        }
    }
    private void initializeMenuItems(SplitMenuButton menuButton) {
        for (MenuItem item : menuButton.getItems()) {
            item.setOnAction(event -> {
                if (item instanceof RadioMenuItem) {
                    ((RadioMenuItem) item).setSelected(true);
                    menuButton.setText(item.getText());
                }
            });
        }
    }
    public void initialize() {
        initializeMenuItems(typequest);
        // Other initialization code...
    }

    private String getSelectedItem(SplitMenuButton menuButton) {
        for (MenuItem item : menuButton.getItems()) {
            if (item instanceof RadioMenuItem && ((RadioMenuItem) item).isSelected()) {
                menuButton.setText(item.getText());
                return item.getText();
            }
        }
        return null;
    }

    public void Create_Question(ActionEvent event) throws IOException {
        if (event.getSource() == confirmquest) {
            if (idquestfield.getText().isEmpty() || enoncefield.getText().isEmpty()) {
                wronginfo.setText("Please enter your question information");
            } else {
                String selectedItem = getSelectedItem(typequest);
                if (selectedItem != null) {
                    System.out.println("Selected item: " + selectedItem);
                    if (selectedItem.equals("QCM") || selectedItem.equals("QCU")) {
                        if (selectedItem.equals("QCU") && !prop1.getText().isEmpty() && !prop2.getText().isEmpty()) {
                            Set<String> propositions = new LinkedHashSet<>();
                            propositions.add(prop1.getText());
                            propositions.add(prop2.getText());
                            QuestionQCU qcu = new QuestionQCU(idquestfield.getText(), enoncefield.getText(), prop3.getText(), propositions);
                            Orthophoniste.ajouterquestion(qcu);
                        } else if (selectedItem.equals("QCM") && !prop1.getText().isEmpty() && !prop2.getText().isEmpty() && !prop3.getText().isEmpty() && !prop4.getText().isEmpty()) {
                            Set<String> propositions = new LinkedHashSet<>();
                            propositions.add(prop1.getText());
                            propositions.add(prop2.getText());
                            propositions.add(prop3.getText());
                            propositions.add(prop4.getText());
                            QuestionQCM qcm = new QuestionQCM(idquestfield.getText(), enoncefield.getText(), propositions, propositions);
                            Orthophoniste.ajouterquestion(qcm);
                        } else {
                            wronginfo.setText("Please enter your propositions");
                        }
                    } else if (selectedItem.equals("Free Answer")) {
                        Question q = new Question(idquestfield.getText(), enoncefield.getText());
                        Orthophoniste.ajouterquestion(q);
                    }
                } else {
                    wronginfo.setText("Please select a question type");
                }
            }
        }
    }

    public void Question_Liste(){

    }
}

