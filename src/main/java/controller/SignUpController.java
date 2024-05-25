package controller;

import App.Navigator;
import App.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.dto.UserDto;
import service.UserService;

public class SignUpController {
    @FXML
    private TextField txtEmri;
    @FXML
    private TextField txtMbiemri;
    @FXML
    private TextField txtNumriPersonal;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdFjalekalimi;
    @FXML
    private PasswordField pwdKonfirmoFjalekalimin;

    @FXML
    public void initialize() {
        txtEmri.setOnKeyPressed(this::handleKeyPressed);
        txtMbiemri.setOnKeyPressed(this::handleKeyPressed);
        txtNumriPersonal.setOnKeyPressed(this::handleKeyPressed);
        txtUsername.setOnKeyPressed(this::handleKeyPressed);
        txtEmail.setOnKeyPressed(this::handleKeyPressed);
        pwdFjalekalimi.setOnKeyPressed(this::handleKeyPressed);
        pwdKonfirmoFjalekalimin.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent ke) {
        if (ke.getCode() == KeyCode.ENTER) {
            ActionEvent actionEvent = new ActionEvent(ke.getSource(), null);
            handleSignUp(actionEvent);
        }
    }

    @FXML
    private void handleSignUp(ActionEvent ae) {
        if (SessionManager.isLoggedIn()) {
            System.out.println("Already logged in. No need to sign up.");
            Navigator.navigate(ae, Navigator.HOME_PAGE);  // Redirect to home
            return;
        }

        String emri = this.txtEmri.getText().trim();
        String mbiemri = this.txtMbiemri.getText().trim();
        String numriPersonal = this.txtNumriPersonal.getText().trim();
        String username = this.txtUsername.getText().trim();
        String email = this.txtEmail.getText().trim();
        String fjalekalimi = this.pwdFjalekalimi.getText().trim();
        String konfirmoFjalekalimin = this.pwdKonfirmoFjalekalimin.getText().trim();


        if (emri.isEmpty() || mbiemri.isEmpty() || numriPersonal.isEmpty() ||
                username.isEmpty() || email.isEmpty() || fjalekalimi.isEmpty() ||
                konfirmoFjalekalimin.isEmpty()) {

            System.out.println("Te gjitha pjeset duhet te jene te plotesuara!");
            return;
        }


        if (!fjalekalimi.equals(konfirmoFjalekalimin)) {
            System.out.println("Fjalekalimet nuk perputhen");
            return;
        }


        UserDto userSignUpData = new UserDto(
                emri, mbiemri, numriPersonal, username, email, fjalekalimi, konfirmoFjalekalimin
        );


        boolean response = UserService.signUp(userSignUpData);
        if (response) {
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
            System.out.println("Sign up successful. Navigate to login page.");
        } else {
            System.out.println("Sign up failed.");
        }
    }

    @FXML
    private void handleCancel(ActionEvent ae) {

        this.txtEmri.setText("");
        this.txtMbiemri.setText("");
        this.txtNumriPersonal.setText("");
        this.txtUsername.setText("");
        this.txtEmail.setText("");
        this.pwdFjalekalimi.setText("");
        this.pwdKonfirmoFjalekalimin.setText("");
    }

    @FXML
    private void handleChangeLanguage(ActionEvent ae) {
        Navigator.changeLanguage();
        Navigator.navigate(ae, Navigator.SIGNUP_PAGE);
    }
}
