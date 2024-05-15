package service;

import model.dto.CreateQytetariDto;
import model.dto.QytetariDto;
import repository.QytetariRepository;

import static service.DBConnector.getConnection;

public class QytetariService {
    public static boolean regjistrohu(QytetariDto qytetariData){
        // Krijimi i një objekti të transferimit të të dhënave për të kaluar në repository
        CreateQytetariDto createQytetariData = new CreateQytetariDto(
                qytetariData.getNrPersonal(),
                qytetariData.getEmri(),
                qytetariData.getMbiemri(),
                qytetariData.getGjinia().getText(), // Retrieve gjinia as string
                qytetariData.getDitelindja(),
                qytetariData.getAdresa(),
                qytetariData.getNrTelefonit(),
                qytetariData.getEmail()
        );

        // Thirr repository për të ruajtur qytetarin në bazën e të dhënave
        return QytetariRepository.create(createQytetariData, getConnection());
    }
}
