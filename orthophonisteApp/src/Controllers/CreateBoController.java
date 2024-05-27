package Controllers;

import Classes.*;
import Controllers.AddTroubleController;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private final ObservableList<Test> testObservableList = convertSetToObservableList(mestests);

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

        Exercice exercice1 = new Exercice("E1", "Exercice 1: Solve the following equation", new HashSet<>(Set.of("Equation 1", "Equation 2")));
        Exercice exercice2 = new Exercice("E2", "Exercice 2: Write a short paragraph about your favorite hobby", new HashSet<>(Set.of("Paragraph task")));

        exercices.add(exercice1);
        exercices.add(exercice2);

        return exercices;
    }

    private final Set<Test> selectedTests = new HashSet<>();
    private final ObservableList<Test> selectedTestsList = convertSetToObservableList(selectedTests);
    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    @FXML
    private void initialize() {
        troublesListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Trouble> call(ListView<Trouble> troubleListView) {
                return new TroubleListCell();
            }
        });
        troublesListView.setItems(troubles);
        troublesListView.setPlaceholder(new Label("No current troubles, please create a new one"));

        testsListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Test> call(ListView<Test> testListView) {
                return new TestListCell();
            }
        });

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
    }

    private void initializeConfirmButton() {
        confirmButton.setOnAction(event -> {
            selectedTests.clear();
            for (Test test : testObservableList) {
                if (test.isSelected()) {
                    selectedTests.add(test);
                }
            }
            selectedTestsList.clear();
            selectedTestsList.addAll(selectedTests);

            testsListView.setItems(selectedTestsList);
            System.out.println("Selected tests: " + selectedTests.stream().map(Test::getNom).collect(Collectors.joining(", ")));
        });
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

        public TestListCell() {
            super();
            hBox.setSpacing(10);
            testNameLabel.setPrefWidth(190);
            Font customFont = Font.font("Arial", FontWeight.BOLD, 14);
            testNameLabel.setFont(customFont);
            HBox.setHgrow(testNameLabel, Priority.ALWAYS);
            hBox.getChildren().addAll(checkBox, testNameLabel);
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
        diagnostic.setTroubles(getTroubles().toArray(new Trouble[0])); // Convert list to array
        bilanO.setDiagnostique(diagnostic);

        bilanO.setTests(selectedTests);

        dossier.ajouterBO(bilanO);
    }

    private void loadTestDetails(Test test) {
        String fxmlFile = "";
        if (test instanceof TestQuestionnaire) {
            fxmlFile = "../Layouts/GradeTestQuestionnaire.fxml";
        } else if (test instanceof TestExercice) {
            fxmlFile = "../Layouts/GradeTestExercice.fxml";
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            AnchorPane testDetails = loader.load();
            mainLayout.setContent(testDetails);

            if (test instanceof TestQuestionnaire) {
                GradeTestQuestionnaireController controller = loader.getController();
                controller.setTest((TestQuestionnaire) test);
            } else if (test instanceof TestExercice) {
                GradeTestExerciceController controller = loader.getController();
                controller.setTest((TestExercice) test);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BilanO_Anamnese createBOAnamnese() {
        BilanO_Anamnese bilanO = new BilanO_Anamnese();
        String projetThName = projetThField.getText();
        ProjetTh projetTh = new ProjetTh(projetThName);
        bilanO.setProjet(projetTh);
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setTroubles(getTroubles().toArray(new Trouble[0])); // Convert list to array
        bilanO.setDiagnostique(diagnostic);

        return bilanO;
    }

    public List<Trouble> getTroubles() {
        return new ArrayList<>(troubles);
    }
}
