package repository;
import model.Qytetari;
import model.dto.CreateQytetariDto;
import service.DBConnector;


import java.sql.*;

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
            pst.setString(5, qytetariData.getDitelindja);
            pst.setString(6, qytetariData.getAdresa());
            pst.setString(7, qytetariData.getNrTel());
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
                    NrPersonal,Emri,Mbiemri,Gjinia,Ditelindja,Adresa,NrTelefonit,Email);
        }
        catch (Exception e){
            return null;
        }
    }

    public static boolean EkzistonQytetari(String nrPersonal, int adresa, Connection connection) throws SQLException, SQLException {
        String sql = "Select * from qytetari where NrPersonal = ? and Adresa = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nrPersonal);
        statement.setInt(2, adresa);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return true;
        }
        else{
            return false;
        }
    }


    public void Update(Qytetari modifikoQytetarin, Connection connection) throws SQLException{
        String sql = "UPDATE qytetari SET NrPersonal = ?, Emri = ?, Mbiemri = ?, Ditelindja = ?, Email = ?, NrTelefonit = ?, Gjinia = ?, Adresa = ? WHERE Id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, modifikoQytetarin.getNrPersonal());
        statement.setString(2, modifikoQytetarin.Emri);
        statement.setString(3, modifikoQytetarin.getMbiemri());
        statement.setDate(4, Date.valueOf(modifikoQytetarin.getDitelindja()));
        statement.setString(5, modifikoQytetarin.getEmail());
        statement.setString(6, modifikoQytetarin.getNrTel());
        statement.setString(7, modifikoQytetarin.getGjinia());
        statement.setInt(8, modifikoQytetarin.Adresa);
        statement.setInt(9, modifikoQytetarin.Id);
        statement.executeUpdate();
    }
}