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

public class SignInController {
    public SignInController() {

    }

    @FXML
    private Button signInBtn;

    @FXML
    private TextField e_mail;

    @FXML
    private PasswordField userPassword;
    @FXML
    private Label wrongInfo;

    public void signInClicked(ActionEvent event) throws IOException {
        checkSignIn();
    }

    private void checkSignIn() throws IOException {
        Main m = new Main();
        String email = e_mail.getText();
        String password = userPassword.getText();

        // Check if the email and password fields are not empty
        if(email.isEmpty() || password.isEmpty()) {
            wrongInfo.setText("Please enter your data.");
            // Check if the email is a valid Gmail address
        } else if (!email.endsWith("@gmail.com")) {
            wrongInfo.setText("Please enter a valid Gmail address.");
            // Proceed with sign in if validations pass
        } else {
            UserAccount account = AccountManager.getAccount(email);
            if(account != null && account.getPassword().equals(password)) {

                AccountManager.setcurrentuser(account);
                m.changeScene("/Layouts/HomePage.fxml");
            } else {
                wrongInfo.setText("Wrong username or password!");
            }
        }
    }

}