package controller;

import model.Adresa;
import Database.DatabaseUtil;
import model.dto.CreateQytetariDto;
import repository.QytetariRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class QytetariController {

    // Address Info Fields
    private String qytetiValue;
    private String komuna;
    private String fshati;
    private String rrugaValue;
    private int numriNdertesesValue;
    private int kodiPostarValue;
    private int adresaId;
    private String currentText;

    // FXML UI Components
    @FXML
    private TextField Adresa, Email, Emri, Mbiemri, NrPersonal, NrTel, adresaIdField;
    @FXML
    private DatePicker Ditelindja;
    @FXML
    private RadioButton Femer, Mashkull;
    @FXML
    private Button Ruaj, adresatBtn, dashboardBtn, qytetaretBtn;
    @FXML
    private Label ditelindjaError, emailError, emriLabel, errorEmri, errorNrPersonal, errorQytetariEkziston, gjiniaError, mbiemriError, nrTelError;

    // Initialization method
    public void initialize() {
        setupPhoneNumberFormatting();
        setupGenderSelection();
    }

    // Setup phone number formatting
    private void setupPhoneNumberFormatting() {
        NrTel.textProperty().addListener((observable, oldValue, newValue) -> {
            String strippedText = newValue.replaceAll("[^\\d]", "");
            StringBuilder formattedText = new StringBuilder();

            for (int i = 0; i < strippedText.length(); i++) {
                if (i == 0) {
                    formattedText.append("+").append(strippedText.charAt(i));
                } else if (i == 3 || i == 5 || i == 8) {
                    formattedText.append(" ").append(strippedText.charAt(i));
                } else {
                    formattedText.append(strippedText.charAt(i));
                }
            }

            if (!formattedText.toString().equals(currentText)) {
                currentText = formattedText.toString();
                NrTel.setText(currentText);
                NrTel.positionCaret(currentText.length());
            }
        });
    }

    // Setup gender selection handling
    private void setupGenderSelection() {
        Femer.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) Mashkull.setSelected(false);
        });
        Mashkull.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) Femer.setSelected(false);
        });
    }

    // Set address info
    public void setAddressInfo(int id, String qytetiValue, String komuna, String fshati, String rrugaValue, int numriNdertesesValue, int kodiPostarValue) {
        this.qytetiValue = qytetiValue;
        this.komuna = komuna;
        this.fshati = fshati;
        this.rrugaValue = rrugaValue;
        this.numriNdertesesValue = numriNdertesesValue;
        this.kodiPostarValue = kodiPostarValue;
        this.adresaId = id;

        StringBuilder adresaValue = new StringBuilder();

        appendToAddress(adresaValue, qytetiValue);
        appendToAddress(adresaValue, komuna);
        appendToAddress(adresaValue, fshati);
        appendToAddress(adresaValue, rrugaValue);
        appendToAddress(adresaValue, String.valueOf(numriNdertesesValue));
        appendToAddress(adresaValue, String.valueOf(kodiPostarValue));

        Adresa.setText(adresaValue.toString());
        adresaIdField.setText(String.valueOf(adresaId));
    }

    private void appendToAddress(StringBuilder adresaValue, String value) {
        if (value != null && !value.isEmpty()) {
            if (adresaValue.length() > 0) {
                adresaValue.append(", ");
            }
            adresaValue.append(value);
        }
    }

    // Clear form fields
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

    // Save Qytetari
    @FXML
    void Ruaj(ActionEvent event) {
        try {
            String nrPersonal = NrPersonal.getText();
            String emri = Emri.getText();
            String mbiemri = Mbiemri.getText();
            LocalDate ditelindja = Ditelindja.getValue();
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .toFormatter();
            String ditelindjaStr = ditelindja == null ? null : ditelindja.format(formatter);

            String email = Email.getText();
            String nrTel = NrTel.getText();
            String gjinia = Femer.isSelected() ? "Femer" : Mashkull.isSelected() ? "Mashkull" : "";
            String adresa = Adresa.getText();

            if (!validateInputs(nrPersonal, emri, mbiemri, ditelindjaStr, email, nrTel, gjinia)) return;

            Connection connection = DatabaseUtil.getConnection();

            if (connection != null) {
                CreateQytetariDto qytetariDto = new CreateQytetariDto(nrPersonal, emri, mbiemri, ditelindjaStr, email, nrTel, gjinia, Integer.parseInt(adresaIdField.getText()));
                QytetariRepository qytetariRepository = new QytetariRepository();
                boolean qytetariExists = QytetariRepository.EkzistonQytetari(nrPersonal, adresaId, connection);

                if (!qytetariExists) {
                    qytetariRepository.create(qytetariDto, connection);
                    System.out.println("Qytetari u krijua me sukses");
                    clearForm();
                } else {
                    errorQytetariEkziston.setVisible(true);
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting address into database: " + e.getMessage());
        }
    }

    private boolean validateInputs(String nrPersonal, String emri, String mbiemri, String ditelindjaStr, String email, String nrTel, String gjinia) {
        if (nrPersonal == null || nrPersonal.length() != 10) {
            showError(errorNrPersonal, "Kerkohet numri personal!");
            return false;
        }
        if (emri == null || emri.isEmpty()) {
            showError(errorEmri, "Kerkohet emri!");
            return false;
        }
        if (mbiemri == null || mbiemri.isEmpty()) {
            showError(mbiemriError, "Kerkohet mbiemri!");
            return false;
        }
        if (ditelindjaStr == null || ditelindjaStr.isEmpty()) {
            showError(ditelindjaError, "Kerkohet ditelindja!");
            return false;
        }
        if (email == null || email.isEmpty()) {
            showError(emailError, "Kerkohet email!");
            return false;
        }
        if (nrTel == null || nrTel.isEmpty()) {
            showError(nrTelError, "Kerkohet numri i telefonit!");
            return false;
        }
        if (gjinia == null || gjinia.isEmpty()) {
            showError(gjiniaError, "Kerkohet gjinia!");
            return false;
        }
        return true;
    }

    private void showError(Label errorLabel, String errorMessage) {
        errorLabel.setVisible(true);
        errorLabel.setText(errorMessage);
    }
}
