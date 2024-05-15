package controller;

import App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private void handleSignUp(ActionEvent ae) {
        // Merr tekstin nga secila pjese
        String emri = this.txtEmri.getText().trim();
        String mbiemri = this.txtMbiemri.getText().trim();
        String numriPersonal = this.txtNumriPersonal.getText().trim();
        String username = this.txtUsername.getText().trim();
        String email = this.txtEmail.getText().trim();
        String fjalekalimi = this.pwdFjalekalimi.getText().trim();
        String konfirmoFjalekalimin = this.pwdKonfirmoFjalekalimin.getText().trim();

        // Shikon nese ndonje pjese nuk eshte plotesuar
        if (emri.isEmpty() || mbiemri.isEmpty() || numriPersonal.isEmpty() ||
                username.isEmpty() || email.isEmpty() || fjalekalimi.isEmpty() ||
                konfirmoFjalekalimin.isEmpty()) {

            System.out.println("Te gjitha pjeset duhet te jene te plotesuara!");
            return;
        }

        // Kontrollo nese fjalekalimet perputhen
        if (!fjalekalimi.equals(konfirmoFjalekalimin)) {
            System.out.println("Fjalekalimet nuk perputhen");
            return;
        }

        // Nese te gjitha pjeset jane te plotesuara dhe fjalekalimet perputhen, krijo UserDto
        UserDto userSignUpData = new UserDto(
                emri, mbiemri, numriPersonal, username, email, fjalekalimi, konfirmoFjalekalimin
        );

        // Provo te regjistrosh perdoruesin
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
        // Kthe tekstin e seciles pjese ne nje empty string
        this.txtEmri.setText("");
        this.txtMbiemri.setText("");
        this.txtNumriPersonal.setText("");
        this.txtUsername.setText("");
        this.txtEmail.setText("");
        this.pwdFjalekalimi.setText("");
        this.pwdKonfirmoFjalekalimin.setText("");

    }
}
