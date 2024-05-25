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

import java.util.HashSet;
import java.util.Set;

public class AnamneseController {

    @FXML
    private TableView<QuestionAnamneseEnfant> AQuestions;
    @FXML
    private TableView<QuestionAnamneseAdult> AQuestions1;

    @FXML
    private Label ERROR;

    @FXML
    private Label ERROR1;

    @FXML
    private TableColumn<QuestionAnamneseEnfant, String> QCat;

    @FXML
    private TableColumn<QuestionAnamneseEnfant, String> QEnonce;

    @FXML
    private TableColumn<QuestionAnamneseEnfant, String> Qdelete;

    @FXML
    private TableColumn<QuestionAnamneseAdult, String> QCat1;

    @FXML
    private TableColumn<QuestionAnamneseAdult, String> QEnonce1;

    @FXML
    private TableColumn<QuestionAnamneseAdult, String> Qdelete1;

    @FXML
    private Button addquest;

    @FXML
    private AnchorPane makenewtest;

    @FXML
    private AnchorPane makenewtest1;

    @FXML
    private Button savetest;

    @FXML
    private TextArea testcapacity;

    @FXML
    private TextField testid;

    @FXML
    private ComboBox<String> type_test;

    @FXML
    private ComboBox<String> type_test1;

    private Set<QuestionAnamneseEnfant> Questions = new HashSet<>();
    private UserAccount Orthophoniste = AccountManager.getCurrentuser();
    private Set<QuestionAnamneseAdult> QuestionsA = new HashSet<>();
    private Anamnese currentA;

    private ObservableList<QuestionAnamneseEnfant> questions = FXCollections.observableArrayList();
    private ObservableList<QuestionAnamneseAdult> questionsA = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        Set<String> items = Set.of("Child", "Adult");
        type_test.setItems(FXCollections.observableArrayList(items));
        type_test.setValue("Child");

        type_test.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateTypeTest1Items(newValue);
            updateTableVisibility(newValue);
        });

        updateTypeTest1Items(type_test.getValue());
        updateTableVisibility(type_test.getValue());

        initializeAnamneseEnfantlist();
        initializeAnamneseAdultlist();
    }

    private void updateTypeTest1Items(String selectedType) {
        type_test1.getItems().clear();

        if ("Child".equals(selectedType)) {
            for (CategorieQuestionEnfant category : CategorieQuestionEnfant.values()) {
                type_test1.getItems().add(category.name());
            }
        } else if ("Adult".equals(selectedType)) {
            for (CategorieQuestionAdult category : CategorieQuestionAdult.values()) {
                type_test1.getItems().add(category.name());
            }
        }
    }

    private void updateTableVisibility(String selectedType) {
        if ("Child".equals(selectedType)) {
            AQuestions.setVisible(true);
            AQuestions1.setVisible(false);
        } else if ("Adult".equals(selectedType)) {
            AQuestions.setVisible(false);
            AQuestions1.setVisible(true);
        }
    }

    @FXML
    private void handleAddQuestion(ActionEvent event) {
        String q = testcapacity.getText();
        String t = type_test1.getValue();
        if ("Child".equals(type_test.getValue())) {
            if (event.getSource() == addquest) {
                QuestionAnamneseEnfant Q = new QuestionAnamneseEnfant(String.valueOf(Questions.size() + 1), q, CategorieQuestionEnfant.valueOf(t));
                Questions.add(Q);
                questions.add(Q);
                testcapacity.clear();
                System.out.println("Question added for Child");
            }
        } else if ("Adult".equals(type_test.getValue())) {
            if (event.getSource() == addquest) {
                QuestionAnamneseAdult Q = new QuestionAnamneseAdult(String.valueOf(QuestionsA.size() + 1), q, CategorieQuestionAdult.valueOf(t));
                QuestionsA.add(Q);
                questionsA.add(Q);
                testcapacity.clear();
                System.out.println("Question added for Adult");
            }
        }
    }

    @FXML
    private void CreateAnamnese(ActionEvent event) {
        String id = testid.getText();
        if (event.getSource() == savetest) {
            Anamnese A = new Anamnese(id);
            if ("Child".equals(type_test.getValue())) {
                A.setQuestionsEnfant(Questions);
            } else if ("Adult".equals(type_test.getValue())) {
                A.setQuestionsAdult(QuestionsA);
            }
            Orthophoniste.ajouterAnamnese(A);
            testid.clear();
            System.out.println("Anamnese saved");
        }
    }

    @FXML
    void initializeAnamneseEnfantlist() {
        QEnonce.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getenonce()));
        QCat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie().toString()));
        setButtonCellFactory(Qdelete, "Delete", this::deleteQE);
        questions.setAll(Questions);  // Initialize the table with the current set of questions
        AQuestions.setItems(questions);
    }

    @FXML
    void initializeAnamneseAdultlist() {
        QEnonce1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getenonce()));
        QCat1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie().toString()));
        setButtonCellFactoryAdult(Qdelete1, "Delete", this::deleteQA);
        questionsA.setAll(QuestionsA);  // Initialize the table with the current set of questions
        AQuestions1.setItems(questionsA);
    }

    private Void deleteQE(QuestionAnamneseEnfant questionAnamneseEnfant) {
        Questions.remove(questionAnamneseEnfant);
        questions.remove(questionAnamneseEnfant);
        return null;
    }

    private Void deleteQA(QuestionAnamneseAdult questionAnamneseAdult) {
        QuestionsA.remove(questionAnamneseAdult);
        questionsA.remove(questionAnamneseAdult);
        return null;
    }

    private void setButtonCellFactory(TableColumn<QuestionAnamneseEnfant, String> column, String buttonText, Callback<QuestionAnamneseEnfant, Void> action) {
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

    private void setButtonCellFactoryAdult(TableColumn<QuestionAnamneseAdult, String> column, String buttonText, Callback<QuestionAnamneseAdult, Void> action) {
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
