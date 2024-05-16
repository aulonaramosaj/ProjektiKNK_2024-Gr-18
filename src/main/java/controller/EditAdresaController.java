package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditAdresaController {

    @FXML
    private TextField qytetiTextField;

    @FXML
    private TextField komunaTextField;

    @FXML
    private TextField fshatiTextField;

    @FXML
    private TextField rrugaTextField;

    @FXML
    private TextField objektiTextField;

    @FXML
    private TextField hyrjaTextField;

    @FXML
    private TextField numriTextField;

    @FXML
    private TextField numriPostalTextField;

    @FXML
    private ChoiceBox<String> llojiVendbanimitChoiceBox;

    @FXML
    private Button adresatButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button qytetaretButton;

    @FXML
    private Button shfaqQytetaretButton;

    @FXML
    private Button shtoQytetarinButton;

    @FXML
    private Button shtoAdresenButton;

    @FXML
    public void initialize() {
        // Initialize choice box with some options
        llojiVendbanimitChoiceBox.getItems().addAll("Qytet", "Fshat");
    }

    @FXML
    private void handleAdresatButtonClick() {
        showAlert("Adresat button clicked");
        // Implement logic to handle showing or managing addresses
    }

    @FXML
    private void handleDashboardButtonClick() {
        showAlert("Dashboard button clicked");
        // Implement logic to navigate to the dashboard
    }

    @FXML
    private void handleQytetaretButtonClick() {
        showAlert("Qytetaret button clicked");
        // Implement logic to show or manage city residents
    }

    @FXML
    private void handleShfaqQytetaretButtonClick() {
        showAlert("Shfaq Qytetaret button clicked");
        // Implement logic to display a list of city residents
    }

    @FXML
    private void handleShtoQytetarinButtonClick() {
        showAlert("Shto Qytetarin button clicked");
        // Implement logic to add a new city resident
    }

    @FXML
    private void handleShtoAdresenButtonClick() {
        showAlert("Shto Adresen button clicked");
        // Implement logic to add a new address
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
