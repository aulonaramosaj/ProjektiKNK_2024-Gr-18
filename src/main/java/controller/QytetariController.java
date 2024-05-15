package controller;

import model.Adresa;
import Database.DatabaseUtil;
import model.dto.CreateQytetariDto;
import model.Qytetari;
import repository.QytetariRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QytetariController {

    public String qytetiValue;
    public String rrugaValue;
    public int numriNdertesesValue;
    public int kodiPostarValue;
    public String komuna;
    public String fshati;
    public String objekti;
    public String hyrja;
    @FXML
    public TextField Adresa;

    @FXML
    private DatePicker Ditelindja;

    @FXML
    private TextField Email;

    @FXML
    private TextField Emri;


    @FXML
    private RadioButton Femer;

    @FXML
    private RadioButton Mashkull;

    @FXML
    private TextField Mbiemri;

    @FXML
    private TextField NrPersonal;

    @FXML
    private TextField NrTel;

    @FXML
    private Button Ruaj;

    @FXML
    private TextField adresaId;

    @FXML
    private Button adresatBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Label ditelindjaError;

    @FXML
    private Label ditelindjaLabel;

    @FXML
    private Label emailError;

    @FXML
    private Label emailLabel;


    @FXML
    private Label emriLabel;


    @FXML
    private Label errorEmri;

    @FXML
    private Label errorNrPersonal;

    @FXML
    private Label errorQytetariEkziston;

    @FXML
    private Label gjiniaError;

    @FXML
    private Label gjiniaLabel;

    @FXML
    private Label mbiemriError;

    @FXML
    private Label mbiemriLabel;



    @FXML
    private Label nrPersonalLabel;

    @FXML
    private Label nrTelError;

    @FXML
    private Label nrTelefonitLabel;

    @FXML
    private Label personalData;

    @FXML
    private Button qytetaretBtn;

    @FXML
    private Label qytetariAdresa;

    @FXML
    private Label qytetariAdresaLabel;

    public String currentText;
    public int AdresaId;
    private int KodiPostarValue;


    public void initialize() {
        currentText = "";
        // Add a listener to the text property to enforce the mask
        NrTel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Strip all non-digit characters from the input
                String strippedText = newValue.replaceAll("[^\\d]", "");

                // Insert the spaces between the segments of the mask
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

                // Set the new text on the TextField
                if (!formattedText.toString().equals(currentText)) {
                    currentText = formattedText.toString();
                    NrTel.setText(currentText);
                    NrTel.positionCaret(currentText.length());
                }
            }
        });

        Femer.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Mashkull.setSelected(false);
            }
        });
        Mashkull.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Femer.setSelected(false);
            }
        });


    }

    public void setAddressInfo(int id, String qytetiValue, String komuna, String fshati, String rrugaValue, int numriNdertesesValue, int kodiPostarValue) {
        this.qytetiValue = qytetiValue;
        this.komuna = komuna;
        this.fshati = fshati;
        this.rrugaValue = rrugaValue;
        this.objekti = objekti;
        this.hyrja = hyrja;
        this.numriNdertesesValue = numriNdertesesValue;
        this.kodiPostarValue = kodiPostarValue;
        String adresaValue = "";
        this.AdresaId = id;


        if (qytetiValue != null && !qytetiValue.isEmpty()) {
            adresaValue += qytetiValue;
        }

        if (komuna != null && !komuna.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += komuna;
        }

        if (fshati != null && !fshati.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += fshati;
        }

        if (rrugaValue != null && !rrugaValue.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += rrugaValue;
        }

        if (objekti != null && !objekti.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += objekti;
        }

        if (hyrja != null && !hyrja.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += hyrja;
        }

        if (numriNdertesesValue != 0) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += String.valueOf(numriNdertesesValue);
        }

        if (kodiPostarValue != 0) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += String.valueOf(kodiPostarValue);
        }
        Adresa.setText(adresaValue);
        adresaId.setText(String.valueOf(AdresaId));

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
    void Ruaj(ActionEvent event) {

        try {
            // Get the values entered in the text fields
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
            String gjinia = "";
            String adresa = Adresa.getText();
            if (Femer.isSelected()) {
                gjinia = "Femer";
            }
            if (Mashkull.isSelected()) {
                gjinia = "Mashkull";
            }

            //Nese njera prej textfieldave eshte i zbrazet

            if (nrPersonal.length() < 10 || nrPersonal.length() > 10) {
                errorNrPersonal.setVisible(true);
                System.out.println("Numri personal eshte gabim");
                return;
            } else if (nrPersonal == null || nrPersonal == "") {
                errorNrPersonal.setVisible(true);
                errorNrPersonal.setText("Kerkohet numri personal!");
                return;
            } else if (emri == null || emri.equals("")) {
                errorEmri.setVisible(true);
                errorEmri.setText("Kerkohet emri!");
                return;
            } else if (mbiemri == null || mbiemri.equals("")) {
                mbiemriError.setVisible(true);
                mbiemriError.setText("Kerkohet mbiemri!");
                return;
            } else if (ditelindjaStr == null || ditelindjaStr.equals("")) {
                ditelindjaError.setVisible(true);
                ditelindjaError.setText("Kerkohet ditelindja!");
                return;
            } else if (email == null || email.equals("")) {
                emailError.setVisible(true);
                emailError.setText("Kerkohet email!");
                return;
            } else if (nrTel == null || nrTel.equals("")) {
                nrTelError.setVisible(true);
                nrTelError.setText("Kerkohet numri i telefonit!");
                return;
            } else if (gjinia == null || gjinia.equals("")) {
                gjiniaError.setVisible(true);
                gjiniaError.setText("Kerkohet gjinia!");
                return;
            }

            Connection Connection = DatabaseUtil.getConnection();

            if (Connection != null) {
                // Insert the new address into the database
                CreateQytetariDto qytetariDto = new CreateQytetariDto(NrPersonal.getText(), Emri.getText(),  Mbiemri.getText(), ditelindjaStr, Email.getText(), NrTel.getText(), gjinia, Integer.parseInt(adresaId.getText()));
                QytetariRepository qytetariRepository = new QytetariRepository();
                boolean QytetariExists = QytetariRepository.qytetariExists(NrPersonal.getText(), AdresaId, Connection);
                if (QytetariExists == false) {
                    qytetariRepository.create(qytetariDto, Connection);
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

}
