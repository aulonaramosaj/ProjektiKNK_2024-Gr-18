package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditAdresaController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ChoiceBox<String> llojiVendbanimitChoiceBox;

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
    private Button adresatButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button qytetaretButton;

    @FXML
    private void initialize() {
        // Initialize choice box with some options
        llojiVendbanimitChoiceBox.getItems().addAll("Option 1", "Option 2", "Option 3");
    }

    @FXML
    private void handleAdresatButtonClick(ActionEvent event) {
        // Handle Adresat button click
        System.out.println("Adresat button clicked");
    }

    @FXML
    private void handleDashboardButtonClick(ActionEvent event) {
        // Handle Dashboard button click
        System.out.println("Dashboard button clicked");
    }

    @FXML
    private void handleQytetaretButtonClick(ActionEvent event) {
        // Handle Qytetaret button click
        System.out.println("Qytetaret button clicked");
    }
}
