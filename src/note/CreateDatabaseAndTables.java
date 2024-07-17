package note;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabaseAndTables {

    public static void main(String[] args) {
        createDatabaseAndTable();
    }

    protected static void createDatabaseAndTable() {
        // Fill in your database connection details
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ESTBM_Exam_Management";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();

            // Create the database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            stmt.executeUpdate("USE " + dbName);

            // Create the table for storing notes
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Notes (" +
                    "Apogge VARCHAR(8) PRIMARY KEY, " +
                    "Nom varchar(30),"+
                    "filiere VARCHAR(10), " +
                    "cour VARCHAR(10),"+
                    "note DOUBLE)");

            System.out.println("Database and table created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDatabaseAndTable(Connection connection) {
    }


}
