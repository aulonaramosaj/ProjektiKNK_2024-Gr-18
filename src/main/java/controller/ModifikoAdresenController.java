package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ModifikoAdresenController {

    @FXML
    private TextField qytetiTextField, komunaTextField, fshatiTextField, rrugaTextField, numriNdertesesTextField, kodiPostarTextField;

    @FXML
    private ChoiceBox<String> llojiVendbanimitChoiceBox;

    @FXML
    private Button adresatButton, dashboardButton, qytetaretButton, shfaqQytetaretButton, shtoQytetarinButton, shtoAdresenButton;

    @FXML
    public void initialize() {
        llojiVendbanimitChoiceBox.getItems().addAll("Qytet", "Fshat");
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        if (event.getSource() == adresatButton) {
            showInfoAlert("Adresat button clicked");
        } else if (event.getSource() == dashboardButton) {
            showInfoAlert("Dashboard button clicked");
        } else if (event.getSource() == qytetaretButton) {
            showInfoAlert("Qytetaret button clicked");
        } else if (event.getSource() == shfaqQytetaretButton) {
            showInfoAlert("Shfaq Qytetaret button clicked");
        } else if (event.getSource() == shtoQytetarinButton) {
            showInfoAlert("Shto Qytetarin button clicked");
        } else if (event.getSource() == shtoAdresenButton) {
            showInfoAlert("Shto Adresen button clicked");
        }
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
