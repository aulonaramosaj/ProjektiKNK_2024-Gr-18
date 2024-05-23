package controller;

import App.Navigator;
import App.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.User;
import model.dto.CreateQytetariDto;
import repository.QytetariRepository;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import Database.DatabaseUtil;
import App.Navigator.ParametrizedController;

public class QytetariController implements ParametrizedController {

    @FXML
    private TextField Emri;
    @FXML
    private TextField Mbiemri;
    @FXML
    private TextField NrPersonal;
    @FXML
    private TextField NrTel;
    @FXML
    private TextField Email;
    @FXML
    private TextField Adresa;

    @FXML
    private DatePicker Ditelindja;
    @FXML
    private RadioButton Femer, Mashkull;

    public void clearForm() {
        NrPersonal.setText("");
        Emri.setText("");
        Mbiemri.setText("");
        Ditelindja.setValue(null);
        Email.setText("");
        NrTel.setText("");
        Femer.setSelected(false);
        Mashkull.setSelected(false);
    }

    @Override
    public void setParams(Object params) {
        if (params instanceof Integer) {
            Adresa.setText(params.toString());
        }
    }

    @FXML
    private void Ruaj(ActionEvent ae) {
        if (!SessionManager.isLoggedIn()) {
            SessionManager.setLastAttemptedPage(Navigator.QYTETARI); // Store last attempted page
            System.out.println("Please log in first.");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
            return;
        }

        try {
            String nrPersonal = NrPersonal.getText();
            String emri = Emri.getText();
            String mbiemri = Mbiemri.getText();
            String email = Email.getText();
            String nrTel = NrTel.getText();
            int adresa = Integer.parseInt(Adresa.getText());
            LocalDate localDate = Ditelindja.getValue();
            Date ditelindjaValue = (localDate != null) ? Date.valueOf(localDate) : null;
            String gjinia = Femer.isSelected() ? "Femer" : Mashkull.isSelected() ? "Mashkull" : null;

            if (emri.isEmpty() || mbiemri.isEmpty() || email.isEmpty() || nrTel.isEmpty() || gjinia == null || ditelindjaValue == null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Ju lutem plotesoni te gjitha pjeset ne menyre korrekte.");
                return;
            }

            if (nrTel.length() > 20) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Numri i telefonit eshte shume i gjate. Ju lutem shenoni nje numer te telefonit deri ne 20 karaktere.");
                return;
            }

            // Check for duplicate NrPersonal before saving
            if (QytetariRepository.existsByNrPersonal(nrPersonal)) {
                showAlert(Alert.AlertType.ERROR, "Duplicate Error", "Një qytetar me këtë Numër Personal tashmë ekziston.");
                return;
            }

            User currentUser = SessionManager.getUser();
            int userId = currentUser.getId();

            Connection connection = DatabaseUtil.getConnection();
            if (connection != null) {
                CreateQytetariDto qytetariDto = new CreateQytetariDto(nrPersonal, emri, mbiemri, gjinia, ditelindjaValue, adresa, nrTel, email, userId);
                boolean success = QytetariRepository.create(qytetariDto);
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Qytetari eshte ruajtur me sukses.");
                    clearForm();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failure", "Qytetari deshtoi qe te ruhet.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Deshtoi qe te ruhet ne databaze.");
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
    private void btnOpen1(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADRESA);
    }

    @FXML
    private void btnOpen2(ActionEvent ae) {}

    @FXML
    private void btnOpen3(ActionEvent ae) {}
}