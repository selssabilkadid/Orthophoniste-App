package Layouts;

import Classes.Question;
import Classes.QuestionQCM;
import Classes.QuestionQCU;
import Classes.TestQuestionnaire;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GradeTestQuestionnaireController {

    @FXML
    private Label testNameLabel;

    @FXML
    private VBox questionsContainer;

    private TestQuestionnaire test;

    public void setTest(TestQuestionnaire test) {
        this.test = test;
        initializeView();
    }

    @FXML
    private void initialize() {
        initializeView();
    }

    private void initializeView() {
        if (test != null) {
            testNameLabel.setText(test.getNom());
            int totalScore = 0;
            for (Question question : test.getQuestions()) {
                VBox questionBox = new VBox(5);
                Label questionLabel = new Label(question.getenonce());
                questionLabel.setStyle("-fx-font-weight: bold;");



                TextField scoreField = new TextField();
                scoreField.setPromptText("Enter score");

                questionBox.getChildren().addAll(questionLabel, scoreField);
                questionsContainer.getChildren().add(questionBox);
                try {
                    int score = Integer.parseInt(scoreField.getText());
                    if (score >= 1 && score <= 10) {
                        totalScore += score;
                    } else {
                        // Handle invalid score input
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid score input
                }
                System.out.println(totalScore);
            }
        }
    }


}
