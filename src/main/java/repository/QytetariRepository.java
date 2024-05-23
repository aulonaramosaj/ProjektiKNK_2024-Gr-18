package repository;

import model.Qytetari;
import model.dto.CreateQytetariDto;
import model.dto.QytetariDto;
import service.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QytetariRepository {

    public static boolean existsByNrPersonal(String nrPersonal) {
        String query = "SELECT COUNT(*) AS count FROM Qytetari WHERE NrPersonal = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nrPersonal);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
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
        String query = """
                INSERT INTO Qytetari (NrPersonal, Emri, Mbiemri, Gjinia, Ditelindja, Adresa, NrTelefonit, Email, User)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, qytetariData.getNrPersonal());
            pst.setString(2, qytetariData.getEmri());
            pst.setString(3, qytetariData.getMbiemri());
            pst.setString(4, qytetariData.getGjinia());
            pst.setDate(5, new java.sql.Date(qytetariData.getDitelindja().getTime()));
            pst.setInt(6, qytetariData.getAdresa());
            pst.setString(7, qytetariData.getNrTel());
            pst.setString(8, qytetariData.getEmail());
            pst.setInt(9, qytetariData.getUserId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modify(QytetariDto qytetari) {
        String query = """
                UPDATE Qytetari SET Emri = ?, Mbiemri = ?, Gjinia = ?, Ditelindja = ?, Adresa = ?, NrTelefonit = ?, Email = ?, User = ?
                WHERE NrPersonal = ?
                """;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, qytetari.getEmri());
            pst.setString(2, qytetari.getMbiemri());
            pst.setString(3, qytetari.getGjinia());
            pst.setDate(4, new java.sql.Date(qytetari.getDitelindja().getTime()));
            pst.setInt(5, qytetari.getAdresa());
            pst.setString(6, qytetari.getNrTelefonit());
            pst.setString(7, qytetari.getEmail());
            pst.setInt(8, qytetari.getUserId());
            pst.setString(9, qytetari.getNrPersonal());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Qytetari> getAllQytetaret(Connection conn) {
        List<Qytetari> qytetaret = new ArrayList<>();
        String query = "SELECT * FROM Qytetari";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Qytetari qytetari = getFromResultSet(rs);
                if (qytetari != null) {
                    qytetaret.add(qytetari);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qytetaret;
    }

    private static Qytetari getFromResultSet(ResultSet rs) {
        try {
            return new Qytetari(
                    rs.getInt("Id"),
                    rs.getInt("Adresa"),
                    rs.getString("NrPersonal"),
                    rs.getString("Emri"),
                    rs.getString("Mbiemri"),
                    rs.getDate("Ditelindja"),
                    rs.getString("Email"),
                    rs.getString("NrTelefonit"),
                    rs.getString("Gjinia"),
                    rs.getInt("User")
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
    public static List<Qytetari> filterQytetaret(Connection conn, String nrPersonal, String emri, String mbiemri) throws SQLException {
        List<Qytetari> qytetaret = new ArrayList<>();
        String query = "SELECT * FROM Qytetari WHERE NrPersonal LIKE ? AND Emri LIKE ? AND Mbiemri LIKE ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, "%" + nrPersonal + "%");
            pst.setString(2, "%" + emri + "%");
            pst.setString(3, "%" + mbiemri + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                qytetaret.add(getFromResultSet(rs));
            }
        }
        return qytetaret;
    }

    public static boolean deleteQytetari(int id) {
        String query = "DELETE FROM Qytetari WHERE Id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}