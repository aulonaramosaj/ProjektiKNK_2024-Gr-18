package repository;

import model.Qytetari;
import model.dto.CreateQytetariDto;
import model.dto.QytetariDto;
import service.DBConnector;

import java.sql.*;

public class QytetariRepository {

    // Metoda me shiku nese ekziston nje Qytetar me Numer Personal te njejte i regjistruar
    public static boolean existsByNrPersonal(String nrPersonal) {
        Connection conn = DBConnector.getConnection();
        String query = "SELECT COUNT(*) FROM Qytetari WHERE NrPersonal = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nrPersonal);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // kthen true nese counter eshte me i madh se 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean create(CreateQytetariDto qytetariData) {
        if (existsByNrPersonal(qytetariData.getNrPersonal())) {
            return false;
        }

        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO Qytetari (NrPersonal, Emri, Mbiemri, Gjinia, Ditelindja, Adresa, NrTelefonit, Email, User)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, qytetariData.getNrPersonal());
            pst.setString(2, qytetariData.getEmri());
            pst.setString(3, qytetariData.getMbiemri());
            pst.setString(4, qytetariData.getGjinia());
            pst.setDate(5, qytetariData.getDitelindja());
            pst.setInt(6, qytetariData.getAdresa());
            pst.setString(7, qytetariData.getNrTel());
            pst.setString(8, qytetariData.getEmail());
            pst.setInt(9, qytetariData.getUserId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static Qytetari getFromResultSet(ResultSet result) {
        try {
            return new Qytetari(
                    result.getInt("Id"),
                    result.getInt("Adresa"),
                    result.getString("NrPersonal"),
                    result.getString("Emri"),
                    result.getString("Mbiemri"),
                    result.getDate("Ditelindja"),
                    result.getString("Email"),
                    result.getString("NrTelefonit"),
                    result.getString("Gjinia"),
                    result.getInt("User")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean modifiko(QytetariDto qytetari) {
        Connection conn = DBConnector.getConnection();
        String query = """
                UPDATE Qytetari SET Emri = ?, Mbiemri = ?, Gjinia = ?, Ditelindja = ?, Adresa = ?, NrTelefonit = ?, Email = ?, User = ?
                WHERE NrPersonal = ?
                """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, qytetari.getEmri());
            statement.setString(2, qytetari.getMbiemri());
            statement.setString(3, qytetari.getGjinia());
            statement.setDate(4, qytetari.getDitelindja());
            statement.setInt(5, qytetari.getAdresa());
            statement.setString(6, qytetari.getNrTelefonit());
            statement.setString(7, qytetari.getEmail());
            statement.setInt(8, qytetari.getUserId());
            statement.setString(9, qytetari.getNrPersonal());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}