package Layouts;

import Classes.TestExercice;
import javafx.fxml.FXML;

public class GradeTestExerciceController {

        private TestExercice test;

        public void setTest(TestExercice test) {
            this.test = test;
            // Initialize the view with test data
        }

        @FXML
        private void initialize() {
            // Initialize UI components if needed
        }

}
