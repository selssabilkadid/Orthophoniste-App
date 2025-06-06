package Controllers;

import Classes.*;
import Layouts.GradeTestExerciceController;
import Layouts.GradeTestQuestionnaireController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static Layouts.MyTestsController.convertSetToObservableList;

public class CreateBoController {
    @FXML
    private Button confirmButton;
    @FXML
    private TextField projetThField;
    @FXML
    private ListView<Trouble> troublesListView;
    private final ObservableList<Trouble> troubles = FXCollections.observableArrayList();

    @FXML
    private ListView<Test> testsListView;
    @FXML
    private Button addBilanBtn;
    @FXML
    private ScrollPane mainLayout;

    private UserAccount orthophoniste = AccountManager.getCurrentuser();
    private Set<Test> mestests = orthophoniste.getMes_tests();
    private ObservableList<Test> testObservableList = convertSetToObservableList(mestests);
    private static final Map<Test, CompteRendu> testScores = new HashMap<>();

    private Set<Question> createDummyQuestions() {
        Set<Question> questions = new HashSet<>();
        questions.add(new QuestionQCM("Q1", "Question 1: What rhymes with cat?", new ArrayList<>(Arrays.asList("Bat", "Dog", "Hat"))));
        questions.add(new QuestionQCU("Q2", "Question 2: How many syllables are there in the word Butterfly?", new ArrayList<>(Arrays.asList("1", "2", "3", "4"))));
        return questions;
    }


    private Set<Exercice> createDummyExercices() {
        Set<Exercice> exercices = new HashSet<>();
        exercices.add(new Exercice("E1", "Exercice 1: Solve the following equation", new HashSet<>(Set.of("Equation 1", "Equation 2"))));
        exercices.add(new Exercice("E2", "Exercice 2: Write a short paragraph about your favorite hobby", new HashSet<>(Set.of("Paragraph task"))));
        return exercices;
    }

    private final Set<Test> selectedTests = new HashSet<>();
    private final ObservableList<Test> selectedTestsList = convertSetToObservableList(selectedTests);
    private Dossier dossier;
    private Patient patient;
    private Integer testScore;

    public void setTestScore(String testName, Integer testScore) {
        this.testScore = testScore;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    private final SharedScoreDataModel sharedDataModel = SharedScoreDataModel.getInstance();

    @FXML
    private void initialize() {
        if (dossier != null) {
            initializeDossierData();
        }

        Map<Test, Integer> rawTestScores = sharedDataModel.getTestScores();
        rawTestScores.forEach((test, score) -> testScores.put(test, new CompteRendu(score, "", test)));

        troublesListView.setCellFactory(troubleListView -> new TroubleListCell());
        troublesListView.setItems(troubles);
        troublesListView.setPlaceholder(new Label("No current troubles, please create a new one"));

        testsListView.setCellFactory(testListView -> new TestListCell());
        testsListView.setItems(testObservableList);
        testsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        initializeConfirmButton();

        TestQuestionnaire test1 = new TestQuestionnaire("Test 1", "Test capacity 1", createDummyQuestions());
        TestExercice test2 = new TestExercice("Test 2", "Test capacity 2", createDummyExercices());
        testObservableList.addAll(test1, test2);

        testsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadTestDetails(newValue);
            }
        });

        System.out.println("CreateBoController initialized with dossier: " + dossier);
    }

    private void loadTestDetails(Test test) {
        String fxmlFile = (test instanceof TestQuestionnaire) ? "../Layouts/GradeTestQuestionnaire.fxml" : "../Layouts/GradeTestExercice.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            AnchorPane testDetails = loader.load();
            mainLayout.setContent(testDetails);

            if (test instanceof TestQuestionnaire) {
                GradeTestQuestionnaireController controller = loader.getController();
                controller.setSelectedTests(selectedTests);
                controller.setTroubles(troubles);
                controller.setProjetThField(projetThField.getText());
                controller.setDossierAndPatient(dossier, patient);
                controller.setTest((TestQuestionnaire) test);
            } else if (test instanceof TestExercice) {
                GradeTestExerciceController controller = loader.getController();
                controller.setSelectedTests(selectedTests);
                controller.setTroubles(troubles);
                controller.setProjetThField(projetThField.getText());
                controller.setDossierAndPatient(dossier, patient);
                controller.setTest((TestExercice) test);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeDossierData() {
        if (dossier != null) {
            System.out.println("Dossier data initialized: " + dossier);
        }
    }

    private void initializeConfirmButton() {
        confirmButton.setOnAction(event -> {
            selectedTests.clear();
            for (Test test : testObservableList) {
                if (test.isSelected()) {
                    selectedTests.add(test);
                }
            }
            selectedTestsList.setAll(selectedTests);
            testsListView.setItems(selectedTestsList);
            System.out.println("Selected tests: " + selectedTests.stream().map(Test::getNom).collect(Collectors.joining(", ")));
        });
    }

    public void addTestScore(String testName, int totalScore) {
        Test testToUpdate = mestests.stream()
                .filter(test -> test.getNom().equals(testName))
                .findFirst()
                .orElseGet(() -> mestests.stream()
                        .filter(test -> test.getNom().contains(testName))
                        .findFirst()
                        .orElse(null));

        if (testToUpdate != null) {
            CompteRendu compteRendu = new CompteRendu(totalScore, "", testToUpdate);
            testScores.put(testToUpdate, compteRendu);
        } else {
            System.out.println("Test with name '" + testName + "' not found.");
        }
    }

    public void setTestsAndTroubles(Set<Test> tests, List<Trouble> troubles) {
        selectedTests.addAll(tests);
        selectedTestsList.setAll(selectedTests);
        testsListView.setItems(selectedTestsList);

        this.troubles.setAll(troubles);
        troublesListView.setItems(this.troubles);
    }

    public void setProjetTh(String projetTh) {
        projetThField.setText(projetTh);
    }

    @FXML
    private void addTrouble() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layouts/AddTrouble.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        AddTroubleController controller = loader.getController();
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String troubleName = controller.getTroubleName();
                CategorieTrouble selectedType = controller.getSelectedTroubleType();
                if (troubleName != null && !troubleName.isEmpty() && selectedType != null) {
                    troubles.add(new Trouble(troubleName, selectedType));
                }
            }
            return null;
        });
        dialog.showAndWait();
    }

    static class TestListCell extends ListCell<Test> {
        private final HBox hBox = new HBox();
        private final CheckBox checkBox = new CheckBox();
        private final Label testNameLabel = new Label();
        private final Label testScoreLabel = new Label();

        public TestListCell() {
            super();
            hBox.setSpacing(10);
            testNameLabel.setPrefWidth(190);
            testScoreLabel.setPrefWidth(50);
            Font customFont = Font.font("Arial", FontWeight.BOLD, 14);
            testNameLabel.setFont(customFont);
            testScoreLabel.setFont(customFont);
            HBox.setHgrow(testNameLabel, Priority.ALWAYS);
            hBox.getChildren().addAll(checkBox, testNameLabel, testScoreLabel);
            hBox.setPadding(new Insets(10, 10, 15, 7));
            checkBox.setOnAction(event -> {
                if (getItem() != null) {
                    getItem().setSelected(checkBox.isSelected());
                }
            });
        }

        @Override
        protected void updateItem(Test test, boolean empty) {
            super.updateItem(test, empty);
            if (empty || test == null) {
                setText(null);
                setGraphic(null);
            } else {
                testNameLabel.setText(test.getNom());
                CompteRendu compteRendu = testScores.get(test);
                testScoreLabel.setText(compteRendu != null ? String.valueOf(compteRendu.getScoretotal()) : "");
                checkBox.setSelected(test.isSelected());
                setGraphic(hBox);
            }
        }
    }

    static class TroubleListCell extends ListCell<Trouble> {
        private final HBox hBox = new HBox();
        private final Label troubleNameLabel = new Label();
        private final Label troubleTypeLabel = new Label();

        public TroubleListCell() {
            super();
            hBox.setSpacing(10);
            troubleNameLabel.setPrefWidth(190);
            troubleTypeLabel.setPrefWidth(190);
            Font customFont = Font.font("Arial", FontWeight.BOLD, 14);
            troubleNameLabel.setFont(customFont);
            troubleTypeLabel.setFont(customFont);
            HBox.setHgrow(troubleNameLabel, Priority.ALWAYS);
            HBox.setHgrow(troubleTypeLabel, Priority.ALWAYS);
            hBox.getChildren().addAll(troubleNameLabel, troubleTypeLabel);
            hBox.setPadding(new Insets(10, 10, 15, 7));
        }

        @Override
        protected void updateItem(Trouble trouble, boolean empty) {
            super.updateItem(trouble, empty);
            if (empty || trouble == null) {
                setText(null);
                setGraphic(null);
            } else {
                troubleNameLabel.setText(trouble.getNom());
                troubleTypeLabel.setText(trouble.getCategorie().toString());
                setGraphic(hBox);
            }
        }
    }

    @FXML
    private void addBilan() {
        BilanO bilanO = new BilanO();
        String projetThName = projetThField.getText();
        ProjetTh projetTh = new ProjetTh(projetThName);
        bilanO.setProjet(projetTh);

        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setTroubles(new ArrayList<>(troubles)); // Convert ObservableList to ArrayList
        bilanO.setDiagnostique(diagnostic);

        bilanO.setTests(selectedTests);

        dossier.ajouterBO(bilanO);
        orthophoniste.ajouterBilan(dossier, bilanO);
    }
}
