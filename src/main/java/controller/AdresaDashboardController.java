package controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AdresaDashboardController {
        @FXML
        private AnchorPane root;

        @FXML
        private ImageView imageView;

        @FXML
        private Label AdresaDashboard;

        @FXML
        private Label komunaLabel;

        @FXML
        private Label kodiPostarLabel;

        @FXML
        private Label fshatiLagjiaLabel;

        @FXML
        private Label rrugaLabel;

        @FXML
        private Label numriNdertesesLabel;

        @FXML
        private TextField komunaTextField;

        @FXML
        private TextField kodiPostarTextField;

        @FXML
        private TextField fshatiLagjiaTextField;

        @FXML
        private TextField rruga;

        @FXML
        private TextField NrNderteses;

        @FXML
        private TableView<?> tableView;

        @FXML
        private TableColumn<?, ?> idColumn;

        @FXML
        private TableColumn<?, ?> komunaColumn;

        @FXML
        private TableColumn<?, ?> kodiPostarColumn;

        @FXML
        private TableColumn<?, ?> fshatiLagjiaColumn;

        @FXML
        private TableColumn<?, ?> rrugaColumn;

        @FXML
        private TableColumn<?, ?> numriNdertesesColumn;
        @FXML
        private Button button;

        @FXML
        private void addAddress() {
                // Create a new Address object from text field values
                Address newAddress = new Address(
                        addressList.size() + 1,
                        komunaTextField.getText(),
                        kodiPostarTextField.getText(),
                        fshatiLagjiaTextField.getText(),
                        rruga.getText(),
                        NrNderteses.getText()
                );

                // Add the new address to the list
                addressList.add(newAddress);

                // Clear text fields after adding
                komunaTextField.clear();
                kodiPostarTextField.clear();
                fshatiLagjiaTextField.clear();
                rruga.clear();
                NrNderteses.clear();
        }

        // Address class to represent address data
        class Address {
                private final int id;
                private final String komuna;
                private final String kodiPostar;
                private final String fshatiLagjia;
                private final String rruga;
                private final String numriNderteses;

                public Address(int id, String komuna, String kodiPostar, String fshatiLagjia, String rruga, String numriNderteses) {
                        this.id = id;
                        this.komuna = komuna;
                        this.kodiPostar = kodiPostar;
                        this.fshatiLagjia = fshatiLagjia;
                        this.rruga = rruga;
                        this.numriNderteses = numriNderteses;
                }

                public int getId() {
                        return id;
                }

                public String getKomuna() {
                        return komuna;
                }

                public String getKodiPostar() {
                        return kodiPostar;
                }

                public String getFshatiLagjia() {
                        return fshatiLagjia;
                }

                public String getRruga() {
                        return rruga;
                }

                public String getNumriNderteses() {
                        return numriNderteses;
                }
        }



}




