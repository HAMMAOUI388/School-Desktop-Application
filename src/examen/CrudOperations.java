package examen;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CrudOperations {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ESTBM_Exam_Management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public CrudOperations() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createExam(String course, Date examDate, String location) {
        try {
        	String sql = "UPDATE Exam SET course = ?, examDate = ?, location = ? WHERE examId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, course);
                preparedStatement.setDate(2, (java.sql.Date) examDate);
                preparedStatement.setString(3, location);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateExam(int examId, Date examDate, String location) {
        try {
            String sql = "UPDATE Exam SET examDate = ?, location = ? WHERE examenId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, (java.sql.Date) examDate);
                preparedStatement.setString(2, location);
                preparedStatement.setInt(3, examId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteExam(int examId) {
        try {
            String sql = "DELETE FROM Exam WHERE examenId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, examId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertNote(int int1, String text, double double1) {
    }

}

