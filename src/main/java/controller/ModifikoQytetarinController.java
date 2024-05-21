package controller;

import App.Navigator;
import App.SessionManager;
import Database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.User;
import model.dto.QytetariDto;
import repository.QytetariRepository;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

public class ModifikoQytetarinController {

    @FXML
    private Label NrPersonalLabel;
    @FXML
    private TextField adresaId;
    @FXML
    private Button btnOpen1;
    @FXML
    private Button btnOpen2;
    @FXML
    private Button btnOpen3;
    @FXML
    private DatePicker ditelindja;
    @FXML
    private Label ditelindjaLabel;
    @FXML
    private TextField email;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField emri;
    @FXML
    private Label emriLabel;
    @FXML
    private RadioButton femer;
    @FXML
    private Label gjiniaLabel;
    @FXML
    private RadioButton mashkull;
    @FXML
    private TextField mbiemri;
    @FXML
    private Label mbiemriLabel;
    @FXML
    private Button modifikoQytetarin;
    @FXML
    private TextField nrPersonal;
    @FXML
    private TextField nrTel;
    @FXML
    private Label nrTelefonitLabel;

    @FXML
    void modifikoQytetarin(ActionEvent ae) {
        if (!SessionManager.isLoggedIn()) {
            SessionManager.setLastAttemptedPage(Navigator.MODIFIKO_QYTETARIN); // Store last attempted page
            System.out.println("Please log in first.");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
            return;
        }

        try {
            String Gjinia = "";

            int idAdresaValue = Integer.parseInt(adresaId.getText());
            LocalDate localDate = ditelindja.getValue();
            Date ditelindjaValue = (localDate != null) ? Date.valueOf(localDate) : null;

            if (femer.isSelected()) {
                Gjinia = "Femer";
            } else if (mashkull.isSelected()) {
                Gjinia = "Mashkull";
            }

            if (nrTel.getText().length() > 20) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Numri i telefonit eshte shume i gjate. Ju lutem shenoni nje numer te telefonit deri ne 20 karaktere.");
                return;
            }

            User currentUser = SessionManager.getUser();
            int userId = currentUser.getId();

            Connection connection = DatabaseUtil.getConnection();
            if (connection != null) {
                QytetariDto qytetari = new QytetariDto( nrPersonal.getText(), emri.getText(), mbiemri.getText(), Gjinia, ditelindjaValue, idAdresaValue, email.getText(), nrTel.getText(), userId);
                boolean success = QytetariRepository.modifiko(qytetari);
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Të dhënat e qytetarit u modifikuan me sukses");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failure", "Ndryshimi i të dhënave të qytetarit dështoi për shkak të problemeve me lidhjen në databazë");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Deshtoi qe te lidhet ne databaze.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Id e adreses eshte invalide. Ju lutem shkruani nje numer valid.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnOpen1(ActionEvent event) {}

    @FXML
    void btnOpen2(ActionEvent event) {}

    @FXML
    void btnOpen3(ActionEvent event) {}
}