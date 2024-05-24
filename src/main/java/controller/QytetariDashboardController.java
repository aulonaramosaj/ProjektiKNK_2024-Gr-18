package controller;

import App.Navigator;
import Database.DatabaseUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Qytetari;
import repository.QytetariRepository;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class QytetariDashboardController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Qytetari> qytetariTable;
    @FXML
    private TableColumn<Qytetari, Number> qytetariId, qytetariNrPersonal, qytetariAdresa;
    @FXML
    private TableColumn<Qytetari, String> qytetariEmri, qytetariMbiemri, qytetariEmail, qytetariDitelindja, qytetariGjinia, qytetariNrTelefonit;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField nrPersonalTextField, emriTextField, mbiemriTextField;
    @FXML
    private DatePicker ditelindja;


    private ObservableList<Qytetari> qytetariList = FXCollections.observableArrayList();
    private final int rowsPerPage = 10;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();
        bindTableViewToObservableList();
        setupPagination();
        loadData();
    }

    private void initializeTableColumns() {
        qytetariId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));
        qytetariNrPersonal.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getNrPersonal())));
        qytetariEmri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmri()));
        qytetariMbiemri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMbiemri()));
        qytetariEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        qytetariDitelindja.setCellValueFactory(cellData -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return new SimpleStringProperty(sdf.format(cellData.getValue().getDitelindja()));
        });
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
        clearFilters();
        loadData();
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, qytetariList.size());
        qytetariTable.setItems(FXCollections.observableArrayList(qytetariList.subList(fromIndex, toIndex)));
        return new AnchorPane();
    }

    private void loadData() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            List<Qytetari> qytetaret = QytetariRepository.getAllQytetaret(conn);
            qytetariList.setAll(qytetaret);
            refreshPagination();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filterQytetariTable() {
        String nrPersonal = nrPersonalTextField.getText();
        String emri = emriTextField.getText();
        String mbiemri = mbiemriTextField.getText();

        try (Connection conn = DatabaseUtil.getConnection()) {
            List<Qytetari> filteredQytetaret = QytetariRepository.filterQytetaret(conn, nrPersonal, emri, mbiemri);
            qytetariList.setAll(filteredQytetaret);
            refreshPagination();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error filtering the records.");
        }
    }

    private void clearFilters() {
        nrPersonalTextField.clear();
        emriTextField.clear();
        mbiemriTextField.clear();
        ditelindja.setValue(null);
        loadData();
    }

    private void refreshPagination() {
        pagination.setPageCount((int) Math.ceil(qytetariList.size() / (double) rowsPerPage));
        qytetariTable.setItems(FXCollections.observableArrayList(qytetariList.subList(0, Math.min(rowsPerPage, qytetariList.size()))));
    }

    @FXML
    private void shfaqAdresenBtn(ActionEvent ae) {
        Qytetari selectedQytetari = qytetariTable.getSelectionModel().getSelectedItem();
        if (selectedQytetari != null) {
            Navigator.navigate(ae, Navigator.ADRESA_DASHBOARD, selectedQytetari.getAdresa());
        } else {
            showAlert(Alert.AlertType.WARNING, "No Address Found", "Please select a citizen to show the address.");
        }
    }

    @FXML
    private void modifikoQytetarinBtn(ActionEvent ae) {
        Qytetari selectedQytetari = qytetariTable.getSelectionModel().getSelectedItem();
        if (selectedQytetari != null) {
            // Pass the NrPersonal as a parameter to the navigateWithParams method.
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
                if (QytetariRepository.deleteQytetari(selectedQytetari.getId())) {
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        Navigator.changeLanguage();
        Navigator.navigate(ae,Navigator.QYTETARI_DASHBOARD);
    }
}