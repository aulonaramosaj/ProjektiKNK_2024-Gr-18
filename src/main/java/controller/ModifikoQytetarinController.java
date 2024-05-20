package controller;

import Database.DatabaseUtil;
import model.Qytetari;
import repository.QytetariRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ModifikoQytetarinController {
    @FXML
    private TextField AdresaEdit, adresaId, email, emri, mbiemri, nrPersonal, nrTel, idQytetari;
    @FXML
    private Button Back, adresatBtn, dashboardBtn, qytetaretBtn, updateQytetarin;
    @FXML
    private DatePicker ditelindja;
    @FXML
    private Label ditelindjaLabel, emailLabel, emriLabel, errorQytetariEkziston, gjiniaLabel, mbiemriLabel, nrPersonalLabel, nrTelefonitLabel, personalData;
    @FXML
    private RadioButton femer, mashkull;

    private String currentText = "";

    public void initialize() {
        nrTel.textProperty().addListener((observable, oldValue, newValue) -> {
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
                nrTel.setText(currentText);
                nrTel.positionCaret(currentText.length());
            }
        });

        femer.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) mashkull.setSelected(false);
        });

        mashkull.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) femer.setSelected(false);
        });
    }

    public void vendosQytetarin(int id, String nrPersonal, String emri, String mbiemri, String ditelindja, String email, String nrTel, String gjinia, int adresa) {
        this.idQytetari.setText(String.valueOf(id));
        this.AdresaEdit.setText(String.valueOf(adresa));
        this.nrPersonal.setText(nrPersonal);
        this.emri.setText(emri);
        this.mbiemri.setText(mbiemri);
        this.ditelindja.setValue(LocalDate.parse(ditelindja, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.email.setText(email);
        this.nrTel.setText(nrTel);
        if (gjinia.equals("Femer")) {
            femer.setSelected(true);
        } else {
            mashkull.setSelected(true);
        }
        this.adresaId.setText(String.valueOf(adresa));
    }

    @FXML
    void updateQytetarin(ActionEvent event) {
        try {
            LocalDate ditelindja = this.ditelindja.getValue();
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .toFormatter();
            String ditelindjaStr = ditelindja == null ? null : ditelindja.format(formatter);
            String gjinia = femer.isSelected() ? "Femer" : "Mashkull";

            Connection connection = DatabaseUtil.getConnection();

            if (connection != null) {
                Qytetari modifikoQytetarin = new Qytetari(
                        Integer.parseInt(idQytetari.getText()),
                        nrPersonal.getText(),
                        emri.getText(),
                        mbiemri.getText(),
                        ditelindjaStr,
                        email.getText(),
                        nrTel.getText(),
                        gjinia
                );
                QytetariRepository editQytetariRepository = new QytetariRepository();
                editQytetariRepository.Update(modifikoQytetarin, connection);

                System.out.print("Qytetari u perditesua me sukses!");

            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating qytetari into database: " + e.getMessage());
        }
    }
}