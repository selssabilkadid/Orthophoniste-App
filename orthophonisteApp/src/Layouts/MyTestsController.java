package Layouts;

import Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MyTestsController {

    @FXML
    private Button addmat;

    @FXML
    private Button addprop;

    @FXML
    private Button addquest;

    @FXML
    private AnchorPane buildtest;

    @FXML
    private TableColumn<Test, String> capacite;

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
    private TextArea descriptionfield;

    @FXML
    private TextArea enoncefield;

    @FXML
    private TableColumn<Question, String> enoncequest;

    @FXML
    private TableView<Exercice> exercices_tab;

    @FXML
    private TableColumn<Exercice, String> idexo;

    @FXML
    private TextField idfield;

    @FXML
    private TableColumn<Question, String> idquest;

    @FXML
    private TextField idquestfield;

    @FXML
    private AnchorPane makenewtest;

    @FXML
    private TextField mat1;

    @FXML
    private AnchorPane mytests;

    @FXML
    private TableColumn<Test, String> nbrelements;

    @FXML
    private TableColumn<Test, String> nbrelements1;

    @FXML
    private TextField prop1;

    @FXML
    private TextField questid;

    @FXML
    private TableView<Question> questions_tab;

    @FXML
    private ComboBox<String> questtype;

    @FXML
    private Button savetest;

    @FXML
    private AnchorPane test_elements;

    @FXML
    private TextArea testcapacity;

    @FXML
    private TextField testid;

    @FXML
    private TableView<Test> tests_tab;

    @FXML
    private AnchorPane testsview;

    @FXML
    private TableColumn<Exercice, String> textexo;

    @FXML
    private TableColumn<Test, String> titre; //Test id

    @FXML
    private TableColumn<Test, String> titre1; //Test title

    @FXML
    private CheckBox true1;

    @FXML
    private TableColumn<Question, String> type; //Question type

    @FXML
    private ComboBox<String> type_test;

    @FXML
    private TableColumn<Test, String> typetest;

    @FXML
    private Label wronginfo;

    private UserAccount Orthophoniste = AccountManager.getCurrentuser();


    @FXML
    public void initialize() {
        // Set the buildtest AnchorPane to be initially invisible
        buildtest.setVisible(false);

        // Add an action listener to the createtest button
        createtest.setOnAction(event -> showBuildTestPage());
        //Intializing all comboboxes
        //test_type_combobox
        Set<String> items = Set.of("Quiz", "Exercices");
        type_test.getItems().addAll(items);
        type_test.setValue("Quiz");
        //Question type combobox
        Set<String> types = Set.of("Free Answer", "QCM","QCU");
        questtype.getItems().addAll(types);
        questtype.setValue("QCM");

        // Intialize tableviews



    }

    public void showBuildTestPage() {
        // Hide the testsview and show the buildtest view
        testsview.setVisible(false);
        buildtest.setVisible(true);
        // Show the makenewtest AnchorPane by default
        makenewtest.setVisible(true);
        // Hide the other AnchorPanes
        creationquest.setVisible(false);
        creationexo.setVisible(false);

    }

    @FXML
    void Tests_switchform(ActionEvent event) {

        if (event.getSource() == createquest) {
            // Show the creationquest AnchorPane
            creationquest.setVisible(true);
            // Hide the other AnchorPanes
            creationexo.setVisible(false);
            makenewtest.setVisible(false);
        } else if (event.getSource() == createexo) {
            // Show the creationexo AnchorPane
            creationexo.setVisible(true);
            // Hide the other AnchorPanes
            creationquest.setVisible(false);
            makenewtest.setVisible(false);
        }

    }
    Set<Question> questions = new HashSet<>();
    @FXML
    void handleaddquest(ActionEvent event){
        if(event.getSource()==addquest){

            questid.clear(); // Clear the questid TextField after adding the question
        }
    }
    @FXML
    void buildTest(ActionEvent event) {
        String id = testid.getText();
        String capacite = testcapacity.getText();
        String selectedChoice = type_test.getValue();

        if (selectedChoice.equals("Quiz")) {


            // Event handler to add questions
            addquest.setOnAction(event2 -> {
                Set<Question> newQuestions = (Set<Question>) addElement(event2);
                if (newQuestions != null) {
                    questions.addAll(newQuestions);
                }
                questid.clear(); // Clear the questid TextField after adding the question
            });

            // Adding test only if savetest button is clicked
            savetest.setOnAction(saveEvent -> {
                TestQuestionnaire quiz = new TestQuestionnaire(id, capacite, questions);
                Orthophoniste.ajouterTest(quiz);
            });

        } else if (selectedChoice.equals("Exercices")) {
            Set<Exercice> exercices = new HashSet<>();

            // Event handler to add exercises
            addquest.setOnAction(event2 -> {
                Set<Exercice> newExercices = (Set<Exercice>) addElement(event2);
                if (newExercices != null) {
                    exercices.addAll(newExercices);
                }
                questid.clear(); // Clear the questid TextField after adding the exercise
            });

            // Adding test only if savetest button is clicked
            savetest.setOnAction(saveEvent -> {
                TestExercice exos = new TestExercice(id, capacite, exercices);
                Orthophoniste.ajouterTest(exos);
            });

        } else {
            System.out.println("Invalid test type selected.");
        }
    }

    @FXML
    Set<?> addElement(ActionEvent event2) {
        if (event2.getSource() == addquest) {
            String selectedChoice = type_test.getValue();
            if (selectedChoice.equals("Quiz")) {
                Set<Question> questions = new HashSet<>();
                String idq = questid.getText();
                Question q = Orthophoniste.getQuestionById(idq);
                if (q != null) {
                    questions.add(q);
                }
                return questions;
            } else if (selectedChoice.equals("Exercices")) {
                Set<Exercice> exercices = new HashSet<>();
                String ide = questid.getText();
                Exercice exo = Orthophoniste.getExerciceById(ide);
                if (exo != null) {
                    exercices.add(exo);
                }
                return exercices;
            }
        }
        return null;
    }


    @FXML
    void createQuestion(ActionEvent actionEvent) {
        String id = idquestfield.getText();
        String enonce = enoncefield.getText();
        String selectedChoice = questtype.getValue();

        if (selectedChoice.equals("QCM")) {
            ArrayList<String> prop = new ArrayList<>();
            ArrayList<String> repvraie = new ArrayList<>();

            // Event handler to add propositions
            addprop.setOnAction(event2 -> {
                String proposition = prop1.getText();
                if (proposition != null && !proposition.isEmpty()) {
                    prop.add(proposition);
                    prop1.clear(); // Clear the prop1 TextField after adding the proposition
                }
            });

            // Adding question only if confirmquest button is clicked
            confirmquest.setOnAction(saveEvent -> {
                QuestionQCM qcm = new QuestionQCM(id, enonce, prop, repvraie);
                Orthophoniste.ajouterquestion(qcm);
            });

        } else if (selectedChoice.equals("QCU")) {
            ArrayList<String> prop = new ArrayList<>();

            // Event handler to add propositions
            addprop.setOnAction(event2 -> {
                String proposition = prop1.getText();
                if (proposition != null && !proposition.isEmpty()) {
                    prop.add(proposition);
                    prop1.clear(); // Clear the prop1 TextField after adding the proposition
                }
            });

            // Adding question only if confirmquest button is clicked
            confirmquest.setOnAction(saveEvent -> {
                String correctAnswer = prop1.getText(); // Assuming prop1 holds the correct answer for QCU
                QuestionQCU qcu = new QuestionQCU(id, enonce, correctAnswer, prop);
                Orthophoniste.ajouterquestion(qcu);
            });

        } else if (selectedChoice.equals("Free Answer")) {
            confirmquest.setOnAction(saveEvent -> {
                Question q = new Question(id, enonce);
                Orthophoniste.ajouterquestion(q);
            });
        }
    }

}
