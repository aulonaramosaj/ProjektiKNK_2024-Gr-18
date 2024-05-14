package repository;
import model.Qytetari;
import model.dto.CreateQytetariDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dto.CreateQytetariDto;

public class QytetariRepository {

    public static boolean create(CreateQytetariDto qytetariData){
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO Qytetari (NrPersonal, Emri, Mbiemri, Gjinia, Ditelindja, Adresa, NrTelefonit, Email)
                VALUE (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, qytetariData.getNrPersonal());
            pst.setString(2, qytetariData.getEmri());
            pst.setString(3, qytetariData.getMbiemri());
            pst.setString(4, qytetariData.getGjinia());
            pst.setString(5, qytetariData.getDitelindja());
            pst.setString(6, qytetariData.getAdresa());
            pst.setInt(7, qytetariData.getNrTelefonit());
            pst.setString(8, qytetariData.getEmail());
            pst.execute();
            pst.close();
            conn.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private static Qytetari getFromResultSet(ResultSet result){
        try{
            int NrPersonal=result.getInt("NrPersonal");
            String Emri=result.getString("Emri");
            String Mbiemri=result.getString("Mbiemri");
            String Gjinia=result.getString("Gjinia");
            String Ditelindja=result.getString("Ditelindja");
            String Adresa=result.getString("Adresa");
            String NrTelefonit=result.getString("NrTelefonit");
            String Email=result.getString("Email");
            return new Qytetari(
                    NrPersonal,Emri,Mbiemri,Gjinia,Ditelindja,Adresa,NrTelefonit,Email
            );
        }
        catch (Exception e){
            return null;
        }
    }

}
