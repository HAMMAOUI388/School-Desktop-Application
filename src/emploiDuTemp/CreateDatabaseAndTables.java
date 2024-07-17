package emploiDuTemp;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseAndTables {

    public static void main(String[] args) {
        createDatabaseAndTable();
    }

    public static Connection createDatabaseConnection() {
    
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ESTBM_Exam_Management";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url + dbName, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createDatabaseAndTable() {
        try (Connection connection = createDatabaseConnection();
             Statement stmt = connection.createStatement()) {

         
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS ESTBM_Exam_Management");
            stmt.executeUpdate("USE ESTBM_Exam_Management");

      
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS EmploiDuTemps (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nomfilier VARCHAR(30), " +
                    "semesters VARCHAR(5), " +
                    "emploiDuTemp BLOB)");

            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
