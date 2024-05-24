package controller;

import App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import App.SessionManager;

public class HomeController {

    @FXML
    private void handleAdresaDashboard(ActionEvent ae){
        if (!SessionManager.isLoggedIn()) {
            SessionManager.setLastAttemptedPage(Navigator.ADRESA_DASHBOARD); // Store last attempted page
            System.out.println("Please log in first.");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
            return;
        }

        Navigator.navigate(ae, Navigator.ADRESA_DASHBOARD);
    }

    @FXML
    private void handleQytetariDashboard(ActionEvent ae){
        if (!SessionManager.isLoggedIn()) {
            System.out.println("Please log in first.");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
            return;
        }
        Navigator.navigate(ae, Navigator.QYTETARI_DASHBOARD);
    }
    @FXML
    private void handleStatistikat(ActionEvent ae){
        Navigator.navigate(ae,Navigator.STATISTICS);
    }
    @FXML
    private void handleHelp(ActionEvent ae){
        // Help mund te kete qasje edhe pa LogIn
        Navigator.navigate(ae,Navigator.HELP);
    }
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        Navigator.changeLanguage();
        Navigator.navigate(ae,Navigator.HOME_PAGE);
    }
}