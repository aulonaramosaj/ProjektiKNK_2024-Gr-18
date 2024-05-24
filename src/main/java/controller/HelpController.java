package controller;

import App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelpController {
    @FXML
    private Label ckaeshteSistemi;
    @FXML
    private Label ckaeshtesistemi1;
    @FXML
    private Label funksionimiSistemit;
    @FXML
    private Label funksionimiSistemit1;
    @FXML
    private void handleFaqjaKryesore (ActionEvent ae){
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        Navigator.changeLanguage();
        Navigator.navigate(ae,Navigator.HELP);
    }
}