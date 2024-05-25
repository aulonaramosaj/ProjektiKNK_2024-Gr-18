package Database;

//import com.example.knk2324.java_05.ConnectionUtil;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/KNK";
        String user = "root";
        String password = "Arlinda.Be2004";
        Connection connection = DriverManager.getConnection(
                url, user, password
        );

        if(connection.isValid(1000)){
            System.out.println("Lidhja me bazë të dhënave është krijuar me sukses!");
        }


    }

    public static void lexoPerdoruesin(String id) throws SQLException{
        String sql = "SELECT * FROM users WHERE id = ?";
        System.out.println(sql);
        Connection connection = DatabaseUtil.getConnection();
        //Statement statement = connection.createStatement();
        PreparedStatement statement = connection.prepareStatement(
                sql
        );
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            User user = User.getInstanceFromResultSet(result);
            user.printoDetajet();
        }
    }
}

class User {
    private int Id;
    private String Username;

    private User(int Id, String Username){
        this.Id = Id;
        this.Username = Username;
    }


    public static User getInstanceFromResultSet(ResultSet resultSet){
        try{
            int Id = resultSet.getInt("Id");
            String Username = resultSet.getString("Username");
            return new User(Id, Username);
        }catch (Exception e){
            return null;
        }
    }

    public void printoDetajet(){
        System.out.println("Id: " + this.Id);
        System.out.println("Emri: " + this.Username);
    }
}