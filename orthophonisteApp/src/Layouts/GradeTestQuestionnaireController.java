package Layouts;

import Classes.*;
import Controllers.CreateBoController;
import Controllers.SharedScoreDataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class GradeTestQuestionnaireController {

    @FXML
    private Label testNameLabel;

    @FXML
    private VBox questionsContainer;

    @FXML
    private Button savescorebtn;

    @FXML
    private AnchorPane rootLayout;

    private TestQuestionnaire test;
    private int totalScore = 0;

    private SharedScoreDataModel sharedDataModel = SharedScoreDataModel.getInstance();

    public void setTest(TestQuestionnaire test) {
        this.test = test;
        initializeView();
    }

    @FXML
    private void initialize() {
        if (test != null) {
            initializeView();
        }
    }

    private void initializeView() {
        testNameLabel.setText(test.getNom());
        for (Question question : test.getQuestions()) {
            VBox questionBox = new VBox(5);
            Label questionLabel = new Label(question.getenonce());
            questionLabel.setStyle("-fx-font-weight: bold;");

            TextField scoreField = new TextField();
            scoreField.setPromptText("Enter score");

            questionBox.getChildren().addAll(questionLabel, scoreField);
            questionsContainer.getChildren().add(questionBox);
        }
    }

    @FXML
    private void saveScore() {
        totalScore = 0;
        boolean validScores = true;

        for (int i = 0; i < questionsContainer.getChildren().size(); i++) {
            VBox questionBox = (VBox) questionsContainer.getChildren().get(i);
            TextField scoreField = (TextField) questionBox.getChildren().get(1);

            try {
                int score = Integer.parseInt(scoreField.getText());
                if (score >= 1 && score <= 10) {
                    totalScore += score;
                    scoreField.setStyle(null);  // Clear any previous error style
                } else {
                    validScores = false;
                    scoreField.setStyle("-fx-border-color: red;");  // Highlight invalid score
                }
            } catch (NumberFormatException e) {
                validScores = false;
                scoreField.setStyle("-fx-border-color: red;");  // Highlight invalid score
            }
        }

        if (validScores) {
           // sharedDataModel.setTestScore(test, totalScore);  // Update the shared data model
            navigateToCreateBO(totalScore);
        } else {
            System.out.println("Please enter valid scores between 1 and 10 for all questions.");
        }
    }

    private Set<Test> selectedTests;
    private List<Trouble> troubles;
    private String projetThField;
    private Dossier dossier;
    private Patient patient;

    public void setDossierAndPatient(Dossier dossier, Patient patient) {
        this.dossier = dossier;
        this.patient = patient;
    }

    public void setSelectedTests(Set<Test> selectedTests) {
        this.selectedTests = selectedTests;
    }

    public void setTroubles(List<Trouble> troubles) {
        this.troubles = troubles;
    }

    public void setProjetThField(String projetThField) {
        this.projetThField = projetThField;
    }

    private void navigateToCreateBO(Integer totalScore) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layouts/CreateBO.fxml"));
            ScrollPane newContent = loader.load();
            CreateBoController createBoController = loader.getController();

            createBoController.setTestsAndTroubles(selectedTests, troubles);
            createBoController.setProjetTh(projetThField);
            createBoController.setDossier(dossier);
            createBoController.setPatient(patient);

            //createBoController.addTestScore(test.getNom(), totalScore);
            createBoController.setTestScore(test.getNom(),totalScore);

            rootLayout.getChildren().setAll(newContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
