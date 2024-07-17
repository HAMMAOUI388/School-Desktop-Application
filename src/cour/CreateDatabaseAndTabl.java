package cour;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateDatabaseAndTabl {
    private static final String URL = "jdbc:mysql://localhost:3306/estbm";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB_NAME = "estbm";
  
    public static void createTablecour() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            statement.executeUpdate("USE " + DB_NAME);
            String query = " CREATE TABLE IF NOT EXISTS cour_table ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nom VARCHAR(50) NOT NULL,"
                    + "heure VARCHAR(50) NOT NULL,"
                    + "respo VARCHAR(50) NOT NULL,"
                    + "semestre VARCHAR(50) NOT NULL"

                    + ")";
            statement.executeUpdate(query);
            System.out.println("Base de données et table créées avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


