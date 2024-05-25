package controller;

import App.Navigator;
import Database.DatabaseUtil;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Qytetari;
import model.filter.QytetariFilter;
import service.QytetariService;
import App.Navigator.ParametrizedController;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class QytetariDashboardController implements Initializable, ParametrizedController {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Qytetari> qytetariTable;
    @FXML
    private TableColumn<Qytetari, Number> qytetariId, qytetariAdresa;
    @FXML
    private TableColumn<Qytetari, String> qytetariNrPersonal, qytetariEmri, qytetariMbiemri, qytetariEmail, qytetariDitelindja, qytetariGjinia, qytetariNrTelefonit;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField nrPersonalTextField;
    @FXML
    private TextField emriTextField;
    @FXML
    private TextField mbiemriTextField;
    @FXML
    private DatePicker ditelindja;

    private ObservableList<Qytetari> qytetariList = FXCollections.observableArrayList();
    private final int rowsPerPage = 10;
    private Integer adresaId;

    private QytetariService qytetariService = new QytetariService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();
        bindTableViewToObservableList();
        setupPagination();
        loadData();
        setupEnterKeySubmission();
    }

    @Override
    public void setParams(Object params) {
        this.adresaId = (Integer) params;
        filterQytetariByAdresaId(adresaId);
    }

    private void initializeTableColumns() {
        qytetariId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));
        qytetariNrPersonal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNrPersonal()));
        qytetariEmri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmri()));
        qytetariMbiemri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMbiemri()));
        qytetariEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        qytetariDitelindja.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat("dd-MM-yyyy").format(cellData.getValue().getDitelindja())));
        qytetariNrTelefonit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNrTelefonit()));
        qytetariGjinia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGjinia()));
        qytetariAdresa.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAdresa()));
    }

    private void bindTableViewToObservableList() {
        qytetariTable.setItems(qytetariList);
    }

    private void setupPagination() {
        pagination.setPageFactory(this::createPage);
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, qytetariList.size());
        qytetariTable.setItems(FXCollections.observableArrayList(qytetariList.subList(fromIndex, toIndex)));
        return new AnchorPane();
    }

    public void loadData() {
        try {
            List<Qytetari> qytetaret = qytetariService.getAllQytetaret();
            qytetariList.setAll(qytetaret);
            refreshPagination();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void filterQytetariByAdresaId(int adresaId) {
        try {
            List<Qytetari> filteredQytetaret = qytetariService.getQytetaretByAdresaId(adresaId);
            qytetariList.setAll(filteredQytetaret);
            refreshPagination();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error filtering the records.");
        }
    }

    private void refreshPagination() {
        pagination.setPageCount((int) Math.ceil(qytetariList.size() / (double) rowsPerPage));
        qytetariTable.setItems(FXCollections.observableArrayList(qytetariList.subList(0, Math.min(rowsPerPage, qytetariList.size()))));
    }

    @FXML
    private void handleHome(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    @FXML
    private void handleFilter() {
        filterQytetariTable();
    }

    @FXML
    private void handleClear() {
        nrPersonalTextField.clear();
        emriTextField.clear();
        mbiemriTextField.clear();
        ditelindja.setValue(null);
        adresaId = null;
        loadData();
    }

    private void filterQytetariTable() {
        String nrPersonal = nrPersonalTextField.getText();
        String emri = emriTextField.getText();
        String mbiemri = mbiemriTextField.getText();
        Date ditelindjaValue = ditelindja.getValue() != null ? java.sql.Date.valueOf(ditelindja.getValue()) : null;

        QytetariFilter filter = new QytetariFilter(nrPersonal, emri, mbiemri, ditelindjaValue, adresaId);

        try {
            List<Qytetari> filteredQytetaret = qytetariService.filterQytetaret(filter);
            qytetariList.setAll(filteredQytetaret);
            refreshPagination();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error filtering the records.");
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
    private void handleChangeLanguage(ActionEvent ae) {
        Navigator.changeLanguage();
        Navigator.navigate(ae, Navigator.QYTETARI_DASHBOARD);
    }

    @FXML
    private void modifikoQytetarinBtn(ActionEvent ae) {
        Qytetari selectedQytetari = qytetariTable.getSelectionModel().getSelectedItem();
        if (selectedQytetari != null) {
            String nrPersonal = selectedQytetari.getNrPersonal();
            Navigator.navigate(ae, Navigator.MODIFIKO_QYTETARIN, nrPersonal);
        } else {
            showAlert(Alert.AlertType.WARNING, "No Citizen Selected", "Please select a citizen to modify.");
        }
    }

    @FXML
    private void fshiQytetarinBtn(ActionEvent ae) {
        Qytetari selectedQytetari = qytetariTable.getSelectionModel().getSelectedItem();
        if (selectedQytetari != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Delete Citizen");
            confirmationAlert.setContentText("Are you sure you want to delete this citizen?");

            Optional<ButtonType> response = confirmationAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.OK) {
                if (qytetariService.deleteQytetari(selectedQytetari.getId())) {
                    qytetariList.remove(selectedQytetari);
                    refreshPagination();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Citizen deleted successfully.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failure", "Failed to delete the citizen.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Citizen Selected", "Please select a citizen to delete.");
        }
    }

    @FXML
    private void shfaqAdresenBtn(ActionEvent ae) {
        Qytetari selectedQytetari = qytetariTable.getSelectionModel().getSelectedItem();
        if (selectedQytetari != null) {
            Navigator.navigate(ae, Navigator.ADRESA_DASHBOARD, selectedQytetari.getAdresa());
        } else {
            showAlert(Alert.AlertType.WARNING, "No Citizen Selected", "Please select a citizen to view the address.");
        }
    }

    private void setupEnterKeySubmission() {
        nrPersonalTextField.setOnKeyPressed(this::handleEnterKey);
        emriTextField.setOnKeyPressed(this::handleEnterKey);
        mbiemriTextField.setOnKeyPressed(this::handleEnterKey);
        ditelindja.getEditor().setOnKeyPressed(this::handleEnterKey);
    }

    private void handleEnterKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            handleFilter();
        }
    }
}
