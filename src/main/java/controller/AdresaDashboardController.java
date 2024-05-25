package controller;

import App.Navigator;
import Database.DatabaseUtil;
import interfaces.AddressAddedListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Adresa;
import model.dto.AdresaDto;
import service.AdresaService;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdresaDashboardController implements Initializable, AddressAddedListener, Navigator.ParametrizedController {

        @FXML
        private AnchorPane root;
        @FXML
        private ImageView imageView;
        @FXML
        private TextField komunaTextField;
        @FXML
        private TextField kodiPostarTextField;
        @FXML
        private TextField fshatiLagjiaTextField;
        @FXML
        private TextField rrugaTextField;
        @FXML
        private TextField NrNdertesesTextField;
        @FXML
        private TextField LlojiVendbanimitTextField;
        @FXML
        private TableView<Adresa> tableView;
        @FXML
        private TableColumn<Adresa, Number> kodiPostarColumn, numriNdertesesColumn, idColumn;
        @FXML
        private TableColumn<Adresa, String> komunaColumn, fshatiLagjiaColumn, rrugaColumn, llojiVendbanimitColumn;
        @FXML
        private Button button, handleHome, handleFilter, handleClear;
        @FXML
        private Pagination faqet;

        private ObservableList<Adresa> addressList = FXCollections.observableArrayList();
        private final AdresaService adresaService = new AdresaService();
        private final int rowsPerPage = 10;
        private Integer adresaId;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                initializeTableColumns();
                bindTableViewToObservableList();
                setupPagination();
                loadData();
                setupEnterKeyFiltering();
        }

        @Override
        public void setParams(Object params) {
                if (params instanceof Integer) {
                        this.adresaId = (Integer) params;
                        filterAddressesById(adresaId);
                }
        }

        private void initializeTableColumns() {
                idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));
                komunaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKomuna()));
                kodiPostarColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKodiPostar()));
                fshatiLagjiaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFshati()));
                rrugaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRruga()));
                numriNdertesesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumriNderteses()));
                llojiVendbanimitColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLlojiVendbanimit()));
        }

        private void bindTableViewToObservableList() {
                tableView.setItems(addressList);
        }

        private void setupPagination() {
                faqet.setPageFactory(this::createPage);
        }

        private Node createPage(int pageIndex) {
                int fromIndex = pageIndex * rowsPerPage;
                int toIndex = Math.min(fromIndex + rowsPerPage, addressList.size());
                tableView.setItems(FXCollections.observableArrayList(addressList.subList(fromIndex, toIndex)));
                return new AnchorPane();
        }

        private void loadData() {
                try (Connection conn = DatabaseUtil.getConnection()) {
                        List<Adresa> addresses = adresaService.getAllAdresas(conn);
                        addressList.setAll(addresses);
                        refreshPagination();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        private void filterAddressesById(int adresaId) {
                AdresaDto addressDto = adresaService.getAdresaById(adresaId);
                if (addressDto != null) {
                        addressList.clear();
                        addressList.add(mapToAdresa(addressDto));
                        refreshPagination();
                }
        }

        private Adresa mapToAdresa(AdresaDto addressDto) {
                return new Adresa(
                        addressDto.getId(),
                        addressDto.getKomuna(),
                        addressDto.getFshati(),
                        addressDto.getRruga(),
                        addressDto.getNumriNderteses(),
                        addressDto.getKodiPostar(),
                        addressDto.getLlojiVendbanimit()
                );
        }

        private void refreshPagination() {
                faqet.setPageCount((int) Math.ceil(addressList.size() / (double) rowsPerPage));
                tableView.setItems(FXCollections.observableArrayList(addressList.subList(0, Math.min(rowsPerPage, addressList.size()))));
        }

        @FXML
        private void handleHome(ActionEvent ae) {
                Navigator.navigate(ae, Navigator.HOME_PAGE);
        }

        @FXML
        private void handleFilter() {
                filterAddresses();
        }

        @FXML
        private void handleClear() {
                komunaTextField.clear();
                kodiPostarTextField.clear();
                fshatiLagjiaTextField.clear();
                rrugaTextField.clear();
                NrNdertesesTextField.clear();
                LlojiVendbanimitTextField.clear();
                loadData();
        }

        private void filterAddresses() {
                String komuna = komunaTextField.getText();
                String kodiPostar = kodiPostarTextField.getText();
                String fshatiLagjia = fshatiLagjiaTextField.getText();
                String rruga = rrugaTextField.getText();
                String nrNderteses = NrNdertesesTextField.getText();
                String llojiVendbanimit = LlojiVendbanimitTextField.getText();

                try (Connection conn = DatabaseUtil.getConnection()) {
                        List<Adresa> filteredAddresses = adresaService.getFilteredAdresas(conn, komuna, kodiPostar, fshatiLagjia, rruga, nrNderteses, llojiVendbanimit);
                        addressList.setAll(filteredAddresses);
                        refreshPagination();
                } catch (SQLException e) {
                        e.printStackTrace();
                        showAlert(Alert.AlertType.ERROR, "Database Error", "Error filtering the records.");
                }
        }

        @Override
        public void onAddressAdded(Adresa newAdresa) {
                addressList.add(newAdresa);
                refreshPagination();
        }

        @FXML
        private void handleVendosQytetar(ActionEvent ae) {
                Adresa selectedAddress = tableView.getSelectionModel().getSelectedItem();
                if (selectedAddress != null) {
                        Navigator.navigate(ae, Navigator.QYTETARI, selectedAddress.getId());
                } else {
                        showAlert(Alert.AlertType.WARNING, "No Selection", "No address selected. Please select an address.");
                }
        }

        @FXML
        private void addAddress(ActionEvent ae) {
                Navigator.navigateWithListener(root, Navigator.ADRESA, this);
        }

        @FXML
        private void handleShfaqQytetaret(ActionEvent ae) {
                Adresa selectedAdresa = tableView.getSelectionModel().getSelectedItem();
                if (selectedAdresa != null) {
                        Navigator.navigate(ae, Navigator.QYTETARI_DASHBOARD, selectedAdresa.getId());
                } else {
                        showAlert(Alert.AlertType.WARNING, "No Selection", "No address selected. Please select an address to show related citizens.");
                }
        }

        @FXML
        private void handleModifikoAdresen(ActionEvent ae) {
                Adresa selectedAddress = tableView.getSelectionModel().getSelectedItem();
                if (selectedAddress != null) {
                        int addressId = selectedAddress.getId();
                        Navigator.navigate(ae, Navigator.MODIFIKO_ADRESEN, addressId);
                } else {
                        showAlert(Alert.AlertType.WARNING, "No Selection", "No address selected. Please select an address.");
                }
        }

        @FXML
        private void handleClearAdresa(ActionEvent ae) {
                Adresa selectedAddress = tableView.getSelectionModel().getSelectedItem();
                if (selectedAddress != null) {
                        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmationAlert.setTitle("Confirm Address Deletion");
                        confirmationAlert.setHeaderText("Delete Address");
                        confirmationAlert.setContentText("Are you sure you want to delete this address? This action cannot be undone and may affect related data.");

                        Optional<ButtonType> response = confirmationAlert.showAndWait();
                        if (response.isPresent() && response.get() == ButtonType.OK) {
                                try {
                                        adresaService.deleteAdresa(selectedAddress.getId());
                                        addressList.remove(selectedAddress);
                                        refreshPagination();
                                        showAlert(Alert.AlertType.INFORMATION, "Deletion Successful", "The address has been successfully deleted.");
                                } catch (Exception e) {
                                        showAlert(Alert.AlertType.ERROR, "Deletion Error", "An error occurred while deleting the address: " + e.getMessage());
                                }
                        }
                } else {
                        showAlert(Alert.AlertType.WARNING, "No Selection", "No address selected. Please select an address to delete.");
                }
        }

        private void showAlert(Alert.AlertType alertType, String title, String content) {
                Alert alert = new Alert(alertType);
                alert.setTitle(title);
                alert.setContentText(content);
                alert.showAndWait();
        }

        @FXML
        private void handleChangeLanguage(ActionEvent ae) {
                Navigator.changeLanguage();
                Navigator.navigate(ae, Navigator.ADRESA_DASHBOARD);
        }

        private void setupEnterKeyFiltering() {
                komunaTextField.setOnKeyPressed(this::handleEnterKey);
                kodiPostarTextField.setOnKeyPressed(this::handleEnterKey);
                fshatiLagjiaTextField.setOnKeyPressed(this::handleEnterKey);
                rrugaTextField.setOnKeyPressed(this::handleEnterKey);
                NrNdertesesTextField.setOnKeyPressed(this::handleEnterKey);
                LlojiVendbanimitTextField.setOnKeyPressed(this::handleEnterKey);
        }

        private void handleEnterKey(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                        handleFilter();
                }
        }
}
