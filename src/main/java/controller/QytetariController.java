package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Qytetari;
import model.dto.QytetariDto;
import service.QytetariService;

public class QytetariController {

    @FXML
    private Button btnVendosTeDhenat;

    @FXML
    private RadioButton radioFemer;

    @FXML
    private RadioButton radioMashkull;

    @FXML
    private TextField txtAdresa;

    @FXML
    private TextField txtDitelindja;

    @FXML
    private TextField txtEmaili;

    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtMbiemri;

    @FXML
    private TextField txtNrPersonal;

    @FXML
    private TextField txtNrTelefonit;

    @FXML
    void vendosTeDhenat(ActionEvent event) {
        String emri = txtEmri.getText();
        String mbiemri = txtMbiemri.getText();
        String nrPersonal = txtNrPersonal.getText();
        String ditelindja = txtDitelindja.getText();
        String email = txtEmaili.getText();
        String nrTel = txtNrTelefonit.getText();
        String gjinia = radioFemer.isSelected() ? "Femer" : "Mashkull";
        int adresa = Integer.parseInt(txtAdresa.getText());

        // Krijo objektin e qytetarit
        QytetariDto qytetari = new QytetariDto(0, nrPersonal, emri, mbiemri, ditelindja, email, nrTel, gjinia, adresa);

        // Përdor QytetariService për të regjistruar qytetarin
        boolean regjistrohuSukses = QytetariService.regjistrohu(qytetari);

        // Njofto përdoruesin nëse regjistrimi ishte i suksesshëm ose jo
        if (regjistrohuSukses) {
            showInformationDialog("Regjistrimi u krye me sukses!", "Sukses");
        } else {
            showErrorDialog("Regjistrimi deshtoi. Kontrolloni të dhënat dhe provoni përsëri.", "Gabim");
        }
    }

    private void showInformationDialog(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorDialog(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
