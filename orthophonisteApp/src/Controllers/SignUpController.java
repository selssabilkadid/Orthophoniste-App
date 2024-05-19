package Controllers;

import java.io.IOException;

import Classes.AccountManager;
import Classes.UserAccount;
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
    @FXML
    private Button iHaveAccbtn;

    public void signUpClicked(ActionEvent event) throws IOException {
        checkSignUp();
    }
    private void checkSignUp() throws IOException {
        Main m = new Main();
        String fName = firstName.getText();
        String lName = lastName.getText();
        String email = e_mail.getText();
        String phone = phoneNumber.getText();
        String address = userAddress.getText();
        String password = userPassword.getText();

        if(fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
            wrongInfo.setText("Please enter your data.");
        } else {
            UserAccount account = new UserAccount(fName, lName, email, phone, address, password);
            AccountManager.addAccount(account);
            wrongInfo.setText("Success!");
            m.changeScene("/Layouts/HomePage.fxml");
        }
    }
    @FXML
    private void goToSignIn(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/Layouts/SignIn.fxml");
    }

}