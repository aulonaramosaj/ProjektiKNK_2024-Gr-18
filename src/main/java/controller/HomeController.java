package controller;

import App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void handleAdresaDashboard (ActionEvent ae){
        //Navigator.navigate(ae, Navigator.ADRESA_DASHBOARD);
    }
    @FXML
    private void handleQytetariDashboard (ActionEvent ae){
        //Navigator.navigate(ae, Navigator.QYTETARI_DASHBOARD);
    }
    @FXML
    private void handleAdresa(ActionEvent ae){
        Navigator.navigate(ae, Navigator.ADRESA);
    }
    @FXML
    private void handleQytetari(ActionEvent ae){
        Navigator.navigate(ae, Navigator.QYTETARI);
    }
    @FXML
    private void handleModifikoAdresen(ActionEvent ae){
        Navigator.navigate(ae, Navigator.MODIFIKO_ADRESEN);
    }
    @FXML
    private void handleModifikoQytetarin(ActionEvent ae){
        Navigator.navigate(ae, Navigator.MODIFIKO_QYTETARIN);
    }
    @FXML
    private void handleHelp(ActionEvent ae){
        Navigator.navigate(ae,Navigator.HELP);
    }
}