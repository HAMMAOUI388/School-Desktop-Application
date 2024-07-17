package note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudOperations {

    private Connection connection;

    public CrudOperations(Connection connection) {
        this.connection = connection;
    }

 



    public void createOrUpdateNote(String apogge, String nom, String filiere, String cour, double note) {
        try {
            // Check if the note for the student already exists
            String checkSql = "SELECT * FROM Notes WHERE apogge = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setString(1, apogge);
                if (checkStatement.executeQuery().next()) {
                    // Update the existing note
                    String updateSql = "UPDATE Notes SET note = ? WHERE apogge = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                        updateStatement.setDouble(1, note);
                        updateStatement.setString(2, apogge);
                        updateStatement.executeUpdate();
                    }
                } else {
                    // Create a new note
                    String insertSql = "INSERT INTO Notes (apogge, Nom, filiere, cour, note) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                        insertStatement.setString(1, apogge);
                        insertStatement.setString(2, nom);
                        insertStatement.setString(3, filiere);
                        insertStatement.setString(4, cour);
                        insertStatement.setDouble(5, note);
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    public void deleteNote(String apogge) {
        try {
            // Delete the note for the specified apogge
            String deleteSql = "DELETE FROM Notes WHERE apogge = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                deleteStatement.setString(1, apogge);
                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

