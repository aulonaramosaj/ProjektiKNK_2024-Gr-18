package controller;

import App.Navigator;
import App.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.dto.CreateQytetariDto;
import repository.QytetariRepository;
import service.QytetariService;
import App.Navigator.ParametrizedController;

import java.sql.Date;
import java.time.LocalDate;

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

    private QytetariService qytetariService = new QytetariService();

    @Override
    public void setParams(Object params) {
        if (params instanceof Integer) {
            Adresa.setText(params.toString());
        }
    }

    @FXML
    private void initialize() {
        setupEnterKeySubmission();
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

        try {
            if (QytetariRepository.existsByNrPersonal(NrPersonal.getText())) {
                showAlert(Alert.AlertType.ERROR, "Duplicate Error", "Një qytetar me këtë Numër Personal tashmë ekziston.");
                return;
            }

            if (Emri.getText().isEmpty() || Mbiemri.getText().isEmpty() || NrPersonal.getText().isEmpty() ||
                    NrTel.getText().isEmpty() || Email.getText().isEmpty() || Ditelindja.getValue() == null ||
                    (!Femer.isSelected() && !Mashkull.isSelected())) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Ju lutem plotesoni te gjitha pjeset ne menyre korrekte.");
                return;
            }

            int userId = SessionManager.getUser().getId(); // Assuming SessionManager has a method to get the current user
            String Gjinia = Femer.isSelected() ? "Femer" : "Mashkull";
            LocalDate localDate = Ditelindja.getValue();
            Date ditelindjaValue = Date.valueOf(localDate);

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
    private void handleChangeLanguage(ActionEvent ae) {
        Navigator.changeLanguage();
        Navigator.navigate(ae, Navigator.QYTETARI);
    }

    private void setupEnterKeySubmission() {
        Emri.setOnKeyPressed(this::handleEnterKey);
        Mbiemri.setOnKeyPressed(this::handleEnterKey);
        NrPersonal.setOnKeyPressed(this::handleEnterKey);
        NrTel.setOnKeyPressed(this::handleEnterKey);
        Email.setOnKeyPressed(this::handleEnterKey);
        Adresa.setOnKeyPressed(this::handleEnterKey);
        Ditelindja.setOnKeyPressed(this::handleEnterKey);
        Femer.setOnKeyPressed(this::handleEnterKey);
        Mashkull.setOnKeyPressed(this::handleEnterKey);
    }

    private void handleEnterKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Ruaj(new ActionEvent(keyEvent.getSource(), null));
        }
    }
}
