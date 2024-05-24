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
import App.Navigator.ParametrizedController;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

public class ModifikoQytetarinController implements ParametrizedController {

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
            int idAdresaValue = Integer.parseInt(adresaId.getText());
            String Gjinia = femer.isSelected() ? "Femer" : mashkull.isSelected() ? "Mashkull" : "";
            LocalDate localDate = ditelindja.getValue();
            Date ditelindjaValue = (localDate != null) ? Date.valueOf(localDate) : null;

            User currentUser = SessionManager.getUser();
            int userId = currentUser.getId();

            QytetariDto qytetari = new QytetariDto(nrPersonal.getText(), emri.getText(), mbiemri.getText(), Gjinia, ditelindjaValue, idAdresaValue, email.getText(), nrTel.getText(), userId);
            boolean isModified = QytetariRepository.modifiko(qytetari);

            if (isModified) {
                System.out.println("Të dhënat e qytetarit u modifikuan me sukses");
            } else {
                System.out.println("Ndryshimi i të dhënave të qytetarit dështoi për shkak të problemeve me lidhjen në databazë");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void setParams(Object params) {
        if (params instanceof String) {
            nrPersonal.setText((String) params);
            loadCitizenData((String) params);
        }
    }

    private void loadCitizenData(String nrPersonal) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            QytetariDto qytetari = QytetariRepository.findByNrPersonal(nrPersonal);
            if (qytetari != null) {
                emri.setText(qytetari.getEmri());
                mbiemri.setText(qytetari.getMbiemri());
                email.setText(qytetari.getEmail());
                nrTel.setText(qytetari.getNrTelefonit());
                adresaId.setText(String.valueOf(qytetari.getAdresa()));
                ditelindja.setValue(qytetari.getDitelindja().toLocalDate());
                if (qytetari.getGjinia().equals("Femer")) {
                    femer.setSelected(true);
                } else if (qytetari.getGjinia().equals("Mashkull")) {
                    mashkull.setSelected(true);
                }
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Load Error", "Failed to load citizen data.");
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
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        Navigator.changeLanguage();
        Navigator.navigate(ae,Navigator.MODIFIKO_QYTETARIN);
    }
}