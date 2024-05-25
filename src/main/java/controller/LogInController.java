package controller;

import App.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import App.Navigator;
import model.User;
import model.dto.LoginUserDto;
import repository.UserRepository;
import service.UserService;

public class LogInController {

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;

    @FXML
    public void initialize() {
        txtEmail.setOnKeyPressed(this::handleKeyPressed);
        pwdPassword.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent ke) {
        if (ke.getCode() == KeyCode.ENTER) {
            ActionEvent actionEvent = new ActionEvent(ke.getSource(), null);
            handleLogIn(actionEvent);
        }
    }

    @FXML
    private void handleLogIn(ActionEvent ae) {
        LoginUserDto loginUserData = new LoginUserDto(txtEmail.getText(), pwdPassword.getText());
        boolean isLogin = UserService.login(loginUserData);

        System.out.println("Login attempt: " + isLogin);

        if (isLogin) {
            User loggedInUser = UserRepository.getByEmail(txtEmail.getText());
            SessionManager.setUser(loggedInUser);

            String lastPage = SessionManager.getLastAttemptedPage();
            if (lastPage != null) {
                Navigator.navigate(ae, lastPage);
                SessionManager.setLastAttemptedPage(null);
            } else {
                Navigator.navigate(ae, Navigator.HOME_PAGE);
            }
            System.out.println("Log In Successful.");
        } else {
            System.out.println("Log In Failed");
        }
    }

    @FXML
    private void notHaveAccount(MouseEvent me) {
        Navigator.navigate(me, Navigator.SIGNUP_PAGE);
    }

    @FXML
    private void handleChangeLanguage(ActionEvent ae) {
        Navigator.changeLanguage();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }
}