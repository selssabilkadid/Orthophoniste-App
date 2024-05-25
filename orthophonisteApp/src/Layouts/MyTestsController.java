
package Layouts;

import Classes.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyTestsController {

    @FXML
    private TableColumn<Question, String> EE;

    @FXML
    private TableColumn<Exercice, String> EE1;

    @FXML
    private TableColumn<Question, String> Edelete;

    @FXML
    private TableColumn<Exercice, String> Edelete1;

    @FXML
    private TableColumn<Question, String> Eedit;

    @FXML
    private TableColumn<Exercice, String> Eedit1;

    @FXML
    private TableColumn<Question, String> Eid;

    @FXML
    private TableColumn<Exercice, String> Eid1;

    @FXML
    private TableColumn<Question, String> Etype;

    @FXML
    private TableColumn<Exercice, String> Etype1;

    @FXML
    private TableView<Exercice> Exercicesview;

    @FXML
    private TableView<Question> Quizview;

    @FXML
    private Button TaddnewQ;

    @FXML
    private AnchorPane TeditTest;

    @FXML
    private Button Tsavechanges;

    @FXML
    private Label Ttitle;

    @FXML
    private Label Ttype;

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
    private TableColumn<Test, String> editTest;

    @FXML
    private TableColumn<Test, String> deleteTest;

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
    private TableColumn<Test, String> title;

    @FXML
    private CheckBox true1;

    @FXML
    private TableColumn<Question, String> type;

    @FXML
    private ComboBox<String> type_test;

    @FXML
    private TableColumn<Test, String> typetest;

    @FXML
    private Label wronginfo;

    private UserAccount Orthophoniste = AccountManager.getCurrentuser();

    private Test currentTest;

    private Set<Test> mestests = Orthophoniste.getMes_tests();
    ObservableList<Test> testObservableList = convertSetToObservableList(mestests);
    private Set<Question> mesquestions = Orthophoniste.getQuestions();
    ObservableList<Question> QObservableList = convertSetQToObservableList(mesquestions);
    private Set<Exercice> mesexos = Orthophoniste.getExercice();
    ObservableList<Exercice> EObservableList = convertSetEToObservableList(mesexos);

    @FXML
    public void initialize() {
        buildtest.setVisible(false);
        TeditTest.setVisible(false);
        intializeTestslist();
        createtest.setOnAction(event -> showBuildTestPage());

        Set<String> items = Set.of("Quiz", "Exercices");
        type_test.getItems().addAll(items);
        type_test.setValue("Quiz");

        Set<String> types = Set.of("Free Answer", "QCM", "QCU");
        questtype.getItems().addAll(types);
        questtype.setValue("QCM");

        intializeExoSet();
        intializeQuestionSet();
        intializeTestslist();
        TestQuestionnaire test1 = new TestQuestionnaire("Test 1", "Test capacity 1", createDummyQuestions());
        TestExercice test2 = new TestExercice("Test 2", "Test capacity 2", createDummyExercices());

        testObservableList.addAll(test1, test2);

    }
    private Set<Question> createDummyQuestions() {
        Set<Question> questions = new HashSet<>();

        QuestionQCM question1 = new QuestionQCM("Q1", "Question 1: What is 2 + 2?", new ArrayList<>(List.of("3", "4", "5")));
        QuestionQCU question2 = new QuestionQCU("Q2", "Question 2: What is the capital of France?", new ArrayList<>(List.of("Paris", "Berlin", "London")));

        questions.add(question1);
        questions.add(question2);

        return questions;
    }

    private Set<Exercice> createDummyExercices() {
        Set<Exercice> exercices = new HashSet<>();

        // Create dummy exercices and add them to the set
        Exercice exercice1 = new Exercice("E1", "Exercice 1: Solve the following equation", new HashSet<>(Set.of("Equation 1", "Equation 2")));
        Exercice exercice2 = new Exercice("E2", "Exercice 2: Write a short paragraph about your favorite hobby", new HashSet<>(Set.of("Paragraph task")));

        exercices.add(exercice1);
        exercices.add(exercice2);

        return exercices;
    }
    public void showBuildTestPage() {
        testsview.setVisible(false);
        buildtest.setVisible(true);
        makenewtest.setVisible(true);
        creationquest.setVisible(false);
        creationexo.setVisible(false);
        TeditTest.setVisible(false);
    }

    @FXML
    void Tests_switchform(ActionEvent event) {
        if (event.getSource() == createquest) {
            creationquest.setVisible(true);
            creationexo.setVisible(false);
            makenewtest.setVisible(false);
        } else if (event.getSource() == createexo) {
            creationexo.setVisible(true);
            creationquest.setVisible(false);
            makenewtest.setVisible(false);
        }
    }

    private ArrayList<String> propositions = new ArrayList<>();

    @FXML
    void handleAddpropAction() {
        String prop = prop1.getText();
        if (prop != null && !prop.isEmpty()) {
            propositions.add(prop);
            prop1.clear();
        }
    }

    @FXML
    void CreateQuestion(ActionEvent event) {
        String id = idquestfield.getText();
        String enonce = enoncefield.getText();
        String selectedChoice = questtype.getValue();

        if (selectedChoice.equals("QCM") || selectedChoice.equals("QCU")) {
            addprop.setOnAction(event2 -> handleAddpropAction());
            confirmquest.setOnAction(saveEvent -> {
                if (selectedChoice.equals("QCM")) {
                    QuestionQCM qcm = new QuestionQCM(id, enonce, propositions);
                    Orthophoniste.ajouterquestion(qcm);
                    System.out.println(qcm.getenonce());
                } else if (selectedChoice.equals("QCU")) {
                    QuestionQCU qcu = new QuestionQCU(id, enonce, propositions);
                    Orthophoniste.ajouterquestion(qcu);

                    System.out.println(qcu.getenonce());
                }
                idquestfield.clear();
                enoncefield.clear();
                propositions.clear();
            });
        } else if (selectedChoice.equals("Free Answer")) {
            confirmquest.setOnAction(saveEvent -> {
                Question q = new Question(id, enonce);
                Orthophoniste.ajouterquestion(q);
                System.out.println(q.getenonce());
                idquestfield.clear();
                enoncefield.clear();
            });
        }
    }

    private Set<String> materials = new HashSet<>();

    @FXML
    void handleAddMaterialAction() {
        String mat = mat1.getText();
        if (mat != null && !mat.isEmpty()) {
            materials.add(mat);
            mat1.clear();
        }
    }

    @FXML
    void CreateExercice(ActionEvent event) {
        String id = idfield.getText();
        String enonce = descriptionfield.getText();
        if (event.getSource() == confirmexo) {
            Exercice exo = new Exercice(id, enonce, materials);
            Orthophoniste.ajouterExercice(exo);
            System.out.println(exo.getConsigne());
            idfield.clear();
            descriptionfield.clear();
            materials.clear();
        }
    }

    private Set<Question> questions = new HashSet<>();
    private Set<Exercice> exercices = new HashSet<>();

    @FXML
    void handleAddElement() {
        String quest = questid.getText();
        if (quest != null && !quest.isEmpty()) {
            if (type_test.getValue().equals("Quiz")) {
                Question q = Orthophoniste.getQuestionById(quest);
                questions.add(q);
                System.out.println("added");
                questid.clear();
            } else if (type_test.getValue().equals("Exercices")) {
                Exercice exo = Orthophoniste.getExerciceById(quest);
                exercices.add(exo);
                System.out.println(exo.getConsigne());
                System.out.println("added");
                questid.clear();
            }
        }
    }

    @FXML
    void BuildTest(ActionEvent event) {
        String id = testid.getText();
        String capacite = testcapacity.getText();
        String selectedChoice = type_test.getValue();
        if (event.getSource() == savetest) {
            if (selectedChoice.equals("Quiz")) {
                TestQuestionnaire test = new TestQuestionnaire(id, capacite, questions);
                Orthophoniste.ajouterTest(test);
                Orthophoniste.ajouterTestQuestionnaire(test);
                TestQuestionnaire Q = Orthophoniste.getTestQuestionnaireByName(test.getNom());
                Q.afficher();
                System.out.println("Ok");
            } else if (selectedChoice.equals("Exercices")) {
                TestExercice test = new TestExercice(id, capacite, exercices);
                Orthophoniste.ajouterTest(test);
                Orthophoniste.ajouterTestExercice(test);
                System.out.println("Ok");
            }
            testid.clear();
            testcapacity.clear();
            questions.clear();
            exercices.clear();
        }
    }



    public static ObservableList<Test> convertSetToObservableList(Set<Test> testSet) {
        return FXCollections.observableArrayList(testSet);
    }

    @FXML
    void intializeTestslist() {
        title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        capacite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCapacite()));
        typetest.setCellValueFactory(cellData -> new SimpleStringProperty(getTestTypeName(cellData.getValue())));
        setButtonCellFactory(editTest, "Edit", this::editTest);
        setButtonCellFactory(deleteTest, "Delete", this::deleteTest);
        tests_tab.setItems(testObservableList);
    }



    private void setButtonCellFactory(TableColumn<Test, String> column, String buttonText, Callback<Test, Void> action) {
        column.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button(buttonText);


            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    button.setStyle("-fx-background-color: #D6D3FE; -fx-background-radius: 7;");
                    button.setOnAction(event -> action.call(getTableView().getItems().get(getIndex())));
                    setGraphic(button);
                }
                setText(null);
            }
        });
    }



    private Void deleteTest(Test test) {
        System.out.println("Deleting test: " + test.getNom());
        ObservableList<Test> testData = tests_tab.getItems();
        testData.remove(test);
        Orthophoniste.SupprimerTest(test);
        return null;
    }

    public static ObservableList<Question> convertSetQToObservableList(Set<Question> QuestionSet) {
        return FXCollections.observableArrayList(QuestionSet);
    }

    @FXML
    void intializeQuestionSet() {
        idquest.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        enoncequest.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getenonce()));
        type.setCellValueFactory(cellData -> new SimpleStringProperty(getQuestionTypeName(cellData.getValue())));

        questions_tab.setItems(QObservableList);
    }

    private String getQuestionTypeName(Question q) {
        if (q instanceof QuestionQCM) {
            return "QCM";
        } else if (q instanceof QuestionQCU) {
            return "QCU";
        } else{
            return "Free Answer"; // default case if other types of tests are added in the future
        }
    }
    public static ObservableList<Exercice> convertSetEToObservableList(Set<Exercice> ExoSet) {
        return FXCollections.observableArrayList(ExoSet);
    }

    @FXML
    void intializeExoSet() {
        idexo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        textexo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getConsigne()));

        exercices_tab.setItems(EObservableList);
    }


    // Initialize Edit test tab view
    private String getTestTypeName(Test t) {
        if (t instanceof TestQuestionnaire) {
            return "Quiz";
        } else if (t instanceof TestExercice) {
            return "Exercices";
        } else{
            return "Unknown"; // default case if other types of tests are added in the future
        }
    }

    private void showTestDetailsQuestionnaire(TestQuestionnaire test) {
            Quizview.setVisible(true);
            Exercicesview.setVisible(false);
            test.afficher();
            initializeTestElementSet(test.getQuestions());
    }
    private void showTestDetailsExercices(TestExercice test){
        Quizview.setVisible(false);
        Exercicesview.setVisible(true);
        initializeExerciceElementSet(test.getExercices());
    }

    private Void editTest(Test test) {
        if(getTestTypeName(test).equals("Quiz")){
            TestQuestionnaire testQ = Orthophoniste.getTestQuestionnaireByName(test.getNom());
            currentTest = testQ; // Store the current test
            buildtest.setVisible(false);
            TeditTest.setVisible(true);
            Ttitle.setText(testQ.getNom());
            Ttype.setText(getTestTypeName(testQ));
            showTestDetailsQuestionnaire(testQ);
        }else if(getTestTypeName(test).equals("Exercices")){
            TestExercice testQ = Orthophoniste.getTestExerciceByName(test.getNom());
            buildtest.setVisible(false);
            TeditTest.setVisible(true);
            Ttitle.setText(testQ.getNom());
            Ttype.setText(getTestTypeName(testQ));
            showTestDetailsExercices(testQ);

        }
        System.out.println("Editing test: " + test.getNom());
        return null;
    }




    @FXML
    void initializeTestElementSet(Set<Question> Tquestions) {
        ObservableList<Question> MQObservableList = convertSetQToObservableList(Tquestions);
        Quizview.setItems(MQObservableList);
        Eid.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        Etype.setCellValueFactory(cellData -> new SimpleStringProperty(getQuestionTypeName(cellData.getValue())));
        EE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getenonce()));
        setButtonCellFactoryQ(Eedit, "Edit", this::editQuestion);
        setButtonCellFactoryQ(Edelete, "Delete", this::deleteQuestion);
    }
    private Void editQuestion(Question Q){
        System.out.println("editing Question"+Q.getId());
        return null;
    }
    private Void deleteQuestion(Question Q){
        ObservableList<Question> testData =Quizview.getItems();
        testData.remove(Q);
        if (currentTest instanceof TestQuestionnaire) {
            ((TestQuestionnaire) currentTest).supprimerelement(Q);
        }
        return null;
    }

    private void setButtonCellFactoryQ(TableColumn<Question, String> column, String buttonText, Callback<Question, Void> action) {
        column.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button(buttonText);
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    button.setStyle("-fx-background-color: #D6D3FE; -fx-background-radius: 7;");
                    button.setOnAction(event -> action.call(getTableView().getItems().get(getIndex())));
                    setGraphic(button);
                }
                setText(null);
            }
        });
    }

    @FXML
    void initializeExerciceElementSet(Set<Exercice> Texercices) {
        ObservableList<Exercice> MEObservableList = convertSetEToObservableList(Texercices);
        Exercicesview.setItems(MEObservableList);
        Eid1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        Etype1.setCellValueFactory(cellData -> new SimpleStringProperty("Exercices"));
        EE1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getConsigne()));
        setButtonCellFactoryE(Eedit1, "Edit", this::editExercice);
        setButtonCellFactoryE(Edelete1, "Delete", this::deleteExercice);
    }
    private Void editExercice(Exercice E) {
        System.out.println("Editing Exercice: " + E.getId());
        return null;
    }

    private Void deleteExercice(Exercice E) {
        ObservableList<Exercice> exerciseData = Exercicesview.getItems();
        exerciseData.remove(E);

        if (currentTest instanceof TestExercice) {
            ((TestExercice) currentTest).supprimerExercice(E);
        }
        return null;
    }

    private void setButtonCellFactoryE(TableColumn<Exercice, String> column, String buttonText, Callback<Exercice, Void> action) {
        column.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button(buttonText);

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    button.setStyle("-fx-background-color: #D6D3FE; -fx-background-radius: 7;");
                    button.setOnAction(event -> action.call(getTableView().getItems().get(getIndex())));
                    setGraphic(button);
                }
                setText(null);
            }
        });
    }



}
