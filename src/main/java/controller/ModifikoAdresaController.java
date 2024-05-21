package controller;

import App.Navigator;
import App.SessionManager;
import Database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.User;
import model.dto.AdresaDto;
import repository.AdresaRepository;

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
        if (!SessionManager.isLoggedIn()) {
            SessionManager.setLastAttemptedPage(Navigator.MODIFIKO_ADRESEN); // Store last attempted page
            System.out.println("Please log in first.");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
            return;
        }

        try {
            int idAdresaValue = Integer.parseInt(idAdresa.getText());
            int txtNumriNdertesesValue = Integer.parseInt(txtNumriNderteses.getText());
            int txtKodiPostarValue = Integer.parseInt(txtKodiPostar.getText());

            User currentUser = SessionManager.getUser();
            int userId = currentUser.getId();

            String llojiVendbanimit = radioPerhershem.isSelected() ? "Perhershem" : radioPerkohshem.isSelected() ? "Perkohshem" : "";

            AdresaDto adresa = new AdresaDto(idAdresaValue, txtKomuna.getText(), txtFshati.getText(), txtRruga.getText(), txtNumriNdertesesValue, txtKodiPostarValue, llojiVendbanimit, userId);

            AdresaRepository adresaRepository = new AdresaRepository();
            boolean isModified = adresaRepository.modifiko(adresa);

            if (isModified) {
                System.out.println("Adresa u modifikua me sukses");
            } else {
                System.out.println("Adresa dështoi që të modifikohet në databazë");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numeric fields: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonOpen1(ActionEvent ae) {}

    @FXML
    private void buttonOpen2(ActionEvent ae) {}

    @FXML
    private void buttonOpen3(ActionEvent ae) {}
}
