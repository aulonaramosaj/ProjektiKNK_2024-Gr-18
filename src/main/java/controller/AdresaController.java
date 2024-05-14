package controller;

import App.Navigator;
import Database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.dto.CreateAdresaDto;
import repository.AdresaRepository;




import java.sql.Connection;



public class AdresaController {
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
    private void vendosAdresen (ActionEvent ae) {

        String llojiVendbanimit = "";
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

            CreateAdresaDto adresaDto = new CreateAdresaDto(txtKomuna.getText(), txtFshati.getText(), txtRruga.getText(), txtNumriNdertesesValue, txtKodiPostarValue, llojiVendbanimit);

            AdresaRepository adresaRepository = new AdresaRepository();
            adresaRepository.create(adresaDto);
            System.out.println("Adresa u shtua me sukses");

                Navigator.navigate(ae, Navigator.QYTETARI);

        } else {
            System.out.println("Adresa dështoi që të shtohet në databazë");
        }
    }

}
