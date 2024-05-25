package controller;

import App.Navigator;
import App.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.dto.CreateQytetariDto;
import repository.QytetariRepository;

import java.sql.Date;
import java.time.LocalDate;

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

    @Override
    public void setParams(Object params) {
        if (params instanceof Integer) {
            Adresa.setText(params.toString());
        }
    }

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

    @FXML
    void Ruaj(ActionEvent ae) {
        if (!SessionManager.isLoggedIn()) {
            SessionManager.setLastAttemptedPage(Navigator.QYTETARI); // Store last attempted page
            System.out.println("Please log in first.");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
            return;
        }

        // Validate the input fields
        if (Emri.getText().isEmpty() || Mbiemri.getText().isEmpty() || NrPersonal.getText().isEmpty() ||
                NrTel.getText().isEmpty() || Email.getText().isEmpty() || Ditelindja.getValue() == null ||
                (!Femer.isSelected() && !Mashkull.isSelected())) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Ju lutem plotesoni te gjitha pjeset ne menyre korrekte.");
            return;
        }

        if (QytetariRepository.existsByNrPersonal(NrPersonal.getText())) {
            showAlert(Alert.AlertType.ERROR, "Duplicate Error", "Një qytetar me këtë Numër Personal tashmë ekziston.");
            return;
        }

        try {
            String Gjinia = Femer.isSelected() ? "Femer" : "Mashkull";
            LocalDate localDate = Ditelindja.getValue();
            Date ditelindjaValue = Date.valueOf(localDate);
            int userId = SessionManager.getUser().getId(); // Assuming SessionManager has a method to get the current user

            CreateQytetariDto qytetari = new CreateQytetariDto(
                    NrPersonal.getText(),
                    Emri.getText(),
                    Mbiemri.getText(),
                    Gjinia,
                    ditelindjaValue,
                    Integer.parseInt(Adresa.getText()), // Assuming Adresa is correctly set
                    NrTel.getText(),
                    Email.getText(),
                    userId
            );

            boolean isCreated = QytetariRepository.create(qytetari);

            if (isCreated) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Qytetari eshte ruajtur me sukses.");
                clearForm();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failure", "Qytetari deshtoi qe te ruhet.");
            }
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
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    @FXML
    private void btnOpen2(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADRESA_DASHBOARD);
    }

    @FXML
    private void btnOpen3(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.QYTETARI_DASHBOARD);
    }

    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        Navigator.changeLanguage();
        Navigator.navigate(ae, Navigator.QYTETARI);
    }
}
