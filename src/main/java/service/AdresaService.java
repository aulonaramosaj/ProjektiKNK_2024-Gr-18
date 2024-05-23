package service;

import Database.DatabaseUtil;
import model.Adresa;
import model.dto.AdresaDto;
import model.dto.CreateAdresaDto;
import repository.AdresaRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdresaService {

    private final AdresaRepository adresaRepository;

    public AdresaService() {
        this.adresaRepository = new AdresaRepository();
    }

    public int createAdresa(CreateAdresaDto adresaDto) {
        return adresaRepository.create(adresaDto);
    }

    public boolean updateAdresa(AdresaDto adresaDto) {
        return adresaRepository.modifiko(adresaDto);
    }

    public AdresaDto getAdresaById(int id) {
        return adresaRepository.getAddressById(id);
    }

    public List<Adresa> getAllAdresas(Connection conn) throws SQLException {
        return adresaRepository.getAllAddresses(conn);
    }

    public List<Adresa> getAdresasByUser(Connection conn, int userId) throws SQLException {
        return adresaRepository.getAddressesByUser(conn, userId);
    }

    public boolean deleteAdresa(int id) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            return adresaRepository.deleteAdresa(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Adresa> getFilteredAdresas(Connection conn, String filterConditions) throws SQLException {
        return adresaRepository.getFilteredAddresses(conn, filterConditions);
    }
}