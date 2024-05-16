package controller;


        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.scene.input.MouseEvent;
        import App.Navigator;
        import model.dto.LoginUserDto;
        import service.UserService;

public class LogInController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private void handleLogIn(ActionEvent ae) {
        LoginUserDto loginUserData = new LoginUserDto(
                this.txtEmail.getText(),
                this.pwdPassword.getText()
        );
        boolean isLogin = UserService.login(loginUserData);

        if (isLogin) {
            Navigator.navigate(ae, Navigator.ADRESA);
        } else {
            System.out.println("Log In Failed");
        }
    }


    @FXML
    private void notHaveAccount(MouseEvent me){
        Navigator.navigate(me, Navigator.SIGNUP_PAGE);
    }
}


