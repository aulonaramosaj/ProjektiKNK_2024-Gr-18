package controller;

import App.Navigator;
import App.SessionManager;
import interfaces.AddressAddedListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Adresa;
import model.User;
import model.dto.CreateAdresaDto;
import service.AdresaService;

public class AdresaController {
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
    private RadioButton radioPerhershem;
    @FXML
    private RadioButton radioPerkohshem;

    private final AdresaService adresaService = new AdresaService();
    private AddressAddedListener addressAddedListener;

    @FXML
    private void vendosAdresen(ActionEvent ae) {
        if (!SessionManager.isLoggedIn()) {
            System.out.println("Please log in first.");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
            return;
        }
        String llojiVendbanimit = radioPerhershem.isSelected() ? "Perhershem" : "Perkohshem";
        int numriNderteses = Integer.parseInt(txtNumriNderteses.getText());
        int kodiPostar = Integer.parseInt(txtKodiPostar.getText());

        User currentUser = SessionManager.getUser();
        int userId = currentUser.getId();

        CreateAdresaDto adresaDto = new CreateAdresaDto(
                txtKomuna.getText(), txtFshati.getText(), txtRruga.getText(), numriNderteses, kodiPostar, llojiVendbanimit, userId);

        int addressId = adresaService.createAdresa(adresaDto);

        if (addressId > 0) {
            System.out.println("Adresa u shtua me sukses, ID: " + addressId);
            if (addressAddedListener != null) {
                Adresa newAdresa = new Adresa(addressId, txtKomuna.getText(), txtFshati.getText(), txtRruga.getText(), numriNderteses, kodiPostar, llojiVendbanimit);
                addressAddedListener.onAddressAdded(newAdresa);
            }
            Navigator.navigateWithParams(ae, Navigator.QYTETARI, addressId);
        } else {
            System.out.println("Adresa dështoi që të shtohet në databazë");
        }
    }

    @FXML
    private void buttonOpen1(ActionEvent ae) {

    }

    @FXML
    private void buttonOpen2(ActionEvent ae) {

    }

    @FXML
    private void buttonOpen3(ActionEvent ae) {

    }
}