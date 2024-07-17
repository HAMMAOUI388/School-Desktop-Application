package emploiDuTemp;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDataIntoEmploiDuTemps {

    public static void main(String[] args) {
        // Fill in your database connection details
        String url = "jdbc:mysql://localhost:3306/ESTBM_Exam_Management";
        String userName = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            insertData(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        String insertSql = "INSERT INTO EmploiDuTemps (nomfilier, semesters, emploiDuTemp) VALUES (?, ?, ?)";

        // Filiers and semesters
        String[] filiers = {"AA", "AI", "MLT", "GO", "GP", "GEEE", "ARI", "GI"};
        String[] semesters = {"S1", "S2", "S3", "S4"};

        // Dummy PDF content (replace this with your actual PDF data)
        byte[] dummyPdfData = "Dummy PDF Data".getBytes();

        // Insert data for each combination of filier and semester
        for (String filier : filiers) {
            for (String semester : semesters) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                    preparedStatement.setString(1, filier);
                    preparedStatement.setString(2, semester);
                    preparedStatement.setBytes(3, dummyPdfData);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Data inserted successfully.");
    }
}
