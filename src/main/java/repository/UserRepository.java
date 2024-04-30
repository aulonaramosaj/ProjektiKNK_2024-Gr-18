package repository;

import Database.DatabaseUtil;
import model.User;
import model.dto.CreateUserDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    public static boolean create(CreateUserDto userData){
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO USER (Emri, Mbiemri, NrPersonal, Username, Email, Salt, passwordHash)
                VALUE (?, ?, ?, ?, ?,?,?)
                """;
        //String query = "INSERT INTO USER VALUE (?, ?, ?, ?, ?,?,?)";
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getEmri());
            pst.setString(2, userData.getMbiemri());
            pst.setString(3, userData.getNrPersonal());
            pst.setString(4, userData.getUsername());
            pst.setString(5, userData.getEmail());
            pst.setString(6, userData.getSalt());
            pst.setString(7, userData.getPasswordHash());
            pst.execute();
            pst.close();
            conn.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }


    public static User getByEmail(String email){
        String query = "SELECT * FROM USER WHERE email = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    private static User getFromResultSet(ResultSet result){
        try{
            int Id = result.getInt("Id");
            String Emri = result.getString("Emri");
            String Mbiemri = result.getString("Mbiemri");
            String NrPersonal = result.getString("NrPersonal");
            String Username = result.getString("Username");
            String Email = result.getString("Email");
            String Salt = result.getString("Salt");
            String passwordHash = result.getString("passwordHash");
            return new User(
                    Id, Emri, Mbiemri, NrPersonal, Username, Email, Salt, passwordHash
            );
        }catch (Exception e){
            return null;
        }
    }







}