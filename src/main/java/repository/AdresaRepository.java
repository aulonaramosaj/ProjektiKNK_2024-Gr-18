package repository;

import model.Adresa;
import model.dto.AdresaDto;
import model.dto.CreateAdresaDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresaRepository {
    public static boolean create(CreateAdresaDto adresaData) {
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO Adresa (Komuna, LlojiVendbanimit, Fshati, Rruga, NumriNderteses, KodiPostar)
                VALUE (?, ?, ?, ?, ?,?)
                """;
        //String query = "INSERT INTO USER VALUE (?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, adresaData.getKomuna());
            pst.setString(2, adresaData.getLlojiVendbanimit());
            pst.setString(3, adresaData.getFshati());
            pst.setString(4, adresaData.getRruga());
            pst.setInt(5, adresaData.getNumriNderteses());
            pst.setInt(6, adresaData.getKodiPostar());
            pst.execute();
            pst.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }

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
        Connection conn = DBConnector.getConnection();
        String query = """
                UPDATE Adresa SET  Komuna = ?, LlojiVendbanimit = ?, Fshati = ?, Rruga = ?,  
                NumriNderteses = ?, KodiPostar = ? WHERE Id = ?
                """;

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, modifikoAdresen.getKomuna());
            pst.setString(2, modifikoAdresen.getLlojiVendbanimit());
            pst.setString(3, modifikoAdresen.getFshati());
            pst.setString(4, modifikoAdresen.getRruga());
            pst.setInt(5, modifikoAdresen.getNumriNderteses());
            pst.setInt(6, modifikoAdresen.getKodiPostar());
            pst.setInt(7, modifikoAdresen.getId());
            pst.execute();
            pst.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}