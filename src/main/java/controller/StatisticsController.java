package controller;

import App.Navigator;
import Database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsController {

    @FXML
    private Label numriPerdoruesve;

    @FXML
    private Label numriAdresave;

    @FXML
    private Label numriQytetareve;

    @FXML
    private PieChart gjiniaPieChart;

    @FXML
    private BarChart<String, Number> QytetaretPerAddresaBarChart;

    @FXML
    private CategoryAxis adresaCategoryAxis;

    @FXML
    private NumberAxis qytetaretCountAxis;

    @FXML
    private PieChart llojiVendbanimitPieChart;

    @FXML
    private Label moshaMesatare;

    @FXML
    private Label mbiemriMeShpesh;

    public void initialize() {
        try (Connection connection = DatabaseUtil.getConnection()) {

            numriPerdoruesve.setText(String.valueOf(getNumerimin(connection, "User")));
            numriAdresave.setText(String.valueOf(getNumerimin(connection, "Adresa")));
            numriQytetareve.setText(String.valueOf(getNumerimin(connection, "Qytetari")));

            // Gjinia pie chart
            ObservableList<PieChart.Data> gjiniaData = FXCollections.observableArrayList(
                    new PieChart.Data("Mashkull", getGjiniaNumerimin(connection, "Mashkull")),
                    new PieChart.Data("Femer", getGjiniaNumerimin(connection, "Femer"))
            );
            gjiniaPieChart.setData(gjiniaData);

            // Popullimi me Qytetare per secilen Adrese
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Numri i Qytetarëve nëpër Adresa");
            ResultSet rs = connection.createStatement().executeQuery(
                    "SELECT Adresa.Id, COUNT(Qytetari.Id) as NumeroQytetaret FROM Adresa " +
                            "LEFT JOIN Qytetari ON Adresa.Id = Qytetari.Adresa GROUP BY Adresa.Id"
            );
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString("Id"), rs.getInt("NumeroQytetaret")));
            }
            QytetaretPerAddresaBarChart.getData().add(series);

            //  Lloji vendbanimit  pie chart
            ObservableList<PieChart.Data> llojiVendbanimitData = FXCollections.observableArrayList(
                    new PieChart.Data("Përhershëm", getLlojiVendbanimitCount(connection, "Përhershëm")),
                    new PieChart.Data("Përkohshëm", getLlojiVendbanimitCount(connection, "Përkohshëm"))
            );
            llojiVendbanimitPieChart.setData(llojiVendbanimitData);

            // Vendos moshen mesatare dhe mbiemrin me te shpeshte
            moshaMesatare.setText(String.valueOf(getMoshenMesatare(connection)));
            mbiemriMeShpesh.setText(getEmrinMeTeShpeshte(connection));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNumerimin(Connection connection, String emriTabeles) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM " + emriTabeles);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    private int getGjiniaNumerimin(Connection connection, String gjinia) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM Qytetari WHERE Gjinia = ?");
        ps.setString(1, gjinia);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    private int getLlojiVendbanimitCount(Connection connection, String lloji) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM Adresa WHERE LlojiVendbanimit = ?");
        ps.setString(1, lloji);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    private double getMoshenMesatare(Connection connection) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery(
                "SELECT AVG(YEAR(CURDATE()) - YEAR(Ditelindja)) as MoshaMesatare FROM Qytetari"
        );
        if (rs.next()) {
            return rs.getDouble("MoshaMesatare");
        }
        return 0.0;
    }

    private String getEmrinMeTeShpeshte(Connection connection) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery(
                "SELECT Mbiemri, COUNT(*) as Count FROM Qytetari GROUP BY Mbiemri ORDER BY Count DESC LIMIT 1"
        );
        if (rs.next()) {
            return rs.getString("Mbiemri");
        }
        return "";
    }
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        Navigator.changeLanguage();
        Navigator.navigate(ae,Navigator.STATISTICS);
    }
    @FXML
    public void handleHome(ActionEvent ae){
        Navigator.navigate(ae,Navigator.HOME_PAGE);
    }
}