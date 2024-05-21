package repository;

import Database.DatabaseUtil;
import model.Adresa;
import model.dto.AdresaDto;
import model.dto.CreateAdresaDto;
import service.DBConnector;

import java.sql.*;

public class AdresaRepository {

    public static int create(CreateAdresaDto adresaData) {
        String query = "INSERT INTO Adresa (Komuna, Fshati, Rruga, NumriNderteses, KodiPostar, LlojiVendbanimit,Created_at, User) VALUES (?, ?, ?, ?, ?, ?,CURRENT_TIMESTAMP, ?)";
        int generatedId = -1;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, adresaData.getKomuna());
            pst.setString(2, adresaData.getFshati());
            pst.setString(3, adresaData.getRruga());
            pst.setInt(4, adresaData.getNumriNderteses());
            pst.setInt(5, adresaData.getKodiPostar());
            pst.setString(6, adresaData.getLlojiVendbanimit());
            pst.setInt(7,adresaData.getUserId());
            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }


    private static Adresa getFromResultSet(ResultSet result) {
        try {
            int Id = result.getInt("Id");
            String Komuna = result.getString("Komuna");
            String Fshati = result.getString("Fshati");
            String Rruga = result.getString("Rruga");
            int NumriNderteses = result.getInt("NumriNderteses");
            int KodiPostar = result.getInt("KodiPostar");
            String LlojiVendbanimit = result.getString("LlojiVendbanimit");

            return new Adresa(
                    Id, Komuna, Fshati, Rruga, NumriNderteses, KodiPostar, LlojiVendbanimit
            );
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean modifiko(AdresaDto modifikoAdresen) {
        String query = """
                UPDATE Adresa SET Komuna = ?, LlojiVendbanimit = ?, Fshati = ?, Rruga = ?, NumriNderteses = ?, KodiPostar = ?, User = ? WHERE Id = ?
                """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, modifikoAdresen.getKomuna());
            pst.setString(2, modifikoAdresen.getLlojiVendbanimit());
            pst.setString(3, modifikoAdresen.getFshati());
            pst.setString(4, modifikoAdresen.getRruga());
            pst.setInt(5, modifikoAdresen.getNumriNderteses());
            pst.setInt(6, modifikoAdresen.getKodiPostar());
            pst.setInt(7, modifikoAdresen.getUserId());
            pst.setInt(8, modifikoAdresen.getId());
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}