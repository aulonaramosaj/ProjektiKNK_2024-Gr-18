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
    private void handleSignUp(ActionEvent ae){
            UserDto userSignUpData = new UserDto(
                    this.txtEmri.getText(),
                    this.txtMbiemri.getText(),
                    this.txtNumriPersonal.getText(),
                    this.txtUsername.getText(),
                    this.txtEmail.getText(),
                    this.pwdFjalekalimi.getText(),
                    this.pwdKonfirmoFjalekalimin.getText()
            );
            boolean response = UserService.signUp(userSignUpData);
            Navigator.navigate(ae,Navigator.SIGNUP_PAGE);
    }
    @FXML
    private void handleAnulo(ActionEvent ae){

    }

}
