package examen;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseAndTables {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();

            // Create the database
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS ESTBM_Exam_Management");
            statement.executeUpdate("USE ESTBM_Exam_Management");

            // Create the Exam table
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Exam (" +
                            "examenId INT PRIMARY KEY AUTO_INCREMENT," +
                            "course VARCHAR(50)," +
                            "examDate DATE," +
                            "location VARCHAR(100)" +
                            ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
