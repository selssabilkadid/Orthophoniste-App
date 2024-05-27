package Layouts;

import Classes.*;
import Controllers.CreateBoController;
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

public class GradeTestExerciceController {
        @FXML
        private Label testNameLabel;

        @FXML
        private VBox exerciceContainer;
        @FXML
        private Button savescorebtn;

        @FXML
        private AnchorPane rootLayout;
        private TestExercice test;
        private int totalScore = 0;

        public void setTest(TestExercice test) {
            this.test = test;
                initializeView();        }

        @FXML
        private void initialize() {
                if (test != null) {
                        initializeView();
                }
        }
        private void initializeView() {
                testNameLabel.setText(test.getNom());
                for (Exercice exercice : test.getExercices()) {
                        VBox questionBox = new VBox(5);
                        Label exerciceLabel = new Label(exercice.getConsigne());
                        exerciceLabel.setStyle("-fx-font-weight: bold;");

                        TextField scoreField = new TextField();
                        scoreField.setPromptText("Enter score");

                        questionBox.getChildren().addAll(exerciceLabel, scoreField);
                        exerciceContainer.getChildren().add(questionBox);
                }
        }
        @FXML
        private void saveScore() {
                totalScore = 0;
                boolean validScores = true;

                for (int i = 0; i < exerciceContainer.getChildren().size(); i++) {
                        VBox questionBox = (VBox) exerciceContainer.getChildren().get(i);
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
                         // Update the shared data model
                        navigateToCreateBO();
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

        private void navigateToCreateBO() {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layouts/CreateBO.fxml"));
                        ScrollPane newContent = loader.load();
                        CreateBoController createBoController = loader.getController();

                        createBoController.setTestsAndTroubles(selectedTests, troubles);
                        createBoController.setProjetTh(projetThField);
                        createBoController.setPatient(patient);
                        createBoController.setDossier(dossier);

                        createBoController.addTestScore(test.getNom(), totalScore);

                        rootLayout.getChildren().setAll(newContent);

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
