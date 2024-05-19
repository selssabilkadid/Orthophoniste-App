package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {
    public SignUpController() {

    }

    @FXML
    private Button signUpBtn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField e_mail;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField userAddress;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Label wrongInfo;

    public void signUpClicked(ActionEvent event) throws IOException {
        checkSignUp();
    }
    private void checkSignUp() throws IOException {
        Main m = new Main();
        if(firstName.getText().toString().equals("Assia") && lastName.getText().toString().equals("Kheddia") && phoneNumber.getText().toString().equals("123456789") && e_mail.getText().toString().equals("assia@gmail.com") && userAddress.getText().toString().equals("alger") && userPassword.getText().toString().equals("hello")) {
            wrongInfo.setText("Success!");
            m.changeScene("/Layouts/HomePage.fxml");
        }

        else if(firstName.getText().isEmpty() && lastName.getText().isEmpty() && e_mail.getText().isEmpty() && userAddress.getText().isEmpty() & phoneNumber.getText().isEmpty() && userPassword.getText().isEmpty()) {
            wrongInfo.setText("Please enter your data.");
        }


        else {
            wrongInfo.setText("Wrong username or password!");
        }
    }

}