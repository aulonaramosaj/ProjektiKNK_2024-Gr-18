package service;

import model.Qytetari;
import model.dto.CreateQytetariDto;
import model.dto.QytetariDto;
import model.filter.QytetariFilter;
import repository.QytetariRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QytetariService {

    public boolean existsByNrPersonal(String nrPersonal) {
        return QytetariRepository.existsByNrPersonal(nrPersonal);
    }

    public boolean createQytetari(CreateQytetariDto qytetariDto) {
        return QytetariRepository.create(qytetariDto);
    }

    public boolean updateQytetari(QytetariDto qytetariDto) {
        return QytetariRepository.modifiko(qytetariDto);
    }

    public boolean deleteQytetari(int id) {
        return QytetariRepository.deleteQytetari(id);
    }

    public QytetariDto getQytetariByNrPersonal(String nrPersonal) {
        return QytetariRepository.findByNrPersonal(nrPersonal);
    }

    public List<Qytetari> getAllQytetaret() throws SQLException {
        try (Connection conn = Database.DatabaseUtil.getConnection()) {
            return QytetariRepository.getAllQytetaret(conn);
        }
    }

    public List<Qytetari> filterQytetaret(QytetariFilter filter) throws SQLException {
        try (Connection conn = Database.DatabaseUtil.getConnection()) {
            return QytetariRepository.filterQytetaret(conn, filter);
        }
    }

    public List<Qytetari> getQytetaretByAdresaId(int adresaId) throws SQLException {
        try (Connection conn = Database.DatabaseUtil.getConnection()) {
            return QytetariRepository.getQytetariByAdresaId(conn, adresaId);
        }
    }
}