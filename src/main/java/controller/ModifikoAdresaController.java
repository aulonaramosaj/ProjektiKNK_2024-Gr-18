package controller;


import App.Navigator;
import Database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Adresa;
import model.dto.AdresaDto;
import model.dto.CreateAdresaDto;
import repository.AdresaRepository;

import java.sql.Connection;

public class ModifikoAdresaController {
    @FXML
    private TextField idAdresa;
    @FXML
    private RadioButton radioPerhershem;
    @FXML
    private RadioButton radioPerkohshem;
    @FXML
    private TextField txtKomuna;
    @FXML
    private TextField txtKodiPostar;
    @FXML
    private TextField txtFshati;
    @FXML
    private TextField txtRruga;
    @FXML
    private TextField txtNumriNderteses;
    @FXML
    private void modifikoAdresen(ActionEvent ae) {
        String llojiVendbanimit = "";
        int idAdresaValue = Integer.parseInt(idAdresa.getText());
        int txtNumriNdertesesValue = Integer.parseInt(txtNumriNderteses.getText());
        int txtKodiPostarValue = Integer.parseInt(txtKodiPostar.getText());

        if (radioPerhershem.isSelected()) {
            llojiVendbanimit = "1";
        }
        if (radioPerkohshem.isSelected()) {
            llojiVendbanimit = "0";
        }
        Connection connection = DatabaseUtil.getConnection();
        if (connection != null) {

            AdresaDto adresa = new AdresaDto(idAdresaValue,txtKomuna.getText(), txtFshati.getText(), txtRruga.getText(), txtNumriNdertesesValue, txtKodiPostarValue, llojiVendbanimit);

            AdresaRepository modifikoAdresenRepository = new AdresaRepository();
            modifikoAdresenRepository.modifiko(adresa);
            System.out.println("Adresa u modifikua me sukses");

            //Navigator.navigate(ae, Navigator.QYTETARI);

        } else {
            System.out.println("Adresa dështoi që të shtohet në databazë");
        }
    }

    @FXML
    private void buttonOpen1 (ActionEvent ae){

    }
    @FXML
    private void buttonOpen2 (ActionEvent ae){

    }
    @FXML
    private void buttonOpen3 (ActionEvent ae){

    }


}
