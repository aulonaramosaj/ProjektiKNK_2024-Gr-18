package repository;

import Database.DatabaseUtil;
import model.Adresa;
import model.dto.AdresaDto;
import model.dto.CreateAdresaDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresaRepository {

    public static int create(CreateAdresaDto adresaData) {
        String query = "INSERT INTO Adresa (Komuna, Fshati, Rruga, NumriNderteses, KodiPostar, LlojiVendbanimit, Created_at, User) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?)";
        int generatedId = -1;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, adresaData.getKomuna());
            pst.setString(2, adresaData.getFshati());
            pst.setString(3, adresaData.getRruga());
            pst.setInt(4, adresaData.getNumriNderteses());
            pst.setInt(5, adresaData.getKodiPostar());
            pst.setString(6, adresaData.getLlojiVendbanimit());
            pst.setInt(7, adresaData.getUserId());
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

            return new Adresa(Id, Komuna, Fshati, Rruga, NumriNderteses, KodiPostar, LlojiVendbanimit);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean modifiko(AdresaDto modifikoAdresen) {
        String query = "UPDATE Adresa SET Komuna = ?, LlojiVendbanimit = ?, Fshati = ?, Rruga = ?, NumriNderteses = ?, KodiPostar = ?, User = ? WHERE Id = ?";

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

    public List<Adresa> getAllAddresses(Connection conn) throws SQLException {
        List<Adresa> adresat = new ArrayList<>();
        String query = "SELECT * FROM Adresa";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Adresa adresa = getFromResultSet(rs);
                if (adresa != null) {
                    adresat.add(adresa);
                }
            }
        }
        return adresat;
    }


    public AdresaDto getAddressById(int addressId) {
        String query = "SELECT * FROM Adresa WHERE Id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, addressId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToAdresaDto(rs);
            }
        } catch (SQLException e) {
            System.out.println("Database error when fetching address by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    private static AdresaDto mapToAdresaDto(ResultSet rs) throws SQLException {
        int id = rs.getInt("Id");
        String komuna = rs.getString("Komuna");
        String fshati = rs.getString("Fshati");
        String rruga = rs.getString("Rruga");
        int numriNderteses = rs.getInt("NumriNderteses");
        int kodiPostar = rs.getInt("KodiPostar");
        String llojiVendbanimit = rs.getString("LlojiVendbanimit");
        int userId = rs.getInt("User");

        return new AdresaDto(id, komuna, fshati, rruga, numriNderteses, kodiPostar, llojiVendbanimit, userId);
    }

    public List<Adresa> getAddressesByUser(Connection conn, int userId) throws SQLException {
        List<Adresa> addresses = new ArrayList<>();
        String query = "SELECT * FROM Adresa WHERE User = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Adresa adresa = getFromResultSet(rs);
                if (adresa != null) {
                    addresses.add(adresa);
                }
            }
        }
        return addresses;
    }
    public List<Adresa> getFilteredAddresses(Connection conn, String filterConditions) throws SQLException {
        List<Adresa> addresses = new ArrayList<>();
        String query = "SELECT * FROM Adresa" + filterConditions;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Adresa adresa = getFromResultSet(rs);
                if (adresa != null) {
                    addresses.add(adresa);
                }
            }
        }
        return addresses;
    }

    public boolean deleteAdresa(Connection conn, int addressId) {
        String query = "DELETE FROM Adresa WHERE Id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, addressId);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}